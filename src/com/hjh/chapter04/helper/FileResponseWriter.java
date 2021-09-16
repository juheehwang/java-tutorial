package com.hjh.chapter04.helper;

import java.io.File;
import java.io.IOException;
import java.io.Writer;

public class FileResponseWriter {
    private final Writer writer;
    private final File targetFile;

    public FileResponseWriter(Writer writer, File targetFile) {
        this.writer = writer;
        this.targetFile = targetFile;
    }

    public void write() throws IOException {
        long fileSize = targetFile.length();
        String fileContents = new FileContentsReader(targetFile).read();

        // 위에서 추출된 내용들을 클라이언트로 전송한다.
        println(writer, "File Path: " + targetFile.getAbsolutePath());
        println(writer, "File Size: " + fileSize + "B");
        println(writer, "File Contents:");
        println(writer, fileContents);
        println(writer, "EOF");
    }

    public void println(Writer writer, String message) throws IOException {
        writer.write(message);
        writer.write("\n");
        writer.flush();
    }
}
