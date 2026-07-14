package dsacourse851.youtube;

import java.util.*;

public class YoutubeProblems {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(n);
    }
    // Google question on trees and dp
    // TODO this problem has to be done once again.
    public static int question_1(ArrayList<ArrayList<Integer>> list,int n,int[] values){
        boolean[] vis = new boolean[n];
        int[] dp = new int[n];
        int[]  parent  = new int[n];
        parent[1] = 0;
        question_1_helper(1,list,n,values,vis,dp,parent);
        return dp[1];
    }
    public static void question_1_helper(int node,ArrayList<ArrayList<Integer>> list,int n,int[] values,boolean[] vis,int[] dp,int[] parent){
        vis[node] = true;

        for(int it:list.get(node)){
            if(vis[it]==false){
                parent[it] = node;
                question_1_helper(it,list,n,values,vis,dp,parent);
            }
        }
        if(values[node]==1) dp[node] = 1;
        for(int it:list.get(node)){
            dp[node]+=dp[it];
        }
    }

    // Zomato tree problem
//    public static int question_2(){
//
//    }

    // Tower Research problem on graph
    // TODO have not done  for component related test cases.
    public static int question_3(int[][] grid,int n,int m,int[] arr){

        if(n==1) return arr.length;
       ArrayList<ArrayList<Integer>> list  = new ArrayList<>();

     for(int i=0;i<=n;i++){
         list.add(new ArrayList<>());
     }

     for(int i=0;i<m;i++){
         int u = grid[i][0];
         int v = grid[i][1];
         list.get(u).add(v);
         list.get(v).add(u);
     }

     int[] result = new int[n];
     Arrays.fill(result,-1);
      boolean isGraphBiparte = question_3_bfs(list,n,result);
      if(isGraphBiparte==false) return -1;

        int count_odd = 0;
        int count_even = 0;

     for(int x:arr){
         if((x&1)==0) count_even++;
         else count_odd++;

     }
     int count_one = 0;
     int count_zero = 0;
     for(int x:result){
         if(x==1) count_one++;
         if(x==0) count_zero++;
     }

     int option1 = (int) (Math.pow(count_odd,count_one)) * (int) (Math.pow(count_even,count_zero));
     int option2 = (int) (Math.pow(count_odd,count_zero)) * (int) (Math.pow(count_even,count_one));
     return (option1 + option2);
    }
    public static boolean question_3_bfs(ArrayList<ArrayList<Integer>> list,int n,int[] result){

        for(int i=1;i<=n;i++){
            if(result[i]==-1){
                if(bfs(i,list,n,result,0) == false){
                    return false;
                }
            }
        }
        return  true;
    }
    public static boolean bfs(int node,ArrayList<ArrayList<Integer>> list,int n,int[] result,int colour){
        Queue<Integer> q1 = new LinkedList<>();
        q1.add(node);
        result[node] = colour;
        while(!q1.isEmpty()){
            int edg = q1.peek();
            q1.remove();
            for(int it:list.get(edg)){
                if(result[it]==-1){
                    q1.add(it);
                    result[it] = 1-result[edg];
                }
                else if(result[it] == result[edg]){
                    return false;
                }
            }
        }
        return true;
    }

//    // rubrik oa problem
//    public static int question_5_brute(ArrayList<ArrayList<Integer>> list,int n){
//
//    }
//    public static int question_de_shaw(){}

    // Cisco OA 14/06/26
    // given matrix start cell and end cell (4 direction allowed) find shortest path between start and end cell
    // if the matrix[i][j] = 0 you can travel for 1 you cannot
    //  also given a energy if it become 0 no further travel given charging cells if k charging are allowed
    public static int question_cisco(int sr,int sc,int dr,int dc,int[][] matrix,int battery,int[][] chargingCells,int k){
        int[] drow = {-1,0,1,0};
        int[] dcol = {0,-1,0,1};
        int n = matrix.length;
        int m = matrix[0].length;
        if(matrix[sr][sc]==1 || matrix[dr][dc] == 1) return -1;
        int[][][][] dp = new int[n][m][battery+1][k+2];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                for(int l=0;l<=battery;l++)
                Arrays.fill(dp[i][j][l],Integer.MIN_VALUE);
            }
        }
        dp[sr][sc][battery][0] = 0;

        Queue<Triplet> q1 = new LinkedList();
        q1.add(new Triplet(sr,sc,battery,0));
        while(!q1.isEmpty()){
            Triplet t1 = q1.peek();
            q1.remove();
            int r = t1.row;
            int c = t1.col;
            int left = t1.energyleft;
            int charge_count = t1.chargingdone;
            if(left==0 && r!=dr && c!=dc && charge_count>=k) return -1;
            for(int i=0;i<4;i++){
                int nrow = r + drow[i];
                int ncol = c + dcol[i];
                if(nrow>=0 && nrow<n && ncol>=0 && ncol<=m && dp[nrow][ncol][left][charge_count]==Integer.MIN_VALUE && matrix[nrow][ncol]==0 && charge_count<k && left>=1){
                    if(chargingCells[nrow][ncol]==1){
                        dp[nrow][ncol][battery][charge_count+1] = 1 + dp[r][c][left-1][charge_count];
                        q1.add(new Triplet(nrow, ncol, battery, charge_count+1));
                    }
                    else {
                        dp[nrow][ncol][left-1][charge_count] = 1 + dp[r][c][left][charge_count];
                        q1.add(new Triplet(nrow,ncol,left-1,charge_count));
                    }
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for(int i=0;i<=battery;i++){
            for(int j=0;j<=k+1;j++){
                if(dp[dr][dc][i][j]!=Integer.MIN_VALUE){
                    min = Math.min(min,dp[dr][dc][i][j]);
                }
            }
        }
        return min;

    }
    static class Triplet{
        int row;
        int col;
        int energyleft;
        int chargingdone;
        Triplet(int row,int col,int energyleft,int chargingdone){
            this.row = row;
            this.col=col;
            this.energyleft=energyleft;
            this.chargingdone = chargingdone;
        }
    }

}
