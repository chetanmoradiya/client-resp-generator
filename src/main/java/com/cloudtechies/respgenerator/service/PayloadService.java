package com.cloudtechies.respgenerator.service;

import com.cloudtechies.respgenerator.model.Payload;
import com.cloudtechies.respgenerator.repository.PayloadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PayloadService {

    @Autowired
    PayloadRepository payloadRepository;

    public Payload getPayloadDetails(UUID payloadId) {
        return payloadRepository.findByPayloadId(payloadId);
    }
}
