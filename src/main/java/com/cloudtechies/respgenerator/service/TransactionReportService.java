package com.cloudtechies.respgenerator.service;

import com.cloudtechies.respgenerator.model.TransactionReport;
import com.cloudtechies.respgenerator.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransactionReportService {

    @Autowired
    TransactionRepository transactionRepository;

    public TransactionReport getTransactionReport(UUID transactionReportId) {
        return transactionRepository.findByTransactionReportId(transactionReportId);
    }

}
