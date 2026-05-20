package dsacourse851;

import java.util.*;


public class UnderStandingTwoPointers {
    public static void main(String[] args) {
        int[] a = {1, 2, 3, 8, 13, 18};
        int[] b = {800, 1000, 1200, 1500, 1550, 1800};

        int[] arr1 = {2,1,4,3,2,1,1,4};
        int[] nums1 = {1 ,2 ,1 ,0 ,1 ,1 ,0};
        int[] nums3 = {5 ,5 ,6 ,7 ,8 ,8 ,6 ,5 ,5}; //  g = 5 k = 2
                   // i
        int[] arr2 = {3, 5, 8, 8, 10};
        int target2 = 11;
        int[] nums = {1,2,3};
        int[] nums2 = {1,2,1};
        //            j
        int[] B = {3, 2, 4, 5, 2, 6, 7, 8, 9, 10};

        int target = 1700;
        int res = question_google_sde_brute(nums3,nums3.length,5,2);
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
    public static int question_13(String a,String b,int n,int m){
        int i = 0;
        int count = 0;
        int start = -1;
        while(i<m){
            for(int j=i;j<n;j++){
                char first = a.charAt(i);
                char second = b.charAt(j);
                if(first==second){
                    count++;
                    if(start==-1 && i==0) {
                        start = i;
                    }
                }
            }
            i++;
        }
        if(count<=m-1) return -1;

        return start;

    }
    // Salesforce oa
    // TODO there is a part of good index removal that i have not done
    public static boolean question_14_brute(int[] A,int[] B,int n,int m){
        int count = 0;
        int start = 0;
        int j = 0;
        while(j<m){
            int i = start;
            while(i<n){
                if(B[j] == A[i]){
                    start = i+1;
                    count++;
                    break;
                }
                i++;
            }
            j++;
        }
        return (count==m) ?true:false;
    }

    public static boolean question_14_optimise(int[] A,int[] B,int n,int m){
       int i=0;
       int j = 0;
       int count = 0;
       while(i<n && j<m){
           if(A[i]==B[j]){
               count++;
               i++;
           }
           j++;
       }
       return count==m?true:false;
    }
    // we want to make b subsequence of a
    public static int question_14_actual(int[] a,int[] b,int n,int m){
    int[] prefixB = new int[m];
        int i=0;
        int j = 0;
        while(i<n && j<m){
            if(a[i]==b[j]){

                prefixB[j]=i;
                j++;
            }
            i++;
        }

     int[] suffixB = new int[m];
         i=n-1;
         j = m-1;

        while(i>=0 && j>=0){
            if(a[i]==b[j]){
                suffixB[j]=i;
                j--;
            }
            i--;
        }
    int good  = 0;
    for(int k=0;k<m;k++){
        if(prefixB[k]<suffixB[k]){
            good++;
        }
    }
    return good;



    }

    // microsoft oa problem
    public static int question_15_brute(int[] arr,int n,int l,int r){
        // 2,4 -> 2,3,4

     int start = Integer.MAX_VALUE;
     int count = Math.abs(r-l)+1;
        for(int i=0;i<n;i++){
            HashMap<Integer,Integer> m1 = new HashMap<>();
            for(int j=i;j<n;j++){
                if(arr[j]>=l && arr[j]<=r) {
                    m1.put(arr[j], m1.getOrDefault(arr[j], 0) + 1);
                }
                 if(m1.size()==count){
                     start = i;
                     break;
                }
            }
        }
        return start;
    }

    // leetcode contest problem
    public static int question_16_brute(int[] arr,int n ,int k){
        int min = Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            int sum = 0;
            for(int j=i;j<n;j++){
                sum+=arr[j];
                if(sum>=k){
                    min = Math.min(min,j-i+1);
                    break;
                }
            }
        }
        return min;
    }


    public static int question_16_optimise(int[] arr,int n ,int k){
       int sum = 0;
       int[] prefix = new int[n];
       for(int i=1;i<n;i++){
           prefix[i] = prefix[i-1] + arr[i];
       }

       int i=0;
       int j=n-1;
       int min = Integer.MAX_VALUE;

       while(i<=j){
           if(i==0){

           }
           else {

           }
       }
       return min;
    }
    // find longest subarray with sum<=k
    public static int question_18_brute(int[] arr,int k,int n){
        int max = Integer.MIN_VALUE;
        for (int i=0;i<n;i++){
            int sum = 0;
            for(int j=i;j<n;j++){
                sum+=arr[j];
                if(sum<=k){
                    max = Math.max(max,j-i+1);
                }
            }
        }
        return max;
    }
    // TODO GOOGLE ACTUAL QUESTION
    public static int question_18_optimise(int[] arr,int k,int n){
        int j = 0;
       int i = 0;
       int max = Integer.MIN_VALUE;
      int sum = 0;
       while(i<n && j<n){
           if(i==j){
               if(arr[i]>k){
                i++;
                j++;
                sum= arr[i];
               }
               else{
                   sum+=arr[j];
                   max = Math.max(max,j-i+1);
                   j++;
               }
           }
           else{
               if(sum>k){
                   sum-=arr[i];
                   i++;
                   if(i>j){
                       j=i;
                   }
               }
               else{
                   sum+=arr[j];
                   max = Math.max(max,j-i+1);
                   j++;
               }
           }
       }
       return max;
    }

    // arr = [5, 9, 20, 22, 28, 35, 60, 350, 358, 359, 360]
    //field of view = 30
    // actual question -> was difference between max and min element in a subarray should be less than k
    public static int question_18_actual_google(int[] arr,int n,int k){
        int max = Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            int minElement = Integer.MAX_VALUE;
            int maxElement = Integer.MIN_VALUE;
            for(int j=i;j<n;j++){
                minElement = Math.min(minElement,arr[j]);
                maxElement = Math.max(maxElement,arr[j]);

                if(maxElement - minElement <=k){
                    max = Math.max(max,j-i+1);
                }
            }
        }
        return max;
    }

//    public static int question_18_actual_google_optimise(int[] arr,int n,int k){
//
//    }

    // this question if from expedia oa
    public static int question_expedia(int[] a,int[] b,int n,int m){
        Arrays.sort(a);
        Arrays.sort(b);
        int i = n-1;
        int j=0;
        // a[i]>b[j]
        // 1 2 3
        // 1 1 2
        int count = 0;
        while(i>=0 && j<m){
            if(a[i]>b[j]){
                i--;
                j++;
                count++;
            }
            else{
                break;
            }
        }
        return count;
    }
    // 4 2 3 1 5 6
    // 3 1 4 6 5 2
    public static int question_rubrik_oa(int[] A,int[] B,int n){
        int count = 0;
        for(int i=0;i<n;i++){ // A
            for(int j=i;j<n;j++){ // B
                if(A[i]!=B[j]){
                    int temp = B[i];
                    B[i] = B[j];
                    B[j] = temp;
                    count++;
                }
                else continue;
            }
        }
        return count;
    }

    // longest subarray in which target comes for <=k times
    public static int question_google_sde_brute(int[] arr,int n,int target,int k){
        int max = Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            HashMap<Integer,Integer> m1 = new HashMap<>();
            for(int j=i;j<n;j++){
                if(arr[j]==target){
                    m1.put(arr[j],m1.getOrDefault(arr[j],0)+1);
                }
                else{
                    if(m1.containsKey(target)){
                        if(m1.get(target)<=k) {
                            max = Math.max(max, j - i + 1);
                        }
                    }
                }
            }
        }
        return max;
    }
//    public static int question_google_sde_optimise(int[] arr,int n,int target,int k){
//
//    }

    public static int question_amazon_oa_brute(int[] arr,int n){
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0;i<n;i++){
            HashSet<Integer> s1 = new HashSet<>();
            for(int j=i;j<n;j++){
                s1.add(arr[j]);
                list.add(s1.size());
            }
        }
        Collections.sort(list);
        int mid = list.size()-1;
        return list.get(mid/2);
    }

    // understood it with hint of binary search
    public static int quetion_amazon_oa_optimise(int[] arr,int n){

    }

}
