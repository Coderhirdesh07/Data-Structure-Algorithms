package OnlineAssement.Graph;

import java.util.*;

public class OAGraph {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();
        int l = sc.nextInt();
        int r= sc.nextInt();
        int k = sc.nextInt();
        int y = sc.nextInt();
        int[] arr = new int[n];
        int[] queries = new int[q];
        for(int i=0;i<n;i++){
            arr[i] = sc.nextInt();
        }
        for(int i=0;i<q;i++) queries[i] = sc.nextInt();
        ArrayList<ArrayList<Integer>> list = new ArrayList();
        for(int i=0;i<n;i++){
            list.add(new ArrayList());
        }
        for(int i=0;i<n;i++){
            list.get(arr[i]).add(i);
        }
        for(int i=0;i<q;i++){
            int ans = upperbound(list,y,l-1);
            if(ans+k-1<=r){
                System.out.println(-1);
            }
            else System.out.println(ans+k-1);
        }
    }
    public static  int upperbound(ArrayList<ArrayList<Integer>> list,int y,int target){
        int low = 0;
        int high = list.get(y).size()-1;
        int ans = -1;
        while(low<=high){
            int mid = (low + high)/2;
            if(list.get(y).get(mid)>target){
                ans=mid;
                high=mid-1;
            }
            else low=mid+1;
        }
        return ans;
    }

//    public static int contest_problem_4(int n,int[][] edges){
//
//    }
     public static void question_trees(int node,ArrayList<Integer>[] list,boolean[] vis,int[] answer,int[] sum,int[] value,int[] parent){
         vis[node] = true;

         for(int it:list[node]){
             if(!vis[it]){
                 parent[it] = node;
                 question_trees(it,list,vis,answer,sum,value,parent);
             }
         }

         for(int it:list[node]){
             if(it!=parent[node]) {
                 sum[node] += sum[it];
                 answer[node] = Math.max(answer[node],answer[it]);
             }
         }
         sum[node]+=value[node];
         answer[node]=Math.max(answer[node],sum[node]);
         System.out.println(answer[node]);

     }

     public static void question_trees_uber(int node,ArrayList<Integer>[] list,boolean[] vis,int[] value,int[] parent,int[] dp,int[] level){
        vis[node] = true;

        for(int it:list[node]){
            if(vis[it]==false){
                parent[it] = node;
                level[it] = level[node]+1;
                question_trees_uber(it,list,vis,value,parent,dp,level);
            }
        }

        dp[node] = value[node]^level[node];


        for(int it:list[node]){
            if(parent[node]!=it) {
                dp[node] += dp[it];
            }
        }

     }

     static  class Pair{
        int node;
        int wt;
        Pair(int node,int wt){
            this.node = node;
            this.wt = wt;
        }
     }
     // 0 -> {{1,2}}
     public static int questionLeetcodeContest(int n,ArrayList<ArrayList<Pair>> adj,int source,int target,int k){
        int low = 1;
        int high = -1;
        for(int i=0;i<n;i++){
            for(Pair it:adj.get(i)){
                int wt = it.wt;
                high = Math.max(high,wt);
            }
        }
        int ans = 0;
        while(low<=high){
            int mid = (low + high)/2;
            if(isPossible(mid,adj,source,target,k,n)){
                ans = mid;
                high = mid-1;
            }
            else{
                low  = mid+1;
            }
        }
        return ans;
     }
     public static boolean isPossible(int x,ArrayList<ArrayList<Pair>> adj,int source,int target,int k,int n){
        int[] distance = new int[n];
        for(int i=0;i<n;i++){
            for(Pair it:adj.get(i)){
                int dis = it.wt;
                if(dis<=x)it.wt = 0;
                else it.wt = 1;
            }
        }

        Arrays.fill(distance,Integer.MAX_VALUE);
        boolean[] vis = new boolean[n];
         Queue<Pair> q1 = new LinkedList<>();
         q1.add(new Pair(source,0));
         vis[source] = true;
         while(!q1.isEmpty()){
             Pair pair = q1.peek();
             q1.remove();
             int node = pair.node;
             int dis = pair.wt;

             for(Pair it:adj.get(node)){
                 int edge = it.node;
                 int weight = it.wt;
                 if(distance[edge]<weight+dis){
                     distance[edge] = dis + weight;
                     vis[edge] = true;
                     q1.add(new Pair(edge,distance[edge]));
                 }
             }
         }
        return (distance[target]<=k);
     }
}
