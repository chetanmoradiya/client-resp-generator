package com.cloudtechies.respgenerator.listener;

import com.cloudtechies.respgenerator.config.RespGeneratorProperties;
import com.cloudtechies.respgenerator.entity.TransactionReport;
import com.cloudtechies.respgenerator.enums.PayloadState;
import com.cloudtechies.respgenerator.exception.UnrecoverableException;
import com.cloudtechies.respgenerator.generator.ResponseFileGenerator;
import com.cloudtechies.respgenerator.model.FilePayloadMessage;
import com.cloudtechies.respgenerator.model.PayloadResponse;
import com.cloudtechies.respgenerator.repository.PayloadRepository;
import com.cloudtechies.respgenerator.repository.TransactionReportRepository;
import com.cloudtechies.respgenerator.writer.ResponseWriter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PayloadKafkaListener {
    /**
     * PayloadKafkaListener class to handle kafka listeners.
     */
    @Autowired
    RespGeneratorProperties respGeneratorProperties;

    @Autowired
    ResponseFileGenerator responseFileGenerator;

    @Autowired
    TransactionReportRepository transactionReportRepository;

    @Autowired
    PayloadRepository payloadRepository;

    @Autowired
    ResponseWriter responseWriter;


    @KafkaListener(topics = "#{respGeneratorProperties.kakfaPersistPayloadTopic}", groupId = "#{respGeneratorProperties.kafkaConsumerGroupName}", concurrency = "1")
    public void handleKafkaMessage(@Payload String messages) {
        log.info("Received payload message {}", messages);

        List<PayloadResponse> payloadResponses = new ArrayList<>();
        FilePayloadMessage filePayloadMessage;
        try {
            filePayloadMessage = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).readValue(messages, FilePayloadMessage.class);
        } catch (JsonProcessingException jsonProcessingException) {
            throw new UnrecoverableException("Json couldn't be processed");
        }

        Optional<List<TransactionReport>> transactionReportList = transactionReportRepository.findByPayloadID(filePayloadMessage.getPayloadId());

        if (transactionReportList.isPresent()) {
            log.info("Fetched transactionData, Trying to write response");
            payloadResponses = responseWriter.generateResponse(transactionReportList.get());
        }
        log.info("Going to generate csv response file");
        if (responseFileGenerator.generateResponseFile(filePayloadMessage, payloadResponses)) {
            payloadRepository.updatePayloadStatus(filePayloadMessage.getPayloadId(), PayloadState.COMPLETED.name());
        }
    }
}
