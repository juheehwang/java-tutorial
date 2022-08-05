package com.hjh.chapter06_1;

import java.net.Socket;

public class IOHandler {

  private final Socket socket;

  public IOHandler(Socket socket) {
    this.socket = socket;
  }

  public void excute() {
    readStream();
    writeStream();
  }

  private void readStream() {

  }

  private void writeStream() {

  }
}
