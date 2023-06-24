package com.jeantesla.representativekafkaeventsproducer.service;

import com.jeantesla.representativekafkaeventsproducer.kafka.KafkaProducer;
import com.jeantesla.representativekafkaeventsproducer.model.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class EventRedirectService {

    @Autowired
    private KafkaProducer kafkaProducer;

    private final Logger LOGGER = LoggerFactory.getLogger(EventRedirectService.class);

    public void execute(Event event){
        Long hardwareId = event.getHardwareId();

        String clientId = getClient(hardwareId);

        if(clientId.equals("0")){
            LOGGER.info("No customers found for this event");
            return;
        }

        kafkaProducer.sendMessage(getClient(hardwareId), event);
    }

    private String getClient(Long hardwareId){
        int clientId = 0;

        if(Arrays.asList(1L, 2L, 3L).contains(hardwareId)) clientId = 1;
        else if(Arrays.asList(4L,5L,6L).contains(hardwareId)) clientId = 2;

        return Integer.toString(clientId);
    }
}
