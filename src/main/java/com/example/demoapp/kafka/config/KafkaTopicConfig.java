package com.example.demoapp.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;


@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic userActivity(){

        // costruisce il topic "user_activity_2" con 4 partizioni
        return TopicBuilder.name("user_activity_2").partitions(4).build();

    }

}
