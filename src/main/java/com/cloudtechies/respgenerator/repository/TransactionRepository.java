package com.cloudtechies.respgenerator.repository;

import com.cloudtechies.respgenerator.model.TransactionReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository<TransactionReport, UUID> {

    TransactionReport findByTransactionReportId(UUID transactionReportId);
}
