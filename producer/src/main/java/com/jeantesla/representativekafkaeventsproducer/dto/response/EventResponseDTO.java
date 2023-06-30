package com.jeantesla.representativekafkaeventsproducer.dto.response;

import com.jeantesla.representativekafkaeventsproducer.model.Event;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EventResponseDTO {
    private Long hardwareId;
    private String componentId;
    private String slaveAddress;
    private Integer functionCode;
    private Boolean data;
    private String crc;

    public static EventResponseDTO fromEntity(Event event){
        return EventResponseDTO.builder()
                .hardwareId(event.getHardwareId())
                .componentId(event.getComponentId())
                .slaveAddress(event.getSlaveAddress())
                .functionCode(event.getFunctionCode())
                .data(event.getData())
                .crc(event.getCrc())
                .build();
    }
}
