package DSA;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;


public class UnderStandingDisjointSet {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = sc.nextInt();
        }

        int ans = helper(n-1,arr,k);
        System.out.println(ans);

    }
    public static  int helper(int ind,int[] arr,int target,int[][] dp){
        if(ind==0){
            if(target%arr[ind]==0) return target/arr[0];
            else return Integer.MAX_VALUE;
        }
        if(ind<0) return Integer.MAX_VALUE;
        if(dp[ind][target]!=-1) return dp[ind][target];
        int left = helper(ind-1,arr,target,dp);
        int right = Integer.MAX_VALUE;
        if(arr[ind]<=target){
            right = 1+helper(ind,arr,target-arr[ind],dp);
        }
        return dp[ind][target] =  Math.min(left,right);
    }

    public static  int helper(int ind,int[] arr,int target){
        if(target==0) return 1;
       if(ind==0){
           if(target%arr[0]==0) return 1;
           else return 0;
       }
       int option1 = helper(ind-1,arr,target);
       int option2 = 0;
       if(arr[ind]<=target) option2 = helper(ind,arr,target-arr[ind]);

       return option1 + option2;
    }


//    public static int helper(int n,int[] dp,int mod){
//        if(n==0) return 1;
//        if(n==1) return 1;
//        if(n<0) return 0;
//        if(dp[n]!=-1) return dp[n];
//        int ans = 0;
//        for(int i=1;i<=6;i++){
//            if(n>=i){
//                ans+=helper(n-i,dp,mod);
//            }
//        }
//        return dp[n] =  ans%mod;
//    }
}

//class DisjointSet{
//    ArrayList<Integer> parent = new ArrayList<>();
//    ArrayList<Integer> rank = new ArrayList<>();
//    ArrayList<Integer> size = new ArrayList<>();
//    DisjointSet(int n){
//        for(int i=0;i<=n;i++){
//         parent.add(i);
//         rank.add(0);
//         size.add(1);
//        }
//    }
//    public int findUParent(int node){
//        if(node==parent.get(node)) return node;
//        int ulp = findUParent(parent.get(node));
//        parent.set(node,ulp);
//        return parent.get(node);
//    }
//
//    public void unionByRank(int u,int v){
//        int ulp_u = findUParent(u);
//        int ulp_v = findUParent(v);
//
//        if(ulp_u == ulp_v) return;
//         if(rank.get(ulp_u)<rank.get(ulp_v)){
//             parent.set(ulp_u,ulp_v);
//         }
//         else if(rank.get(ulp_u)>rank.get(ulp_v)){
//             parent.set(ulp_v,ulp_u);
//         }
//         else{
//             parent.set(ulp_v,ulp_u);
//             int rankU = rank.get(ulp_u);
//             rank.set(ulp_u,rankU+1);
//         }
//    }
//    public void unionBySize(int u,int v) {
//        int ulp_u = findUParent(u);
//        int ulp_v = findUParent(v);
//
//        if(ulp_u==ulp_v) return;
//        if(size.get(ulp_u)<size.get(ulp_v)){
//            parent.set(ulp_u,ulp_v);
//            size.set(ulp_v,size.get(ulp_u) + size.get(ulp_v));
//        }
//        else{
//            parent.set(ulp_v,ulp_u);
//            size.set(ulp_u,size.get(ulp_u)+size.get(ulp_v));
//        }
//    }
//}

//class DisjointSet{
//    ArrayList<Integer> parent = new ArrayList<>();
//    ArrayList<Integer> rank = new ArrayList<>();
//    ArrayList<Integer> size = new ArrayList<>();
//
//   public DisjointSet(int n){
//        for(int i=0;i<=n;i++) {
//            parent.add(i);
//            rank.add(0);
//            size.add(1);
//        }
//    }
//    public int findUPar(int node){
//        if(node==parent.get(node)){
//            return node;
//        }
//        int ulp = findUPar(parent.get(node));
//        parent.set(node,ulp);
//        return parent.get(node);
//    }
//
//    public void unionByRank(int u , int v){
//       int ulp_u = findUPar(u);
//       int ulp_v = findUPar(v);
//
//       if(ulp_v==ulp_u) return;
//
//       if(rank.get(ulp_v)< rank.get(ulp_u)){
//           parent.set(ulp_v,ulp_u);
//       }
//       else if(rank.get(ulp_u)<rank.get(ulp_v)){
//           parent.set(ulp_u,ulp_v);
//       }
//       else{
//           parent.set(ulp_v,ulp_u);
//           int ranU = rank.get(ulp_u);
//           rank.set(ulp_u,ranU+1);
//       }
//    }
//
//    public void unionBySize(int u,int v){
//       int ulp_u = findUPar(u);
//       int ulp_v = findUPar(v);
//       if(ulp_v==ulp_u) return ;
//
//       if(size.get(ulp_u)<size.get(ulp_v)){
//           parent.set(ulp_u,ulp_v);
//           size.set(ulp_v,size.get(ulp_u)+size.get(ulp_v));
//       }
//       else{
//           parent.set(ulp_v,ulp_u);
//           size.set(ulp_u,size.get(ulp_u)+size.get(ulp_v));
//       }
//    }
//
//}

