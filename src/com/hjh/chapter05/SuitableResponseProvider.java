package com.hjh.chapter05;

import java.io.File;
import java.io.IOException;

public class SuitableResponseProvider {
    private final String commendLine;
    private FileNameValidator fileNameValidator;
    private ResponseDataGenerator responseDataGenerator;

    public SuitableResponseProvider(String commendLine) {
        this.commendLine = commendLine;
    }

    public String respondForFileWriter() {
        int statusCode;
        System.out.println(commendLine);
        fileNameValidator = new FileNameValidator(commendLine);
        System.out.println("fileNameValidator.getRequestFile()" + fileNameValidator.getRequestedFile());
        if (fileNameValidator.validate()) {
            responseDataGenerator = new ResponseDataGenerator(fileNameValidator.getRequestedFile());
            if (fileNameValidator.getRequestedFile().length() > 10 * 1024 * 1024) {
                statusCode = 400;
            }
            statusCode = 200;

        } else {
            statusCode = 404;
        }
        return responseDataGenerator.getResponseHeader(statusCode);
    }

    public File getResponseFileNameToProvider() {
        fileNameValidator = new FileNameValidator(commendLine);
        return fileNameValidator.getRequestedFile();
    }

    public byte[] getResponseBodyToProvider() throws IOException {
        return responseDataGenerator.getResponseBody();
    }

}
