package com.cloudtechies.respgenerator.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "transactions")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionReport {

    @Id
    @Column(name = "transaction_id")
    @GeneratedValue
    private UUID payloadId;

    @Column(name = "create_ts")
    private LocalDateTime createdTimeStamp;

}
