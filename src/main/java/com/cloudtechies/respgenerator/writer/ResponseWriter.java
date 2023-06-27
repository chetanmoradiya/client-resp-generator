package com.cloudtechies.respgenerator.writer;

import com.cloudtechies.respgenerator.config.RespGeneratorProperties;
import com.cloudtechies.respgenerator.entity.TransactionReport;
import com.cloudtechies.respgenerator.model.PayloadResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Component
public class ResponseWriter {

    public final String COMMA = ",";

    @Autowired
    RespGeneratorProperties respGeneratorProperties;

    public List<PayloadResponse> generateResponse(List<TransactionReport> transactionReportList) {
        log.info("Writing Response from transactions");
        List<PayloadResponse> payloadResponsesList = new ArrayList<>();
        for (TransactionReport report : transactionReportList) {
            PayloadResponse response = PayloadResponse.builder().trnId(report.getTrnId()).txnStatus(report.getTxnStatus()).build();
            payloadResponsesList.add(response);
        }
        log.info("Payload Response List generated Successfully");
        return payloadResponsesList;

    }

    public List<String> generateOutputLines(List<PayloadResponse> responseList) {
        List<String> responses = new ArrayList<>();
        responses.add(respGeneratorProperties.getRespCSVHeader());
        int rowNum = 1;
        for (PayloadResponse response : responseList) {
            String csvRow = String.valueOf(rowNum).concat(COMMA).concat(response.getTrnId()==null?"":response.getTrnId()).concat(COMMA).concat(String.valueOf(response.getTxnStatus()));
            responses.add(csvRow);
            rowNum++;
        }

        return responses;
    }
}
