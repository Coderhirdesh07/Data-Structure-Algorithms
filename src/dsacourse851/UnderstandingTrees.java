package dsacourse851;

import java.util.*;

public class UnderstandingTrees {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int[][] edges = new int[m][2];
        for(int i=0;i<m;i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            edges[i][0] = u;
            edges[i][1] = v;
        }
        int[] passengers = new int[n];
        for(int i=0;i<n;i++){
            passengers[i]  = sc.nextInt();
        }
        int ans = question_11(edges,n,passengers);
        System.out.println(ans);

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



   // Codeforces problem
   public static int question_7(int[][] matrix,int n,int[] danger){
     ArrayList<ArrayList<Integer>> list = new ArrayList<>();
     for(int i=0;i<n;i++){
         list.add(new ArrayList());
     }
     for(int i=0;i<n;i++){
        int u = matrix[i][0];
        int v  = matrix[i][1];
        list.get(u).add(v);
        list.get(v).add(u);
     }
     int[] result = new int[n];
     boolean[] vis = new boolean[n];
     boolean flag = true;
     question_7_dfs(1,list,danger,result,vis,flag);

     return result[1];

   }
   public static void question_7_dfs(int node,ArrayList<ArrayList<Integer>> list,int[] danger,int[] result,boolean[] vis,boolean flag){
        vis[node] = true;
        for(int it:list.get(node)){
            if(!vis[it]){
                question_7_dfs(it,list,danger,result,vis,!flag);
            }
        }
        result[node] = danger[node];
        for(int it:list.get(node)){
            if(flag==false){
                result[it]-=danger[it];
            }
            else result[it]+=danger[it];
        }
   }


   // swiggy oa problem
   public static int question_8(int n,int[] towns,int[][] roads){
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for(int i=0;i<=n;i++){
            list.add(new ArrayList<>());
        }
        for(int i=0;i<n;i++){
            int u = roads[i][0];
            int v = roads[i][1];
            list.get(u).add(v);
            list.get(v).add(u);
        }
        int[] parent = new int[n+1];
        int[] sum = new int[n+1];
        boolean[] vis = new boolean[n+1];
        question_8_dfs(1,list,sum,towns,vis,parent);

        int min = Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            int u = roads[i][0];
            int v = roads[i][1];
            int value1 = sum[1] - sum[u-1];
            int value2 = sum[v-1];
            int diff = Math.abs(value1-value2);
            min = Math.min(min,diff);
        }
        return min;

   }
   public static void question_8_dfs(int node,ArrayList<ArrayList<Integer>> list,int[] sum,int[] towns,boolean[] vis,int[] parent){
        vis[node] = true;

        for(int it:list.get(node)){
            if(vis[it]==false){
                parent[it] = node;
                question_8_dfs(it,list,sum,towns,vis,parent);
            }
        }
        int total = 0;

        for(int child:list.get(node)){
            if(parent[node]!=child){
                total+=towns[child-1];
            }
        }

        sum[node] = total + towns[node-1];
   }



   // serviceNow oa problem
    // TODO we will consider 1 based indexing
    // TODO please do it once again as i was not able to understand the problem and the also the solution did not come to me it uses dp on trees
    public static int question_10(int[] arr,int n){
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for(int i=0;i<=n;i++) list.add(new ArrayList<>());
        for(int i=0;i<n;i++){
            int u  = i+1;
            int v = arr[i];
            list.get(u).add(v);
            list.get(v).add(u);
        }
        boolean[] vis = new boolean[n+1];
        int[] parent = new int[n+1];
        int[] count = new int[n+1];
        Arrays.fill(count,1);
        question_10_dfs(0,list,vis,parent,count);
        int total = 0;
        for(int x:count) total+=x;
        if(count[0]<=total/2) return count[0];
       return -1;

    }
    public static void question_10_dfs(int node,ArrayList<ArrayList<Integer>> list,boolean[] vis,int[] parent,int[] count){
        vis[node] = true;
        for(int it:list.get(node)){
            if(vis[it]==false){
                parent[it] = node;
                question_10_dfs(it,list,vis,parent,count);
            }
        }

        for(int it:list.get(node)){
            if(parent[node]!= it){
                count[node]++;
            }
        }
    }
    // google oa problem
    // TODO need to do this question once again
    public static int question_11(int[][] edges,int n,int[] passengers){
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for(int i=0;i<n;i++){
            list.add(new ArrayList<>());
        }

        for(int i=0;i<edges.length;i++){
            int u = edges[i][0]-1;
            int v = edges[i][1]-1;
            list.get(u).add(v);
            list.get(v).add(u);
        }

        int[] sum = new int[n];
        boolean[] vis = new boolean[n];
        int[] parent = new int[n];
        Arrays.fill(parent,-1);
        Arrays.fill(sum,0);
        question_11_bfs(1,list,passengers,sum,vis,parent);

        return sum[1];
    }
    public static void question_11_bfs(int node,ArrayList<ArrayList<Integer>> list,int[] passenger,int[] sum,boolean[] vis,int[] parent){
         vis[node] = true;
         for(int it:list.get(node)){
             if(!vis[it]){
                 passenger[it] = node;
                 question_11_bfs(it,list,passenger,sum,vis,parent);
             }
         }
        if(passenger[node]==1) passenger[node]=1;
         for(int it:list.get(node)){
             if(parent[node]!=it){
                 sum[node]+=sum[it];
             }
         }

    }

   // Rubrik oa problem
   public static int question_12(int n,int m,int k,int[] values,int[] parent){
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for(int i=0;i<=n;i++){
            list.add(new ArrayList<>());
        }
        // 1 2 3 4 5
       // -1 1 1 2 3
        for(int i=1;i<n;i++){
            int u = parent[i];
            int v = i+1;
            list.get(u).add(v);
            list.get(v).add(u);
        }
        boolean[] vis = new boolean[n+1];
        int[] sum = new int[n+1];
        question_12_dfs(1,values,list,parent,vis,sum);

        int count = 0;

        // 1->2
       // 2 -> 1 ,4
       // TODO this part needs to be done again
        for(int i=1;i<=n;i++){
            for(int child:list.get(i)){
                if(parent[i]!=child){
                    if(sum[i]>k) count++;
                }
            }
        }


        return count>m?-1:count;

   }
   public static void question_12_dfs(int node,int[] values,ArrayList<ArrayList<Integer>> list,int[] parent,boolean[] vis,int[] sum){
        vis[node] = true;
        for(int it:list.get(node)){
            if(vis[it]==false){
                question_12_dfs(it,values,list,parent,vis,sum);
            }
        }
        int total = 0;
        for(int child:list.get(node)){
            if(parent[node]!=child){
                total+=values[child];
            }
        }
        sum[node] = total + values[node];
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
