package com.jeantesla.representativekafkaeventsproducer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeantesla.representativekafkaeventsproducer.dto.request.EventRequestDTO;
import com.jeantesla.representativekafkaeventsproducer.dto.response.EventResponseDTO;
import com.jeantesla.representativekafkaeventsproducer.exception.ClientNotFoundException;
import com.jeantesla.representativekafkaeventsproducer.service.EventRedirectService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class EventControllerTest {

    @Mock
    EventRedirectService eventRedirectService;

    @InjectMocks
    EventController eventController;

    MockMvc mockMvc;

    EventRequestDTO eventRequestDTO;

    ObjectMapper objectMapper = new ObjectMapper();

    String uri = "/api/hardware-event";

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(eventController).build();
        eventRequestDTO = EventRequestDTO.builder()
                .hardwareId(1L)
                .build();
    }

    @Test
    public void testSaveEventOk() throws Exception {

        String eventInString = objectMapper.writeValueAsString(eventRequestDTO);

        EventResponseDTO eventResponseDTO = EventResponseDTO.builder()
                .hardwareId(1L)
                .build();

        when(eventRedirectService.execute(any())).thenReturn(eventResponseDTO);

        MvcResult result = mockMvc.perform(
                post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(eventInString)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.getResponse().getContentAsString().equals(eventInString));
        verify(eventRedirectService, times(1)).execute(any());

    }

    @Test
    public void testSaveEventClientNotFound() throws Exception {

        String eventInString = objectMapper.writeValueAsString(eventRequestDTO);

        when(eventRedirectService.execute(any())).thenThrow(ClientNotFoundException.class);

        MvcResult result =  mockMvc.perform(
                        post(uri)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(eventInString)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();

        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.getResponse().getContentAsString().equals(eventInString));
        verify(eventRedirectService, times(1)).execute(any());
    }

}