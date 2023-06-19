package com.cloudtechies.respgenerator.model;

import com.cloudtechies.respgenerator.enums.TransactionStatus;
import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.TypeDef;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TypeDef(name = "json", typeClass = JsonType.class)
public class PayloadResponse {

    private String trnId;
    private TransactionStatus txnStatus;
}
