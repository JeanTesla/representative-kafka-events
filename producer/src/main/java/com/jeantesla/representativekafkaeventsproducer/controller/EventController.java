package com.jeantesla.representativekafkaeventsproducer.controller;

import com.jeantesla.representativekafkaeventsproducer.model.Event;
import com.jeantesla.representativekafkaeventsproducer.service.EventRedirectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hardware-event")
public class EventController {

    @Autowired
    private EventRedirectService eventRedirectService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void saveEvent(@RequestBody Event event){
        eventRedirectService.execute(event);
    }

}
