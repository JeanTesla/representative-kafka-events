package com.jeantesla.representativekafkaeventsproducer.service;

import com.jeantesla.representativekafkaeventsproducer.data.ClientData;
import com.jeantesla.representativekafkaeventsproducer.kafka.KafkaProducer;
import com.jeantesla.representativekafkaeventsproducer.model.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EventRedirectService {
    private final KafkaProducer kafkaProducer;

    private final ClientData clientData;

    public EventRedirectService(KafkaProducer kafkaProducer, ClientData clientData){
        this.kafkaProducer = kafkaProducer;
        this.clientData = clientData;
    }

    private final Logger LOGGER = LoggerFactory.getLogger(EventRedirectService.class);

    public void execute(Event event){
        long hardwareId = event.getHardwareId();

        int clientId = clientData.getClient(hardwareId);

        if(clientId == 0){
            LOGGER.info("No customers found for this event");
            return;
        }

        kafkaProducer.sendMessage(clientId, event);
    }

}
