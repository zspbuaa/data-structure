import stack.MyStack;
import tree.BiTree;
import tree.BiTreeNode;

/**
 * Created by zsp on 2017/3/28.
 */
public class Test {
    public static void main(String[] args){
        BiTreeTest();
    }








    
    public static void BiTreeTest(){
        //数据结构p129图6.9
        String v1 = "- + a # # * b # # - c # # d # # / e # # f # #";
        String v2 = "A B C # # D E # G # # F # # #";
        BiTree t = new BiTree();
        t.Creat(v2);
        BiTreeNode node = t.RecuTra(3);
        while(node!=null){
            System.out.print(node.getValue());
            node=node.getNext();
        }
    }
}
