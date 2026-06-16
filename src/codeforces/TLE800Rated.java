package codeforces;

import java.io.IOException;

import java.util.*;

public class TLE800Rated  {
    public static void main(String[] args) throws IOException {
//        Scanner s1 = new Scanner(System.in);
//        int T = s1.nextInt();
//        while(T-->0) {
//            int n = s1.nextInt();
//            String input = s1.next();
//            int k = s1.nextInt();
//            int[] arr = new int[n];
//
//            for (int i = 0; i < n; i++) {
//                arr[i] = s1.nextInt();
//            }
//           int ans =  lineTrip(arr,n,k);
//            System.out.println(ans);

            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            int[] arr = new int[3];
            int[] cost = new int[3];
            for(int i=0;i<3;i++) arr[i] = sc.nextInt();
            for(int i=0;i<3;i++) cost[i] = sc.nextInt();
            int budget = sc.nextInt();
            int ans = hamburgers(input,arr,cost,budget);
            System.out.println(ans);

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
    // Binary search problem
    // TODO have to be done it gives wrong answer
    public static int hamburgers(String input,int[] arr,int[] cost,int budget){
       int count_B =0;
       int count_S = 0;
       int count_C = 0;
        int n = input.length();
        for(int i=0;i<n;i++){
            char f = input.charAt(i);
           if(f=='B') count_B++;
           if(f=='S') count_S++;
           if(f == 'C') count_C++;
        }
        int[] ref = new int[3];
        ref[0] = count_B;
        ref[1] = count_S;
        ref[2] = count_C;
        int low = 1;
        int high = n;
        int ans = 0;
        for(int i=low;i<=high;i++){
            if(isPossible_hamburgers(i,arr,cost,budget,ref)){
                ans = i;
            }
        }
        return ans;
    }
    // 371C
    public static boolean isPossible_hamburgers(int toMake,int[] arr,int[] cost,int budget,int[] ref){
        int total = 0;
        int index = 0;
        while(index<3 || budget<0) {
            int c1 =  ref[index]* toMake; // what is need
            int c2 = arr[index]; //  quantity required to fullfill the possibliy;
            if (c1 > c2) {
                int cost_required = Math.abs(c1 - c2) * cost[index];
                total += cost_required;
            }
                index++;
        }
        return total<=budget;
    }

}
