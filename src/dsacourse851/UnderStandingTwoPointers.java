package dsacourse851;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class UnderStandingTwoPointers {
    public static void main(String[] args) {
        int[] a = {1, 2, 3, 8, 13, 18};
        int[] b = {800, 1000, 1200, 1500, 1550, 1800};

        int[] arr1 = {2, 5, 8, 10, 15};
        int[] arr2 = {3, 5, 8, 8, 10};
        int target2 = 11;
        int[] nums = {2, 2};
        int[] B = {3, 2, 4, 5, 2, 6, 7, 8, 9, 10};

        int target = 1700;
        int res = question_8(nums,nums.length);
        System.out.println(res);
    }

    public static int[] question_1(int[] arr, int n, int target) {
        int[] ans = new int[2];

        ans[0] = -1;
        ans[1] = -1;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int sum = arr[i] + arr[j];
                if (sum == target) {
                    ans[0] = i;
                    ans[1] = j;
                }
            }
        }
        return ans;
    }

    // concept:- pointer movement institution
    public static int question_2_brute(int[] a, int[] b, int n, int target) {
        // 1 4 5 7
        // 10 20 30 40
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int sum = a[i] + b[j];
                if (sum <= target) {
                    if (ans < sum) {
                        ans = sum;
                    }
                }
            }
        }
        return ans;
    }

    // concept:- pointer movement institution
    public static int question_2(int[] a, int[] b, int n, int target) {
        // 1 4 5 7
        // 10 20 30 40

        int i = 0;
        int j = n - 1;
        int ans = Integer.MIN_VALUE;

        while (i < n || j >= 0) {

            if (a[i] + b[j] <= target) {
                int sum = a[i] + b[j];
                ans = Math.max(ans, sum);
                i++;
            } else {
                j--;
            }
        }
        return ans;
    }

    public static void question_5(int[] nums1, int n, int[] nums2, int m) {
        int i = 0;
        int j = 0;

        // 1 2 3 0 0 0
        // 2 5 6
        while (i < n && j < m) {
            if (nums1[i] <= nums2[j]) {
                i++;
            } else {
                if (nums2[j] > nums1[i] && nums1[i] != 0) {
                    int temp = nums1[i];
                    nums1[i] = nums2[j];
                    nums2[j] = temp;
                } else nums1[i] = nums2[j];
                j++;
            }
            while (j < m) {
                nums1[i] = nums2[j];
                j++;
            }
        }

    }

    public static int question_6_brute(int[] nums,int n,int target){
        int max = -1;
        for(int i=0;i<n;i++){
            int sum = 0;
            for(int j=i;j<n;j++){
                sum+=nums[j];
                if(sum<=target){
                    max = Math.max(max,j-i+1);
                }
            }
        }
        return max;
    }
    public static int question_6_optimise(int[] nums,int n,int target){
        int i = 0;
        int max = -1;
        int sum = 0;
        for(int j=0;j<n;j++){
          sum+=nums[j];
          while(sum>target){
              sum-=nums[i];
              i++;
          }
          int length = j-i+1;
          max = Math.max(length,max);
        }
        return max;
    }
    public static String question_6_actual(String input,int n,int target){
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        String result = "";
        for(int i=0;i<n;i++){
            for(int j=i;j<n;j++){
                char f = input.charAt(j);
                max = Math.max(max,f-'a');
                min = Math.min(min,f-'a');
                if(max-min<=target && j-i+1>result.length()){
                    result = input.substring(i,j+1);
                }
            }
        }
        return result;
    }
    public static String question_6_actual_optimise(String input,int n,int target){
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        String result = "";
        int j =0;
        for(int i=0;i<n;i++){
            char f = input.charAt(i);
             max = Math.max(max,f-'a');
            min = Math.min(min,f-'a');
            int diff = max-min;
            while(diff>target){
                max = Math.max(max,input.charAt(j)-'a');
                min = Math.min(min,input.charAt(j)-'a');
                diff = max-min;
                j++;
            }
            result = input.substring(i,j+1);
        }
        return result;

    }

    // 3 6 2 3 5
    public static int question_8(int[] arr,int n){
        int first_score  = 0;
        int second_score = 0;
        int j = n-1;
        boolean flag = true;
        boolean turn = true;
        for(int i=0;i<=j;i++){
            if(arr[i]%2!=0){
                if(turn==true){
                    turn=!turn;
                    first_score+=arr[i];
                }
                else {
                    turn=!turn;
                    second_score+=arr[i];
                }
            }
            else{
                flag=false;
                if(turn==true){
                    first_score+=arr[i];
                    turn=!turn;
                }
                else{
                    second_score+=arr[i];
                    turn=!turn;
                }
                while (flag!=true && j>i){
                    if(arr[j]%2!=0){
                        if(turn==true){
                            first_score+=arr[j];
                            turn=!turn;
                        }
                        else{
                            second_score+=arr[j];
                            turn=!turn;
                        }
                    }
                    else{
                        if(turn==true){
                            first_score+=arr[j];
                            turn=!turn;
                        }
                        else{
                            second_score+=arr[j];
                            turn=!turn;
                        }
                    }
                    j--;
                }

            }
        }
        return first_score - second_score;
    }

    // question of flipkart interview
    public static int question_10(int[] arr,int n){
        int max = -1;
        for(int i=0;i<n;i++){
            HashMap<Integer,Integer> m1 = new HashMap<>();
            for(int j=i;j<n;j++){
                if(m1.containsKey(arr[j])==false){
                    m1.put(arr[j],m1.getOrDefault(arr[j],0)+1);
                    max = Math.max(max,j-i+1);
                }
                else break;
            }
        }
        return max;
    }
    public static int question_10_optimise(int[] arr,int n){
        int max = -1;
        Arrays.sort(arr);
        HashMap<Integer,Integer> m1 = new HashMap<>();
        int j = 0;
        for(int i=0;i<n;i++){
            if(m1.containsKey(arr[i])==false){
                m1.put(arr[i],i);
                max = Math.max(max,i-j+1);
            }
           else {
                while (m1.get(arr[i]) > 1) {
                    if (arr[j] == arr[i]) {
                        int res = m1.get(arr[j]) - 1;
                        m1.put(arr[j], res);
                    }
                    j++;
                }
            }
        }
        return max;
    }

    public static int question_12_brute(int[] arr,int n,int target){
        int count = 0;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                int sum  =  arr[i] + arr[j];
                if(sum<=target){
                    count++;
                }
            }
        }
        return count;
    }

    public static int question_12_optimise(int[] arr,int n,int target){
        int count = 0;
        for(int i=0;i<n;i++){
            for(int j=n-1;j>=0;j--){
                int sum  =  arr[i] + arr[j];
                if(sum<=target){
                    count += j-i;
                    break;
                }
            }
        }
        return count;
    }
    // amazon oa question find pairs whose sum is between [l,r] inclusive
    public static int question_12_actual(int[] arr,int l,int r){
        Arrays.sort(arr);
        int n = arr.length;
        int lower = 0;
        if(l>=1){
            lower = question_12_optimise(arr,n,l-1);
        }
        int high = question_12_optimise(arr,n,r);
        return high - lower;
    }

    // 2
    // abcbc
    // cba
    // lhs
    // rhs

    // Google oa
    public static int question_13_brute(String a,String b,int n,int m){
    }
    // Salesforce oa
    public static int question_14_brute(int[] arr,int n){

    }
}
