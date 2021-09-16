package com.hjh.chapter04.helper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileContentsReader {
    private final File targetFile;

    public FileContentsReader(File targetFile) {
        this.targetFile = targetFile;
    }

    public String read() throws IOException {
        var lineList = Files.readAllLines(Path.of(targetFile.getAbsolutePath()));
        var stringBuilder = new StringBuilder();
        lineList.forEach(line -> stringBuilder.append(line).append("\n"));

        return stringBuilder.toString();
    }
}
