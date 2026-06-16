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

}
