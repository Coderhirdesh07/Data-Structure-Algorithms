package dsacourse851.youtube;

import java.util.ArrayList;
import java.util.Scanner;

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
}
