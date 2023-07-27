public class AVLTree extends MainTree{

    public AVLTree(int value) {
        super(value);
    }

    // Returns the height of a given node in the AVLTree
    public int getHeight(Node node) {
        return node == null ? 0 : node.getHeightvalue();
    }

    // Recomputes the height of a given node in the AVLTree
    public void updateHeight(Node node) {
        node.setHeightvalue(1 + Math.max(getHeight(node.getLeftHeir()), getHeight(node.getRightHeir())));
    }

    // Checks if a given node in the AVLTree is balanced
    public boolean isNodeBalanced(Node node) {
        return Math.abs(getHeight(node.getLeftHeir()) - getHeight(node.getRightHeir())) <= 1;
    }

    // Returns the child node with the greater height of a given node in the AVLTree
    public Node getTallerChild(Node node) {
        Node left = node.getLeftHeir();
        Node right = node.getRightHeir();

        return getHeight(left) > getHeight(right) ? left : right;
    }

    // Rebalances a given node in the AVLTree by performing rotations as necessary
    public void rebalanceNode(Node node) {
        while (node != null) {
            updateHeight(node);
            if (!isNodeBalanced(node)) {
                node = restructure(getTallerChild(getTallerChild(node)));
                updateHeight(node.getLeftHeir());
                updateHeight(node.getRightHeir());
            }
            node = node.getParent();
        }
    }

    // Rebalances the AVLTree after an insertion
    public void rebalanceInsertion(Node node) {
        rebalanceNode(node);
    }

    // Rebalances the AVLTree after a deletion
    public void rebalanceDeletion(Node node) {
        if (node != getRoot()) {
            rebalanceNode(node.getParent());
        }
    }
}


