package com.hjh.chapter04_1;

import com.hjh.chapter04_1.assistance.CommonUtilization;

import java.io.*;
import java.net.Socket;

public class FileInfoRequestClient {
    private Socket socket;
    private String requestFilename;


    public void initialize() throws IOException {
        System.out.println("========== Build a socket... =========");
        socket = new Socket("127.0.0.1", 9999);
        System.out.println("========== The Connection is successful " + socket.getInetAddress().getHostAddress() + "=========");

    }

    public void requestFileInfo() throws IOException {
        var out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        var in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        System.out.println("Client start to request a file information...");

        requestFilename = CommonUtilization.COMMEND_LINE_PREFIX + "java-tutorial/src/com/hjh/chapter04/FileResponseServer.java";
        out.write(requestFilename);
        out.flush();

        String getInfo = "";
        if ((getInfo = in.readLine()) != null) {
            System.out.println(getInfo + "\n");

        } else {
            in.close();
        }


    }

    public static void main(String[] args) throws IOException {
        var fileInfoRequestClient = new FileInfoRequestClient();
        fileInfoRequestClient.initialize();
        fileInfoRequestClient.requestFileInfo();

    }
}
