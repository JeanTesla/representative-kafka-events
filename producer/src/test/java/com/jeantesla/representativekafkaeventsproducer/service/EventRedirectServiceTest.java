package com.jeantesla.representativekafkaeventsproducer.service;

import com.jeantesla.representativekafkaeventsproducer.dto.response.EventResponseDTO;
import com.jeantesla.representativekafkaeventsproducer.exception.ClientNotFoundException;
import com.jeantesla.representativekafkaeventsproducer.model.Event;
import com.jeantesla.representativekafkaeventsproducer.repository.ClientData;
import com.jeantesla.representativekafkaeventsproducer.dto.request.EventRequestDTO;
import com.jeantesla.representativekafkaeventsproducer.kafka.KafkaProducer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EventRedirectServiceTest {

    @Mock
    KafkaProducer kafkaProducer;

    @Mock
    ClientData clientData;

    @InjectMocks
    EventRedirectService eventRedirectService;

    EventRequestDTO eventRequestDTO;

    @BeforeEach
    public void setUp(){
        eventRequestDTO = EventRequestDTO.builder()
                .hardwareId(1L)
                .build();
    }

    @Test
    public void testSendMessageOk() {

        when(clientData.getClient(anyLong())).thenReturn(1);

        Object result = eventRedirectService.execute(eventRequestDTO);

        verify(kafkaProducer, times(1))
                .sendMessage(anyInt(), any(Event.class));

        Assert.notNull(result);
        Assert.isInstanceOf(EventResponseDTO.class, result);
    }

    @Test
    public void testSendMessageClientNotFound(){

        when(clientData.getClient(anyLong())).thenReturn(0);

        Assertions.assertThrows(ClientNotFoundException.class,
                () -> eventRedirectService.execute(eventRequestDTO));

        verify(kafkaProducer, times(0))
                .sendMessage(1, eventRequestDTO.toEntity());

    }
}
