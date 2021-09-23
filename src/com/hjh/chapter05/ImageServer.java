package com.hjh.chapter05;

import java.io.IOException;
import java.net.ServerSocket;

public class ImageServer {

  private ServerSocket serverSocket;
  private CacheManager cacheManager;
  private final int port;

  public ImageServer() {
    this(9999);
  }

  public ImageServer(int port) {
    this.port = port;
  }

  public static void main(String[] args) throws IOException {
    var imageServer = new ImageServer();
    imageServer.initialize();
    imageServer.start();
    imageServer.close();
  }

  public void initialize() throws IOException {
    System.out.println("------------ 서버 초기화 ------------");
    cacheManager = new CacheManager();

    System.out.println("------------ 서버 포트 오픈 ------------");
    serverSocket = new ServerSocket(port);
  }

  public void start() throws IOException {
    while (true) {
      var clientSocket = serverSocket.accept();
      System.out.println("------------ 클라이언트와 연결 ------------");
      var cacheableSocketHandler = new CacheableClientSocketHandler(clientSocket, cacheManager);
      cacheableSocketHandler.handle();

      clientSocket.close();
    }
  }

  public void close() throws IOException {
    serverSocket.close();
  }
}
