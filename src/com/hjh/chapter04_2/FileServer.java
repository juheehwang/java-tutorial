package com.hjh.chapter04_2;

import java.io.*;
import java.net.ServerSocket;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileServer {
    private ServerSocket serverSocket;
    private final int port;


    public FileServer() {
        this(9999);
    }

    public FileServer(int port) {
        this.port = port;
    }

    public void initialize() throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void start() throws IOException {
        while (true) {
            var socket = serverSocket.accept();
            var in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            var out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            //1. 받은 요청이 제대로 된건지 처리
            String extension = ".java";
            String filePath;

            if ((filePath = in.readLine()) != null) {
                if (!filePath.endsWith(extension)) {
                    System.out.println("Invalid Extension, available extension is " + extension);
                } else {
                    //2. 제대로 된 요청이면 파일정보(이름, 크기)와 파일내용 추출하기
                    File requestFile = new File(filePath);
                    long fileSize = requestFile.length();
                    var fileContents = Files.readString(Path.of(filePath));

                    //3. 2에서 추출된 정보 내보내기
                    out.write("filePath: " + filePath + "\n");
                    out.write("fileSize: " + fileSize + "\n");
                    out.write("fileContents \n" + fileContents);
                    out.flush();

                }
            }

            socket.close();
        }
    }

    public void close() throws IOException {
        serverSocket.close();
    }

    public static void main(String[] args) throws IOException {
        var fileServer = new FileServer();
        fileServer.initialize();
        fileServer.start();
        fileServer.close();
    }
}
