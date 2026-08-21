package dsacourse851.DynamicProgramming;

import java.util.Arrays;
import java.util.HashMap;

public class UnderstadingLisDP {
    public static void main(String[] args) {
        int[] arr = {2,100,4,50,8,10,12,14,15};
        int[] arr2 = {3, 9 ,4 ,2 ,16};
        int res = question_3_optimise_2(arr2,arr2.length);
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
    public static int question_3(int[] arr,int n){
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
    public static int question_3_optimise(int[] arr,int n){
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
    public static int question_3_optimise_2(int[] arr,int n){
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

}
