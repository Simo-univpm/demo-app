package com.example.demoapp.kafka.config;

import com.example.demoapp.kafka.entity.Message;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootsrapServers;

    // setta le impostazioni dell'oggetto usando una hashmap
    public Map<String, Object> producerConfig(){

        Map<String, Object> props = new HashMap<>();

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootsrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class); // x inviare oggetti UserActivityRequest

        return props;

    }


    @Bean
    public ProducerFactory<String, Message> userActivityRequestProducerFactory(){

        return new DefaultKafkaProducerFactory<>(producerConfig());

    }


    @Bean
    public KafkaTemplate<String, Message> userActivityRequestKafkaTemplate(){

        return new KafkaTemplate<>(userActivityRequestProducerFactory());

    }

}
