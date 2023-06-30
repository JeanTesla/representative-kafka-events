package com.jeantesla.representativekafkaeventsproducer.model;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
public class  Event {
    private Long hardwareId;
    private String componentId;
    private String slaveAddress;
    private Integer functionCode;
    private Boolean data;
    private String crc;
}
