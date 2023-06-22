package com.cloudtechies.respgenerator.generator;

import com.cloudtechies.respgenerator.config.RespGeneratorProperties;
import com.cloudtechies.respgenerator.enums.PayloadState;
import com.cloudtechies.respgenerator.exception.UnrecoverableException;
import com.cloudtechies.respgenerator.model.FilePayloadMessage;
import com.cloudtechies.respgenerator.model.PayloadResponse;
import com.cloudtechies.respgenerator.writer.ResponseWriter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.List;

@Component
@Slf4j
public class ResponseFileGenerator {

    @Autowired
    ResponseWriter responseWriter;

    @Autowired
    RespGeneratorProperties respGeneratorProperties;

    public FilePayloadMessage generateResponseFile(FilePayloadMessage payload, List<PayloadResponse> response) {
        String rootPath = respGeneratorProperties.getRespRootPath();
        String outFileName = payload.getRespFileName();
        String respPath = rootPath +File.separator+payload.getFtpFolder();
        String respFileTempPath = respPath + File.separator + outFileName+".tmp";
        String respFileFinalPath = respPath + File.separator + outFileName+".csv";

        File finalFile = new File(respFileFinalPath);

        File tempRespFile = new File(respFileTempPath);
        try {
            if(finalFile.exists()){
                log.info("CSV response file found... deleting before generating");
                finalFile.delete();
            }
            if(tempRespFile.exists()){
                log.info(".temp response file found... deleting before generating");
                tempRespFile.delete();
            }
            writeDatatoFile(response,tempRespFile);
        } catch (IOException e) {
            throw new UnrecoverableException("Exception while writing data to file");
        }
        tempRespFile.renameTo(finalFile);
        payload.setRespFilePath(finalFile.getPath());
        payload.setUpdateTs(Instant.now().toEpochMilli());
        payload.setPayloadState(PayloadState.PROCESSED);
        return payload;

    }

    private void writeDatatoFile(List<PayloadResponse> response, File file) throws IOException {
        FileUtils.writeLines(file, responseWriter.generateOutputLines(response));
    }
}
