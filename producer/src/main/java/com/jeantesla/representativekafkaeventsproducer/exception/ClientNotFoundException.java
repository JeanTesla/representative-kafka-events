package com.jeantesla.representativekafkaeventsproducer.exception;

import com.jeantesla.representativekafkaeventsproducer.exception.advice.DomainException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClientNotFoundException extends DomainException {

    public ClientNotFoundException(){
        super("Client not found");
    }
}
