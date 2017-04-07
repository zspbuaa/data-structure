package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by zsp on 2017/3/29.
 */
public class BiTree {
    private BiTreeNode root;
    //一个字符串对应一个节点，以空格分割，若无这个节点，则输入#
    //输入一行字符，先输入根节点n1的值，再输入根节点的左节点n2，若左节点n2不为#，则以这个左节点n2为根节点，
    //再输入其左节点n3，若n3为#，则输入n2的右节点，然后以n2的右节点为根节点继续进行
    public BiTree Creat(String input){
        if(input.length()<1){
            return this;
        }
        String[] t = input.split(" ");
        List<String> v = new ArrayList<>();
        for(int i=0;i<t.length;++i){
            //连续空格会造成空字符串
            while(i<t.length&&t[i].length()==0){
                ++i;
            }
            v.add(t[i]);
        }
        root = subCreat(v);
        return this;
    }
    private BiTreeNode subCreat(List<String> input){
        BiTreeNode root = null;
        if(input.size()>0&&!input.get(0).equalsIgnoreCase("#")){
            root = new BiTreeNode();
            root.setValue(input.get(0));
            input.remove(0);
            root.setLeft(subCreat(input));
            root.setRight(subCreat(input));
        }else if(input.size()>0&&input.get(0).equalsIgnoreCase("#")){
            input.remove(0);
        }
        return root;
    }

    public BiTreeNode RecuTra(int type){//1是先序遍历，2是中序遍历，3是后序遍历
        StringBuffer sb = new StringBuffer("");
        Pair p=null;
        if(type==1) {
            p=subFirstTra(sb, root);
        }else if(type==2){
            p=subMidTra(sb,root);
        }else if (type==3){
            p=subLastTra(sb,root);
        }
        System.out.println(sb.toString());
        if(p==null){
            return null;
        }else {
            return p.getHead();
        }
    }
    //为了生成线索二叉树，每次遍历都要返回链表的头和尾
    private Pair subFirstTra(StringBuffer sb, BiTreeNode root){
        Pair p = null;
        if(root!=null){
            //输出根节点
            sb.append(root.getValue());
            //遍历左子树
            Pair t1 = subFirstTra(sb,root.getLeft());
            //遍历右子树
            Pair t2 = subFirstTra(sb,root.getRight());

//            //生成线索二叉树
//            p = new Pair();
//            p.setHead(root);
//            p.setTail(root);//这两句可以保证若返回值不为空，则head和tail也不为空
//            //将三段链表拼接起来
//            BiTreeNode now = p.getTail();
//            if(t1!=null) {
//                now.setNext(t1.getHead());
//                now = t1.getTail();
//            }
//            if(t2!=null) {
//                now.setNext(t2.getHead());
//                now = t2.getTail();
//            }
//            p.setTail(now);


            Pair p3 = new Pair();
            p3.setHead(root);
            p3.setTail(root);
            p=contactList(contactList(p3,t1),t2);
        }
        return p;
    }
    private Pair subMidTra(StringBuffer sb, BiTreeNode root){
        Pair p = null;
        if(root!=null){
            Pair p1 = subMidTra(sb,root.getLeft());
            sb.append(root.getValue());
            Pair p2 = subMidTra(sb,root.getRight());
//笨办法
//            p = new Pair();
//            BiTreeNode now;
//            if(p1!=null){
//                p.setHead(p1.getHead());
//                p.setTail(p1.getTail());
//                now = p1.getTail();
//                now.setNext(root);
//                now = root;
//            }else{
//                p.setHead(root);
//                p.setTail(root);
//                now=root;
//            }
//            if(p2!=null){
//                now.setNext(p2.getHead());
//                p.setTail(p2.getTail());
//            }

            Pair p3 = new Pair();
            p3.setHead(root);
            p3.setTail(root);
            p=contactList(contactList(p1,p3),p2);
        }
        return p;
    }
    private Pair subLastTra(StringBuffer sb, BiTreeNode root){
        Pair p = null;
        if(root!=null){
            Pair p1 = subLastTra(sb,root.getLeft());
            Pair p2 = subLastTra(sb,root.getRight());
            sb.append(root.getValue());
            Pair p3 = new Pair();
            p3.setTail(root);
            p3.setHead(root);
            p = contactList(contactList(p1,p2),p3);
        }
        return p;
    }
    private Pair contactList(Pair p1,Pair p2){
        if(p1==null&&p2==null){
            return null;
        }
        Pair p=new Pair();
        if(p1!=null){
            p.setHead(p1.getHead());
            p.setTail(p1.getTail());
            if(p2!=null){
                p.getTail().setNext(p2.getHead());
                p.setTail(p2.getTail());
            }
        }else{
            p.setHead(p2.getHead());
            p.setTail(p2.getTail());
        }
        return p;
    }

    public String StackFirstTra(){
        StringBuffer sb = new StringBuffer();
        Stack<BiTreeNode> nodes = new Stack<>();
        if(root==null){
            return "";
        }
        BiTreeNode node;
        nodes.push(root);
        while(!nodes.empty()){
            //栈中存储的是未输出子树的根节点，直接输出
            node = nodes.pop();
            sb.append(node.getValue());
            //因为要先输出左边的值，所以先将右边压栈，再将左边压栈
            //弹出一个节点，同时将其子节点入栈，右边先入栈就可以保证先输出左边
            if(node.getRight()!=null){
                nodes.push(node.getRight());
            }
            if(node.getLeft()!=null){
                nodes.push(node.getLeft());
            }
        }
        return sb.toString();
    }

    public String StackMidTra(){
        StringBuffer sb = new StringBuffer();
        Stack<BiTreeNode> nodes = new Stack<>();
        if(root==null){
            return "";
        }
        //栈顶节点是当前需要遍历的子树的根节点
        nodes.push(root);
        //pre是在在栈顶之前需要遍历的子树的根节点
        //因为是中序遍历，所以首先应遍历其左子树
        BiTreeNode pre = nodes.peek().getLeft();

        while(pre!=null||!nodes.empty()){
            //如果其左孩子不为空，则应先遍历其左子树
            while(pre!=null){
                nodes.push(pre);
                pre = pre.getLeft();
            }
            //当栈顶没有左孩子的时候，可以输出栈顶
            BiTreeNode top = nodes.pop();
            sb.append(top.getValue());
            //刚刚输出的栈顶的右子树应该在新栈顶之前输出
            pre = top.getRight();
        }
        return sb.toString();
    }
}
