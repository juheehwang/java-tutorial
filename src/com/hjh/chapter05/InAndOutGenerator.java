package com.hjh.chapter05;

import java.io.*;
import java.net.Socket;

public class InAndOutGenerator {
    private final Socket socket;
    private SuitableResponseProvider suitableResponseProvider;
    private ResponseDataGenerator responseDataGenerator;

    public InAndOutGenerator(Socket socket) {
        this.socket = socket;
    }

    public String readCommendLine() throws IOException {
        var bufferReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String commendLine = bufferReader.readLine();
        suitableResponseProvider = new SuitableResponseProvider(commendLine);
        return commendLine;
    }

    public void socketOutputStream() throws IOException {
        if (responseDataGenerator.getStatusCode() == 200) {
            var outputStream = socket.getOutputStream();
            outputStream.write(responseDataGenerator.getResponseBody());
            outputStream.flush();
        }
    }

    public void writer() throws IOException {
        var bufferWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bufferWriter.write(suitableResponseProvider.forwardToFileWriter());
        bufferWriter.flush();
    }

}
