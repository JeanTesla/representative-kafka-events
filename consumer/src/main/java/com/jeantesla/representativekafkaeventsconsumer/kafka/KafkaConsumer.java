package com.jeantesla.representativekafkaeventsconsumer.kafka;

import com.jeantesla.representativekafkaeventsconsumer.model.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "hardware-events", groupId = "myGroup")
    public void consume(@Payload Event event){
        LOGGER.info(event.toString());
    }

}
