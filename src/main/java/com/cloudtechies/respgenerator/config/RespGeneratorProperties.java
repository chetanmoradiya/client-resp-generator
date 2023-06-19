package com.cloudtechies.respgenerator.config;


import lombok.Data;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Configuration
@Data
@Validated
@EnableConfigurationProperties(RespGeneratorProperties.class)
public class RespGeneratorProperties {


    private String kakfaClusterURL;

    private String kafkaConsumerGroupName;

    private String kafkaPersistPayloadTopic;

    private String maxPollSize;

    private String respCSVHeader;

    private String respRootPath;


}
