package OnlineAssement.BinarySearch;


import java.util.HashMap;

public class BinarySearch {
    public static void main(String[] args) {
        System.out.println("Hello World");
    }
//    public static int helper(int[] arr,int n){
//        int low = 1;
//        int high = n;
//        int ans = n;
//        while(low<=high){
//            int mid = (low + high)/2;
//            if(check(mid,arr,n)){
//                ans = mid;
//                low = mid+1;
//            }
//            else{
//                if(check(mid-1,arr,n)==true){
//                    ans = mid-1;
//                }
//                else{
//                    high=mid-1;
//                }
//            }
//        }
//        return ans;
//    }
//    public static boolean check(int size,int[] arr,int n){
//        HashMap<Integer,Integer> m1 = new HashMap<>();
//        for(int i=0;i<n-size+1;i++){
//            int res = rolling_hash(arr,i,i+size+1);
//            m1.put(res,m1.getOrDefault(res,0)+1);
//        }
//        for(Integer it:m1.keySet()){
//            if(m1.get(it)>1){
//                return false;
//            }
//        }
//        return true;
//    }

    public static int question_deshaw(int[][] matrix,int n,int m){
        int max = Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                max = Math.max(max,matrix[i][j]);
            }
        }
        int ans = max;
        for(int i=0;i<=max;i++){
            if(isPossible(i,matrix,n,m)){
                ans = i;
            }
        }

        return ans;
    }
    public static boolean isPossible(int target,int[][] matrix,int n,int m){

        int[][] prefix = new int[n][m];
        int[][] suffix = new int[n][m];
        for(int i=0;i<n;i++){
            prefix[i][0]  = matrix[i][0];
            suffix[i][m-1] = matrix[i][m-1];
        }

        for(int i=0;i<n;i++){
            for(int j=1;j<n;j++){
             prefix[i][j] = Math.max(prefix[i][j-1],matrix[i][j]);
            }
        }

        for(int i=0;i<n;i++){
            for(int j=n-2;j>=0;j--){
                suffix[i][j] = Math.max(suffix[i][j+1],matrix[i][j]);
            }
        }

        int min = Integer.MAX_VALUE;
        for(int i=1;i<m;i++){
            int max = Integer.MIN_VALUE;
            for(int j=0;j<n-1;j++){
                int val1 = prefix[j][i-1];
                int val2 = suffix[j][i+1];
                max = Math.max(max,Math.max(val1,val2));
                min = Math.min(max,min);
            }
        }

        return (min==target);
    }
}
