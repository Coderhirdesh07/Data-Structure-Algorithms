package dsacourse851;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class UnderstandingTrees {
    public static void main(String[] args) {

    }
    public static int question_5(ArrayList<ArrayList<Integer>> list ,int[] values,int[] parent,int n,int m){
        int[] ans = new int[n];
        boolean[] vis = new boolean[n];

        for(int i=0;i<n;i++){
            if(vis[i]==false){
                question_5_helper(i,list,values,parent,vis,ans);
            }
        }
        int max = Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            max = Math.max(max,ans[i]);
        }
        return max;
    }
    // TODO :- Little Doubt in this question hence need one more time analysis.
    public static void question_5_helper(int node,ArrayList<ArrayList<Integer>> list,int[] values,int[] parent,boolean[] vis,int[] ans){
        vis[node] = true;
        for(int it:list.get(node)){
            if(vis[it]==false){
                question_5_helper(it,list,values,parent,vis,ans);
            }
        }

        // sum of node = max(sum of children) + value  of node

        int max = 0;
        for(int it:list.get(node)){
            if(parent[node]!=it) {
                max  = Math.max(max, ans[it]);
            }
        }
        ans[node] = values[node] + max;
    }

   public static int question_6(int node,ArrayList<ArrayList<Integer>> list ,int[] values,int[] parent,boolean[] vis,boolean[] hazardous,int[] prefixMax,int m){
       Queue<Integer> q1 = new LinkedList<>();
       if(hazardous[node]==true) values[node] = 1;
       else values[node] = 0;

       prefixMax[node] = Math.max(values[node],0);
       q1.add(node);
       vis[node] = true;
       while(!q1.isEmpty()){
           int it = q1.peek();
           q1.remove();
           for(int child:list.get(it)){
               if(hazardous[child]==true && vis[child]==false){
                   values[child] = values[it]+1;
               }
               else{
                   values[child] = 0;
               }
               vis[child]=true;
               prefixMax[child] = Math.max(prefixMax[it],0 );
               q1.add(child);
           }
       }

       int count  =0;
       for(int i=1;i<list.size();i++){
           if(list.get(i).size()==1){
               if(prefixMax[i]<=m) count++;
           }
       }
        return count;

   }


   public static int question_7(ArrayList<ArrayList<Integer>> list,int n,boolean[] infected,int m){
        int count = 0;

        Stack<Pair> s1 = new Stack<>();
        boolean[] vis = new boolean[n];

        if(infected[1]==true) s1.push(new Pair(1,1));
        else s1.push(new Pair(1,0));
        vis[1] = true;

        while(!s1.isEmpty()){
            Pair pair = s1.peek();
            s1.pop();
            int node = pair.first;
            int infected_count = pair.second;
            if(list.get(node).size()==0 && infected_count<=m) count++;
            for(int it:list.get(node)){
                if(vis[it]==false){
                    if(infected[it]==false){
                        s1.push(new Pair(it,infected_count));
                    }
                    else s1.push(new Pair(it,infected_count++));
                }
            }
        }
        return count;
   }

   // TODO need to do this question once again
   public static int question_11(int[][] edges,int n,int[] passengers){
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for(int i=0;i<=n;i++){
            list.add(new ArrayList<>());
        }

        for(int i=0;i<n;i++){
            int u = edges[i][0];
            int v = edges[i][1];
            list.get(u).add(v);
            list.get(v).add(u);
        }
        boolean[] vis = new boolean[n];
        int[] result = new int[n];
        dfs(1,list,n,vis,passengers,result);

        return result[1];
   }
   public static void dfs(int node,ArrayList<ArrayList<Integer>> list,int n,boolean[] vis,int[] passengers,int[] result){
        vis[node] = true;
        for(int it:list.get(node)){
            if(vis[it]==false){
                dfs(it,list,n,vis,passengers,result);
            }
        }
        result[node] = (passengers[node]==1)?1:0;
        for(int it:list.get(node)){
            result[node]+=result[it];
        }

   }

  static class Pair{
        int first;
        int second;
        Pair(int first,int second){
            this.first = first;
            this.second = second;
        }
   }
}
