package com.hjh.chapter04.helper;

import java.io.IOException;
import java.io.Writer;

public class CommandLineParser {
    private final String PREFIX = "GET ";
    private final String commandLine;
    private final Writer writer;

    public CommandLineParser(Writer writer, String commandLine) {
        this.writer = writer;
        this.commandLine = commandLine;

    }

    public String getFilepath() {
        return commandLine.substring(PREFIX.length());
    }

    public boolean isValidCommandLine() {
        return commandLine.startsWith(PREFIX);
    }

    public void sayNo() throws IOException {
        var message = "Invalid CommandLine: " + commandLine;
        System.out.println(message);
        writer.write(message + "\n");
        writer.flush();
    }
}
