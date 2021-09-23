package com.hjh.chapter05;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;

public class CacheableClientSocketHandler {

  private final Socket socket;
  private final CacheManager cacheManager;

  public CacheableClientSocketHandler(Socket socket, CacheManager cacheManager) {
    this.socket = socket;
    this.cacheManager = cacheManager;
  }

  public void handle() throws IOException {
    var commandLine = readCommandLine();
    var requestParser = new RequestParser(commandLine);

    var targetFile = new File("/Users/hwangjuhui/Downloads", requestParser.getRequestedUri());
    writeResponse(targetFile);
  }

  private String readCommandLine() throws IOException {
    var bufferReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    return bufferReader.readLine();
  }

  private void writeResponse(File targetFile) throws IOException {
    var bufferWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    var outputStream = socket.getOutputStream();
    var headerGenerator = new HeaderGenerator(targetFile);

    bufferWriter.write(headerGenerator.getHeader());
    bufferWriter.flush();

    var cachedBytes = cacheManager.get(targetFile);
    if (cachedBytes == null) {
      var bytes = Files.readAllBytes(Path.of(targetFile.getAbsolutePath()));
      cacheManager.put(targetFile, bytes);
      cachedBytes = bytes;
    }

    outputStream.write(cachedBytes);
    outputStream.flush();
  }

}
