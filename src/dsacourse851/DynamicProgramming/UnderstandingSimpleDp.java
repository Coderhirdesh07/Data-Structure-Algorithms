package dsacourse851.DynamicProgramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class UnderstandingSimpleDp {
    public static void main(String[] args) {
        int[] arr = { 2 ,10, 8 ,-5 ,-10 ,5};
        int[] arr2  = {2 ,-100 ,8 ,5 ,0};
        int[] a = {23, 4,5 ,101};
        int[] b = {21,1,10, 100};
        int[] c = {2,1,3};
        int[] d = {10,10,10};
        // 23 + 10 + 100 =
        //ATBTA = 25 + 50 + 70
        String[] input2 = {"N", "-2","N"};
        // 2 3 5 8 10
        // 2 3 5 10
        //
        int[] nums = {1,4,5,7,8,10,12};
        String input = "ababbacaabbbb";
        int k = 1;
//        Scanner sc  = new Scanner(System.in);
//        int n = sc.nextInt();
//        int m = sc.nextInt();
//        int[][] matrix = new int[n][m];
//        for(int i=0;i<n;i++){
//            for(int j=0;j<m;j++){
//                matrix[i][j] = sc.nextInt();
//            }
//        }

        // 1 1 1 1 , 2 2 ,4,
        HashMap<Integer,Integer> m1  = new HashMap<>();
        m1.put(1,1);
        m1.put(2,1);
        m1.put(4,1);
        m1.put(6,1);
        int res = question_26(nums,nums.length);
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

    // 1 2 3 4 7
    // 3 4 5 6 9
    // uber oa problem
    public static int[] question_13(int[] a,int[] b,int n){
    int[] dp_a_even = new int[n];
    int[] dp_a_odd = new int[n];
    int[] dp_b_even = new int[n];
    int[] dp_b_odd = new int[n];
    if(a[0]%2==0){
        dp_a_even[0] = 1;
    }
    else{
        dp_a_odd[0] = 1;
    }
    if(b[0]%2==0){
        dp_b_even[0]=1;
    }
    else dp_b_odd[0] = 1;


     for(int i=1;i<n;i++){
         if(a[i]%2==0){
             dp_a_even[i] =  dp_a_even[i-1] + dp_b_even[i-1];
         }
         if(a[i]%2!=0){
             dp_a_even[i] = dp_a_odd[i-1] + dp_b_odd[i-1];
         }
         if(b[i]%2==0) {
             dp_b_even[i] = dp_a_even[i - 1] + dp_b_even[i - 1];
         }
         if(b[i]%2!=0){
             dp_b_even[i] = dp_a_odd[i-1] + dp_b_odd[i-1];
         }
     }

     return new int[]{dp_a_even[n-1]+dp_b_even[n-1]};
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

    // goldman sachs
    public static int question_17(int[] arr,int n){
        // 0 -- is for forward and 1 is for backward
        int[][] dp = new int[n+1][2];
        dp[0][0] = arr[0];
        dp[0][1] = Integer.MAX_VALUE;
        dp[1][0] = Integer.MAX_VALUE;
        dp[1][1] = arr[2] + arr[1] + dp[0][0];

        for(int i=2;i<n-1;i++){
            dp[i][0] = arr[i] + Math.max(dp[i-2][0],dp[i-2][1]);
            dp[i][1] = arr[i]+arr[i+1] + dp[i-1][1];
        }
        dp[n-1][1] = Integer.MAX_VALUE;
        dp[n-1][0] = arr[n-1] + Math.min(dp[n-2][1],dp[n-2][0]);
        return Math.min(dp[n-1][1],Math.max(dp[n-2][0],dp[n-2][1]));
    }


    // TODO this question has to be done once again
    public static int question_18(String[] arr,int n){
        // *x = multiplies  -x = subtracts  /x = divides  +x = adds  N = *-1
        int[] dp = new int[n];
        dp[0]=1;
        if(arr[0].charAt(0)=='N'){
            dp[1] = Math.max(dp[0],-1);
        }
       else if(arr[0].charAt(0)=='+'){
            dp[1] = Math.max(dp[0],1+Integer.parseInt(arr[0].substring(1)));
        }
        else if(arr[0].charAt(0)=='*'){
            dp[1] = Math.max(dp[0],1*Integer.parseInt(arr[0].substring(1)));
        }
        else if(arr[0].charAt(0)=='/'){
            dp[1] = Math.max(dp[0],1/Integer.parseInt(arr[0].substring(1)));
        }
        else if(arr[0].charAt(0)=='-'){
            dp[1] = Math.max(dp[0],1-Integer.parseInt(arr[0].substring(1)));
        }

        for(int i=2;i<n;i++){
//            int option1 = dp[i-1];

            if(arr[i].charAt(0)=='N'){
              dp[i] = Math.max(dp[i-1],-1*dp[i-1]);
            }
            else if(arr[i].charAt(0)=='+'){
                dp[i] = Math.max(dp[i-1],dp[i-1]+Integer.parseInt(arr[i].substring(1)));
            }
             else if(arr[i].charAt(0)=='*'){
                dp[i]= Math.max(dp[i-1],dp[i-1]*Integer.parseInt(arr[i].substring(1)));
            }
             else if(arr[i].charAt(0)=='/'){
                dp[i] = Math.max(dp[i-1],dp[i-1]/Integer.parseInt(arr[i].substring(1)));
            }
             else{
                dp[i] = Math.max(dp[i-1],dp[i-1]-Integer.parseInt(arr[i].substring(1)));
            }
        }
        return dp[n-1];
    }

    // google sde3 interview problem
    // TODO took a little bit of hint
    public static int question_19(int[] a,int[] b,int n){
       int[][] dp = new int[n][2];
       // 0->a and 1->b tells dp[i][0] -> tells that the i the element is from a array
        dp[0][0] = a[0];
        dp[0][1] = b[0];
        dp[1][0] = Math.max(a[1],dp[0][0]+a[1]);
        dp[1][1] = Math.max(b[1],dp[1][1]+b[1]);
        for(int i=2;i<n;i++){
            dp[i][0] = a[i] +  Math.max(dp[i-1][0],dp[i-2][1]);
            dp[i][1] = b[i] + Math.max(dp[i-1][1],dp[i-2][0]);
        }
        return Math.max(dp[n-1][0],dp[n-1][1]);

    }
    // follow up problem of 19 question
    public static int question_19_follow_up(int[] a,int[] b,int[] c,int n){
        // 0->a  1->b  2->c
        int[][] dp = new int[n][3];
        dp[0][0] = a[0];
        dp[0][1] = b[0];
        dp[0][2] = c[0];

        dp[1][0] = Math.max(a[1],dp[0][0]+a[0]);
        dp[1][1] = Math.max(b[1],dp[0][1]+b[0]);
        dp[1][2] = Math.max(c[1],dp[0][2]+c[0]);

        for(int i=2;i<n;i++){
            dp[i][0] = Math.max(dp[i-1][0],Math.max(dp[i-2][1],dp[i-2][2]));
            dp[i][1] = Math.max(dp[i-1][1],Math.max(dp[i-2][0],dp[i-2][2]));
            dp[i][2] = Math.max(dp[i-1][2],Math.max(dp[i-2][0],dp[i-2][1]));

        }


        return Math.max(dp[n-1][0],Math.max(dp[n-1][1],dp[n-1][2]));
    }

    // uber oa problem
    public static int question_20(int[] a,int[] b,int n){
        // 0->a and 1->b
        // dp[i][a][0] = best ans till i the index if element was taken from array for 1st time
        // dp[i][a][1] = best ans till i the index if element was taken from array for 2nd time
       int[][][] dp = new int[n][2][2];
       for(int i=0;i<n;i++){
           for(int j=0;j<2;j++){
               Arrays.fill(dp[i][j],Integer.MIN_VALUE);
           }
       }
       dp[0][0][0] = a[0];
       dp[0][1][0] = b[0];

       dp[1][0][0]  = a[1] + Math.max(dp[0][1][0],dp[0][1][1]);
       dp[1][0][1] = a[1] + dp[0][0][0];

       dp[1][1][0] = b[1] + Math.max(dp[0][0][0],dp[0][0][1]);
       dp[1][1][1] = b[1] + dp[0][1][0];

        for(int i=2;i<n;i++){
            // for a
            dp[i][0][0]  = a[i] + Math.max(dp[i-1][1][0],dp[i-1][1][1]);
            dp[i][0][1] = a[i] + dp[i-1][0][0];
            // for b
            dp[i][1][0] = b[i] + Math.max(dp[i-1][0][0],dp[i-1][0][1]);
            dp[i][1][1] = b[i] + dp[i-1][1][0];
        }

       return Math.max(dp[n-1][0][1],Math.max(dp[n-1][0][0],Math.max(dp[n-1][1][0],dp[n-1][1][1])));

    }
    // barclays oa problem
    public static int question_21(int[][] arr,int n){
        // arr[0][0] = easy task  arr[0][1] = difficult task
        // 0-> easy task 1-> no task 2-> difficult task
        int[][] dp = new int[n][3];
        for(int i=0;i<n;i++){
            Arrays.fill(dp[i],0);
        }
        dp[0][0] = arr[0][0];
//        dp[0][2] = arr[0][1];


        dp[1][0] = dp[0][0] + arr[0][0];
        dp[1][1] = Math.max(dp[0][0],Math.max(dp[0][1],dp[0][2]));
        dp[1][2] = dp[0][1] + arr[1][1];

        for(int i=2;i<n;i++){
            dp[i][0] = Math.max(dp[i-1][0],Math.max(dp[i-1][1],dp[i-1][2])) + arr[i][0];
            dp[i][1] = 0 + Math.max(dp[i-1][0],Math.max(dp[i-1][1],dp[i-1][2]));
            dp[i][2] = dp[i-1][1] + arr[i][1];
        }

        return Math.max(dp[n-1][0],Math.max(dp[n-1][1],dp[n-1][2]));
    }


    // microsoft oa problem
    // no are 1 2 4 6
    // TODO this problem was done with hint.
    public static int question_23(HashMap<Integer,Integer> m1, int y){
        int[][] dp  = new int[y+1][3];
        for(int i=0;i<=y;i++) {
            Arrays.fill(dp[i], 0);
        }
        dp[0][0]  = 1;

        for(int i=1;i<=y;i++){
          if(i-1>=0) dp[i][0]+=dp[i-1][0];
          if(i-2>=0) dp[i][0]+=dp[i-2][0];
          if(i-6>=0) dp[i][0]+=dp[i-6][0];
            // 1 four is used
          if(i-1>=0) dp[i][1]+=dp[i-1][0];
          if(i-2>=0) dp[i][1]+=dp[i-2][0];
          if(i-4>=0) dp[i][0]+=dp[i-4][0];
          if(i-6>=0) dp[i][1]+=dp[i-6][0];
            // 2 four is used
          if(i-1>=0) dp[i][0]+=dp[i-1][2];
          if(i-2>=0) dp[i][0]+=dp[i-2][2];
          if(i-4>=0) dp[i][0]+=dp[i-4][1];
          if(i-6>=0) dp[i][0]+=dp[i-6][2];
        }

        return  dp[y][2]+dp[y][1] + dp[y][0];
    }

    // Airbnb oa
    // TODO Solved with hint
    public static int question_25(int[] a,int[] b,int n){
        // here ordering will matter
        // 0-> last ( first i then i-1)   and 1-> second last(i-1 then i)
        // both adj are present b[i] if one is there a[i] , none is there cost is 0;
        int[][] dp = new int[n][2];
        dp[0][0] = a[0];
        dp[0][1] = 0;
        dp[1][0]  = a[1] + Math.min(dp[0][0],dp[0][1]);
        // 0 is remove first
        dp[1][1] = b[0] +  0;
        for(int i=2;i<n;i++){
            dp[i][0] = a[i] + Math.min(dp[i-1][0],dp[i-1][1]);

            dp[i][1] = Math.min(b[i-1]+Math.min(dp[i-2][0],dp[i-2][1]),a[i-1]+dp[i-2][1]);
        }
        return Math.min(dp[n-1][0],dp[n-1][1]);
    }

    // phone pe oa problem
    public static int question_26(int[] arr,int n){
        // can rob 2 consecutive house or can rob 3 consecutive house
        // if option 1 is selected  he will leave atleast one house if option2 is selected he will leave atleast 2 houses
        // 0 will represent type-1 and 1 will represent type-2

        // 0  1  2  3  4   5  6
        // 1 ,4 ,5 ,7 ,8 ,10 ,12

        int[][] dp = new int[n][2];
        for(int i=0;i<n;i++){
            Arrays.fill(dp[i],Integer.MIN_VALUE);
        }

        dp[1][0] = arr[1] + arr[0];
        dp[2][0] = arr[2]+arr[1];
        dp[2][1] = arr[2]+arr[1]+arr[0];
        for(int i=3;i<n;i++){

            dp[i][0] = arr[i]+arr[i-1] + Math.max(dp[i-2][0],Math.max(dp[i-2][1],Math.max(dp[i-3][0],dp[i-3][1])));
            if(i>=4){
                dp[i][1] = arr[i]+arr[i-1]+arr[i-2] + Math.max(dp[i-3][0],Math.max(dp[i-3][1],Math.max(dp[i-4][0] ,dp[i-4][1])));
            }
        }
        return Math.max(dp[n-1][0],dp[n-1][1]);

    }
    // google girl hackathon problem
//    public static int question_27(int[] arr,int n){
//
//    }
    // detuche bank oa
    public static int question_28(int[][] arr,int n){
        int[][] dp = new int[n][3];
        // 0-> easy 1-> medium 2-> hard
        for(int i=0;i<n;i++){
            Arrays.fill(dp[i],0);
        }
        dp[0][0] = arr[0][0];
        dp[1][0] = dp[1][0] + arr[1][1];


        for(int i=2;i<n;i++){
            // easy task
            dp[i][0] = arr[i][0] + Math.max(dp[i-1][0],Math.max(dp[i-1][1],dp[i-1][2]));
            // medium task
            dp[i][1] = arr[i][1] + arr[i-1][0] + dp[i-2][1];
            // hard task
            int option1 = arr[i][2] + arr[i-1][0] + dp[i-2][0];
            int option2= 0;
            int option3=0;
            if(i>=3) {
                option2 = arr[i][2] + arr[i-1][1] + arr[i-2][0]+dp[i-3][0];
                option3 = arr[i][2] + arr[i-1][2] + arr[i-2][1] + dp[i-3][1];
            }
                dp[i][2] = Math.max(option1,Math.max(option2,option3));
        }
        return Math.max(dp[n-1][0],Math.max(dp[n-1][1],dp[n-1][2]));
    }


}

