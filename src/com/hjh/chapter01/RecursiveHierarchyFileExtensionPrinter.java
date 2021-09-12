package com.hjh.chapter01;

import com.hjh.chapter01.treeStructure.Node;
import com.hjh.chapter01.treeStructure.Tree;

import java.io.File;

public class RecursiveHierarchyFileExtensionPrinter {
    private final File topLevelDirectory;
    private final String fileExtension;
    private final Tree tree;

    public RecursiveHierarchyFileExtensionPrinter(String topDirectoryPath, String fileExtension) {
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

        this.tree = new Tree(topLevelDirectory.getPath());
        this.fileExtension = fileExtension;
    }

    public static void main(String[] args) {
        var recursiveHierarchyFileExtensionPrinter = new RecursiveHierarchyFileExtensionPrinter(
                "/Users/hwangjuhui/workspace",
                ".java");
        recursiveHierarchyFileExtensionPrinter.startPrint();

    }

    public void startPrint() {
        var rootNode = tree.getRoot();//루트 생성

        createNodeRecursively(topLevelDirectory, rootNode);
        System.out.println(tree);

    }

    private boolean createNodeRecursively(File filePath, Node newNode) {
        var files = filePath.listFiles();
        if (files == null || files.length == 0) {
            return false;
        }

        boolean isAdded = false;
        for (File file : files) {
            var fileName = file.getName();

            //도움 받은 부분
            if (file.isDirectory()) {
                var childNode = new Node(fileName, newNode.getDepth() + 1);
                boolean result = createNodeRecursively(file, childNode);
                if (result) {
                    isAdded = true;
                    tree.addNode(newNode, childNode);
                }
            }
            if (!file.isDirectory() && file.getName().endsWith(fileExtension)) {
                tree.addNode(newNode, fileName);
                isAdded = true;
            }
        }
        return isAdded;
    }
}
