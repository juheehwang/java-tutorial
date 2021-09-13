package com.hjh.chapter03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class CreateAndConnectServer {
    public static void main(String[] args) {
        BufferedReader in;
        //   BufferedWriter out = null;
        Socket socket;

        try (ServerSocket serverSocket = new ServerSocket(9999)) {
            socket = serverSocket.accept();
            System.out.println("server connected by client");
            System.out.println("server port" + socket.getInetAddress());

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            while (true) {
                String message = in.readLine();

                if (message == null) {
                    System.out.println("server is closed by client");
                    break;
                }
                System.out.println("msg from Client: " + message + "\r");
            }
//            out.write("여기는 서버에서 보낸당");
//            out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
