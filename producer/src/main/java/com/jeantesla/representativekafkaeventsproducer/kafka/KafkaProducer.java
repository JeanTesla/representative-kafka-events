package com.jeantesla.representativekafkaeventsproducer.kafka;

import com.jeantesla.representativekafkaeventsproducer.model.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    private KafkaTemplate<String, Event> kafkaTemplate;

    public void sendMessage(int messageKey, Event data){
        Message<Event> message = MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, "hardware-events")
                .setHeader(KafkaHeaders.MESSAGE_KEY, Integer.toString(messageKey))
                                .build();

        kafkaTemplate.send(message);
        LOGGER.info(String.format("Message sent -> %s", data));
    }
}
