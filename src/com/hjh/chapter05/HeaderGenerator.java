package com.hjh.chapter05;

import java.io.File;

public class HeaderGenerator {

  private final File targetFile;
  private int statusCode;
  private String message;

  public HeaderGenerator(File targetFile) {
    this.targetFile = targetFile;
  }

  public String getHeader() {
    statusCode = 200;
    message = "Response is successfully";

    if (!targetFile.exists()) {
      statusCode = 404;
      message = "No Such File Found: " + targetFile.getName();
    } else if (targetFile.length() > 10 * 1024 * 1024) {
      statusCode = 400;
      message =
          "File is too large, Cannot serve a file larger than 10MB: requested file size is : "
              + targetFile.length();
    }
    return buildHeader();
  }

  private String buildHeader() {
    var completedHeader = new StringBuilder();
    completedHeader.append("HTTP/1.1 ");
    completedHeader.append(statusCode);
    completedHeader.append("\n");
    completedHeader.append(statusCode == 200 ? "Content-Type: image/png\n" : "");
    completedHeader.append(
        statusCode == 200 ? "Content-Length: " + targetFile.length() + "\n" : "");
    completedHeader.append("Message: ");
    completedHeader.append(message);
    completedHeader.append("\n");
    completedHeader.append("Connection: close\n\n");

    return completedHeader.toString();
  }
}
