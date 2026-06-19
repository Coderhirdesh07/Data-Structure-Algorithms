package dsacourse851.DynamicProgramming;

public class UnderstandingSimpleDp {
    public static void main(String[] args) {
        int[] arr = {4 ,12 ,13 ,18 ,10 ,12 };
        int[] nums = {5 ,8 ,3 ,100, -5 ,-5, 5, 10};
        int res = question_8(nums,nums.length);
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

}
