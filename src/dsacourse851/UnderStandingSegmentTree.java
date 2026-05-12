package dsacourse851;

public class UnderStandingSegmentTree {
    public static void main(String[] args) {
        int[] arr = {1,23,4,5,6,7,8};
        SegmentTree s1 = new SegmentTree(arr);

    }
   static class SegmentTree{

        static class Node{
            int data;
            int startInterval;
            int endInterval;
            Node left;
            Node right;
            public Node(int startInterval,int endInterval){
                this.startInterval = startInterval;
                this.endInterval = endInterval;
            }
        }
        Node root;
        public SegmentTree(int[] arr){
            this.root = constructTree(arr,0,arr.length-1);
        }
        public static Node constructTree(int[] arr,int startInd,int endInd){
            if(startInd == endInd){
               Node leaf = new Node(startInd,endInd);
               leaf.data = arr[startInd];
               return leaf;
            }

            Node newRoot = new Node(startInd,endInd);

            int mid = (startInd + endInd) /2;

            Node left = constructTree(arr,startInd,mid);
            Node right = constructTree(arr,mid+1,endInd);
            newRoot.left  = left;
            newRoot.right = right;
            newRoot.data = left.data + right.data;
            return newRoot;

        }
    }
}
