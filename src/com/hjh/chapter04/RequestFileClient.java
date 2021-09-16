package com.hjh.chapter04;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class RequestFileClient {
    public static void main(String[] args) throws IOException {

        try (Scanner sc = new Scanner(System.in);
             Socket socketClient = new Socket("127.0.0.1", 9999)) {
            System.out.println("서버와 연결이 되었습니다...");

            var clientIn = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
            var clientOut = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));

            while (true) {
                String get = "GET ";
                String clientRequestFileNameWithExt = get + sc.nextLine();
                System.out.println(clientRequestFileNameWithExt);
                clientOut.write(clientRequestFileNameWithExt + "\n");
                clientOut.flush();

                if (clientIn.readLine() == null) {
                   
                    break;
                } else {
                    String serverMsg = clientIn.readLine();
                    System.out.println(serverMsg + "\n");
                }
            }

        } catch (EOFException eof) {
            System.out.println("EOF");
            //  e.printStackTrace();
        } catch (IOException io) {
            System.out.println("io");
        }
    }
}
