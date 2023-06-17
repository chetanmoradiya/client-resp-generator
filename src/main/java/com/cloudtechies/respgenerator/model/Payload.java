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

@Entity(name = "payload")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Payload {

    @Id
    @Column(name = "payload_id")
    @GeneratedValue
    private UUID payloadId;

    @Column(name = "create_ts")
    private LocalDateTime createdTimeStamp;
}
