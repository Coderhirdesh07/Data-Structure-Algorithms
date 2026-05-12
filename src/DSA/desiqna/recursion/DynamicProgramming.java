package DSA.desiqna.recursion;

import java.util.*;

public class DynamicProgramming {
    public static void main(String[] args) {
//         We are given an array of integers(a[n]) .
//         We are given multiple queries of the form : (1, i)
//         which means we need to output the sum of all numbers from index- ‘1’ to index ‘i’ of the array.
//         int n = 4;
//         int[] arr = {6,7,3,2,2};
//        int[] arr1 = {2,4,6,7,8};
//        int[] arr2 = {1 ,  5  , 3 , 21234};
//        int[] arr3 = {-4509 , 200 , 3 , 40};
//         int[] query = {0,3,4,2};
//         int[] dp = new int[n];
//         Arrays.fill(dp,0);
////         for(int i=0;i<query.length;i++){
////            dp_problem_1(query[i],arr,n,dp);
////             System.out.println(dp[query[i]]);
////         }
////        int ans = dp_problem_3(arr2,arr3,4,dp);
////        System.out.println(ans);
//        int[] newArr  = {-1,3,-1,-1,5,-1,4,-1};
//       boolean result = oa_question_amazon(newArr,newArr.length);
//        System.out.println(result);
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        int[] dp = new int[n];
        dp[0] = 0;
        dp[1] = Math.abs(arr[0] - arr[1]);
        for (int i = 2; i < n; i++) {

            int option1 = dp[i - 1] + Math.abs(arr[i] - arr[i - 1]);
            int option2 = dp[i - 2] + Math.abs(arr[i] - arr[i - 2]);
            dp[i] = Math.min(option1, option2);
        }
        System.out.println(dp[n - 1]);


    }

    public static int helper(int ind, int[] arr, int[] dp, int n) {
        if (ind >= n) return (int) 1e9;
        if (dp[ind] != -1) return dp[ind];
        int option1 = (int) 1e9;
        if (ind < n - 1) option1 = Math.abs(arr[ind] - arr[ind + 1]) + helper(ind + 1, arr, dp, n);
        int option2 = (int) 1e9;
        if (ind < n - 2) {
            option2 = Math.abs(arr[ind] - arr[ind + 2]) + helper(ind + 2, arr, dp, n);
        }

        return dp[ind] = Math.min(option1, option2);
    }

    public static void dp_problem_1(int ind, int[] arr, int n, int[] dp) {
        for (int i = 0; i <= ind; i++) {
            if (i == 0) dp[0] = arr[0];
            else dp[i] = arr[i] + dp[i - 1];
        }

    }

    public static int dp_problem_2(int[] arr, int n, int[] dp) {
        //  {2,4,6,7,8}
        if (n == 2) return Math.max(arr[0], arr[1]);
        dp[0] = arr[0];
        dp[1] = arr[1];
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], arr[i] + dp[i - 2]);
        }
        return dp[n - 1];

    }

    public static int dp_problem_3(int[] arr1, int[] arr2, int n, int[] dp) {
        dp[0] = Math.max(arr1[0], arr2[0]);
        dp[1] = Math.max(arr1[1], Math.max(arr2[1], dp[0]));


        //      1 ,  5  , 3 , 21234
        //  -4509 , 200 , 3 , 40
        for (int i = 2; i < n; i++) {
            int x = dp[i - 1];
            int y = dp[i - 2] + arr2[i];
            int z = dp[i - 2] + arr1[i];
            dp[i] = Math.max(x, Math.max(y, z));
        }
        return dp[n - 1];
    }
}


