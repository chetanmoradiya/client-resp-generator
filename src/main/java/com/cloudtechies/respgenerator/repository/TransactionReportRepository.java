package com.cloudtechies.respgenerator.repository;

import com.cloudtechies.respgenerator.entity.TransactionReport;
import com.cloudtechies.respgenerator.entity.TransactionReportPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TransactionReportRepository extends JpaRepository<TransactionReport, TransactionReportPK> {

    Optional<TransactionReport> findByTransactionReportId(@Param("transactionReportId") UUID transactionReportId);

    List<TransactionReport> findByPayloadId(@Param("payloadId") UUID payloadId);


}
