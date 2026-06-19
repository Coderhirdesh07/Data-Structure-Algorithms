package dsacourse851.DynamicProgramming;

public class UnderstandingSimpleDp {
    public static void main(String[] args) {
        int[] arr = {-1,-2,-3,-4,-5,-6};
        int res = question_3(arr,arr.length);
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

}
