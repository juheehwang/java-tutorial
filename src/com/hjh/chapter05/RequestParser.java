package com.hjh.chapter05;

public class RequestParser {

  private String commandLine;
  private String[] parsedCommandLine;

  public RequestParser(String commandLine) {
    this.commandLine = commandLine;
    parse();
  }

  private void parse() {
    parsedCommandLine = commandLine.split(" ");
    if (parsedCommandLine.length != 3) {
      throw new IllegalArgumentException("Invalid CommandLine: " + commandLine);
    }
  }

  public String getHttpMethod() {
    return parsedCommandLine[0];
  }

  public String getRequestedUri() {
    return parsedCommandLine[1];
  }

  public String getHttpVersion() {
    return parsedCommandLine[2];
  }

}
