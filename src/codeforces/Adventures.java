// package codeforces;

import java.util.*;


public class Adventures {
    public static int search(int[] arr,int target,int n){
        int low = 0;
        int high = n-1;
        int ans = -1;
        while(low<=high){
            int mid = (low + high)/2;
            if(arr[mid]>=target){
                ans = mid;
                high=mid-1;
            }
            else low = mid+1;
        }
        return ans;
    }
    public static void main(String args[]) {
         Scanner sc = new Scanner(System.in);
         int n = sc.nextInt();
         int m = sc.nextInt();
         int x  = sc.nextInt(); //  allowed diff

        int[] arr1 = new int[n];
        int[] arr2 = new int[m];
        for(int i=0;i<n;i++) arr1[i]= sc.nextInt();
        for(int j=0;j<m;j++) arr2[j] = sc.nextInt();

        Arrays.sort(arr2);
        int count = 0;
        for(int i=0;i<n;i++){
            int diff = arr1[i] - x;
            int ans = search(arr2,diff,m);
            if(ans!=-1){
                arr2[ans]=0;
                count++;
                Arrays.sort(arr2);
            }
        }
        System.out.println(count);


    }
}


