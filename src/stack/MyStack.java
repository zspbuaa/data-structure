package stack;

import java.util.Stack;

/**
 * Created by zsp on 2017/3/28.
 */
public class MyStack {
    //进制转换，将ori转换为tar进制数
    //先求余数，再求商，然后将余数反序输出即可
    public static String DemiConversion(int ori, int tar){
        if(tar>10||ori<0||tar<0){
            return "error";
        }
        Stack<Integer> stack = new Stack<Integer>();
        while(ori>0){
            stack.push(ori%tar);
            ori /= tar;
        }
        StringBuffer sb = new StringBuffer();
        while(!stack.empty()){
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    //检测括号是否正确配对，只有小括号和中括号
    //若新来的和栈顶可以配对，则弹出栈顶，否则将新来的入栈
    public static Boolean PairMatch1(String str){
        Stack<Character> stack = new Stack<>();
        for(int i=0;i<str.length();++i){
            char c = str.charAt(i);
            if(stack.empty()){
                if(c==']'||c==')'){
                    return false;
                }else if(c=='['||c=='('){
                    stack.push(c);
                }else{
                    continue;
                }
            }else if(c==']'){
                if(stack.peek()=='['){
                    stack.pop();
                }else{
                    stack.push(c);
                }
            }else if(c==')'){
                if(stack.peek()=='('){
                    stack.pop();
                }else{
                    stack.push(c);
                }
            }else if(c=='['||c=='('){
                stack.push(c);
            }else{
                continue;
            }
        }
        return stack.empty();
    }
    public static Boolean PairMatch2(String str){
        Stack<Character> stack = new Stack<>();
        for(int i=0;i<str.length();++i){
            char c = str.charAt(i);
            switch(c){
                case '[':;
                case '(':
                    stack.push(c);
                    break;
                case ']':
                    if(stack.empty()){
                        return false;
                    }else if(stack.pop()!='['){
                        return false;
                    }else{
                        break;
                    }
                case ')':
                    if(stack.empty()){
                        return false;
                    }else if(stack.pop()!='('){
                        return false;
                    }else{
                        break;
                    }
                default:break;

            }
        }
        return stack.empty();
    }

    //迷宫问题，二维数组中，1表示路，0表示墙，in是入口坐标，out是出口坐标
    public static boolean canOut(int[][] maze,int height, int width, int[] in, int[] out, boolean useDefault){
        if(useDefault){
            out = new int[2];
            out[0]=8;out[1]=8;
            in = new int[2];
            in[0]=1;in[1]=1;
            int[][] m = {
                    {0,0,0,0,0,0,0,0,0,0},
                    {0,1,1,0,1,1,1,0,1,0},
                    {0,1,1,0,1,1,1,0,1,0},
                    {0,1,1,1,1,0,0,1,1,0},
                    {0,1,0,0,0,1,1,1,1,0},
                    {0,1,1,1,0,1,1,1,1,0},
                    {0,1,0,1,1,1,0,1,1,0},
                    {0,1,0,0,0,1,0,0,1,0},
                    {0,0,1,1,1,1,1,1,1,0},
                    {0,0,0,0,0,0,0,0,0,0}};
            maze = m;
            height = 10;
            width = 10;
        }
        int[][] foot = new int[height][width];
        for(int i=0;i<height;++i){
            for(int j=0;j<width;++j){
                foot[i][j] = 0;
            }
        }
        Stack<Cor> path = new Stack<>();
        Cor pos = new Cor(in[0],in[1]);
        foot[pos.x][pos.y]=1;
        path.push(pos);
        //当不为空且不是终点时
        while(!path.empty()&&(pos.x!=out[0]||pos.y!=out[1])){
            //测试右侧是否可以走（没走过且不是墙）
            if(foot[pos.x][pos.y+1]==0&&maze[pos.x][pos.y+1]==1){
                //当前位置加入路径
                path.push(pos);
                //标记已走过
                foot[pos.x][pos.y+1] = 1;
                //走到新位置
                pos = new Cor(pos.x,pos.y+1);
            }else if(foot[pos.x+1][pos.y]==0&&maze[pos.x+1][pos.y]==1){
                path.push(pos);
                foot[pos.x+1][pos.y]=1;
                pos = new Cor(pos.x+1,pos.y);
            }else if(foot[pos.x][pos.y-1]==0&&maze[pos.x][pos.y-1]==1){
                path.push(pos);
                foot[pos.x][pos.y-1]=1;
                pos = new Cor(pos.x,pos.y-1);
            }else if(foot[pos.x-1][pos.y]==0&&maze[pos.x-1][pos.y]==1){
                path.push(pos);
                foot[pos.x-1][pos.y]=1;
                pos = new Cor(pos.x-1,pos.y);
            }else{
                //若四个方向都不可以走，则后退一步
                pos = path.pop();
            }
        }
        if(path.empty()){
            return false;
        }else{
            Stack<Cor> p = new Stack<>();
            while(!path.empty()){
                p.push(path.pop());
            }
            while(!p.empty()){
                System.out.println(p.pop());
            }
            return true;
        }
    }

    //计算器，表达式求值
    public static double cal(String exp){
        double a=0.0;
        int[][] Priorities = new int[6][6];

        Priorities[ '+' ][ '-' ] = '>' ;
        Priorities[ '+' ][ '+' ] = '>' ;
        Priorities[ '+' ][ '*' ] = '<' ;
        Priorities[ '+' ][ '/' ] = '<' ;
        Priorities[ '+' ][ '(' ] = '<' ;
        Priorities[ '+' ][ ')' ] = '>' ;

        Priorities[ '-' ][ '-' ] = '>' ;
        Priorities[ '-' ][ '+' ] = '>' ;
        Priorities[ '-' ][ '*' ] = '<' ;
        Priorities[ '-' ][ '/' ] = '<' ;
        Priorities[ '-' ][ '(' ] = '<' ;
        Priorities[ '-' ][ ')' ] = '>' ;

        Priorities[ '*' ][ '-' ] = '>' ;
        Priorities[ '*' ][ '+' ] = '>' ;
        Priorities[ '*' ][ '*' ] = '>' ;
        Priorities[ '*' ][ '/' ] = '>' ;
        Priorities[ '*' ][ '(' ] = '<' ;
        Priorities[ '*' ][ ')' ] = '>' ;

        Priorities[ '/' ][ '-' ] = '>' ;
        Priorities[ '/' ][ '+' ] = '>' ;
        Priorities[ '/' ][ '*' ] = '>' ;
        Priorities[ '/' ][ '/' ] = '>' ;
        Priorities[ '/' ][ '(' ] = '<' ;
        Priorities[ '/' ][ ')' ] = '>' ;

        Priorities[ '(' ][ '+' ] = '<' ;
        Priorities[ '(' ][ '-' ] = '<' ;
        Priorities[ '(' ][ '*' ] = '<' ;
        Priorities[ '(' ][ '/' ] = '<' ;
        Priorities[ '(' ][ '(' ] = '<' ;
        Priorities[ '(' ][ ')' ] = '=' ;

        // 不存在操作符1是）和 操作符2 比较的情况
        // 因为 ） 会迫使之前的操作符进行运算。
        // 直到遇到匹配的“（”操作符，双双被消除掉
        // 所以下面的数据无意义。
        Priorities[ ')' ][ '+' ] = '?' ;
        Priorities[ ')' ][ '-' ] = '?' ;
        Priorities[ ')' ][ '*' ] = '?' ;
        Priorities[ ')' ][ '/' ] = '?' ;
        Priorities[ ')' ][ '(' ] = '?' ;
        Priorities[ ')' ][ ')' ] = '?' ;


        return a;
    }


}

