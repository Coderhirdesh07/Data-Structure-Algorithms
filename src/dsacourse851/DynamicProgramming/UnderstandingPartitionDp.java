package dsacourse851.DynamicProgramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class UnderstandingPartitionDp {
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        int m = sc.nextInt();
//        int[][] matrix = new int[n][m];
//        for(int i=0;i<n;i++){
//            for(int j=0;j<m;j++){
//                matrix[i][j] = sc.nextInt();
//            }
//        }
//        [8 10 -5 -8 1 10 10 11]
//        int[] b = new int[n+1];
//        [5 10 5 10 1 1 8 2]
        // 1 2 3 4 4
        // 1 2 3 4 6
//        for(int i=1;i<=n;i++){
//            a[i] = sc.nextInt();
//        }
//        for(int i=1;i<=n;i++){
//            b[i] = sc.nextInt();
//        }
//        int[] a = {0,16 ,20 ,12 ,12, 8, 2, 3, 8, 17, 3, 14, 8};
//        int[] b = {0,16, 17, 1, 2, 20, 17, 1, 3 ,9 ,8 ,10, 16};
//        int[] freq = {1,2,2,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};

//        int[] arr= {3,1,4,6,5,2};
//        int[] arr= {0,-2,1,-3,4};
//        int[] arr= {0,5,8,-15,3,4,5};
//         int[] arr= {0,1,2,3,3};

//        int ans = question_68(new int[]{0,2,4,3},new int[]{0,2,1,3},new int[]{0,4,3,1},new int[]{0,2,1,1},30,3); // 1 2 3 4 , 12 3 4, 12 34,123 4 , 1 234
       int ans = question_73(new int[]{6, 3 ,6 ,5 ,1},new int[]{1 ,4 ,5 ,9 ,2},5,3);
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
    // atlassian oa
    public static int question_58(int[] arr,int n,int m){
        int[] dp = new int[n+1];
        dp[0] = 0;
        for(int i=1;i<=n;i++){
            int sum = 0;
            int v = dp[i-1];
            for(int j=i;j>=0;j--){
                    sum += arr[j];
                    int size = Math.abs(j - i) + 1;
                    if (size <= m && j - 1 >= 0) {
                        v = Math.max(v, sum + dp[j - 1]);
                    }
            }
            dp[i] = Math.max(v,dp[i]);
        }
        return dp[n];
    }
    public static int question_58_optimised(int[] arr,int n,int m){
        int[] dp = new int[n+1];
        int[] prefix = new int[n+1];


        for(int i=1;i<=n;i++){
            prefix[i] = prefix[i-1] + arr[i];
        }
        dp[0] = 0;
        for(int i=m;i<=n;i++){
            dp[i] = Math.max(dp[i-1],prefix[i] - prefix[i-m] + dp[i-m]);
        }

        return dp[n];
    }

    // codeforces problem
    public static int question_58_actual(int[] arr,int n,int m,int k){
        int[][] dp = new int[n+1][k+1];

        dp[0][0] = 0;

        for(int prt = 1;prt<=k ;prt++ ){
          for(int i=1;i<=n;i++){
              int v = Math.max(dp[i-1][prt],arr[i] + dp[i-1][prt-1]);
              int sum = 0;
              for(int j=i;j>=0;j--){
                  sum+=arr[j];
                  int size = Math.abs(j-i)+1;
                  if(size<=m && j-1>=0){
                      v = Math.max(v,sum+dp[j-1][prt-1]);
                  }
              }
              dp[i][prt] = Math.max(v,dp[i][prt]);
          }
        }
        return dp[n][k];
    }
    public static int question_58_actual_optimised(int[] arr,int n,int m,int k){
        int[][] dp = new int[n+1][k+1];
        int[] prefix = new int[n+1];
        for(int i=1;i<=n;i++){
            prefix[i] = prefix[i-1] + arr[i];
        }
        for(int prt = 1;prt<=k;prt++){
            for(int i=1;i<=n;i++){
                if(i>=m) {
                    dp[i][prt] = Math.max(dp[i - 1][prt], prefix[i] - prefix[i - m] + dp[i - m][prt - 1]);
                }
                else dp[i][prt] = dp[i-1][prt];
            }
        }
        return dp[n][k];
    }
    public static int question_59(int[] arr,int n){
        int[] dp = new int[n+1];
        dp[0] = 0;
        for(int i=1;i<=n;i++){
            for(int j=i;j>=0;j--){
                if(arr[j] == j+1-i+1){
                    dp[i] += dp[j-1];
                }
            }
        }
        return dp[n];
    }

    public static int question_67(int W, int val[], int wt[]) {
        int n = val.length;
        int[][] dp = new int[n+1][W+1];


        for(int i= 1;i<=n;i++){
            for(int j=0;j<=W;j++){
                // notpick
                // pick
                if(j>=wt[i-1]){
                    dp[i][j] = Math.max(dp[i-1][j],val[i-1] + dp[i-1][j-wt[i-1]]);
                }
                else dp[i][j] = dp[i-1][j];
            }
        }
        return dp[n][W];
    }
    public static int question_68(int[] cost,int[] happy,int[] min,int[] max,int m,int n){

        int[][] dp = new int[n+1][m+1];

        for(int i=1;i<=n;i++){
            Arrays.fill(dp[i], -1);
        }

        for(int i=1;i<=n;i++){
          for(int j=1;j<=m;j++){
              int count = min[i];
              int finalAnswer = -1;
              while(count<=max[i]){
                  int g = count*cost[i];
                  if(j>=g){
                      int answer = count*happy[i] + dp[i-1][j-g];
                      finalAnswer = Math.max(answer,finalAnswer);
                  }
                  count++;
              }
              dp[i][j] = finalAnswer;
              System.out.println("value for dp[i][j] = " + dp[i][j]);
          }
        }
        return dp[n][m];
    }
        public static int question_71(int[][] items, int budget) {
            int n = items.length;
            int[][][] dp = new int[n][budget+1][2];
            int maxi = -1;
            for(int i=0;i<n;i++){
                maxi = Math.max(maxi,items[i][0]);
            }
            int[] freq = new int[maxi+1];
            for(int i=0;i<n;i++){
                freq[items[i][0]]++;
            }
            int[] factors = new int[maxi+1];
            for (int d = 1; d <= maxi; d++) {
                for (int multiple = d; multiple <= maxi; multiple += d) {
                    factors[d] += freq[multiple];
                }
            }

            // 0 will tell not taken
            // 1 will tell taken 1 time
            for(int i=0;i<n;i++){
                dp[i][0][0] = 0;
                dp[i][0][1] = -(int)1e9;
                for(int j=1;j<=budget;j++){
                    if(i-1>=0){
                        dp[i][j][0] = Math.max(dp[i-1][j][0],dp[i][j][0]);
                        dp[i][j][0] = Math.max(dp[i-1][j][1],dp[i][j][0]);
                    }
                    int f = items[i][0];
                    int wi = items[i][1];
                    if(j-wi>=0){
                        int v;
                        v = 1 + dp[i][j-wi][0];
                        dp[i][j][1] = Math.max(dp[i][j][1],v + factors[f]-1);
                        v = 1 + dp[i][j-wi][1];
                        dp[i][j][1] = Math.max(dp[i][j][1],v);
                    }

                }
            }

            return Math.max(dp[n-1][budget][0],dp[n-1][budget][1]);
        }

    public static int question_72(int[][] matrix,int k){
        int n = matrix.length;
        int m = matrix[0].length;
        boolean[][] dp = new boolean[n+1][5001];

        for(int i=1;i<=n;i++){
            for(int j=1;j<=5001;j++){
                for(int l=1;l<=m;l++){
                    int p0 = matrix[i-1][l-1];
                    int p1 = j - p0;
                    if(p1>=0 && dp[i-1][p1]){
                        dp[i][j] = true;
                    }
                }
            }
        }

       int mini = Integer.MAX_VALUE;
       for(int i=1;i<=5001;i++){
         if(dp[n][i] == true) {
            int po = Math.abs(i-k);
            mini = Math.min(mini,po);
         }
       }
       return mini;
    }
    // this problem will require 4d dp
    public static int question_73(int[] a,int[] b,int n,int k){
        int sum1  = 0;
        int sum2 = 0;
        for(int x:a) sum1+=x;
        for(int x:b) sum2+=x;

        boolean[][][][] dp = new boolean[n+1][k+1][sum1+1][sum2+1];
//        dp[0][0][0] = 0;
//        dp[0][0][1] = 0;
//        for(int i=1;i<=n;i++){
//            for(int j=1;j<=k;j++){
//              dp[i][j][0] =   Math.max(a[i] + dp[i-1][j-1][0],dp[i-1][j][0]);
//              dp[i][j][1] =  Math.max(b[i] + dp[i-1][j-1][1],dp[i-1][j][1]);
//            }
//        }
//        return Math.min(dp[n][k][0],dp[n][k][1]);

        for(int i=1;i<=n;i++){
            for(int j=1;j<=k;j++){
                for(int l=0;l<=sum1;l++){
                    for(int m=0;m<=sum2;m++){
                        if(dp[i-1][j][l][m] == true){
                            dp[i][j][l][m] = true;
                        }
                        else{
                            if(a[i]>=l && b[i]>=m && j-1>=1) {
                                if(dp[i-1][j-1][l-a[i]][m-b[i]] == true) dp[i][j][l][m] = true;
                            }
                        }
                    }
                }
            }
        }


        int min = Integer.MAX_VALUE;
        for(int i=0;i<=sum1;i++){
            for(int j=0;j<=sum2;j++){
                if(dp[n][k][i][j] == true){
                    min = Math.min(min,Math.min(sum1,sum2));
                }
            }
        }
        return min;
    }
    // consider q and r = 0;
    public static int question_74_easy(int[] arr,int n,int q,int p,int r){
        // ignore p elements
        int[][] dp = new int[n+1][p+1];
        dp[0][0]  = 0;
        for(int i=1;i<=n;i++){
            int option1 = 0;
            int option2 = 0;
            for(int j=0;j<=p;j++){
                dp[i][j] = Math.max(dp[i-1][j] + arr[i-1],dp[i-1][j-1]);
            }
        }
       return dp[n][p];
    }
    // here we take only r =0;
    public static int question_74_easy_2(int[] arr,int n,int q,int p,int r){
        // ignore p elements and q pairs
        int[][][] dp = new int[n+1][p+1][q+1];
        dp[0][0][0]  = 0;
        for(int i=1;i<=n;i++){
            for(int j=1;j<=p;j++){
                for(int k=1;k<=q;k++){
                    // arr[i] is a part of a single element ignore
                    // arr[i] is a part of a single element dont ignore
                    // arr[i] is a part of a pair ignore
                    // arr[i] is a part of a pair dont ignore it
                    int option1 = dp[i-1][p-1][q];
                    int option2 = arr[i-1] + dp[i-1][p][q];
                    int option3 = dp[i-2][p][q-1];
                    // option4 should be there but in doc it not there
                    int option4 = arr[i] + arr[i-1] + dp[i-2][p][q];
                    dp[i][j][k] = Math.max(option1,Math.max(option2,Math.max(option3,option4)));
                }
            }
        }

        return dp[n][p][q];
    }

    public static int question_74_actual(int[] arr,int n,int q,int p,int r){
        // ignore p elements and q pairs
        int[][][][] dp = new int[n+1][p+1][q+1][r+1];
        dp[0][0][0][0]  = 0;
        for(int i=1;i<=n;i++){
            for(int j=1;j<=p;j++){
                for(int k=1;k<=q;k++){
                    for(int m=1;m<=r;m++) {
                        int option1 = dp[i-1][j-1][k][m];
                        int option2 = arr[i] + dp[i-1][j][k][m];
                        int option3 = arr[i] + arr[i-1] + dp[i-2][j][k][m];
                        int option4 = dp[i-2][j][k-1][m];
                        int option5 = dp[i-3][j][k][m-1];
                        int option6 = arr[i] + arr[i-1] + arr[i-3] + dp[i-4][j][k][m-1];
                        dp[i][j][k][m] = Math.max(option1,Math.max(option2,Math.max(option3,Math.max(option4,Math.max(option5,option6)))));
                    }
                }
            }
        }

        return dp[n][p][q][r];
    }
    public static int question_75(int[] arr,int[][] price,int k){
        int n = arr.length;

        int[][] dp = new int[n+1][k+1];

        for(int i=1;i<=n;i++){
            for(int j=0;j<=k;j++){
                int val1 = dp[i-1][j];
                int val2 = Integer.MIN_VALUE;
                // not selecting
                for(int l=1;l<=j;l++){

                    val2 = Math.max(val2, price[i][l] + dp[i - 1][j - l]);

                }
            }
        }

        return dp[n-1][k];
    }
}

