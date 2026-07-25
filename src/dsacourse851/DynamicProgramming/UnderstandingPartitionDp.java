package dsacourse851.DynamicProgramming;

public class UnderstandingPartitionDp {
    public static void main(String[] args) {

    }
    public static int partition_dp(int[] arr,int m){
        int n = arr.length;
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = ((arr[1]+arr[0]) <=m)?3:2;

        for(int i=2;i<n;i++){
            int j = i;
            int sum = arr[j];
            int v = 0;
            while(j>0 && sum<=m){
                int g  = dp[j-1];
                v+=g;
                j--;
                sum +=arr[j];
            }
            dp[i] = v;
        }

        return dp[n-1];
    }
}
