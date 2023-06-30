package com.jeantesla.representativekafkaeventsproducer.service;

import com.jeantesla.representativekafkaeventsproducer.dto.request.EventRequestDTO;
import com.jeantesla.representativekafkaeventsproducer.dto.response.EventResponseDTO;
import com.jeantesla.representativekafkaeventsproducer.exception.ClientNotFoundException;
import com.jeantesla.representativekafkaeventsproducer.kafka.KafkaProducer;
import com.jeantesla.representativekafkaeventsproducer.model.Event;
import com.jeantesla.representativekafkaeventsproducer.repository.ClientData;
import org.springframework.stereotype.Service;

@Service
public class EventRedirectService {
    private final KafkaProducer kafkaProducer;

    private final ClientData clientData;

    public EventRedirectService(KafkaProducer kafkaProducer, ClientData clientData){
        this.kafkaProducer = kafkaProducer;
        this.clientData = clientData;
    }

    public EventResponseDTO execute(EventRequestDTO eventRequestDTO){

        Event event = eventRequestDTO.toEntity();
        int clientId = clientData.getClient(event.getHardwareId());

        if(clientId == 0) throw new ClientNotFoundException();

        kafkaProducer.sendMessage(clientId, event);

        return EventResponseDTO.fromEntity(event);
    }

}
