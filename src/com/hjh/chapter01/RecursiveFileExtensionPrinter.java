package com.hjh.chapter01;

import java.io.File;

public class RecursiveFileExtensionPrinter {
    private final File topLevelDirectory;
    private final String fileExtension;

    public RecursiveFileExtensionPrinter(String topDirectoryPath, String fileExtension) {
        if (topDirectoryPath == null || topDirectoryPath.isEmpty()) {
            throw new IllegalArgumentException("topDirectoryPath cannot be null.");
        }
        topLevelDirectory = new File(topDirectoryPath);

        if (!topLevelDirectory.exists() && !topLevelDirectory.isDirectory()) {
            throw new IllegalArgumentException("topDirectoryPath should be an existing directory.");
        }
        if (fileExtension == null || fileExtension.isEmpty()) {
            throw new IllegalArgumentException("fileExtension cannot be null");
        }

        this.fileExtension = fileExtension;
    }

    public static void main(String[] args) {
        var recursiveFileExtensionPrinter = new RecursiveFileExtensionPrinter(
                "/Users/hwangjuhui/workspace",
                ".java"
        );
        recursiveFileExtensionPrinter.startPrint();
    }

    public void startPrint() {
        printRecursively(topLevelDirectory);
    }

    private void printRecursively(File filePath) {
        var files = filePath.listFiles();

        if (files == null || files.length == 0) {
            return;
        }

        for (File file : files) {
            if (isTargetFile(file)) {
                System.out.println();
                printAllFilesInDirectory(file.getParentFile());
                return;
            } else {
                printRecursively(file);
            }
        }
    }

    private void printAllFilesInDirectory(File fileParentPath) {
        // System.out.println(fileParentPath);

        var fileList = fileParentPath.listFiles();
        if (fileList == null || fileList.length == 0) {
            return;
        }

        for (File file : fileList) {
            if (isTargetFile(file)) {
                System.out.println("  " + file.getName());
            }
        }

    }

    private boolean isTargetFile(File file) {
        return !file.isDirectory() && file.getName().endsWith(fileExtension);
    }


}



