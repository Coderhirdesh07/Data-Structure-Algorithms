package codeforces;

import java.io.IOException;

import java.util.*;

public class TLE800Rated  {
    public static void main(String[] args) throws IOException {
        Scanner s1 = new Scanner(System.in);
        int T = s1.nextInt();
        while(T-->0) {
            int n = s1.nextInt();
            String input = s1.next();
//            int k = s1.nextInt();
//            int[] arr = new int[n];
//
//            for (int i = 0; i < n; i++) {
//                arr[i] = s1.nextInt();
//            }
//           int ans =  lineTrip(arr,n,k);
//            System.out.println(ans);


        }
    }


    public static void helper(int[] arr , int k , int n){
        boolean sorted = true;
        for (int i = 1; i < n; i++) {
            if (arr[i - 1] > arr[i]) sorted = false;
        }

        if(sorted || k>1) System.out.println("YES");
        else System.out.println("NO");
    }
    public static int blankSpace(int[] arr,int n){
        Queue<Integer> q1 = new LinkedList();
        for(int i=0;i<n;i++){
            if(arr[i]==1) q1.add(i);
        }
        int prev = -1;
        if(!q1.isEmpty()) {
            prev = q1.peek();
            q1.remove();
        }
        int max = -1;
        while(!q1.isEmpty()){
            int curr = q1.peek();
            q1.remove();
            max = Math.max(max,curr-prev);
            prev=curr;
        }
        return  max;
    }

    public static  int lineTrip(int[] arr,int n,int k){
        int prev = 0;
        int target = k;
        Arrays.sort(arr);
        int max = -1;
        for(int i=0;i<n;i++){
            int diff = arr[i] - prev;
            max = Math.max(diff,max);
            prev = arr[i];
        }
        int diff = target-arr[n-1];
        max = Math.max(max,diff*2);
        return max;
    }

}
