package OnlineAssement.Dp;

import java.util.*;

public class OADP {
    public static void main(String[] args) {
//        int [] arr = {1,1,3,1,1,1,2};
//        boolean res = oa_question_amazon_2(arr,7);
//        int[] a = {1,2,3};
//        int[] b = {3,2,1};
//        int[] arr = {5 ,-2,3,-100,4,5};
//        int res = question_stripe_follow_up(arr,6);
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] arr = new long[n];
        for(int i=0;i<n;i++){
            arr[i] = sc.nextInt();
        }
        long ans = cses(arr,n);

        System.out.println(ans);

    }
    public static long cses(long[] arr,int n){
        HashSet<Long> s1 = new HashSet<>();
        for(long x:arr){
            s1.add(x);
        }
        return s1.size();
    }
    // Meta OA question
    public static int oa_question(int index1,int index2,int direction,int[][] grid,int n,int m,int[][][] dp){
        // 0 -> up    1 -> left  2 -> right
        if(index1<0 || index2<0 || index1>=n || index2>=m) return Integer.MIN_VALUE;
        if(index1==0 && index2==0){
            if(direction==0) return 1;
            if(direction==1) return 1;
            if (direction==2) return Integer.MIN_VALUE;
        }

        if(dp[index1][index2][direction]!=-1) return dp[index1][index2][direction];
        int option1_left = 1 + Math.max(oa_question(index1,index2-1,1,grid,n,m,dp),oa_question(index1,index2-1,0,grid,n,m,dp));
        int option2_right = 1 + Math.max(oa_question(index1,index2+1,2,grid,n,m,dp),oa_question(index1,index2+1,0,grid,n,m,dp));
        int option3_up = 1 + Math.max(oa_question(index1-1,index2,1,grid,n,m,dp),Math.max(oa_question(index1-1,index2,0,grid,n,m,dp),oa_question(index1-1,index2,2,grid,n,m,dp)));

        return dp[index1][index2][direction] = Math.max(option1_left,Math.max(option2_right,option3_up));
    }


    public static int upperBound(ArrayList<Integer> list, int target){
        int low = 0;
        int high = list.size()-1;
        int ans = -1;
        while(low<=high){
            int mid = (low+ high)/2;
            if(list.get(mid)>target){
                ans = mid;
                high=mid-1;
            }
            else low = low+1;
        }
        return ans;
    }
    public static boolean oa_question_amazon(int[] arr,int n){
        int[] pmin = new int[n];
        pmin[0] = arr[0];
        for(int i=1;i<n;i++){
            pmin[i] = Math.min(arr[i],pmin[i-1]);
        }
        TreeSet<Integer> list1 = new TreeSet<>();
        boolean ans = false;

        for(int j=n-2;j>=1;j--){
            int min_left = pmin[j-1];
            // upperbound(list,target);
            int min_right = list1.ceiling(min_left);
            if(min_right>min_left && arr[j]>min_right){
                ans = true;
                break;
            }
            list1.add(arr[j]);

        }
        return ans;
    }

    public static boolean oa_question_amazon_2(int[] arr,int n){
        int[] pmin = new int[n];
        pmin[0] = arr[0];
        for(int i=1;i<n;i++){
            pmin[i] = Math.min(arr[i],pmin[i-1]);
        }

        boolean ans = false;
        int current = Integer.MIN_VALUE;
        Stack<Integer> s1 = new Stack<>();

        // 5 4 7
        for(int i=n-1;i>=1;i--){
            int minLeft = pmin[i-1];
            while(!s1.isEmpty() && s1.peek()<=arr[i]){
                current = s1.pop();
            }
            s1.push(arr[i]);
            if(minLeft<current && current<arr[i]){
                ans = true;
                break;
            }
        }
        return ans;
    }


   // 1 2 2     l->2 3  s-> 4 5
    public static int oa_question_amazon_3(int ind1,int ind2,int[] worklist,int[] l,int[] s,int n){
      int[][] dp = new int[n][n];
      dp[0][0] = 0;
      for(int i=1;i<n;i++){
          if(worklist[i-1]==worklist[i]){
              dp[i][0] = dp[i-1][0] + s[worklist[i]];
              dp[0][i] = dp[0][i-1] + s[worklist[i]];
          }
          else{
              dp[i][0] = dp[i-1][0] + l[worklist[i]];
              dp[0][i] = dp[0][i-1] + l[worklist[i]];
          }
      }
        int ans = (int) 1e9;
      for(int i=1;i<n;i++){
          for(int j=1;j<n;j++){
              if(i!=j){
                  if(i>j){
                      if(i>j+1){
                          if(worklist[i-1]==worklist[i]){
                              dp[i][j] = dp[i-1][j] + s[worklist[i]];
                          }
                          else dp[i][j]  = dp[i-1][j] + l[worklist[i]];
                      }
                      if(i==j+1){
                          int ind  = 2;
                          while(i-ind>=0){
                              int p1 = 0;
                              if(worklist[i-ind]==worklist[i]){
                                  p1 = dp[i-ind][j] + s[worklist[i]];
                              }
                              else p1 = dp[i-ind][j] + l[worklist[i]];
                              dp[i][j] = Math.min(dp[i][j],p1);
                              ind++;
                          }

                      }
                  }
                  else{
                      if(j>i+1){
                          if(worklist[j]==worklist[j-1]){
                              dp[i][j] = dp[i][j-1] + s[worklist[j]];
                          }
                          else{
                              dp[i][j] = dp[i][j-1] + l[worklist[j]];
                          }

                      }
                      if(j==i+1){
                          int ind = 2;
                          while(j-ind>=0){
                              int p2 = 0;
                              if(worklist[j-ind]==worklist[j]){
                                  p2 = dp[i][j-ind] + s[worklist[j]];
                              }
                              else p2 = dp[i][j-ind] + l[worklist[j]];
                              dp[i][j] = Math.min(dp[i][j],p2);
                              ind++;
                          }


                      }
                  }
              }
          }
      }
      for(int i=0;i<n;i++){
          ans = Math.min(dp[n-1][i],ans);
          ans = Math.min(dp[i][n-1],ans);
      }
      return ans;
    }


    public static int[] nextGreater(int[] nums,int n){
        int[] ans = new int[n];
        Stack<Integer> s1 = new Stack<>();
        s1.push(nums[n-1]);
        ans[n-1] = n;
        for(int i=n-2;i>=0;i--){
            while(!s1.isEmpty() && nums[s1.peek()]<=nums[i]){
                s1.pop();
            }
            if(!s1.isEmpty()){
                ans[i] = i;
            }
            else ans[i]=n;
            s1.push(i);
        }
        return ans;
    }
    public static int[] previousGreater(int[] nums,int n){
        int[] ans = new int[n];
        Stack<Integer> s1 = new Stack<>();
        s1.push(nums[0]);
        ans[0] = -1;
        for(int i=1;i<n;i++){
            while(!s1.isEmpty() && nums[s1.peek()]<=nums[i]){
                s1.pop();
            }
            if(!s1.isEmpty()){
                ans[i] = s1.peek();
            }
            else ans[i]=-1;
            s1.push(i);
        }
        return ans;
    }
    public static int oa_question_tower_research(int[] arr,int n){
        int[] pGreater = previousGreater(arr,n);
        int[] nGreater = nextGreater(arr,n);
        int sum = 0;
        for(int i=0;i<n;i++){
            int left = i-pGreater[i]-1;
            int right=  nGreater[i]-i-1;
            sum+=((left+1)*(right+1))*arr[i];
        }
        return sum;
    }

    // google graph question
    public  static int oa_question_2(int row,int col,int[][] grid,int n,int m,boolean[][] vis,int[][] distance,int[] drow,int[] dcol,int targetRow,int targetCol){
        // drow = {-1,0,1,0}
        // dcol = {0,1,0,-1}
        Queue<Triplet> q1 = new LinkedList();
        q1.add(new Triplet(row,col,0));
        distance[row][col]=0;
        vis[row][col] = true;
        while(q1.isEmpty()){
            Triplet it = q1.peek();
            int sRow = it.r;
            int sCol = it.c;
            int dist  = it.dis;
            if(sRow==targetRow && sCol==targetCol) return dist+1;
            q1.remove();
            for(int i=0;i<4;i++){
                int nRow = sRow + drow[i];
                int nCol = sCol + dcol[i];
                if(nRow>=0 && nRow<n && nCol>=0 && nCol<m  && grid[nRow][nCol]!=4 && vis[nRow][nCol]==false){
                    q1.add(new Triplet(nRow,nCol,dist+1));
                    distance[nRow][nCol] = dist+1;
                    vis[nRow][nCol] = true;
                }
                else if(vis[nRow][nCol]==true){
                    q1.add(new Triplet(nRow,nCol,dist));
                }
            }
        }
        return -1;

    }

     //  google trees + dp  question
//    public static  int oa_question_google_swe(int node,ArrayList<ArrayList<Integer>> list,int[][] dp,boolean[] vis,int[] parent,int[] value){
//        // 0  for node not included and 1 for  node included
//        vis[node]  = true;
//        for(int it:list.get(node)){
//            if(!vis[it]){
//                oa_question_google_swe(it,list,dp,vis,parent,value);
//                parent[it] = node;
//            }
//        }
//        dp[node][1] = value[node];
//        dp[node][0] = Integer.MIN_VALUE;
//        for(int child:list.get(node)){
//
//        }
//        return Math.max(dp[1][0],dp[1][1]);
//    }
    public static int question_infosys(int[] arr,int k,int n,int[] dp){
        dp[0]=0;
        dp[1] = arr[0]*arr[1];
        for(int i=2;i<n;i++){
            int ind = Math.min(0,i-k);
            for(int j=ind;j<i;j++){
                int res = dp[j] + arr[j];
                dp[j] = Math.min(dp[j],res);
            }
        }
        return dp[n-1];
    }

    public static int question_infosys_2(int[] A,int[] B,int[][] s,int[][][] dp ,int n,int m){
        // 0 - from A      1- from B    n is length of A and m is length of B
        dp[0][1][1] = 0;
        dp[1][0][0] = 0;

        for(int i=1;i<n;i++){
            for(int j=1;j<m;j++){

                dp[i][j][0] = Math.max(dp[i-1][j][0] + s[A[i-1]][A[i]] , Math.max(dp[i][j][0] , dp[i-1][j][1]+s[B[j]][A[i]]));

                dp[i][j][1] = Math.max(dp[i][j-1][0] + s[A[i]][B[j]] , Math.max(dp[i][j][1],dp[i][j-1][1] + s[B[j-1]][B[j]]));
            }
        }
        return Math.max(dp[n-1][m-1][0],dp[n-1][m-1][1]);
    }

    public static int[] peak_calculator(int[] arr,int n){
        int[] prefix = new int[n];
        int max = Math.max(arr[0],arr[n-1]);
        prefix[0] = Math.abs(arr[0]-max)+1;

        for(int i=1;i<n-1;i++){
            int maxElement = Math.max(arr[i],arr[i-1]);
            prefix[i] = Math.abs(maxElement - arr[i])+1;
        }
        prefix[n-1] = Math.abs(arr[n-1]-max)+1;
        return prefix;

    }
    public static int question_leetcode_contest_4(int[] arr,int n,int k,int[][][] dp,int[] prefix){
        // 0 -> i-th element is not the peak
        // 1 => i-th element is the j-th peak

        if(k>Math.floor(n/2)) return -1;
        dp[0][1][0] = prefix[n-1];
        dp[0][1][1] = prefix[0];
        for(int i=0;i<n;i++){
            for(int j=0;j<=k;j++){
                Arrays.fill(dp[i][j],Integer.MAX_VALUE);
            }
        }

        for(int i=1;i<n;i++){
            dp[i][1][0] = Math.min(dp[i-1][1][0],dp[i-1][0][1]);
        }
        for(int i=1;i<n;i++){
            dp[i][1][1] = Math.min(dp[i-1][1][1],prefix[i]);
        }

        for(int i=1;i<n;i++){
            for(int j=1;j<=k;j++){
                dp[i][j][0] = Math.min(dp[i-1][j][0],dp[i-1][j][1]);
                dp[i][j][1] = prefix[i]+dp[i-1][j-1][1];
            }
        }
        return dp[n-1][k][0];
    }

    // a- 3,1 6
    // b- 2,7,4

    public static int question_oa_jpmorgan(int[] a,int[] b,int k,int n){
        HashMap<Integer,Integer> m1 = new HashMap<>();
        for(int i=0;i<n;i++){
            m1.put(b[i],a[i]);
        }
        int max = 0;
        Arrays.sort(b);
        int j = n-1;
        while(k>0 && j>=0){
            if(m1.get(b[j])>=k) {
                int freq = Math.min(m1.get(b[j]),k);
                max+=b[j]*freq;
                k-=freq;
            }
            else{
                k-=m1.get(b[j]);
                max+=b[j]*m1.get(b[j]);
            }
            j--;
        }
        return max;
    }

//    public static int question_salesforce(String input,int n,int[][][] dp){
//
//
//        for(int i=0;i<n;i++){
//            char f = input.charAt(i);
//            if(f!='#') {
//                for (int k = 0; k < n; k++) {
//                    for (int j = 0; j < 26; j++) {
//                        if (j == f - 'a') {
//                            continue;
//                        } else dp[i][k][j] = Integer.MIN_VALUE;
//                    }
//                }
//            }
//        }
//
//        for(int i=0;i<n;i++){
//            char f = input.charAt(i);
//            for(int j=0;j<26;j++){
//                if(f=='#'){
//                    for(int y=0;y<26;y++) {
//                        int max = Integer.MIN_VALUE;
//                        for (int k = 0; k < 26; k++) {
//                            for (int x =0;x<i;x++){
//
//                            }
//                        }
//                    }
//                    dp[i][1][]
//
//                }
//                else{
//                    // int max = Integer.MIN_VALUE;
//                    int first = f-'a';
//                    int max = Integer.MIN_VALUE;
//                    for(int k=0;k<26;k++){
//                        if(k!=first) {
//                            for (int x = 0; x < i; x++) {
//                                max = 1 + Math.max(max, dp[i - 1][x][k]);
//                            }
//                        }
//                    }
//                    dp[i][1][first] = max;
//                }
//            }
//        }
//        return dp[n-1][2][0];
//
//    }

    public static int question_stripe_1(int[] arr,int n){
        // here we have to partition the arr in such a way
        // s1-s2+s3 is max.
        int sum = 0;
        for(int x:arr) {
            sum += x;
        }

        for(int i=0;i<n;i++){
            arr[i] = -1 * arr[i];
        }
        int maxSum = 0;
        int curSum = 0;
        for(int i=1;i<n-1;i++){
            curSum+=arr[i];
            if(curSum>maxSum){
                maxSum = curSum;
            }
            else{
                curSum = 0;
            }
        }
        maxSum = (-1) * (maxSum);

        return (sum - 2*maxSum);
    }
    public static  int question_stripe_follow_up(int[] arr,int n){

        int sum = 0;
        for(int x:arr){
            sum+=x;
        }

        for(int i=0;i<n;i++){
            arr[i] = -1 * arr[i];
        }
        int[] prefixMax = new int[n];
        prefixMax[0] = arr[0];
        for(int i=1;i<n;i++){
            prefixMax[i] = Math.max(prefixMax[i-1] + arr[i],arr[i]);
        }

        int[] suffixMax = new int[n];

        suffixMax[n-1] = arr[n-1];
        for(int i=n-2;i>=0;i--){
            suffixMax[i] = Math.max(suffixMax[i+1]+arr[i],arr[i]);
        }


        int max_s2_s4 = Integer.MIN_VALUE;
        for(int j=1;j<n-2;j++){
            max_s2_s4 = Math.max(prefixMax[j]+suffixMax[j+2],max_s2_s4);
        }
        max_s2_s4 = -1*max_s2_s4;

        return sum - 2*(max_s2_s4);
    }

    // 5 -2 3 -100 4 5
    // 5 3,6 ,

    public static int question_salesforce_intern(int[][] arr,int[] dp,int n){
        // size of dp is n+5
        Arrays.sort(arr,(x,y)->Integer.compare(x[1],y[1]));

        int ans = Integer.MIN_VALUE;

        for(int i=0;i<arr.length;i++){
            int start = arr[i][0];
            int end = arr[i][1];
            int cost = end - start + arr[i][2];
            int cost_2 = 0;
            for(int j=0;j<i;j++){
                int nstart = arr[j][0];
                int nend = arr[j][1];
                if(nstart>=end) {
                    cost_2 = Math.max(cost_2, dp[nend]);
                }
            }
            dp[end] = Math.max(dp[end],cost+cost_2);
            ans = Math.max(ans,dp[end]);
        }
        return ans;

    }
     // this is from tiling dp
    // int[] dp = new int[m]
    public static int question_tiling_dp(char[][] arr,int m,int[] dp){


        dp[0] = (arr[0][0]!=arr[1][0])?1:0;

        int hor_res = 0;
        if(arr[0][1]!=arr[0][0]) hor_res++;
        if(arr[1][1]!=arr[1][0]) hor_res++;
        int ver_res = 0;
       if(arr[0][0]!=arr[0][1]) ver_res++;
       if(arr[0][1]!=arr[1][1]) ver_res++;

        dp[1] = Math.min(hor_res,ver_res);

           for(int i=2;i<m;i++){
               int cost_vertical = 0;
                if(arr[0][i]!=arr[1][i]) cost_vertical++;

               int cost_horizontal = 0;
               if(arr[0][i]!=arr[0][i-1]) cost_horizontal++;
               if(arr[1][i]!=arr[1][i-1]) cost_horizontal++;

               dp[i] = Math.min(cost_vertical+dp[i-1],cost_horizontal + dp[i-2]);
           }


        return dp[m-1];
    }
    public static  int[] question_agoda_1(int[][] queries,int n,int k,int length){
       int[] arr = new int[length];
       int[] ans = new int[length];
        for(int i=0;i<n;i++){
            int start = queries[i][0];
            int end = queries[i][1];
            arr[start] += 1;
            arr[end+1] -= 1;
        }
        int[] prefixSum = new int[length];

        for(int i=1;i<length;i++){
            prefixSum[i] += prefixSum[i-1];
        }
        for(int i=0;i<length;i++){
            if(prefixSum[i]>=k){
                prefixSum[i]=1;
            }
        }

        // prefix sum once again
        for(int i=1;i<length;i++){
            prefixSum[i] = prefixSum[i-1];
        }


        for(int i=0;i<n;i++){
            int start = queries[i][0];
            int end = queries[i][1];
           ans[i] = prefixSum[end] - prefixSum[start-1];
        }

        return ans;

    }


    public static int question_leetCode_question_4(int[] arr,int n,int[][] dp){
        // 0 -> will represent downward
        // 1 -> will represent upward
        // dp = new int[n][2]

        int max = Integer.MIN_VALUE;

        dp[0][0] = arr[0];
        dp[0][1] = arr[0];

        for(int i=1;i<n;i++){
            for(int j=i;i>=0;j--) {
                // downward case

                if (arr[i]<arr[j]) {
                    dp[i][0] = Math.max(dp[i][0],arr[i] + dp[j][1]);
                }
                // upward case
                if(arr[i]>arr[j]){
                    dp[i][1] = Math.max(dp[i][1],arr[i] + dp[j][0]);
                }
                max = Math.max(max,Math.max(dp[i][0], dp[i][1]));
            }
        }
        return max;
    }
    // return max possible xor sum
    public static int uber_interview_question(int[] arr,int n,int x){
        int[] dp0 = new int[n];
        int[] dp1 = new int[n];

        dp0[0] = 0;
        dp1[0] = 0;

        dp0[1] = Math.max(arr[0]^arr[1] , (arr[0]+x)^arr[1]);
        dp1[1] = Math.max(arr[0] ^ (arr[1]+x) , (arr[0]+x) ^ (arr[1]+x));

        for(int i=2;i<n;i++){
            // x is not added to i th element
            dp0[i] =  dp0[i-1] + Math.max(arr[i-1]^arr[i],(arr[i-1]+x)^arr[i]);
            // x is added to i th element
            dp1[i] = dp1[i-1] + Math.max(arr[i-1]^(arr[i]+x) , (arr[i-1]+x)^(arr[i]+x));
        }

        return Math.max(dp0[n-1],dp1[n-1]);
    }
    // Google interview question part-1
    public static int google_interview_question(int[] arr,int n){
        int[] dp = new int[n];
        dp[0] = Math.max(0,arr[0]); // 2
        dp[1] = Math.max(0,Math.max(arr[0],arr[1])); // 2

        for(int i=2;i<n;i++){
            int pick = 0;
            int notpick = 0;

            pick = dp[i-2] + arr[i];

            notpick = dp[i-1];

            dp[i] = Math.max(pick,notpick);
        }
        return dp[n-1];
    }

    public static int google_interview_question_follow_up1(int[] arr,int n,int k){
        int[][] dp  = new int[n][k+1];

        for(int i=0;i<n;i++){
            Arrays.fill(dp[i],Integer.MIN_VALUE);
        }
        dp[0][0] = 0;
        dp[0][1] = arr[0];
        dp[1][1] = Math.max(arr[1],arr[0]);

        for(int i=2;i<n;i++){
            for(int j=0;j<=k;j++){
                int notpick = 0;
                int pick = 0;
                notpick = dp[i-1][j];
                pick = Math.max(dp[i - 2][j - 1] + arr[i], dp[i - 1][j]);
               dp[i][j] = Math.max(pick,notpick);
            }
        }
        return dp[n-1][k];
    }


    static class Triplet{
        int r;
        int c;
        int dis;
        Triplet(int r,int c,int dis){
            this.r=r;
            this.c=c;
            this.dis=dis;
        }
    }
}

