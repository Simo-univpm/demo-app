package com.example.demoapp.kafka.service;

import com.example.demoapp.kafka.entity.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;


@Service
@RequiredArgsConstructor
public class TopicUpdaterService {

    /**
     * classe che scrive sulle varie partizioni del topic user_activity_2
     */

    private final KafkaTemplate<String, Message> kafkaTemplate;

    public void updatePartition(int partition, String key, String username){

        // campi del messaggio sul topic user_activity_2
        Message message = new Message(

          String.valueOf(partition),
          key,
          username,
          getCurrentTimestamp()

        );

        // scrive su topic
        kafkaTemplate.send("user_activity_2", partition, key, message);

    }


    private Timestamp getCurrentTimestamp(){

        long currentTimeMillis = System.currentTimeMillis();                // ottiene il timestamp corrente in millisecondi
        java.util.Date currentDate = new java.util.Date(currentTimeMillis); // crea un oggetto Date con il timestamp corrente
        return new Timestamp(currentDate.getTime());                        // crea un oggetto Timestamp a partire dall'oggetto Date

    }

}
