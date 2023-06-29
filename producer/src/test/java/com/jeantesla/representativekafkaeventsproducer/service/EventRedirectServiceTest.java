package com.jeantesla.representativekafkaeventsproducer.service;

import com.jeantesla.representativekafkaeventsproducer.data.ClientData;
import com.jeantesla.representativekafkaeventsproducer.kafka.KafkaProducer;
import com.jeantesla.representativekafkaeventsproducer.model.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EventRedirectServiceTest {

    @Mock
    KafkaProducer kafkaProducer;

    @Mock
    ClientData clientData;

    @InjectMocks
    EventRedirectService eventRedirectService;

    Event event;

    @BeforeEach
    public void setUp(){
        event = Event.builder()
                .hardwareId(1L)
                .build();
    }

    @Test
    public void testSendMessageOk(){

        when(clientData.getClient(anyLong())).thenReturn(1);

        eventRedirectService.execute(event);

        verify(kafkaProducer, times(1))
                .sendMessage(1, event);
    }

    @Test
    public void testSendMessageClientNotFound(){

        when(clientData.getClient(anyLong())).thenReturn(0);

        eventRedirectService.execute(event);

        verify(kafkaProducer, times(0))
                .sendMessage(1, event);
    }
}
