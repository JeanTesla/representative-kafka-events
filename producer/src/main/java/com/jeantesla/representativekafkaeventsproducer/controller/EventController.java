package com.jeantesla.representativekafkaeventsproducer.controller;

import com.jeantesla.representativekafkaeventsproducer.model.Event;
import com.jeantesla.representativekafkaeventsproducer.service.EventRedirectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hardware-event")
public class EventController {

    private final EventRedirectService eventRedirectService;

    public  EventController(EventRedirectService eventRedirectService){
        this.eventRedirectService = eventRedirectService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void saveEvent(@RequestBody Event event){
        eventRedirectService.execute(event);
    }

}
