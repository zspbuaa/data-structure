package tree;

/**
 * Created by zsp on 2017/3/29.
 */
public class BiTreeNode {
    private BiTreeNode left;
    private BiTreeNode right;
    private BiTreeNode prev;
    private BiTreeNode next;
    private String value;

    public BiTreeNode getLeft() {
        return left;
    }

    public void setLeft(BiTreeNode left) {
        this.left = left;
    }

    public BiTreeNode getRight() {
        return right;
    }

    public void setRight(BiTreeNode right) {
        this.right = right;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public BiTreeNode getPrev() {
        return prev;
    }

    public void setPrev(BiTreeNode prev) {
        this.prev = prev;
    }

    public BiTreeNode getNext() {
        return next;
    }

    public void setNext(BiTreeNode next) {
        this.next = next;
    }
}
