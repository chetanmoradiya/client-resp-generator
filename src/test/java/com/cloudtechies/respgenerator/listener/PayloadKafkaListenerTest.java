package com.cloudtechies.respgenerator.listener;

import com.cloudtechies.respgenerator.model.FilePayloadMessage;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

class PayloadKafkaListenerTest {

    @Test
    void test1() throws Exception{
        String input ="{\n" +
                "\t\"payloadId\": \"534cd50d-e1a1-4f68-bff9-1914489be93f\",\n" +
                "\t\"fileName\": \"TradeData.csv\",\n" +
                "\t\"ftpFolder\": \"client1\",\n" +
                "\t\"absolutePath\": \"SomePath\",\n" +
                "\t\"createTs\": 1687445809932,\n" +
                "\t\"updateTs\": 1687446643203,\n" +
                "\t\"lastModifiedTs\": 1687443245032,\n" +
                "\t\"instructionCount\": 4,\n" +
                "\t\"respFileName\": \"RESP_TradeData_20230622_204043.csv\",\n" +
                "\t\"respFilePath\": null,\n" +
                "\t\"payloadState\": \"RESP_PENDING\",\n" +
                "\t\"rejectionReason\": null\n" +
                "}";

        FilePayloadMessage filePayloadMessage = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).readValue(input, FilePayloadMessage.class);
    }

}