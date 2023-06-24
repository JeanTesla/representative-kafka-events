package com.jeantesla.representativekafkaeventsconsumer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    private Long hardwareId;
    private String componentId;
    private String slaveAddress;
    private Integer functionCode;
    private Boolean data;
    private String crc;


    @Override
    public String toString() {
        return "Event{" +
                "hardwareId=" + hardwareId +
                ", componentId='" + componentId + '\'' +
                ", slaveAddress='" + slaveAddress + '\'' +
                ", functionCode=" + functionCode +
                ", data=" + data +
                ", crc='" + crc + '\'' +
                '}';
    }
}