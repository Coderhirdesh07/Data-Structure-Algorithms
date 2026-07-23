package dsacourse851.DynamicProgramming;

import java.util.Arrays;
import java.util.Scanner;
public class UnderstandingSubarrayDp {
    public static void main(String[] args) {
        int[] arr = {5,5,8};
        int[] arr2 = {5,8,2,10};
        int res = question_google_interview(arr2,arr2.length);
        System.out.println(res);

    }
    // find the count of palidrome in string
     public static int question_31(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];

        int count = 0;
        for(int i=0;i<n;i++){
            dp[i][i] = true;
            count++;
        }
        for(int i=0;i<n-1;i++){
            if(s.charAt(i) == s.charAt(i+1)){
                dp[i][i+1] = true;
                count++;
            }
        }
        // for lenght 3 or more;
        int length = 3;
        while(length<=n){
            for(int j = 0;j<n-length+1;j++){
                int y = j+length -1;
                if(s.charAt(j) == s.charAt(y) && dp[j+1][y-1]==true){
                    dp[j][y] = true;
                    count++;
                }
            }
            length++;
        }
        return count;
    }

    // find the length or the max length  palidromic substring
      public String question_32(String s) {
        // code here
        int n = s.length();
        int start = -1;
        boolean[][] dp = new boolean[n][n];

        int maxLength= -1;
        // for length 1
        for(int i=0;i<n;i++){
            dp[i][i] = true;
            if(1>maxLength){
                maxLength = 1;
                start=i;
            }

        }
        // for length 2
        for(int i=0;i<n-1;i++){
            if(s.charAt(i) == s.charAt(i+1)){
                dp[i][i+1] = true;
                if(2>maxLength){
                    start=i;
                    maxLength = 2;
                }
            }
        }

        // for length 3 and 4 and 5 and so on
        int length = 3;
        while(length<=n){
            for(int i=0;i<n-length+1;i++){
                int j = i+length-1;
                if(s.charAt(i)==s.charAt(j) && dp[i+1][j-1] == true){
                    dp[i][j] = true;
                    if(length>maxLength){
                        maxLength = length;
                        start = i;
                    }
                }
            }
            length++;
        }


        return s.substring(start,start + maxLength);
    }

    // find the length or count of  palindromic subsequence in the given string
    public static int question_33(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int[][] dp1 = new int[n][n];

        int count = 0;
        for(int i=0;i<n;i++){
            dp[i][i] = true;
            dp1[i][i] = 1;
            count++;
        }
        for(int i=0;i<n-1;i++){
            if(s.charAt(i) == s.charAt(i+1)){
                dp[i][i+1] = true;
                dp1[i][i+1] = 3;
                count++;
            }
            else dp1[i][i+1] = 2;
        }
        // for lenght 3 or more;
        int length = 3;
        while(length<=n){
            for(int j = 0;j<n-length+1;j++){
                int y = j+length -1;
                if(s.charAt(j) == s.charAt(y) && dp[j+1][y-1]==true){
                    dp[j][y] = true;
                    dp1[j][y] = dp1[j+1][y] + dp1[j][y-1] - dp1[j+1][y-1] + 1 + dp1[j+1][y-1] + 1;
                    count++;
                }
                else{
                    dp1[j][y] = dp1[j+1][y] + dp1[j][y-1] - dp1[j+1][y-1];
                }
            }
            length++;
        }
        return dp1[n-1][n-1];
    }
    // follow up problem given l and r  find the count of palidromic substring for [l ... r]
    public static int[] question_34(String s,int[][] matrix){
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int[][] dp1 = new int[n][n];

        for(int i=0;i<n;i++){
            dp[i][i]=true;
            dp1[i][i] = 1;
        }

        for(int i=0;i<n-1;i++){
            if(s.charAt(i) == s.charAt(i+1)){
                dp[i][i+1] = true;
                dp1[i][i+1] = dp1[i+1][i+1] + dp1[i][i] + 1;
            }
           else dp1[i][i+1] = dp1[i+1][i+1] + dp1[i][i];
        }

        int length = 3;
        while(length<=n){
            for(int i=0;i<n-length+1;i++){
                int j = i + length - 1;
                if(s.charAt(i) == s.charAt(j) && dp[i+1][j-1] == true){
                    dp[i][j] = true;
                    dp1[i][j] = dp1[i+1][j] + dp1[i][j-1] - dp1[i+1][j-1] + 1;
                }
                else dp1[i][j] = dp1[i+1][j] + dp1[i][j-1] - dp1[i+1][j-1] ;
            }
            length++;
        }
        int m = matrix.length;
        int[] ans = new int[m];
        for(int i=0;i<m;i++){
            int l = matrix[i][0];
            int r = matrix[i][1] ;
            ans[i] = dp1[l][r];
        }
        return ans;


    }

    public static int question_34_part_2(String s){
        int  n = s.length();
        boolean[][] dp = new boolean[n][n];
        int[][] dp1 = new int[n][n];

        for(int i=0;i<n;i++){
            dp[i][i] = true;
            dp1[i][i] = 1;
        }
        for(int i=0;i<n-1;i++){
            if(s.charAt(i) == s.charAt(i+1)){
                dp[i][i+1] = true;
            }
            dp1[i][i+1] = (dp[i][i+1] == true)?1:0 + dp1[i+1][i+1] + dp1[i][i];

        }

        int length = 3;
        while(length<=n){
            for(int i=0;i<n-length+1;i++){
                int j = i + length - 1;
                if(s.charAt(i) == s.charAt(j) && dp[i+1][j-1] == true){
                    dp[i][j] = true;
                    dp1[i][j] = dp1[i+1][j] + dp1[i][j+1] - dp1[i+1][j-1] + 1 + dp1[i+1][j-1];
                }
                else dp1[i][j]  = dp1[i+1][j] + dp1[i][j-1] - dp1[i+1][j-1];
            }
            length++;
        }
        // find pairs of i,j which will form a palindrome
        int start = 1;
        int total = 0;
       while(start++<n-1){
           int count_left = 0;
           for(int j=start;j>=0;j--){
               if(dp[j][start] == true) count_left++;
           }
           int count_right = dp1[start+1][n-1];
           total += count_right*count_left;
       }
       return total;
    }

    public static int longest_palindromic_subsequence(String s){
        int n = s.length();
        int[][] dp = new int[n][n];
        for(int i=0;i<n;i++){
            dp[i][i] = 1;
        }
        // aa
        for(int i=0;i<n-1;i++){
            if(s.charAt(i) == s.charAt(i+1)){
                dp[i][i+1] = 2;
            }
            else{
                dp[i][i+1] = 1;
            }
        }
        int length = 3;

        while(length<=n){
            for(int i=0;i<n-length;i++){
                int j = i+length-1;
                if(s.charAt(i) == s.charAt(j)){
                    dp[i][j] = Math.max(2+dp[i+1][j-1],Math.max(dp[i][j-1],dp[i+1][j]));
                }
                else{
                    dp[i][j] = Math.max(dp[i+1][j],dp[i][j-1]);
                }
            }
            length++;
        }
            return dp[0][n-1];
    }

    // needed to find triplet in this
    public static int question_35_google_oa(String s){
        int  n = s.length();
        boolean[][] dp = new boolean[n][n];
        int[][] dp1 = new int[n][n];
        int[] dp2 = new int[n];

        for(int i=0;i<n;i++){
            dp[i][i] = true;
            dp1[i][i] = 1;
        }
        for(int i=0;i<n-1;i++){
            if(s.charAt(i) == s.charAt(i+1)){
                dp[i][i+1] = true;
            }
            dp1[i][i+1] = (dp[i][i+1] == true)?1:0 + dp1[i+1][i+1] + dp1[i][i];

        }

        int length = 3;
        while(length<=n){
            for(int i=0;i<n-length+1;i++){
                int j = i + length - 1;
                if(s.charAt(i) == s.charAt(j) && dp[i+1][j-1] == true){
                    dp[i][j] = true;
                    dp1[i][j] = dp1[i+1][j] + dp1[i][j+1] - dp1[i+1][j-1] + 1 + dp1[i+1][j-1];
                }
                else dp1[i][j]  = dp1[i+1][j] + dp1[i][j-1] - dp1[i+1][j-1];
            }
            length++;
        }
        // find pairs of i,j which will form a palindrome
        int start = 1;
        while(start<n-1){
            int count_left = 0;
            for(int j=start;j>=0;j--){
                if(dp[j][start] == true) count_left++;
            }
            int count_right = dp1[start+1][n-1];

            dp2[start] =  count_right * count_left;
        }
        int total = 0;

        for(int i = 1;i<n-2;i++){
            int j = i;
            int count_left = 0;
            while(j-->=0){
                if(dp[j][i] == true) count_left++;
            }
            int right = dp2[start+1];
            total+=count_left*right;
        }
        return total;

    }

    public static int question_36_Leetcode(String input,int k){
        int n = input.length();

        int[][][] dp = new int[n][n][k+1];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                Arrays.fill(dp[i][j],Integer.MIN_VALUE);
            }
        }


        for(int i=0;i<n;i++){
            for(int j=0;j<=k;j++){
                dp[i][i][j] = 1;
            }
        }

        for(int len=2;len<=n;len++){
            for(int i=0;i<n-len+1;i++){
                int j = i+len-1;
                for(int x = 0;x<=k;x++){
                    if(input.charAt(i)==input.charAt(j)){
                        dp[i][j][x] = Math.max(2+dp[i+1][j-1][x],Math.max(dp[i][j-1][x],dp[i+1][j][x]));
                    }
                    else{

                        int cost = Math.min(Math.abs((int)input.charAt(i)- (int) input.charAt(j)),26-Math.abs((int)input.charAt(i)-(int)input.charAt(j)));
                        dp[i][j][x] = Math.max(dp[i][j-1][x],dp[i+1][j][x]);
                        if(cost<=x){
                            dp[i][j][x-cost] = Math.max(dp[i][j][x],2+ dp[i+1][j-1][x-cost]);
                        }
                    }
                }
            }
        }

        return dp[0][n-1][k];
    }
    public static int question_google_interview(int[] arr,int n){
        int[][] dp = new int[n][n];
//        for(int i=0;i<n;i++) Arrays.fill(dp[i],Integer.MAX_VALUE);
        int[] prefix = new int[n];
        prefix[0] = arr[0];
        for(int i=1;i<n;i++){
            prefix[i] = prefix[i-1] + arr[i];
        }

        for(int i=0;i<n;i++){
            dp[i][i] = 0;
        }
        for(int i=0;i<n-1;i++){
            dp[i][i+1] = arr[i] + arr[i+1];
        }
        int length  = 3;
        while(length<=n){
            for(int i=0;i<n-length+1;i++){
                int j = i+length-1;
                int option1 = dp[i][i] + dp[i+1][j];
                int option2 = dp[j][j] + dp[i][j-1];
                int option3 = dp[i][i+1] + dp[i+2][j];
                dp[i][j] = Math.min(option1,Math.min(option2,option3)) + (i>=1 ? prefix[j]-prefix[i-1]:prefix[j]);
            }
            length++;
        }
        return dp[0][n-1];
    }


}



