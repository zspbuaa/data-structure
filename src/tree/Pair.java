package tree;

/**
 * Created by zsp on 2017/4/5.
 * 为了构建线索二叉树而定义的数据结构
 * head和tail分别指向一个链表的头结点和尾节点
 *
 */
public class Pair {
    private BiTreeNode head;
    private BiTreeNode tail;

    public BiTreeNode getHead() {
        return head;
    }

    public void setHead(BiTreeNode head) {
        this.head = head;
    }

    public BiTreeNode getTail() {
        return tail;
    }

    public void setTail(BiTreeNode tail) {
        this.tail = tail;
    }
}
