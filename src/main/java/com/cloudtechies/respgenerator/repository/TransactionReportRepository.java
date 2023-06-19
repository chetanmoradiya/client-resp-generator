package com.cloudtechies.respgenerator.repository;

import com.cloudtechies.respgenerator.entity.TransactionReport;
import com.cloudtechies.respgenerator.entity.TransactionReportPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionReportRepository extends JpaRepository<TransactionReport, TransactionReportPK> {

    Optional<TransactionReport> findByTxnReportId(@Param("txn_report_id") UUID transactionReportId);

    Optional<List<TransactionReport>> findByPayloadID(@Param("payload_id") UUID payloadId);


}
