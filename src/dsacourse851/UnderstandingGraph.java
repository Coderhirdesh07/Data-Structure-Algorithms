package dsacourse851;

import java.lang.reflect.Array;
import java.util.*;

public class UnderstandingGraph {
    public static void main(String[] args) {
        int[][] matrix = new int[4][4];
        Scanner sc = new Scanner(System.in);
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                matrix[i][j] = sc.nextInt();
            }
        }
        int result = question_8(matrix,4,4);
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
        ArrayList<Integer> component =

        for(int i=0;i<n2;i++){
            int u = g[i][0];
            int v = g[i][1];
            list2.get(u).add(v);
            list2.get(v).add(u);
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
//           1 3 -1 5
//          -1 -1 -1 -1
//           2 6 -1 10
//           8 7 -1 11
}
