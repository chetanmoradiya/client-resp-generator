package com.cloudtechies.respgenerator.repository;

import com.cloudtechies.respgenerator.entity.Payload;
import com.cloudtechies.respgenerator.entity.PayloadPK;
import com.cloudtechies.respgenerator.enums.PayloadState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PayloadRepository extends JpaRepository<Payload, PayloadPK> {

    Optional<Payload> findByFileNameAndLastModifiedTs(@Param("fileName") String fileName, @Param("lastModifiedTs") Instant lastModifiedTs);

    List<Payload> findByPayloadStateOrderByUpdateTsDescCreateTsDesc(@Param("payloadState") PayloadState state);

    Optional<Payload> findByPayloadId(@Param("payloadId") UUID payloadId);


    @Transactional
    @Modifying
    @Query(value = "UPDATE payload set state= :state, update_ts= :updatets, resp_file_path= :respfilepath  WHERE payload_id= :payloadId ", nativeQuery = true)
    void updatePayloadStatus(UUID payloadId, PayloadState state, Instant updatets, String respfilepath);


}
