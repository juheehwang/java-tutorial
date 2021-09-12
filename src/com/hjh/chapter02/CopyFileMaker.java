package com.hjh.chapter02;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class CopyFileMaker {

    private final String fileExtension;
    private final File originFile;
    private final File destination;

    public CopyFileMaker(String fileExtension, File originFile, File destination) {
        this.fileExtension = fileExtension;
        this.originFile = originFile;
        this.destination = destination;
    }


    public static void main(String[] args) throws IOException {

        //File originFile = new File("/Users/hwangjuhui/workspace/java-tutorial/src/com/hjh/chapter01/HelloWorldApp.java");
        //File destination = new File("/Users/hwangjuhui/temp/HelloWorldApp.java");
        File originFile = new File("/Users/hwangjuhui/workspace");
        File destination = new File("/Users/hwangjuhui/temp");


        // Files.copy(originFile.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);


        var copyFileMaker = new CopyFileMaker(".java", originFile, destination);
        copyFileMaker.startCopy();
    }

    private void startCopy() throws IOException {
        copyDirectory(originFile, destination);
    }

    private void copyDirectory(File origin, File destination) throws IOException {
        //List<String> childDirectoryList = new ArrayList<>();
        var childList = origin.listFiles();

        if (childList == null || childList.length == 0) {
            return;
        }
        for (File child : childList) {

            if (!child.isDirectory() && child.getName().endsWith(fileExtension)) {
                System.out.println(child.getAbsoluteFile().toString());
                var removeRootFile = child.getPath().replaceAll(originFile.getPath(), "");
                System.out.println(removeRootFile);
                var temp = new File(destination + File.separator + removeRootFile);
                temp.mkdirs();
                Files.copy(child.toPath(), temp.toPath(), StandardCopyOption.REPLACE_EXISTING);

                return;
            } else {
                copyDirectory(child, destination);
            }
        }
    }

}
