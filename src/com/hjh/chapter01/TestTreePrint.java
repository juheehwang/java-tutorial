package com.hjh.chapter01;

import com.hjh.chapter01.treeStructure.Tree;

public class TestTreePrint {
    public static void main(String[] args) {
        var tree = new Tree("root");
        var rootNode = tree.getRoot();

        var node2 = tree.addNode(rootNode, "node2");
        var node21 = tree.addNode(node2, "node21");
        var node22 = tree.addNode(node2, "node22");

        var node1 = tree.addNode(rootNode, "node1");
        var node11 = tree.addNode(node1, "node11");
        var node12 = tree.addNode(node1, "node12");

        var node3 = tree.addNode(rootNode, "node3");

        //    System.out.println(node1.checkFitChild(node1, "t"));
        System.out.println(tree);


    }
}
