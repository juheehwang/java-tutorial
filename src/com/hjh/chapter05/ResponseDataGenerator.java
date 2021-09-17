package com.hjh.chapter05;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ResponseDataGenerator {
    private final File filePath;

    public ResponseDataGenerator(File filePath) {
        this.filePath = filePath;
    }

    public String getResponseHeader() {
        return "HTTP/1.1 200 \n" +
                "Server: HJH BAD Server\n" +
                "Vary: Origin\n" +
                "Vary: Access-Control-Request-Method\n" +
                "Vary: Access-Control-Request-Headers\n" +
                "Last-Modified: Thu, 16 Sep 2021 00:21:12 GMT\n" +
                "Accept-Ranges: bytes\n" +
                "X-Content-Type-Options: nosniff\n" +
                "X-XSS-Protection: 1; mode=block\n" +
                "Cache-Control: no-cache, no-store, max-age=0, must-revalidate\n" +
                "Pragma: no-cache\n" +
                "Expires: 0\n" +
                "X-Frame-Options: DENY\n" +
                "Content-Type: image/png\n" +
                "Content-Length: " + filePath.length() + "\n" +
                "Date: Thu, 16 Sep 2021 07:07:39 GMT\n" +
                "Connection: close\n\n";
    }

    public byte[] getResponseBody() throws IOException {
        return Files.readAllBytes(Path.of(filePath.getAbsolutePath()));
    }
}
