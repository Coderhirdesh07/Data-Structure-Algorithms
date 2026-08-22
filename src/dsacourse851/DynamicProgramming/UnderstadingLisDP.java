package dsacourse851.DynamicProgramming;

import java.util.Arrays;
import java.util.HashMap;

public class UnderstadingLisDP {
    public static void main(String[] args) {
        int[] arr = {2,100,4,50,8,10,12,14,15};
        int[] arr2 = {3, 9 ,4 ,2 ,16};
        int[] arr3 = {1 ,5 ,15 ,3 ,9};
        int[] arr4 = {18,26,18,24,24,20,22};
        int[] arr5 = {3, 1, 2, 1, 8, 10};
        int[] arr6 = {1,2,1,1,3};
        int res = question_85(arr6,arr6.length,2);
        System.out.println(res);

    }
    public static int question_79(int[] arr,int n){
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
    public static int question_79_part2(int[] arr,int n){
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
    public static int question_79_part3(int[] arr,int n,int k){
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
    public static int question_81(int[] arr,int n){
        Arrays.sort(arr);
        int[] dp = new int[n];
        Arrays.fill(dp,1);

        for(int i=1;i<n;i++){
            int current = arr[i];
            for(int j=i-1;j>=0;j--) {
                if (arr[j] * arr[j] == current) {
                    dp[i] = Math.max(dp[i],1 + dp[j]);
                }
            }
        }
        int max = -1;
        for(int x:dp){
            max = Math.max(max,x);
        }
        return max;

    }
    public static int question_81_optimise(int[] arr,int n){
        Arrays.sort(arr);
        int[] dp = new int[n];
        HashMap<Integer,Integer> m1 = new HashMap<>();

        dp[0] = 1;
        int finalAnswer = -1;
        m1.put(arr[0],0);
        for(int i=1;i<n;i++){
            int search = (int) Math.sqrt(arr[i]);
            int curr = arr[i];
                if(search*search == curr){
                    if(m1.containsKey(search) ){
                        int ind = m1.get(search);
                        dp[i] = 1 + dp[ind];
                    }
                }
            m1.put(arr[i],i);
            finalAnswer = Math.max(finalAnswer,dp[i]);
        }
        return finalAnswer;
    }
    public static int question_81_optimise_2(int[] arr,int n){
        Arrays.sort(arr);
        HashMap<Integer,Integer> m1 = new HashMap<>();
        for(int i=0;i<n;i++){
            m1.put(arr[i],1);
        }
        int finalanswer = 1;
        for(int it:m1.keySet()){
            int val  = (int) Math.sqrt(it);
            if(m1.containsKey(val) && val *val == it){
                m1.put(it,1+m1.get(val));
            }
            finalanswer = Math.max(finalanswer,m1.get(it));
        }

      return finalanswer;
    }
  // needs optimisation
    public static int question_82(int[] arr,int n){
        int[] dp  = new int[n];
        Arrays.fill(dp,1);

        for(int i=1;i<n;i++){
            int current = arr[i];
            for(int j=i-1;j>=0;j--){
                if(arr[j]*3 == current){
                    dp[i] = 1+dp[j];
                }
            }
        }

        int max = -1;
        for(int x:dp){
            max = Math.max(x,max);
        }
        return max;
    }
    public static int question_83(int[] arr,int n){
        Arrays.sort(arr);
        int[][] dp = new int[n][101];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], 1);
        }

        int max = -1;
        for(int i=1;i<n;i++){
            for(int j=0;j<=100;j++){
            for(int k=i-1;k>=0;k--){
                    if(j + arr[k] == arr[i]){
                        dp[i][j] = Math.max(dp[i][j],1 + dp[k][j]);
                    }
                }
                max = Math.max(max,dp[i][j]);
            }
        }
        return max;
    }

    public static int question_83_optimise(int[] arr,int n){
        Arrays.sort(arr);
        int[][] dp = new int[n][101];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], 1);
        }

        int max = -1;
        for(int i=1;i<n;i++){
            for(int j=0;j<=100;j++){
                for(int k=i-1;k>=0;k--){
                    if(j + arr[k] == arr[i]){
                        dp[i][j] = Math.max(dp[i][j],1 + dp[k][j]);
                    }
                }
                max = Math.max(max,dp[i][j]);
            }
        }
        return max;
    }

    public static int question_84(int[] arr,int n,int k){
      int[][] dp = new int[n][k+1];
      for(int i=0;i<n;i++) Arrays.fill(dp[i],0);
      int max = -1;
      for(int i=1;i<n;i++){
          for(int j=0;j<=k;j++){
              for(int l=i-1;l>=0;l--){
                  int sum = arr[i] + arr[l];
                  if(sum%k == j){
                      dp[i][j] = Math.max(dp[i][j],1+dp[l][j]);
                  }
              }
              max  = Math.max(dp[i][j],max);
          }
      }
      return max;
    }
    public static int question_84_optimise(int[] arr,int n,int k){
        int[][] dp = new int[n][k+1];
        for(int i=0;i<n;i++) Arrays.fill(dp[i],0);
        int max = -1;
        for(int i=1;i<n;i++){
            for(int j=0;j<=k;j++){
                for(int l=i-1;l>=0;l--){
                    int sum = arr[i] + arr[l];
                    if(sum%k == j){
                        dp[i][j] = Math.max(dp[i][j],1+dp[l][j]);
                    }
                }
                max  = Math.max(dp[i][j],max);
            }
        }
        return max;
    }
    public static int question_85(int[] arr,int n,int k){
        int[][] dp = new int[n][k+1];
        int max = -1;
        for(int i=0;i<n;i++) Arrays.fill(dp[i],1);

        for(int i=1;i<n;i++){
            for(int l=0;l<=k;l++){
                for(int j=i-1;j>=0;j--){
                    if(arr[i]!=arr[j]){
                        if(l-1>=0) {
                            dp[i][l] = Math.max(dp[i][l], 1 + dp[j][l - 1]);
                        }
                    }
                    else dp[i][l] = Math.max(dp[i][l], 1 + dp[j][l]);
                }
            }
        }
        for(int i=0;i<n;i++){
            int u = dp[i][k];
            max = Math.max(max,u);
        }

        return max;
    }

}
