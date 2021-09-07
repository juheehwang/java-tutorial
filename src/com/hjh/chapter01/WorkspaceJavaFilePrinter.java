package com.hjh.chapter01;

import java.io.File;

public class WorkspaceJavaFilePrinter {
    private final String targetExtension;
    private final String topLevelDirectory;

    public WorkspaceJavaFilePrinter(String topLevelDirectory) {
        this(topLevelDirectory, ".java");
    }

    public WorkspaceJavaFilePrinter(String topLevelDirectory, String targetExtension) {
        this.topLevelDirectory = topLevelDirectory;
        this.targetExtension = targetExtension;
    }

    public static void main(String[] args) {
        WorkspaceJavaFilePrinter workspaceJavaFilePrinter = new WorkspaceJavaFilePrinter("/Users/hwangjuhui/workspace");
        workspaceJavaFilePrinter.startPrintFiles();
    }

    public void startPrintFiles() {
        File topLevelPath = new File(topLevelDirectory);
        if (!topLevelPath.exists() || !topLevelPath.isDirectory()) {
            throw new IllegalArgumentException("TopLevelDirectory[" + topLevelDirectory + "] is not a valid directory.");
        }
        printRecursively(topLevelPath);
    }

    // Tail Call Optimization
    public void printRecursively(File filePath) {
        File[] files = filePath.listFiles();

        // escape condition
        if (files == null || files.length == 0) {
            return;
        }

        for (File newFile : files) {
            if (!newFile.isDirectory() && newFile.getName().endsWith(targetExtension)) {
                System.out.println(newFile.getName());
            } else {
                printRecursively(newFile);
            }
        }
    }

}
