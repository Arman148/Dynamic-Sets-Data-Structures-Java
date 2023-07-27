public class AVLTreeTester {
    public void run() {
        AVLTree tree = new AVLTree(10);
        tree.insert(5);
        tree.insert(3);
        tree.insert(2);
        tree.insert(1);
        tree.insert(4);
        tree.insert(11);
        tree.insert(25);
        tree.insert(15);
        tree.inorder(tree.getRoot());
        System.out.println();
        tree.remove(25);
        tree.inorder(tree.getRoot());
    }
}