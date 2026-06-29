package codeforces;

import java.util.Scanner;

public class CFTemplates {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

    }
    public static int[] prefix_sum(int[] arr,int n){
        int[] prefix = new int[n];
        prefix[0] = arr[0];
        for(int i=1;i<n;i++){
            prefix[i] = prefix[i-1] + arr[i];
        }
        return prefix;
    }
//    public static int prefix_sum_matrix(int[][] matrix,int n){}
//    public static int prefix_sum_matrix_query(int l1,int r1,int l2,int r2,int[][] matrix){
//
//    }
    public static int prefix_sum_query(int left,int right,int[] prefix){
        if(left==0) return prefix[right];
        return prefix[right] - prefix[left-1];
    }
    public static int binary_search(int[] arr,int target,int n){
        int low = 0;
        int high = n-1;
        int ans = n;

        while(low<=high){
            int mid = (low + high)/2;
            if(arr[mid] == target){
                ans = mid;
            }
            else if(arr[mid]>target){
                high = mid-1;
            }
            else low = mid+1;
        }

        return ans;

    }

    public static int linear_search(int[] arr,int target,int n){
        int index = -1;
        for(int i=0;i<n;i++){
            if(arr[i] == target){
                index = i;
                break;
            }
        }
        return index;
    }

//    public static int gcd(int a,int b){}
//
//    public static int lcm(int a,int b){}

    // public static int difference_array(){}
}
