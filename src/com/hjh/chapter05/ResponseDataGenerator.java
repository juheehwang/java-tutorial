package com.hjh.chapter05;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ResponseDataGenerator {
    private final File filePath;
    private int statusCode;

    public ResponseDataGenerator(File filePath) {
        this.filePath = filePath;
    }

    public String getResponseHeader(int code, String message) {
        statusCode = code;

        return "HTTP/1.1 " + statusCode + "\n" +
                "Content-Type: image/png\n" +
                "Content-Length: " + filePath.length() + "\n" +
                "Message: " + message + "\n" +
                "Connection: close\n\n";
    }

    public byte[] getResponseBody() throws IOException {
        return Files.readAllBytes(Path.of(filePath.getAbsolutePath()));
    }

    public int getStatusCode() {
        return statusCode;
    }
  
}
