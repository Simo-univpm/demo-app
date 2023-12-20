package com.example.demoapp.kafka.service;

import com.example.demoapp.kafka.entity.Message;
import com.example.demoapp.model.entities.UserActivityEntity;
import com.example.demoapp.repository.UserActivityRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

/**
 * listener in ascolto sulle partizioni 0,1 e 2 del topic "user_activity_2"
 * in 0 vengono pubblicate le registrazioni degli utenti
 * in 1 vengono pubblicati i login degli utenti
 * in 2 vengono pubblicati gli edit degli utenti
 * in 3 vengono pubblicate le delete degli utenti
 */

/**
 * JSON --> MESSAGE --> USER ACTIVITY ENTITY--> DB
 *
 * il messaggio viene letto dal topic kafka sotto forma di json
 * il json viene convertito in un oggetto di tipo Message, questa classe
 * funge un po' da interfaccia come le classi nel package "request".
 * Successivamente questo oggetto viene mappato in un oggetto UserActivity (ORM)
 * per essere scritto nel database.
 *
 * Quindi il Listener è un servizio che legge i json nel topic e li scrive a db.
 *
 * (Alla fine è un giro analogo a quello che succede con le richieste HTTP, ovvero:
 * JSON --> REQUEST --> ENTITY --> DB)
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class ListenerService {

    private final ObjectMapper objectMapper;
    private final UserActivityRepository userActivityRepository;

    @KafkaListener(topicPartitions = @TopicPartition(topic = "user_activity_2", partitions = {"0", "1", "2", "3"}), groupId = "groupId_1")
    public void listener(@Header(KafkaHeaders.RECEIVED_PARTITION) String partition, @Header(KafkaHeaders.RECEIVED_KEY) String key, @Payload String jsonMessage){

        System.out.println("partition: " + partition + " | key: " + key + " | message: " + jsonMessage);

        try {

            // mappa il json di kafka nell'oggetto di tipo Messaggio
            Message message = objectMapper.readValue(jsonMessage, Message.class);

            // mappa il messaggio nella userActivity da scrivere a db
            UserActivityEntity userActivity = mapUserActivity(message);

            // scrive a db
            userActivityRepository.saveAndFlush(userActivity);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    private UserActivityEntity mapUserActivity(Message message){

        UserActivityEntity userActivity = new UserActivityEntity();

        userActivity.setActivity_partition(message.getActivity_partition());
        userActivity.setActivity_key(message.getActivity_key());
        userActivity.setUsername(message.getUsername());
        userActivity.setActivity_date(message.getActivity_date());

        return userActivity;

    }

}
