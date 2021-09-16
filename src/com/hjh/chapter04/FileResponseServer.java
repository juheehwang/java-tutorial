package com.hjh.chapter04;

import com.hjh.chapter04.helper.CommandLineParser;
import com.hjh.chapter04.helper.FileResponseWriter;

import java.io.*;
import java.net.ServerSocket;

public class FileResponseServer {
    private static final String ROOT_DIRECTORY = "/Users/hwangjuhui/workspace";
    private ServerSocket serverSocket;
    private final int port;

    public FileResponseServer() {
        this(9999);
    }

    public FileResponseServer(int port) {
        this.port = port;
    }

    public void initialize() throws IOException {
        System.out.println("Initializing Server....");
        serverSocket = new ServerSocket(port);
        System.out.println("Server Initialized.");
    }

    public void start() throws IOException {
        System.out.println("Starting Server...");
        while (true) {
            var socket = serverSocket.accept();
            System.out.println("Socket Accepted: " + socket.toString());

            var reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            var writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            var commandLine = reader.readLine();
            System.out.println("Requested CommandLine:" + commandLine);


            var commandLineParser = new CommandLineParser(writer, commandLine);
            if (!commandLineParser.isValidCommandLine()) {
                commandLineParser.sayNo();
                socket.close();
                continue;
            }

            var filepath = commandLineParser.getFilepath();
            var targetFile = new File(ROOT_DIRECTORY, filepath);

            var responseWriter = new FileResponseWriter(writer, targetFile);
            responseWriter.write();

            socket.close();
        }
    }

    public void close() throws IOException {
        serverSocket.close();
    }

    public static void main(String[] args) throws IOException {
        var server = new FileResponseServer();
        server.initialize();
        server.start();
        server.close();
    }
}
