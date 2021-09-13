package com.hjh.chapter01.treeStructure;

public class Tree {
    private final Node root;

    public Tree(final String rootName) {
        if (rootName == null || rootName.isEmpty()) {
            throw new IllegalArgumentException("root node name cannot be null");
        }
        this.root = new Node(rootName, 0);
    }

    public Node getRoot() {
        return root;
    }

    public Node addNode(final Node parent, final String nodeName) {
        return parent.addChild(nodeName, parent.getDepth());
    }

    public Node addNode(final Node parent, final Node child) {
        return parent.addChild(child);
    }

    public Node removeNode(final Node parent, final String nodeName) {
        var children = parent.getChildren();
        for (var i = 0; i < children.size(); i++) {
            var child = children.get(i);
            if (child.getNodeName().equals(nodeName)) {
                children.remove(i);
                return child;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return root.toString();
    }
}
