package com.cloudtechies.respgenerator.listener;

import com.cloudtechies.respgenerator.config.RespGeneratorProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PayloadKafkaListener {
    /**
     * PayloadKafkaListener class to handle kafka listeners.
     */
    @Autowired
    RespGeneratorProperties respGeneratorProperties;


    @KafkaListener(topics = "#{respGeneratorProperties.kakfaPersistPayloadTopic}", groupId = "#{respGeneratorProperties.kafkaConsumerGroupName}", concurrency = "1")
    public void handleKafkaMessage(@Payload String messages) {
        log.info("Received payload message {}", messages);

    }
}
