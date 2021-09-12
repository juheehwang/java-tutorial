package com.hjh.chapter01.treeStructure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Node implements Comparable<Node> {
    private final String nodeName;
    private final int depth;
    private final List<Node> children = new ArrayList<>();

    public Node(String pathName, int depth) {
        this.nodeName = pathName;
        this.depth = depth;
    }

    public String getNodeName() {
        return nodeName;
    }

    public int getDepth() {
        return depth;
    }

    public Node addChild(String childName, int depth) {
        var childNode = new Node(childName, depth + 1);
        children.add(childNode);

        return childNode;
    }

    public Node addChild(Node child) {
        children.add(child);
        return child;
    }

    public List<Node> getChildren() {
        return children;
    }

    public boolean checkFitChild(Node checkName, String criteria) {
        boolean check = false;

        for (int i = 0; i < checkName.getChildren().size(); i++) {
            System.out.println(checkName.getChildren());
            if (checkName.getChildren().get(i).toString().contains(criteria)) {
                check = true;
            }
        }


        return check;
    }

    @Override
    public String toString() {
        StringBuilder message = new StringBuilder();
        message.append("  ".repeat(Math.max(0, depth))).append(nodeName).append("\n");

        Collections.sort(children);

        for (var child : children) {
            // System.out.println("criteria" + criteria);

            message.append(child);

        }


        return message.toString();

    }

    @Override
    public int compareTo(Node o) {
        return nodeName.compareTo(o.nodeName);
    }


}
