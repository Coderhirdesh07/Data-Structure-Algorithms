package OnlineAssement.BinarySearch;


import java.util.HashMap;

public class BinarySearch {
    public static void main(String[] args) {
        System.out.println("Hello World");
    }
    public static int helper(int[] arr,int n){
        int low = 1;
        int high = n;
        int ans = n;
        while(low<=high){
            int mid = (low + high)/2;
            if(check(mid,arr,n)){
                ans = mid;
                low = mid+1;
            }
            else{
                if(check(mid-1,arr,n)==true){
                    ans = mid-1;
                }
                else{
                    high=mid-1;
                }
            }
        }
        return ans;
    }
    public static boolean check(int size,int[] arr,int n){
        HashMap<Integer,Integer> m1 = new HashMap<>();
        for(int i=0;i<n-size+1;i++){
            int res = rolling_hash(arr,i,i+size+1);
            m1.put(res,m1.getOrDefault(res,0)+1);
        }
        for(Integer it:m1.keySet()){
            if(m1.get(it)>1){
                return false;
            }
        }
        return true;
    }
}
