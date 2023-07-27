public class SplayTree extends MainTree {

    // Creates a new SplayTree instance with a root node containing the given value.
    public SplayTree(int value) {
        super(value);
    }

    // Splays the given node to the root of the tree.
    private void splay(Node node) {
        while (node != getRoot()) {
            Node parent = node.getParent();
            Node grandparent = parent.getParent();

            if (grandparent == null) {
                rotate(node);
            } else if (isZigZig(node, parent, grandparent)) {
                rotate(parent);
                rotate(node);
            } else {
                rotate(node);
                rotate(node);
            }
        }
    }

    //
    //  Determines if the given node is in a Zig-Zig case.
    private boolean isZigZig(Node node, Node parent, Node grandparent) {
        return (parent == grandparent.getLeftHeir() && node == parent.getLeftHeir())
                || (parent == grandparent.getRightHeir() && node == parent.getRightHeir());
    }


    // Rebalances the tree after inserting a new node.
    public void rebalanceInsert(Node insertedNode) {
        splay(insertedNode);
    }

    //  Rebalances the tree after accessing a node.
    public void rebalanceAccess(Node accessedNode) {
        if (accessedNode != null) {
            splay(accessedNode);
        }
    }

    // Rebalances the tree after deleting a node.
    public void rebalanceDelete(Node deletedNode) {
        if (deletedNode != getRoot()) {
            splay(deletedNode.getParent());
        }
    }

}

