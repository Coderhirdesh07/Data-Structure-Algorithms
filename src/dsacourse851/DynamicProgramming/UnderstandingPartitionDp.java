package dsacourse851.DynamicProgramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class UnderstandingPartitionDp {
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        int[] a = new int[n+1];
//        int[] b = new int[n+1];
////        [5 10 5 10 1 1 8 2]
//        // 1 2 3 4 4
//        // 1 2 3 4 6
//        for(int i=1;i<=n;i++){
//            a[i] = sc.nextInt();
//        }
//        for(int i=1;i<=n;i++){
//            b[i] = sc.nextInt();
//        }
        int[] a = {0,16 ,20 ,12 ,12, 8, 2, 3, 8, 17, 3, 14, 8};
        int[] b = {0,16, 17, 1, 2, 20, 17, 1, 3 ,9 ,8 ,10, 16};
        int[] freq = {1,2,2,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};

//        int[] arr= {3,1,4,6,5,2};
//        int[] arr= {0,-2,1,-3,4};
//        int[] arr= {0,5,8,-15,3,4,5};
         int[] arr= {0,1,2,3,3};

        int ans = question_57(arr,4,4); // 1 2 3 4 , 12 3 4, 12 34,123 4 , 1 234
        System.out.println("This is the "+ans);
        // 5 3
        //1 2 1 3 5

    }
    public static int partition_dp(int[] arr,int m,int n){
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i=2;i<=n;i++){
            int j = i;
            int sum = arr[i];
            int v = 0;
            while(j>0 && sum<=m){
                int g  = dp[j-1];
                v+=g;
                j--;
                sum +=arr[j];
            }
            dp[i] = v;
        }

        return dp[n];
    }

    public static int partition_dp_follow_up(int[] arr,int m,int n,int k){
        int[][] dp = new int[n+1][k+1];
        for(int i=0;i<=n;i++) Arrays.fill(dp[i],Integer.MIN_VALUE);

        dp[0][0]  = 1;
        dp[1][1] = 1;


        for(int i=2;i<=n;i++){
            int part = 1;
            while(part<=k) {
              int j = i;
              int sum = arr[i];
              int v = 0;
              while(j>0 && sum<=m){
                  int g = dp[j-1][part-1];
                  if(g>0) v+=g;
                  j--;
                  sum+=arr[j];
              }
              if(v>0) dp[i][part] = v;
              part++;
            }
        }
        return dp[n][k];
    }
    // calculating max case
    public static int question_42(int[] arr,int n,int size){
        int[] dp = new int[n+1];

        Arrays.fill(dp,Integer.MIN_VALUE);
        dp[0] = 0;
        dp[2] = arr[1] - arr[2];

        for(int i=3;i<=n;i++){
           for(int j = i-2;j>=0;j--){
               dp[i] = Math.max(dp[i],dp[j] + arr[j+1] - arr[i]);
           }
        }

        return dp[n];
    }
    public static int question_42_follow_up(int[] arr,int n,int size,int k){
        int[][] dp = new int[n+1][k+1];
        Arrays.fill(dp,(int) 1e18);
        dp[0][0] = 0;
        dp[2][1] = arr[1] - arr[2];
        for(int i=3;i<=n;i++){
            for(int x = 1;x<=k;x++) {
                for (int j = i - size; j > 0; j--) {
                    dp[i][x] = Math.min(dp[i][x], arr[j + 1] - arr[i] + dp[j][x-1]);
                }
            }
        }
        return dp[n][k];
    }
    public static int convertToNumber(String x){
        int n = x.length();
        int original = 0;
        int product = 1;
        for(int i = n-1;i>=0;i--){
            original += product * (x.charAt(i)-'0');
            product*=2;
        }
        return original;
    }

    public static int question_43(String s) {
        int n = s.length();
        int[] dp  = new int[n+1];
        Arrays.fill(dp,100);
        dp[0] = 0;

        int i = 0;
        while(i<n){
            int ans = 100;
            int j = i;
            while(j>=0){
                String check = s.substring(j,i+1);
                int res = convertToNumber(check);
                if(s.charAt(j)!='0'  && res!=0 && (15625%res) == 0){
                    ans = Math.min(ans,1 + dp[j]);
                }
                j--;
            }
            dp[i+1] = ans;
            i++;
        }

        return (dp[n] == 100)?-1:dp[n];
    }

    // leetcode contest problem
    public static int question_44(String s) {
        int n = s.length();
        int[] dp  = new int[n+1];
        Arrays.fill(dp,100);
        dp[0] = 0;

        int i = 0;
        while(i<n){
            int ans = 100;
            int j = i;
            while(j>=0){
                String check = s.substring(j,i+1);
                int res = convertToNumber(check);
                if(s.charAt(j)!='0'  && res!=0 && (15625%res) == 0){
                    ans = Math.min(ans,1 + dp[j]);
                }
                j--;
            }
            dp[i+1] = ans;
            i++;
        }

        return (dp[n] == 100)?-1:dp[n];
    }

    // linkedin oa problem
    public static int question_45(int[] arr,int k){
        int n = arr.length;
        int[][] dp = new int[n+1][k+1];
        for(int i=0;i<=n;i++){
            Arrays.fill(dp[i],Integer.MAX_VALUE);
        }

       for(int i=0;i<=k;i++) dp[0][i] = 0;

        for(int i=1;i<=n;i++){
            for(int prt = 1;prt<=k;prt++) {
                int max = 0;
                for (int j = i; j >= 1; j--) {
                    max = Math.max(max,arr[j-1]);
                    if(dp[j-1][prt-1]!=Integer.MAX_VALUE){
                        dp[i][prt] = Math.min(max + dp[j-1][prt-1],dp[i][prt]);
                    }
                }
            }
        }
        return dp[n][k];
    }



    public static boolean isStringValid(HashMap<Character,Integer> m1,char ref){
        int check = m1.get(ref);
        for(char it:m1.keySet()){
            if(m1.get(it)!=check) return false;
        }
        return true;
    }

    // leetcode contest problem
    public static int question_46(String s) {
        int n = s.length();
        int[] dp = new int[n+1];
        Arrays.fill(dp,Integer.MAX_VALUE);


        dp[0] = 0;
        for(int i=0;i<n;i++){
            HashMap<Character,Integer> m1 = new HashMap<>();
            for(int j=i;j>=0;j--){
                m1.put(s.charAt(j),m1.getOrDefault(s.charAt(j),0)+1);
                String refer = s.substring(j,i+1);
                boolean isGood = isStringValid(m1,s.charAt(i));
                if(isGood == true){
                    dp[i+1] = Math.min(dp[i+1],1 + dp[j]);
                }
            }
        }
        return dp[n];
    }

    public static int cisco_oa_question_47(int[] arr,int m,int k){
        int n = arr.length;
        int[] dp = new int[n+1];

        dp[0] = 0;
        for(int i=1;i<=n;i++){
            int max = arr[i];
            int min = arr[i];
            for(int j=i-1;j>=0;j--){
                int size = j-i;
                if(size>=k && max - min <=m){
                    dp[i] = 1 + dp[i-j+1];
                }
                max = Math.max(max,arr[j]);
                min = Math.min(min,arr[j]);
            }
        }
        return dp[n];
    }

    // google oa problem
    public static int question_48(int[] a,int[] b,int n){
        int[] dp = new int[n+1];

        dp[1] = a[1];


        for(int i = 2 ; i <= n ; i++){
            int sum = b[i] + b[i-1];
            int sum2 = a[i] + dp[i-1];
            int j = i-1;
           while(j>=1){
                int newSum = sum + dp[j-1];
                sum2 = Math.min(newSum,sum2);
                j--;
                sum+=b[j];
           }
           dp[i] = sum2;
        }

        return dp[n];

    }
    public static boolean isPrime(String check){
        int prime = Integer.parseInt(check);
        int limit = (int) Math.sqrt((double) prime);
        int count = 0;
        for(int i=1;i<=limit;i++){
            if(prime%i == 0){
                count++;
            }
        }
        return count>2?false:true;
    }
    public static int question_50(String s){
        int n = s.length();
        int[] dp = new int[n+1];
        dp[0] = 1;
        for(int i=1;i<n;i++){
            for(int j=i;j>=0;j--){
                String check = s.substring(j,i+1);
//                System.out.println(check);
                boolean prime = isPrime(check);
                if(s.charAt(j)!='0' &&  prime && j-1>=0){
                    dp[i] = 1 + dp[j-1];
                }
            }

        }
        return dp[n-1];
    }

    public static int question_51(String s){
        int n = s.length();
        int[] dp = new int[n+1];
        dp[0] = 1;
        for(int i=1;i<n;i++){
            for(int j=i;j>=0;j--){
                String check = s.substring(j,i+1);
//                System.out.println(check);
                boolean prime = isPrime(check);
                if(s.charAt(j)!='0' &&  prime && j-1>=0){
                    dp[i] = 1 + dp[j-1];
                }
            }

        }
        return dp[n-1];
    }

    // tech-gig semifinal
    public static int question_52(String s,int k){
        int n = s.length();
        // 0 1 2
        // 1 2 3
        int[] dp = new int[n+1];
        dp[0] = 1;
        for(int i=1;i<=n;i++) {
            for (int j = i-1;j>=0;j--){
                String check = s.substring(j,i);
                if(s.charAt(j)!='0'){
                    if(Integer.parseInt(check)<=k){
                        dp[i]+=dp[j];
                    }
                    else dp[i]+=0;
                }
            }
        }
        return dp[n];
      }
//      public static int question_53(){}
    public static int question_54(int[] arr,int n){
        int[] dp = new int[n];
        dp[0] = 0 ;
        for(int i=1;i<n;i++){
            int max = arr[i];
            for(int j=i-1;j>=0;j--){
                boolean isGood = goodSubarray(arr,j,i);
                if(isGood && j-1>=0){
                    dp[i] = Math.max(dp[i],1 + dp[j]);
                }
                else dp[i] = Math.max(dp[i],1);
            }
        }
        return dp[n-1];
    }
    public static boolean goodSubarray(int[] arr,int start,int end){
        int ind = -1;
        int max = -1;
        for(int i=start;i<=end;i++){
            if(max<arr[i]){
                max = arr[i];
                ind = i;
            }
        }
        return (ind<end)?true:false;
    }

    public static int question_55(int[] arr,int n){
        int[] dp = new int[n+1];
        dp[0] = 1;
        for(int i=1;i<=n;i++){
            for(int j=i;j>=0;j--){
                boolean check = isValid(arr,j,i);
                if(check && j-1>=0){
                    dp[i] +=dp[j-1];
                }
                else  dp[i] +=0;
            }
        }
        return dp[n];
    }
    public static boolean isValid(int[] arr,int j,int i){
        int count = 0;
        for(int start = j;start<=i;start++){
            if(arr[start]<0){
                count++;
            }
        }
        return (count>0)?true:false;
    }
    // freq.length = 26
    public static int question_56(String input,int[] freq,int k){
        int n  = input.length();
        int[][] dp = new int[n+1][k+1];
        dp[0][0] = 0;

        for (int i = 1; i<n ; i++) {
            HashMap<Integer,Integer> m1 = new HashMap<>();
            for (int prt=1;prt<=k;prt++) {
                for (int j = i; j >= 0; j--) {
                    char f = input.charAt(j);
                    m1.put(f - 'a', m1.getOrDefault(f - 'a', 0) + 1);
                    boolean isvalid = question_56_check(m1, freq);
                    if (isvalid && j - 1 >= 0) {
                        dp[i][prt] += dp[j - 1][prt - 1];
                    }
                }
            }
        }
        return dp[n-1][k];
    }
    public static boolean question_56_check(HashMap<Integer,Integer> m1,int[] freq){

        for(int it:m1.keySet()){
            if(m1.get(it)>freq[it]){
                return false;
            }
        }
        return true;
    }
    public static int question_57(int[] arr,int k,int n){
        int[][] dp = new int[n+1][k+1];
        for(int i=0;i<=n;i++){
            Arrays.fill(dp[i],-1);
        }
        dp[0][0] = 0;

        for(int prt=1;prt<=k;prt++){
            for(int i = 1;i<=n;i++){
                int v = dp[i-1][prt];
                int sum = 0;
                for(int j=i;j>=1;j--){
                    sum+=arr[j];
                    if(j-1>=0) {
                        v = Math.max(v,sum+dp[j-1][prt-1]);
                    }
                }
                dp[i][prt] = v;
            }
        }
        return dp[n][k];
    }
    public static int question_57_optimise(int[] arr,int k,int n){
        int[][][] dp = new int[n+1][k+1][2];

        for(int i=0;i<=n;i++){
            for(int j=0;j<=k;j++) {
                Arrays.fill(dp[i], (int)-1e18);
            }
        }
        dp[0][0][0] = 0;
        // 0 indicates element not taken
        // 1 indicates element taken

        for(int prt=1;prt<=k;prt++) {
            for (int i = 1; i <= n; i++) {
                dp[i][prt][0] = Math.max(dp[i-1][prt][0],dp[i-1][prt][1]);
                dp[i][prt][1] = Math.max(arr[i]+Math.max(dp[i-1][prt-1][0],dp[i-1][prt-1][1]),arr[i]+dp[i-1][prt][1]);
            }
        }
        return Math.max(dp[n][k][0],dp[n][k][1]);
    }

}

