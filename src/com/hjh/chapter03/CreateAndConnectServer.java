package com.hjh.chapter03;

import java.io.IOException;
import java.net.ServerSocket;

public class CreateAndConnectServer {
    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(9999);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
