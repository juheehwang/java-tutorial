package com.hjh.chapter04;

import java.io.*;
import java.net.Socket;

public class FileRequestClient {
    private static final String COMMAND_LINE_PREFIX = "GET ";

    private final String serverAddress;
    private final int port;

    private Socket socket;

    public FileRequestClient(String serverAddress, int port) {
        this.serverAddress = serverAddress;
        this.port = port;
    }

    public void initialize() throws IOException {
        socket = new Socket(serverAddress, port);
    }

    public void start() throws IOException {
        var commandLine = COMMAND_LINE_PREFIX + "java-tutorial/src/com/hjh/chapter04/RequestFileServer.java";

        var reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        var writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        writer.write(commandLine);
        writer.write("\n");
        writer.flush();

        String line = null;
        System.out.println("--------- Response From Server Started --------");
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        System.out.println("--------- Response From Server Finished --------");
    }

    public static void main(String[] args) throws IOException {
        var client = new FileRequestClient("localhost", 9999);
        client.initialize();
        client.start();
    }

}
