package dsacourse851.DynamicProgramming;

import java.util.Arrays;

public class UnderstandingSimpleDp {
    public static void main(String[] args) {
        int[] arr = {2,3,5,8,10};
        // 2 3 5 8 10
        // 2 3 5 10
        //
        int[] nums = {5 ,8 ,3 ,100, -5 ,-5, 5, 10};
        String input = "ababbacaabbbb";
        int k = 1;
        int res = question_14(15);
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

    // TODO THIS PROBLEM HAVE TO BE DONE ONE MORE TIME
    public static int question_11(int[] arr,int n){
        int[][] dp = new int[n][2];
        // 0 is for even and 1 is for odd
        if(arr[0]%2 == 0 ) dp[0][0]=0;
        if(arr[0]%2 == 1) dp[0][1] = 1;

        for(int i=1;i<n;i++){
            if(i>=1){
                if(arr[i]%2==0) dp[i][1] = dp[i-1][1]+1;
                else dp[i][0] = 1 + dp[i-1][0];
            }
            if(i>=2){
                if(arr[i]%2==0) dp[i][1] = dp[i-2][1]+1;
                else dp[i][0] = 1 + dp[i-2][0];
            }
        }
        return dp[n-1][1];
    }
    public static int question_11_part2(int[] arr,int n){
        int[][] dp = new int[n][2];
        // 0 is for even and 1 is for odd
        if(arr[0]%2 == 0 ) dp[0][0]=1;
        if(arr[0]%2 == 1) dp[0][1] = 1;
        if((arr[0]+arr[1])%2==0) dp[1][0] = 1+dp[0][0];
        if((arr[0]+arr[1])%2!=0) dp[1][1] = 1+dp[0][1];

        for(int i=2;i<n;i++){
            if(arr[i]%2==0){
                dp[i][0] = dp[i-1][0] + dp[i-2][0];
                dp[i][1] = dp[i-1][1] + dp[i-2][1];
            }
            if(arr[i]%2!=0){
                dp[i][1] = dp[i-2][1]+dp[i-2][1];
                dp[i][0] = dp[i-2][0]+dp[i-2][0];
            }
        }
        return dp[n-1][0];
    }
    // google interview problem
    public static int question_12(int n,int y,int x,int z,int b){
        int[] dp = new int[n+1];
        dp[0] = Integer.MAX_VALUE;
        dp[1] = 0;

        for(int i=2;i<=n;i++){
            int option1 = y + dp[i-1];
            int option2 = Integer.MAX_VALUE;
            if(i>=3 && i%3==0) option2 = z + dp[i/3];
            int option3 = Integer.MAX_VALUE;
            if(i>=5 && i%5==0) option3 = b + dp[i/5];
            int option4 = Integer.MAX_VALUE;
            if(i>=7 && i%7==0) option4 = x + dp[i/7];

            dp[i] = Math.min(option1,Math.min(option2,Math.min(option3,option4)));
        }
        return dp[n];
    }
    public static int question_14(int n){
        int[] dp = new int[n+1];
        dp[0] = Integer.MAX_VALUE;
        dp[1] = 0;

        for(int i=2;i<=n;i++){
            int option1 = 1 + dp[i-1];
            int option2 = Integer.MAX_VALUE;
            if(i>=2 && i%2==0) option2 = 1 + dp[i/2];
            int option3 = Integer.MAX_VALUE;
            if(i>=3 && i%3==0) option3 = 1 + dp[i/3];

            dp[i] = Math.min(option1,Math.min(option2,option3));
        }
        return dp[n];
    }

}
