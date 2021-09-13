package com.hjh.chapter03;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class CreateAndConnectServer {
    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(9999)) {
            while (true) {
                Socket request = serverSocket.accept();
                System.out.println("server open" + serverSocket.accept().getInetAddress().getHostName());

                var in = new BufferedReader(new InputStreamReader(request.getInputStream()));
                String message = in.readLine();
                System.out.println("msg from Client" + message);

                var out = new BufferedWriter(new OutputStreamWriter(request.getOutputStream()));
                out.write(message + "\n");
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }


    }
}
