package dsacourse851.DynamicProgramming;

import java.util.Scanner;
public class UnderstandingSubarrayDp {
    public static void main(String[] args) {

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

    // needed to find triplet in this
    public static int question_34_google_oa(String s){
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

}



