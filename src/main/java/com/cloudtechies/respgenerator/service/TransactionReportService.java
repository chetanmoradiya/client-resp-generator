package com.cloudtechies.respgenerator.service;

import com.cloudtechies.respgenerator.entity.TransactionReport;
import com.cloudtechies.respgenerator.repository.TransactionReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionReportService {

    @Autowired
    TransactionReportRepository transactionReportRepository;

    public Optional<TransactionReport> getTransactionReportDetails(UUID transactionReportId) {
        return transactionReportRepository.findByTxnReportId(transactionReportId);
    }

}
