package com.hjh.chapter03;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class CreateAndConnectionClient {

    public static void main(String[] args) {
        BufferedWriter out = null;
        // BufferedReader in = null;

        try (Scanner sc = new Scanner(System.in); Socket clientSocket = new Socket("127.0.0.1", 9999)) {
            //in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

            while (true) {
                System.out.print("글자를 입력해주세요!!");
                String clientMsg = sc.nextLine();

                if (clientMsg.equals("exit")) {
                    out.write(clientMsg);
                    break;
                }

                out.write(clientMsg + "\n");
                out.flush();

            }
//            String serverMsg = in.readLine();
//            System.out.println("!!serverMessage!!" + serverMsg);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
