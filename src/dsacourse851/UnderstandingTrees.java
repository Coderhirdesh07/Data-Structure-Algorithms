package dsacourse851;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

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
}
