package dsacourse851;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

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
    static class Pair{
        int first;
        int second;
        Pair(int first,int second){
            this.first = first;
            this.second = second;
        }
    }
}
