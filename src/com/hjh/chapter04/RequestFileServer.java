package com.hjh.chapter04;

import java.io.*;
import java.net.ServerSocket;
import java.nio.file.Files;

public class RequestFileServer {
    private final File rootPathString;
    private final String fileName;

    public RequestFileServer(String rootPathString, String fileName) {
        this.rootPathString = new File(rootPathString);
        this.fileName = fileName;
    }

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(9999)) {
            while (true) {
                var socket = serverSocket.accept();
                System.out.println("sever is connected by client " + socket.getInetAddress().getHostName() + socket.getInetAddress().getHostAddress());

                var in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                var out = new BufferedWriter(new OutputStreamWriter((socket.getOutputStream())));

                String clientRequest = in.readLine();
                String[] findRealRequest = clientRequest.split(" ");
                String realFileName = findRealRequest[1];

                var requestFileServer = new RequestFileServer("/Users/hwangjuhui/workspace", realFileName);
                System.out.println("root 잘들가나? " + requestFileServer.rootPathString.getPath());
                var finalRequestFile = requestFileServer.findFileInChild(realFileName, requestFileServer.rootPathString);

                int filesize = (int) Files.size(finalRequestFile.toPath());


                out.write("fileName: " + finalRequestFile.getName() + "\n");
//                out.append("fileSize")
//                +"fileSize: " + filesize + "\n");
                out.flush();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File findFileInChild(String fileName, File rootPath) {
        File getFile = null;

        var fileList = rootPath.listFiles();

        for (File childFile : fileList) {
            if (!childFile.isDirectory() && childFile.getName().endsWith(fileName)) {
                getFile = childFile;
                System.out.println(getFile.getName());


            } else {
                findFileInChild(fileName, childFile);
            }
        }

        return getFile;
    }

}
