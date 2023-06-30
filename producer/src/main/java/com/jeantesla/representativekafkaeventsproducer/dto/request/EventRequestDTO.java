package com.jeantesla.representativekafkaeventsproducer.dto.request;


import com.jeantesla.representativekafkaeventsproducer.model.Event;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventRequestDTO {
    private Long hardwareId;
    private String componentId;
    private String slaveAddress;
    private Integer functionCode;
    private Boolean data;
    private String crc;

    public Event toEntity(){
        return Event.builder()
                .hardwareId(this.hardwareId)
                .componentId(this.componentId)
                .slaveAddress(this.slaveAddress)
                .functionCode(this.functionCode)
                .data(this.data)
                .crc(this.crc)
                .build();
    }
}
