package com.hjh.chapter05;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;

public class ImageForwardServer {
    private ServerSocket serverSocket;
    private final int port;
    public Map<File, byte[]> cacheMap;


    public ImageForwardServer() {
        this(9999);
    }

    public ImageForwardServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws IOException {
        var imageForwardServer = new ImageForwardServer();
        imageForwardServer.initialize();
        imageForwardServer.start();
        imageForwardServer.close();
    }

    public void initialize() throws IOException {
        System.out.println("------------ 서버 오픈 ------------");
        serverSocket = new ServerSocket(port);
    }

    public void start() throws IOException {
        cacheMap = new HashMap<>();
        System.out.println("생성후 mapKeysize: " + cacheMap.size());

        while (true) {
            var socket = serverSocket.accept();
            System.out.println("------------ 로컬 호스트와 연결 ------------");
            var inAndOutGenerator = new InAndOutGenerator(socket);
            inAndOutGenerator.readCommendLine();
            inAndOutGenerator.writer(cacheMap);

            socket.close();
        }
    }

    public void close() throws IOException {
        serverSocket.close();
    }
}
