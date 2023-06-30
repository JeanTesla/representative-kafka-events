package com.jeantesla.representativekafkaeventsproducer.exception.advice;


import lombok.*;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class GenericErrorResponse {
    private HttpStatus status;
    private String message;
}
