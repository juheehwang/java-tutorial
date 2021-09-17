package com.hjh.chapter05;

import java.io.File;

public class FileNameValidator {
    public static final String DOCUMENT_ROOT = "/Users/hwangjuhui/Downloads";
    public static final String PREFIX = "GET ";

    private static final String SPACE = " ";
    private static final String extension = ".png";
    private static final String postFix = " HTTP/1.1";

    private final String commandLine;
    private File requestedFile;

    public FileNameValidator(String commandLine) {
        this.commandLine = commandLine;
    }

    public boolean validate() {
        // HTTP Command line의 형식은 [METHOD] [URI] [VERSION][\n]
        var splitCommandLine = commandLine.split(SPACE);
        if (splitCommandLine.length != 3) {
            return false;
        }

        var uri = splitCommandLine[1];
        requestedFile = new File(DOCUMENT_ROOT, uri);
        return requestedFile.exists();
    }

    public File getRequestedFile() {
        return requestedFile;
    }
}
