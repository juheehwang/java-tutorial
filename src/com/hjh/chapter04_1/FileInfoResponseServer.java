package com.hjh.chapter04_1;


import com.hjh.chapter04_1.assistance.CommendLineParser;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class FileInfoResponseServer {
    private ServerSocket serverSocket;
    private Socket socket;
    private final int port;

    public FileInfoResponseServer() {
        this(9999);
    }

    public FileInfoResponseServer(int port) {
        this.port = port;
    }

    public void Initialize() throws IOException {
        System.out.println("========= server is starting...============");
        serverSocket = new ServerSocket();
        System.out.println("========= serverSocket is built ===========");
    }

    public void receiveRequestAndResponseTo() throws IOException {
        while (true) {
            socket = serverSocket.accept();
            System.out.println("++++ Server connects with Client ++++ ");
            var in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            var out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            String receivedFileName = in.readLine();

            var commendLineParser = new CommendLineParser();
            String absoluteFileName = commendLineParser.offerUtilCommendLine(out,
                    commendLineParser.confirmProperCommendLine(receivedFileName)
            );

            File absoluteFile = new File(absoluteFileName);
            long fileSize = absoluteFile.length();

        }

    }

    public static void main(String[] args) throws IOException {
        var fileInfoResponseServer = new FileInfoResponseServer();
        fileInfoResponseServer.Initialize();
        fileInfoResponseServer.receiveRequestAndResponseTo();
    }
}
