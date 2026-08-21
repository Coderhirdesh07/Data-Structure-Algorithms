package dsacourse851.DynamicProgramming;

import java.util.Arrays;

public class UnderstadingLisDP {
    public static void main(String[] args) {
        int[] arr = {2,100,4,50,8,10,12,14,15};
        int res = question_1(arr,9);
        System.out.println(res);

    }
    public static int question_1(int[] arr,int n){
        int[] dp = new int[n];
        Arrays.fill(dp,0);
        dp[0] = 1;
        for(int i=1;i<n;i++){
            dp[i] = 1;
            int curr = arr[i];
           for(int j=i-1;j>=0;j--){
               if(curr>arr[j]){
                   dp[i] = Math.max(dp[i],1 + dp[j]);
               }
           }
        }
        int max = -1;
        for(int i=0;i<n;i++){
            max = Math.max(dp[i],max);
        }
        return max;
    }
    public static int question_1_part2(int[] arr,int n){
        int[] dp = new int[n];
        Arrays.fill(dp,0);
        dp[0] = 1;
        for(int i=1;i<n;i++){
            dp[i] = 1;
            int curr = arr[i];
            for(int j=i-1;j>=0;j--){
                if(curr - arr[j] == 1){
                    dp[i] = Math.max(dp[i],1 + dp[j]);
                }
            }
        }
        int max = -1;
        for(int i=0;i<n;i++){
            max = Math.max(dp[i],max);
        }
        return max;
    }
    public static int question_1_part3(int[] arr,int n,int k){
        int[] dp = new int[n];
        Arrays.fill(dp,0);
        dp[0] = 1;
        for(int i=1;i<n;i++){
            dp[i] = 1;
            int curr = arr[i];
            for(int j=i-1;j>=0;j--){
                if(curr - arr[j]==k){
                    dp[i] = Math.max(dp[i],1 + dp[j]);
                }
            }
        }
        int max = -1;
        for(int i=0;i<n;i++){
            max = Math.max(dp[i],max);
        }
        return max;
    }
}
