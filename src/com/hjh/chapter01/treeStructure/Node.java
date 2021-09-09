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

    public int getDepth() {
        return depth;
    }

    public Node addNode(String childName, int depth) {
        var childNode = new Node(childName, depth + 1);
        children.add(childNode);

        return childNode;
    }

    public List<Node> getChildren() {
        return children;
    }

    public boolean checkEmptyChild(Node checkName) {
        boolean check = false;

        if (!checkName.getChildren().isEmpty()) check = true;

        return check;
    }

    @Override
    public String toString() {
        StringBuilder message = new StringBuilder();
        message.append("  ".repeat(Math.max(0, depth))).append(nodeName).append("\n");

        Collections.sort(children);
        for (var child : children) {
            message.append(child.toString());
        }

        return message.toString();
    }

    @Override
    public int compareTo(Node o) {
        return nodeName.compareTo(o.nodeName);
    }


}
