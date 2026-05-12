package DSA;



public class BinaryTrees {


    public static void main(String[] args) {
     Node root = new Node(1);
        inOrderTraversal(root);
        preOrderTraversal(root);
        postOrderTraversal(root);
//        levelOrderTraversal(root);
        verticalOrderTraversal(root);
        zigzagOrderTraversal(root);

    }
    public static void inOrderTraversal(Node root){
        if(root==null){
            return;
        }
        inOrderTraversal(root.left);
        System.out.println(root.data);
        inOrderTraversal(root.right);
    }
    public static void preOrderTraversal(Node root){
        if(root==null){
            return;
        }
        System.out.println(root.data);
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
    }
    public static void postOrderTraversal(Node root){
        if(root==null){
            return;
        }
        postOrderTraversal(root.left);
        postOrderTraversal(root.right);
        System.out.println(root.data);
    }

//    public static void levelOrderTraversal(Node root){
//
//
//    }
    public static void verticalOrderTraversal(Node root){}
    public static void zigzagOrderTraversal(Node root){}

}
 class Node {
    int data;
    Node left;
    Node right;
    public Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}
