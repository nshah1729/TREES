import java.util.*;

public class Custom {
    static class Node{
        int data;
        Node left;
        Node right;

        public Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }
    static class Pair{
        Node node;
        int state;

        public Pair(Node node, int state) {
            this.node = node;
            this.state = state;
        }
    }
    public static void display(Node node){
        if(node==null)return;

        String str="";
        str+=node.left==null?".":node.left.data;
        str+="->"+node.data+"<-";
        str+=node.right==null?".":node.right.data;
        System.out.println(str);

        display(node.left);
        display(node.right);
    }

    public static int size(Node node){//Number of nodes
        if(node==null)return 0;
        int s=1;
        s+=size(node.left)+size(node.right);
        return s;
    }

    public static int max(Node node){
        if(node==null)return Integer.MIN_VALUE;
        int m=node.data;
        m=Math.max(m,Math.max(max(node.left),max(node.right)));
        return m;
    }

    public static int height(Node node){
        if(node==null)return 0;
        return Math.max(height(node.left),height(node.right))+1;
    }

    public static int sum(Node node){
        if(node==null)return 0;
      int s=0;
    s+=node.data+sum(node.left)+sum(node.right);
    return s;
    }

    public static void preOrder(Node node){
        if(node==null)return;
        System.out.println(node.data);
        preOrder(node.left);
        preOrder(node.right);
    }
    public static void inOrder(Node node){
        if(node==null)return;

        preOrder(node.left);
        System.out.println(node.data);
        preOrder(node.right);
    }
    public static void postOrder(Node node){
        if(node==null)return;

        preOrder(node.left);
        preOrder(node.right);
        System.out.println(node.data);
    }
    public static void traverse(Node node){
        if(node==null)return;

        System.out.println("Pre"+node.data);
        traverse(node.left);
        System.out.println("In"+node.data);
        traverse(node.right);
        System.out.println(node.data+"Post");
    }

    public static void LevelOrder(Node node){
        Queue<Node> q=new ArrayDeque<>();
        q.add(node);
        while(!q.isEmpty()){
            Node rem=q.poll();
            System.out.print(rem.data+" ");
            if(rem.left!=null){
                q.add(rem.left);
            }
            if(rem.right!=null){
                q.add(rem.right);
            }
            System.out.println();
        }
    }

    public static void Iterative(Node node){
        Stack<Pair> st=new Stack<>();
        st.add(new Pair(node,1));
        String pre="",post="",in="";
        while(st.size()>0){
            if(st.peek().state==1){
                pre+=st.peek().node.data+" ";
                st.peek().state++;
                if(st.peek().node.left!=null)st.add(new Pair(st.peek().node.left,1));
            }else if(st.peek().state==2){
                in+=st.peek().node.data+" ";
                st.peek().state++;
                if(st.peek().node.right!=null)st.add(new Pair(st.peek().node.right,1));
            }else{
                post+=st.peek().node.data+" ";
                st.pop();
            }
        }
        System.out.println("pre:"+pre);
        System.out.println("In:"+in);
        System.out.println("Post:" +post);
    }

    static ArrayList<Integer> path=new ArrayList<>();
    public static boolean nodeToRootPath(Node node,int t){
        if(node==null)return false;
        if(node.data==t){
            path.add(node.data);
            return true;
        }
        boolean filc=nodeToRootPath(node.left,t);
        if(filc){
            path.add(node.data);
            return true;
        }
        boolean firc=nodeToRootPath(node.right,t);
        if(firc){
            path.add(node.data);
            return true;
        }
        return false;
    }

    public static void printKDown(Node node,int k){
        if(node==null||k<0)return;
        if(k==0){
            System.out.println(node.data);
        }
        printKDown(node.left,k-1);
        printKDown(node.right,k-1);
    }

    public static void printSingleChildNodes(Node node){
        if(node==null)return;
        if(node.left==null&&node.right!=null){
            System.out.println(node.right.data);
        }else if(node.left!=null&&node.right==null){
            System.out.println(node.left.data);
        }
        printSingleChildNodes(node.left);
        printSingleChildNodes(node.right);
    }

    public static void pathToLeafFromRoot(Node node,String path,int sum,int lo,int hi){
        if(node==null){
            if(sum>=lo&&sum<=hi){
                System.out.println(path);
            }
            return;
        }
        pathToLeafFromRoot(node.left,path+" "+node.data,sum+node.data,lo,hi);
        pathToLeafFromRoot(node.right,path+" "+node.data,sum+node.data,lo,hi);
    }

    public static void transformToLeftClonedTree(Node node){
        if(node==null)return;

        if(node.left!=null){
            Node clone =new Node(node.data,node.left,node.right);
            node.left=clone;
        }
        transformToLeftClonedTree(node.left);
        transformToLeftClonedTree(node.right);

    }
    public static void main(String[] args) {
        Integer[] arr= {50,25,12,null,null,37,30,null,null,null,75,62,null,70,null,null,87,null,null};
        Stack<Pair> st=new Stack<>();
        Node root=new Node(50,null,null);
        Pair rp=new Pair(root,1);
        st.add(rp);
        int idx=0;
        while(!st.isEmpty()){
            Pair top=st.peek();
            if(top.state==1){
                idx++;
                if(arr[idx]!=null){
                    Node ln=new Node(arr[idx],null,null);
                    top.node.left=ln;
                    Pair np=new Pair(ln,1);
                    st.push(np);
                }else{
                    top.node.left=null;
                }
                top.state++;
            }else if(top.state==2){
                idx++;
                if(arr[idx]!=null){
                    Node rn=new Node(arr[idx],null,null);
                    top.node.right=rn;
                    Pair np=new Pair(rn,1);
                    st.push(np);
                }else{
                    top.node.right=null;
                }
                top.state++;
            }else{
                st.pop();
            }
        }
//        display(root);
//        System.out.println("size: "+size(root));
//        System.out.println("max: "+max(root));
//        System.out.println("sum: "+sum(root));
//        System.out.println("height: "+height(root));
//        LevelOrder(root);
//        Iterative(root);
//        System.out.println(nodeToRootPath(root,87));
//        System.out.println(path);
//        printKDown(root,2);
//        printSingleChildNodes(root);
//        pathToLeafFromRoot(root,"",0,150,250);
        display(root);
        System.out.println();
        transformToLeftClonedTree(root);
        display(root);
    }
}
