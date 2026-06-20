package dsacourse851.DynamicProgramming;

import java.util.Arrays;

public class UnderstandingSimpleDp {
    public static void main(String[] args) {
        int[] arr = {4 ,12 ,13 ,18 ,10 ,12 };
        int[] nums = {5 ,8 ,3 ,100, -5 ,-5, 5, 10};
        String input = "ababbacaabbbb";
        int k = 1;
        String res = question_6(input,input.length(),k);
        System.out.println(res);

    }
    // find max sum of subset such no two elements are consecutive
    public static int question_3(int[] arr,int n){
        int[] dp = new int[n];
        if(arr[0]>0) dp[0] = arr[0];
        dp[1] = Math.max(arr[1],Math.max(dp[0],0));
        for(int i=2;i<n;i++){
            int option1 = dp[i-1];
            int option2  = 0;
            if(i>=2) option2 = arr[i] + dp[i-2];
            dp[i]  = Math.max(option1,option2);
        }
        return  dp[n-1];
    }
    public static int question_4(int[] arr1,int[] arr2,int n){
        int[] dp = new int[n];
        dp[0] = Math.max(0,Math.max(arr1[0],arr2[0]));
        dp[1] = Math.max(0,Math.max(arr1[1],arr2[1]));

        for(int i=2;i<n;i++){
            int option1 = dp[i-1];
            int option2 = 0;
            if(option2>=2){
                option2 = Math.max(arr1[i],arr2[i]) + dp[i-2];
            }
            dp[i] = Math.max(option1,option2);
        }
        return dp[n-1];
    }

    // amazon oa
    public static int question_5(int[] arr,int n){
        int[] dp = new int[n];
        dp[0] = 0;
        dp[1] = Math.abs(arr[0]-arr[1]);
        dp[2] = Math.abs(arr[1]-arr[2]) + dp[1];
        for(int i=3;i<n;i++){
            int option1 =  dp[i-1] + Math.abs(arr[i-1]-arr[i]);
            int option2 = dp[i-3] + Math.abs(arr[i-3]-arr[i]);

            dp[i] = Math.min(option1,option2);
        }
        return dp[n-1];
    }
    public static String question_6(String input,int n,int k){
        int[] dp = new int[n];
        dp[0] = 1;
//        int val1 = input.charAt(1)-'a';
//        int val2 = input.charAt(0)-'a';
//        int res = Math.abs(val1-val2);
//        if(res<=k) dp[1]=1+dp[0];
//        else dp[1] = 0;
        for(int i=1;i<n;i++){
            int f = input.charAt(i) - 'a';
            int s = input.charAt(i-1) - 'a';
            if(Math.abs(f-s) <= k) dp[i] = dp[i-1]+1;
            else dp[i] = 1;
        }

        int index = -1;
        int max = -1;
        for(int i=0;i<n;i++){
            if(max<dp[i]){
                max = dp[i];
                index = i;
            }
        }
        int start = index - dp[index]+1;
        return input.substring(start,start+dp[index]);
    }
    // google swe intern interview problem
    public static int question_8(int[] arr,int n){
        int[] dp = new int[n];
        dp[0] = arr[0];
        dp[1] = dp[0]+arr[1];

        for(int i=2;i<n;i++){
            int option3 = 0;
            int option1 =  dp[i-1] +arr[i];
            int option2 = 0;
            if(i>=3) option2 = dp[i-3] + arr[i];
            if(i>=5) option3 = dp[i-5]  + arr[i];
            dp[i] = Math.max(option1,Math.max(option3,option2));
        }
        return dp[n-1];
    }
    // TODO this needs to be done once again by me
    public static int question_9(int[] arr,int n,int k){
        int[] dp  = new int[n];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0] = 0;
        dp[1] = arr[0]*arr[1];
        for(int i=2;i<n;i++){
            for(int j=Math.max(0,i-k);j<i-1;j++){
               dp[i] =  Math.min(dp[i],arr[i]*arr[j] + dp[j]);
            }
        }
        return dp[n-1];
    }

}
