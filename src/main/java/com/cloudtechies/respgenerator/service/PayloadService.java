package com.cloudtechies.respgenerator.service;

import com.cloudtechies.respgenerator.repository.PayloadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayloadService {

    @Autowired
    PayloadRepository payloadRepository;

//    public PayloadResponse getPayloadDetails(UUID payloadId) {
//
//
//        return payloadRepository.existsById(payloadId);
//    }
}
