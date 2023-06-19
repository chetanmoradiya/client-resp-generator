package com.cloudtechies.respgenerator.generator;

import com.cloudtechies.respgenerator.model.FilePayloadMessage;
import com.cloudtechies.respgenerator.model.PayloadResponse;
import com.cloudtechies.respgenerator.writer.ResponseWriter;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class ResponseFileGenerator {

    @Autowired
    ResponseWriter responseWriter;

    public boolean generateResponseFile(FilePayloadMessage payload, List<PayloadResponse> response) {
        String outFileName = payload.getFileName();
        String outFullFilePath = payload.getRespFilePath() + File.separator + outFileName.concat(".temp");
        String finalFullFilePath = payload.getRespFilePath() + File.separator + outFileName.concat(".csv");

        File tempRespFile = new File(outFullFilePath);
        try {
            writeDatatoFile(response, tempRespFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        File finalFile = new File(finalFullFilePath);
        tempRespFile.renameTo(finalFile);
        return true;

    }

    private void writeDatatoFile(List<PayloadResponse> response, File file) throws IOException {
        FileUtils.writeLines(file, responseWriter.generateOutputLines(response));
    }
}
