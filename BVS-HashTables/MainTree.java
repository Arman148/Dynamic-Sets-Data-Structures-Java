
//  This is the constructor for the binary search tree, and initializes the root node with the given value.

public class MainTree {
    private Node root;

    public MainTree(int value) {
        root = new Node(value);
    }

    // This function returns the root node of the binary search tree.
    public Node getRoot() {
        return root;
    }

    //  This function takes a key and returns the node with the given key in the binary search tree. It also calls the
    //  rebalanceAccess function to balance the tree after the node is accessed.
    public Node search(int key) {
        Node nodeFound = searchHelper(root, key);
        rebalanceAccess(nodeFound);
        return nodeFound;
    }

    // This is a helper function for the search function, which  searches for the node with the given key in the BST.


    private Node searchHelper(Node node, int key) {
        if (key == node.getValue()) {
            return node;
        } else if (key < node.getValue()) {
            if (node.getLeftHeir() == null) {
                return node;
            }

            return searchHelper(node.getLeftHeir(), key);
        } else {
            if (node.getRightHeir() == null) {
                return node;
            }

            return searchHelper(node.getRightHeir(), key);
        }
    }


    // This function inserts a new node with the given key into the binary search tree.
    // It also calls the rebalanceInsert function to balance the tree after the node is inserted.

    public void insert(int key) {
        Node newNode = new Node(key);

        Node foundNode = searchHelper(root, key);

        if (foundNode != null && foundNode.getValue() == key) { // foundNode is going to be always false if I added that case to better visibility
            rebalanceAccess(foundNode);
            return;
        }

        if (foundNode == null) {
            root = newNode;
        } else if (key < foundNode.getValue()) {
            foundNode.setLeftHeir(newNode);
        } else {
            foundNode.setRightHeir(newNode);
        }

        newNode.setParent(foundNode);
        rebalanceInsert(newNode);
    }

   // This function removes the node with the given key from the binary search tree

    public int remove(int key) {
        Node foundNode = searchHelper(root, key);

        if (foundNode.getValue() == key) {
            return removeHelper(foundNode, key);
        } else {
            rebalanceAccess(foundNode);
            return key;
        }
    }

    // This is a helper function for the remove function, which removes the node with the given key from the binary search tree.
    public int removeHelper(Node node, int key) {
        if (hasNoChildren(node)) {
            removeLeafNode(node, key);
        } else if (hasOneChild(node)) {
            replaceNodeWithChildNode(node);
        } else {
            Node successor = getInorderSuccessor(node.getRightHeir());
            node.setValue(successor.getValue());
            removeHelper(successor, successor.getValue());
        }

        rebalanceDelete(node.getParent());
        return key;
    }

    // This function checks if the given node has no children.
    private boolean hasNoChildren(Node node) {
        return node.getLeftHeir() == null && node.getRightHeir() == null;
    }

    //  This function checks if the given node has one child.\

    private boolean hasOneChild(Node node) {
        return (node.getLeftHeir() != null) ^ (node.getRightHeir() != null);
    }

    // This function checks if the given node has two children.
    private boolean hasTwoChildren(Node node) {
        return node.getLeftHeir() != null && node.getRightHeir() != null;
    }


    //This is a helper function for the removeHelper function, which removes a leaf node from the binary search tree.
    private void removeLeafNode(Node node, int value) {
        Node parentNode = node.getParent();
        if (parentNode == null) {
            // Node is the root node
            if (node.getValue() == value) {
                root = null;
            }
            return;
        }

        Node childNode = (node.getLeftHeir() != null ? node.getLeftHeir() : node.getRightHeir());
        if (node.getValue() == value) {
            // Node to be removed is a leaf node
            if (parentNode.getLeftHeir() == node) {
                parentNode.setLeftHeir(childNode);
            } else {
                parentNode.setRightHeir(childNode);
            }
        }
    }

    // This is a helper function for the removeHelper function, which replaces a node with a child node in the binary search tree.
    private void replaceNodeWithChildNode(Node node) {
        Node child = node.getLeftHeir() != null ? node.getLeftHeir() : node.getRightHeir();

        if (child != null) {
            child.setParent(node.getParent());
        }

        if (node == root) {
            root = child;
            return;
        }

        Node parent = node.getParent();
        if (node == parent.getLeftHeir()) {
            parent.setLeftHeir(child);
        } else {
            parent.setRightHeir(child);
        }
    }


    //  This function returns the inorder successor of the given node in the binary search tree.
    private Node getInorderSuccessor(Node node) {
        for (Node successor = node.getRightHeir(); successor != null; successor = successor.getLeftHeir()) {
            node = successor;
        }

        return node;
    }

    // This function rotates the given node in the binary search tree to balance the tree.


    public void rotate(Node node) {
        Node parent = node.getParent();
        Node grandparent = parent.getParent();

        // Check whether node is a left child of its parent
        boolean nodeIsLeftChild = parent.getLeftHeir() == node;
        // Check whether parent is a left child of its grandparent
        boolean parentIsLeftChild = grandparent != null && grandparent.getLeftHeir() == parent;

        // Update the parent-child relationship between grandparent and node
        if (grandparent == null) {
            root = node;
            node.setParent(null);
        } else {
            relink(grandparent, node, parentIsLeftChild);
        }

        // Update the parent-child relationship between parent and node
        if (nodeIsLeftChild) {
            relink(parent, node.getRightHeir(), true);
            relink(node, parent, false);
        } else {
            relink(parent, node.getLeftHeir(), false);
            relink(node, parent, true);
        }
    }


    //  This function restructures the binary search tree to balance the tree.
    public Node restructure(Node a) {
        Node b = a.getParent();
        Node c = b.getParent();

        boolean aIsRightChild = a == b.getRightHeir();
        boolean bIsRightChild = b == c.getRightHeir();
        boolean shouldRotate = aIsRightChild == bIsRightChild;

        if (shouldRotate) {
            rotate(b);
        } else {
            rotate(a);
            rotate(a);
        }

        return shouldRotate ? b : a;
    }


    //  This is a helper function for the rotate and restructure functions, which relinks nodes in the binary search tree.
    private void relink(Node parent, Node child, boolean isLeftChild) {
        // Set the parent of the child node to the given parent node
        if (child != null) {
            child.setParent(parent);
        }

        // Set the child node as the left or right heir of the parent, depending on the value of isLeftChild
        if (isLeftChild) {
            parent.setLeftHeir(child);
        } else {
            parent.setRightHeir(child);
        }
    }

    // This function checks if the given node is internal (has at least one child).
    public boolean isInternal(Node node) {
        return hasOneChild(node) || hasTwoChildren(node);
    }


    // These functions are called after an insert or remove operation to rebalance the binary search tree.
    // There are different versions of these functions for different rebalancing strategies. they are going to be
    // overriden in AVL Tree, Splat Tree and can be overriden in other cases.
    public void rebalanceAccess(Node node) {}
    public void rebalanceInsert(Node node) {}
    public void rebalanceDelete(Node node) {}


    // Check if the current node is not null.
    // call inorder on the left and right subtree
    // Call the printNode function to print the current node.
    public void inorder(Node node) {
        if (node != null) {
            inorder(node.getLeftHeir());
            printNode(node);
            inorder(node.getRightHeir());
        }
    }

    private void printNode(Node node) {
        String nodeType = (node == getRoot()) ? "Root" : "Current";
        System.out.print(nodeType + " Node: " + node.getValue());
        printNodeInfo(node.getParent(), " Parent: ");
        printNodeInfo(node.getLeftHeir(), " Left Heir: ");
        printNodeInfo(node.getRightHeir(), " Right Heir: ");
        System.out.println();
    }

    private void printNodeInfo(Node node, String label) {
        if (node != null) {
            System.out.print(label + node.getValue());
        }
    }

}
