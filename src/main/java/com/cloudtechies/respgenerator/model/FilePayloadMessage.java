package com.cloudtechies.respgenerator.model;

import com.cloudtechies.respgenerator.enums.PayloadState;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
public class FilePayloadMessage {

    private UUID payloadId;

    private String fileName;

    private String ftpFolder;

    private String absolutePath;

    private Long createTs;

    private Long updateTs;

    private Long lastModifiedTs;

    private Long instructionCount;

    private String respFileName;

    private String respFilePath;

    private PayloadState payloadState;

    private String rejectionReason;

}
