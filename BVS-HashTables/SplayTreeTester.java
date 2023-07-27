public class SplayTreeTester {
    public void run() {
        SplayTree splayTree = new SplayTree(10);
        splayTree.insert(7);
        splayTree.insert(1);
        splayTree.insert(2);
        splayTree.insert(11);
        splayTree.insert(6);
        splayTree.insert(3);
        splayTree.insert(25);
        splayTree.insert(15);
        splayTree.inorder(splayTree.getRoot());
        System.out.println();
        splayTree.remove(11);
        splayTree.inorder(splayTree.getRoot());
        System.out.println();
        splayTree.search(6);
        splayTree.inorder(splayTree.getRoot());
        System.out.println();
        splayTree.search(3);
        splayTree.inorder(splayTree.getRoot());
    }
}
