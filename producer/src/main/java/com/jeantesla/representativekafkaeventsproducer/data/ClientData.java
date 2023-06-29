package com.jeantesla.representativekafkaeventsproducer.data;

import java.util.Arrays;

public class ClientData {

    public int getClient(long hardwareId){
        int clientId = 0;

        if(Arrays.asList(1L, 2L, 3L).contains(hardwareId)) clientId = 1;
        else if(Arrays.asList(4L,5L,6L).contains(hardwareId)) clientId = 2;

        return clientId;
    }
}
