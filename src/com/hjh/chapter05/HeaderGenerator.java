package com.hjh.chapter05;

import java.io.File;
import java.util.Map;

public class HeaderGenerator {

  private static final Map<Integer, String> STATUS_MESSAGE_MAP = Map.of(
      200, "Response is successfully",
      404, "No Such File Found",
      400, "File is too large, Cannot serve a file larger than 10M"
  );

  private final File targetFile;
  private int statusCode;
  private String header;

  public HeaderGenerator(File targetFile) {
    this.targetFile = targetFile;
    this.initialize();
  }

  public File getTargetFile() {
    return targetFile;
  }

  public String getHeader() {
    return header;
  }

  public int getStatusCode() {
    return statusCode;
  }

  private void initialize() {
    setStatusCode();
    buildHeader();
  }

  private void setStatusCode() {
    statusCode = !targetFile.exists() ? 404 :
        targetFile.length() > 10 * 1024 * 1024 ? 400 :
            200;
  }

  private void buildHeader() {

    header = "HTTP/1.1 "
        + statusCode
        + "\n"
        + (statusCode == 200 ? "Content-Type: image/png\n" : "")
        + (statusCode == 200 ? "Content-Length: " + targetFile.length() + "\n" : "")
        + "Message: "
        + STATUS_MESSAGE_MAP.get(statusCode)
        + "\n"
        + "Connection: close\n\n";
  }
}
