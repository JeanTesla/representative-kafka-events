package com.jeantesla.representativekafkaeventsproducer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic hardwareEventsTopic(){
        return TopicBuilder
                .name("hardware-events")
                .build();
    }
}