package com.hjh.chapter06_1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ImageSever {

  private ServerSocket serverSocket;
  private Socket socket;
  private final int port = 9090;

  public static void main(String[] args) throws IOException {
    var imageServer = new ImageSever();
    imageServer.initialize();
    imageServer.start();
    imageServer.close();
  }

  public void initialize() throws IOException {
    serverSocket = new ServerSocket(port);

  }

  public void start() {
    acceptSocket();
    var ioHandler = new IOHandler(socket);
    ioHandler.excute();

  }

  private void acceptSocket() {
    while (true) {
      try {
        socket = serverSocket.accept();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public void close() {

  }

}
