public class Node {
    private Node parent;
    private Node leftHeir;
    private Node rightHeir;
    private int heightvalue = 0;
    private int value;


    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getLeftHeir() {
        return leftHeir;
    }

    public void setLeftHeir(Node leftHeir) {
        this.leftHeir = leftHeir;
    }

    public Node getRightHeir() {
        return rightHeir;
    }

    public void setRightHeir(Node rightHeir) {
        this.rightHeir = rightHeir;
    }

    public int getHeightvalue() {
        return heightvalue;
    }

    public void setHeightvalue(int heightvalue) {
        this.heightvalue = heightvalue;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }


    public Node(int value) {
        this.value = value;
    }

}