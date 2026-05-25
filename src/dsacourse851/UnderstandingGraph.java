package dsacourse851;

import java.lang.reflect.Array;
import java.util.*;

public class UnderstandingGraph {
    public static void main(String[] args) {
        char[][] matrix = new char[6][6];
        Scanner sc = new Scanner(System.in);
        int ind =0;
        for(int i=0;i<6;i++){
            String input = sc.next();
            char[] ans = input.toCharArray();
            for(int j=0;j<ans.length;j++){
                matrix[ind][j] = ans[j];
            }
            ind++;
        }
        int result = question_11(matrix,6,6);
        System.out.println(result);

    }
    public static void bfs(int start, ArrayList<ArrayList<Integer>> list,int n){
        Queue<Integer> q1 = new LinkedList();
        boolean[] vis = new boolean[n];
        q1.add(start);

        vis[start] = true;
        while(!q1.isEmpty()){
            int node = q1.peek();
            q1.remove();

            for(int it:list.get(node)){
                if(!vis[it]){
                    q1.add(it);
                    vis[it] = true;
                }
            }
        }
    }
    // cisco oa question
    public static int[] question_6(int[][] connections,int[][] query,int n,int m){
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for(int i=0;i<n;i++){
            list.add(new ArrayList<>());
        }
        for(int i=0;i<m;i++){
            list.get(connections[i][0]).add(connections[i][1]);
            list.get(connections[i][1]).add(connections[i][0]);
        }
       ArrayList<Integer> ans = new ArrayList<>();
        int[] arr = new int[n];
        Arrays.fill(arr,0);
        for(int i=0;i<query.length;i++){
            int u = query[i][0];
            int v = query[i][1];
            if(u==2){
                arr[v] = -1;
            }
            else{
                if(arr[v]!=-1){
                    ans.add(v);
                }
                else {
                    int val = bfs_6(v,list,n,arr);
                    ans.add(val);
                }
            }
        }
        int[] result = new int[ans.size()];
        for(int i=0;i<ans.size();i++){
            result[i] = ans.get(i);
        }
        return result;
    }
    public static int  bfs_6(int node,ArrayList<ArrayList<Integer>> list,int n,int[] arr){
        Queue<Integer> q1 = new LinkedList<>();
        q1.add(node);
        boolean[] vis = new boolean[n];
        vis[node] = true;
        int min = Integer.MAX_VALUE;
        while (!q1.isEmpty()){
            int edg = q1.peek();
            q1.remove();
            for(int it:list.get(edg)){
                if(arr[it]!=-1){
                    q1.add(it);
                    min = Math.min(min,it);
                    vis[it] = true;
                }
            }
        }
        return (min==Integer.MAX_VALUE)?-1:min;
    }
    // question media.net oa problem
    public static int question_8(int[][] matrix,int n,int m){
        int[][] sed = new int[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(matrix[i][j]!=-1){
                   int res =  helper(i,j,matrix,n,m);
                   sed[i][j] = res;
                }
                else sed[i][j]=-1;
            }
        }
        int sum = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                sum+=sed[i][j];
            }
        }
        int mod = (int) 1e9+7;
        return sum%mod;
    }
    public static int helper(int r,int c,int[][] matrix,int n,int m){
        int[] drow = {-1,0,1,0};
        int[] dcol = {0,-1,0,1};
        boolean[][] vis = new boolean[n][m];
        int value = 0;
       Queue<Pair> q1 = new LinkedList();
       q1.add(new Pair(r,c));
       vis[r][c]  = true;
       while(!q1.isEmpty()){
           Pair it = q1.peek();
           q1.remove();
           int row = it.first;
           int col = it.second;
           for(int i=0;i<4;i++){
               int nrow = row + drow[i];
               int ncol = col + dcol[i];
               if(nrow>=0 && nrow<n && ncol>=0 && ncol<m && vis[nrow][ncol]==false && matrix[nrow][ncol]!=-1){
                   q1.add(new Pair(nrow,ncol));
                   vis[nrow][ncol] = true;
               }
           }
       }
       for(int i=0;i<n;i++){
           for(int j=0;j<m;j++){
               if(vis[i][j]==false && matrix[i][j] !=-1 && matrix[i][j]%matrix[r][c]==0) value+=matrix[i][j];
           }
       }
       return value;
    }
    public static int question_8_optimise(int[][] matrix,int n,int m){

        boolean[][] vis = new boolean[n][m];
        HashMap<Pair,Integer> m1 = new HashMap<>();
        int count= 0;
        int ind = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                Pair it = new Pair(i,j);
                if(matrix[i][j]!=-1){
                    if(m1.containsKey(it)==false) {
                        helper_bfs(i, j, matrix, vis, n, m, m1, ind);
                        ind++;
                    }

                }
                else count++;
            }
        }
        int sum = count;


        return sum;
    }
    public static void helper_bfs(int r,int c,int[][] matrix,boolean[][] vis,int n,int m,HashMap<Pair,Integer> m1,int level){
        int[] drow = {-1,0,1,0};
        int[] dcol = {0,-1,0,1};

        Queue<Pair> q1 = new LinkedList();
        m1.put(new Pair(r,c),level);
        while(!q1.isEmpty()){
            Pair it = q1.peek();
            q1.remove();
            int row = it.first;
            int col = it.second;
            for(int i=0;i<4;i++){
                int nrow = row + drow[i];
                int ncol = col + dcol[i];
                if(nrow>=0 && nrow<n && ncol>=0 && ncol<m && m1.containsKey(new Pair(nrow,ncol))==false && matrix[nrow][ncol]!=-1){
                    q1.add(new Pair(nrow,ncol));
                    m1.put(new Pair(nrow,ncol),level);
                }
            }
        }
    }


    public static int question_10(int[][] f,int[][] g,int n1,int m1,int n2,int m2){
        ArrayList<ArrayList<Integer>> list1 = new ArrayList<>();
        ArrayList<ArrayList<Integer>> list2 = new ArrayList<>();
        for(int i=0;i<n1;i++){
            list1.add(new ArrayList<>());
        }
        for(int i=0;i<n2;i++){
            list2.add(new ArrayList<>());
        }
        for(int i=0;i<n1;i++){
            int u = f[i][0];
            int v = f[i][1];
            list1.get(u).add(v);
            list1.get(v).add(u);
        }
        ArrayList<ArrayList<Integer>> component = helper_10(list1,n1,m1);
        ArrayList<ArrayList<Integer>> component2 = helper_10(list2,n2,m2);

        for(int i=0;i<n2;i++){
            int u = g[i][0];
            int v = g[i][1];
            list2.get(u).add(v);
            list2.get(v).add(u);
        }

        return component.size() + component2.size();
    }
    public static ArrayList<ArrayList<Integer>> helper_10(ArrayList<ArrayList<Integer>> adj,int nodes,int edges){
        ArrayList<ArrayList<Integer>> components = new ArrayList();
        boolean[] vis = new boolean[nodes];
        for(int i=0;i<nodes;i++){
            components.add(new ArrayList<>());
        }
        int ind = 0;
        for(int i=0;i<nodes;i++){
            if(vis[i]==false){
                helper_10_bfs(i,adj,nodes,vis,ind,components);
                ind++;
            }
        }
        return components;
    }
    public static void helper_10_bfs(int node,ArrayList<ArrayList<Integer>> list,int nodes,boolean[] vis,int ind,ArrayList<ArrayList<Integer>> compo){
        Queue<Integer> q1 = new LinkedList();
        q1.add(node);
        compo.get(ind).add(node);
        vis[node] = true;
        while(!q1.isEmpty()){
            int adj = q1.peek();
            q1.remove();
            for(int it:list.get(adj)){
                if(vis[it]==false){
                    q1.add(it);
                    vis[it] = true;
                    compo.get(ind).add(it);
                }
            }
        }
    }
    // codeforces graph problem
    // true is for row change and false if for column change
    // got a little bit of hint for the question
    // TODO this is question does not give correct answer and i have not optimised it.
    public static int question_11(char[][] input,int n,int m){
        int max = -1;
        // try to change a row
        for(int i=0;i<n;i++){
            int res2 = question_11_helper(i,true,input,n,m);
            max = Math.max(max,res2);
        }
        // try to change a column
        for(int i=0;i<m;i++){
            int res = question_11_helper(i,false,input,n,m);
            max = Math.max(max,res);
        }
        return max;

    }
    public static int question_11_helper(int index,boolean flag,char[][] input,int n,int m){
        if(flag==true){
            for(int i=0;i<m;i++){
                input[index][i]='#';
            }
        }
        else{
            for(int i=0;i<n;i++){
                input[i][index] = '#';
            }
        }

        ArrayList<ArrayList<Pair>> list = new ArrayList();

        for(int i=0;i<=n+m;i++){
            list.add(new ArrayList());
        }
        boolean[][] vis = new boolean[n][m];
        int ind = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if( vis[i][j]==false &&  input[i][j]=='#'){
                    question_11_bfs(i,j,input,n,m,list,ind,vis);
                    ind++;
                }
            }
        }
        int max = 0;
        for(int i=0;i<list.size();i++){
            max = Math.max(max,list.get(i).size());
        }
        return max;
    }
    public static void question_11_bfs(int r,int c,char[][] input,int n,int m,ArrayList<ArrayList<Pair>> list,int index,boolean[][] vis){
        int[] drow = {-1,0,1,0};
        int[] dcol = {0,-1,0,1};
        Queue<Pair> q1 = new LinkedList();
        q1.add(new Pair(r,c));
        vis[r][c]  = true;
        while (!q1.isEmpty()){
            Pair it = q1.peek();
            int row = it.first;
            int col = it.second;
            q1.remove();
            for(int i=0;i<4;i++){
                int nrow = row+drow[i];
                int ncol = col+dcol[i];
                if(nrow>=0 && nrow<n && ncol>=0 && ncol<m && vis[nrow][ncol]==false && input[nrow][ncol]=='#'){
                    q1.add(new Pair(nrow,ncol));
                    vis[nrow][ncol] = true;
                    list.get(index).add(new Pair(nrow,ncol));
                }
            }
        }
    }
//            #...#
//            ....#
//            #...#
//            .....
//            ...##


    static class Pair{
        int first;
        int second;
        Pair(int first,int second){
            this.first = first;
            this.second = second;
        }
    }
//           1 3 -1 5
//          -1 -1 -1 -1
//           2 6 -1 10
//           8 7 -1 11
}
