package com.cloudtechies.respgenerator.repository;

import com.cloudtechies.respgenerator.model.Payload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PayloadRepository extends JpaRepository<Payload, UUID> {

    Payload findByPayloadId(UUID payloadId);


}
