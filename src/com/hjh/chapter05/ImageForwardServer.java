package com.hjh.chapter05;

import java.io.*;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;

public class ImageForwardServer {
    private ServerSocket serverSocket;
    private final int port;


    public ImageForwardServer() {
        this(9999);
    }

    public ImageForwardServer(int port) {
        this.port = port;
    }

    public void initialize() throws IOException {
        System.out.println("------------ 서버 오픈 ------------");
        serverSocket = new ServerSocket(port);
    }

    public void start() throws IOException {
        Map<File, byte[]> cacheMap = new HashMap<>();
        System.out.println("생성후 mapKeysize: " + cacheMap.size());

        while (true) {
            var socket = serverSocket.accept();
            System.out.println("------------ 로컬 호스트와 연결 ------------");
            var reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            var socketOutputStream = socket.getOutputStream();
            var writer = new BufferedWriter(new OutputStreamWriter(socketOutputStream));


            var commandLine = reader.readLine();


            System.out.println("COMMAND_LINE: " + commandLine);
            var fileNameValidator = new FileNameValidator(commandLine);
            boolean validationResult = fileNameValidator.validate();
            System.out.println("VALIDATION_RESULT: " + validationResult);
            var requestedFile = fileNameValidator.getRequestedFile();
            System.out.println("REQUESTED_FILE: " + requestedFile.getAbsolutePath());

            if (!validationResult) {
                var message = "No Such File Found: " + requestedFile.getAbsolutePath();
                writer.write("HTTP/1.0 404\n");
                writer.write("content-type: text/plain\n");
                writer.write("content-length:" + message.length() + "\n");
                writer.write("\n");
                writer.write(message + "\n");
            } else {
                if (requestedFile.length() > 10 * 1024 * 1024) {
                    var message = "File is too large, Cannot serve a file larger than 10MB: requested file size is : " + requestedFile.length();
                    writer.write("HTTP/1.0 400\n");
                    writer.write("content-type: text/plain\n");
                    writer.write("content-length:" + message.length() + "\n");
                    writer.write("\n");
                    writer.write(message + "\n");
                } else {
                    var responseDataGenerator = new ResponseDataGenerator(requestedFile);
                    if (cacheMap.containsKey(requestedFile)) {
                        System.out.println("cache hit");
                        writer.write(responseDataGenerator.getResponseHeader());
                        writer.flush();
                        socketOutputStream.write(cacheMap.get(requestedFile));
                    } else {
                        cacheMap.put(requestedFile, responseDataGenerator.getResponseBody());
                        System.out.println("cache miss발생! cache 등록 mapKeysize: " + cacheMap.size());
                        writer.write(responseDataGenerator.getResponseHeader());
                        writer.flush();


                        socketOutputStream.write(responseDataGenerator.getResponseBody());
                    }
                    socketOutputStream.flush();
                }
            }

            writer.flush();
            socketOutputStream.flush();
            socket.close();
        }
    }

    public void close() throws IOException {
        serverSocket.close();
    }

    public static void main(String[] args) throws IOException {
        var imageForwardServer = new ImageForwardServer();
        imageForwardServer.initialize();
        imageForwardServer.start();
        imageForwardServer.close();
    }
}
