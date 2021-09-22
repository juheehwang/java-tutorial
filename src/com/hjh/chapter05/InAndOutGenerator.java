package com.hjh.chapter05;

import java.io.*;
import java.net.Socket;
import java.util.Map;

public class InAndOutGenerator {
    private final Socket socket;
    private SuitableResponseProvider suitableResponseProvider;

    public InAndOutGenerator(Socket socket) {
        this.socket = socket;
    }

    public void readCommendLine() throws IOException {
        var bufferReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String commendLine = bufferReader.readLine();
        suitableResponseProvider = new SuitableResponseProvider(commendLine);
    }

    public void writer(Map<File, byte[]> cacheMap) throws IOException {
        var bufferWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        var fileName = suitableResponseProvider.getResponseFileNameToProvider();
        var outputStream = socket.getOutputStream();

        bufferWriter.write(suitableResponseProvider.respondForFileWriter());
        bufferWriter.flush();

        if (cacheMap.containsKey(fileName)) {
            System.out.println("cache hit!!!");
            outputStream.write(cacheMap.get(fileName));
        } else {
            cacheMap.put(fileName, suitableResponseProvider.getResponseBodyToProvider());
            System.out.println("cache miss...");
            outputStream.write(suitableResponseProvider.getResponseBodyToProvider());
        }
        outputStream.flush();

    }

}
