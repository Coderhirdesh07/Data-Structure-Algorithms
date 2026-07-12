package Tlecourse;

import java.util.Scanner;

public class Level2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<n;i++) arr[i] = sc.nextInt();
        int m = sc.nextInt();
        int[][] queries = new int[m][2];
        for(int i=0;i<m;i++){
            queries[i][0] = sc.nextInt();
            queries[i][1] = sc.nextInt();
        }
        int[] pXor = question_2(arr,n);
        for(int i=0;i<m;i++){
            int l = queries[i][0];
            int r = queries[i][1];
            int res = pXor[r] ^ pXor[l-1];
            System.out.println(res);
        }

    }
    public static int[] question_2(int[] arr,int n){
        int[] prefixMatrix = new int[n];
        prefixMatrix[0] = arr[0];
        for(int i=1;i<n;i++){
            prefixMatrix[i] = prefixMatrix[i-1]^arr[i];
        }
        return prefixMatrix;
    }
}
