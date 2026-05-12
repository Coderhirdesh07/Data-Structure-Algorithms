package DSA;

import java.util.Arrays;
import java.util.Scanner;

public class DynamicProgramming {

    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        int k = sc.nextInt();
//        int[] arr = new int[n];
//        for(int i=0;i<n;i++){
//            arr[i] = sc.nextInt();
//        }
//        int[] dp = new int[n+1];
//        int[] dp2 = new int[n+1];
//        sheet.Arrays.fill(dp,-1);
//        sheet.Arrays.fill(dp2,-1);
//        int ans = helper(0,arr,n,dp);
//        int res = helper2(0,arr,n,k,dp2);
//        System.out.println(ans);

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] arr = new int[n][3];
        for(int i=0;i<n;i++){
            for(int j=0;j<3;j++){
                arr[i][j] = sc.nextInt();
            }
        }
        int[][] dp3 = new int[n+1][3];
        for(int i=0;i<=n;i++){
            Arrays.fill(dp3[i],-1);
        }
        int ans = helper3(0,-1,arr,n,dp3);
        System.out.println(ans);

    }

    public static int helper(int index,int[] arr,int n,int[] dp){
        if(index == n-1){
            return 0;
        }
        if(dp[index]!=-1) return dp[index];
        int jump1 = Integer.MAX_VALUE;
        int jump2 = Integer.MAX_VALUE;
        if(index+1 < n) {
             jump1 = helper(index + 1, arr, n,dp) + Math.abs(arr[index + 1] - arr[index]);
        }

        if(index+2 < n)
         jump2 = helper(index+2,arr,n,dp) + Math.abs(arr[index+2]-arr[index]);
         int ans = Math.min(jump1,jump2);
         dp[index]=ans;
        return ans;
    }
    public static int helper2(int index,int[] arr,int n,int k,int[] dp2){
        if(index==n-1) return 0;
        if(dp2[index]!=-1) return dp2[index];
        int jump1 = Integer.MAX_VALUE;
        for(int i=1;i<=k;i++){
            if(index+i<n) {
                jump1 = Math.min(jump1, Math.abs(arr[index+i]-arr[index]) + helper2(index + i, arr, n, k,dp2));
            }
        }
        dp2[index] = jump1;
        return jump1;
    }
    public static int helper3(int index,int last,int[][] nums,int n,int[][] dp3){
        if(index==n){
            return 0;
        }
        if(dp3[index][last]!=-1) return dp3[index][last];

        if(last==-1){
            int option1 = helper3(0,0,nums,n,dp3) + nums[index][0];
            int option2 = helper3(1,1,nums,n,dp3) + nums[index][1];
            int option3 = helper3(2,2,nums,n,dp3) + nums[index][2];
            int ans = Math.max(option1,Math.max(option2,option3));

            return ans;
        }
        int ans = 0;
        for(int i=0;i<3;i++){
            if(last!=i) {
                int current = helper3(index+1, i, nums, n,dp3) + nums[index][i];
                ans = Math.max(current,ans);
            }
        }
        dp3[index][last] = ans;
        return ans;

    }
    // knapsack problem
//    public static int helper4(int row,,int[][] array,int w,int[][] dp5){
//      if(row==array.length){
//          return 0;
//      }
//      if(dp5[row][w]!=-1) return dp5[row][w];
//      int option1 = 0;
//
//      if(w>=array[row][0]) {
//          int option1 = helper4(row + 1, array, w - array[row][0],dp5) + array[row][1];
//      }
//      int option2 = helper4(row+1,array,w,dp5);
//      int ans = Math.max((option1,option2));
//
//       dp5[row][w] = ans;
//       return ans;
//
//    }
}
