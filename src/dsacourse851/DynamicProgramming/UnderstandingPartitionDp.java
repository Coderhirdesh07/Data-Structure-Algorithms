package dsacourse851.DynamicProgramming;

import java.util.Arrays;
import java.util.Scanner;

public class UnderstandingPartitionDp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        int[] arr = new int[n+1];
////        [5 10 5 10 1 1 8 2]
//        // 1 2 3 4 4
//        // 1 2 3 4 6
//        for(int i=1;i<=n;i++){
//            arr[i] = sc.nextInt();
//        }
        int ans = question_43("1011");
        System.out.println(ans);

    }
    public static int partition_dp(int[] arr,int m,int n){
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i=2;i<=n;i++){
            int j = i;
            int sum = arr[i];
            int v = 0;
            while(j>0 && sum<=m){
                int g  = dp[j-1];
                v+=g;
                j--;
                sum +=arr[j];
            }
            dp[i] = v;
        }

        return dp[n];
    }

    public static int partition_dp_follow_up(int[] arr,int m,int n,int k){
        int[][] dp = new int[n+1][k+1];
        for(int i=0;i<=n;i++) Arrays.fill(dp[i],Integer.MIN_VALUE);

        dp[0][0]  = 1;
        dp[1][1] = 1;


        for(int i=2;i<=n;i++){
            int part = 1;
            while(part<=k) {
              int j = i;
              int sum = arr[i];
              int v = 0;
              while(j>0 && sum<=m){
                  int g = dp[j-1][part-1];
                  if(g>0) v+=g;
                  j--;
                  sum+=arr[j];
              }
              if(v>0) dp[i][part] = v;
              part++;
            }
        }
        return dp[n][k];
    }
    // calculating max case
    public static int question_42(int[] arr,int n,int size){
        int[] dp = new int[n+1];

        Arrays.fill(dp,Integer.MIN_VALUE);
        dp[0] = 0;
        dp[2] = arr[1] - arr[2];

        for(int i=3;i<=n;i++){
           for(int j = i-2;j>=0;j--){
               dp[i] = Math.max(dp[i],dp[j] + arr[j+1] - arr[i]);
           }
        }

        return dp[n];
    }
    public static int question_42_follow_up(int[] arr,int n,int size,int k){
        int[][] dp = new int[n+1][k+1];
        Arrays.fill(dp,(int) 1e18);
        dp[0][0] = 0;
        dp[2][1] = arr[1] - arr[2];
        for(int i=3;i<=n;i++){
            for(int x = 1;x<=k;x++) {
                for (int j = i - size; j > 0; j--) {
                    dp[i][x] = Math.min(dp[i][x], arr[j + 1] - arr[i] + dp[j][x-1]);
                }
            }
        }
        return dp[n][k];
    }
    public static int convertToNumber(String x){
        int n = x.length();
        int original = 0;
        int product = 1;
        for(int i = n-1;i>=0;i--){
            original += product * (x.charAt(i)-'0');
            product*=2;
        }
        return original;
    }
    public static boolean powerOfFive(int number){
        if(number==1) return true;
        while(number>1){
            if(number%5!=0){
                return false;
            }
            else{
                number=number/5;
            }
        }
        return true;
    }
    public static int question_43(String s) {
        int n = s.length();
        int[] dp  = new int[n];
        Arrays.fill(dp,Integer.MAX_VALUE);

        dp[0] = (s.charAt(0)=='1' ? 1:0);
        dp[1] = (s.charAt(0)=='1'?2:0);

        for(int i=2;i<n;i++){
            for(int j=i-1;j>0;j--){
                String check = s.substring(j,i+1);
                int res = convertToNumber(check);
                if(s.charAt(i)!='0'){
                    boolean ans = powerOfFive(res);
                    if(ans){
                        dp[i] = Math.min(dp[i],1 + dp[j-1]);
                    }
                }
            }
        }

        // return (dp[n] == Integer.MAX_VALUE)?-1:dp[n];
        return dp[n-1];
    }
}
