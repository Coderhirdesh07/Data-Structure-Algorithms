package dsacourse851.Hashing;

import java.lang.reflect.Array;
import java.security.spec.MGF1ParameterSpec;
import java.util.*;

public class UnderStandingHashMap {
    static   class Pair{
        int first;
        int second;
        Pair(int first,int second){
            this.first=first;
            this.second=second;
        }
    }
    public static void main(String[] args) {
//        Scanner sc  = new Scanner(System.in);
//        long n = sc.nextLong();
////        int q = sc.nextInt();
//        int[] arr = new int[n];
////        int[] queries = new int[q];
//        for(int i=0;i<n;i++){
//            arr[i] = sc.nextInt();
//        }
////        for(int i=0;i<q;i++){
////            queries[i] = sc.nextInt();
////        }
////        HashMap<Integer,Integer> map  = question_1(arr,queries);
////        for(int i=0;i<q;i++){
////            if(map.containsKey(queries[i])) {
////                System.out.print(" "+ map.get(queries[i]));
////            }
////            else System.out.println(" " + -1);
////        }
//        int ans = question_microsoft_oa_optimise(arr,n);
//        System.out.println(ans);
//          int res = question_amazon_oa_intern("abdadccacd","edac");
//           System.out.println(res);
//        int[] packageWeight = {4,1,5,2,8,7,6,9,3,10 };
        int[] arr = {5 ,6 ,7 ,8 ,10 ,4 ,3 ,2 ,1};
        int[] nums = {12,6,1,2,7};
        int[] nums2 = {1 ,2 ,3 ,4 ,-1 ,-2 ,-2};
        int[] nums3 = {0,3,14,15};
//        int[] nums3 = {0,1,2,3};
        long res = question_uber_oa_sde1_optimise(nums3,4);
        System.out.println(res);

//        question_arcesium_oa("cda","abcd");

    }
    public static HashMap<Integer,Integer> question_1(int[] arr,int[] queries){
        HashMap<Integer,Integer> m1 = new HashMap<>();

        for(int x:arr){
            m1.put(x,m1.getOrDefault(x,0)+1);
        }
        return m1;
    }

    public static boolean question_2(int[] arr,int k){
        int n = arr.length;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                if(arr[i]==arr[j]){
                    if(j-i+1<=k){
                        return true;
                    }
                }
            }
        }
        return false;

    }

    public static boolean question_2_optimise(int[] arr,int k){
        int n = arr.length;
       HashMap<Integer,Integer> m1 = new HashMap<>();
       for(int i=0;i<n;i++){
           if(m1.containsKey(arr[i])){
               if(Math.abs(i-arr[i]+1)<=k){
                   return true;
               }
           }
           else m1.put(arr[i],i);
       }
       return false;
    }

    public static int question_3(int[] arr,int k){
        int n = arr.length;
        int count = 0;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                int sum = arr[i] + arr[j];
                if(sum==k){
                    count++;
                }
            }
        }
        return count;
    }

    public static int question_3_optimise(int[] arr,int k){
        int n = arr.length;
        int count = 0;
       HashMap<Integer,Integer> m1 = new HashMap<>();
       for(int i=0;i<n;i++){
           int diff = k-arr[i];
           if(m1.containsKey(diff)){
               count++;
           }
           m1.put(arr[i],i);
       }
        return count;
    }

    public static  int question_4(int[] arr,int k){
        // arr[i] - arr[j]
        int count = 0;
        int n = arr.length;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                int diff = arr[i] - arr[j];
                if(diff==k) count++;
            }
        }
        return count;
    }

    public static  int question_4_optimise(int[] arr,int k){
        // arr[i] - arr[j] == k
        // k + arr[j] = arr[i]
        int count = 0;
        int n = arr.length;
        HashMap<Integer,Integer> m1 = new HashMap();
        for(int i=0;i<n;i++){
            int check = arr[i] - k;
            if(m1.containsKey(check)) count++;
            m1.put(arr[i],i);
        }
        return count;
    }

    // abs(b[i] - b[j]) = k
    // 1.)  b[i] = k + b[j]
    // 2.) b[i] = b[j] - k

    public  static  int question_5(int[] arr,int k){
        int count = 0;
        int n = arr.length;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                int diff = Math.abs(arr[i]-arr[j]);
                if(diff==k) count++;
            }
        }
        return count;
    }
    public static int question_5_optimise(int[] arr,int k){
        int n = arr.length;
        int count = 0;
        HashMap<Integer,Integer> m1 = new HashMap<>();
        for (int i=0;i<n;i++){
            int diff1 = arr[i] + k;
            int diff2 = arr[i]-k;
            if(m1.containsKey(diff1) || m1.containsKey(diff2)) count++;
            m1.put(arr[i],m1.getOrDefault(arr[i],0)+1);
        }
        return count;
    }


    // not correct
    public static  int question_6(int[] arr,int k){
        int n = arr.length;
        int  count = 0;
        for(int i=0;i<n;i++){
            int sum = 0;
            int maxLen = -1;
            int minLen = Integer.MAX_VALUE;
            for(int j=i;j<n;j++){
                sum+=arr[i];
                if(sum==k){
                    int len = j-i+1;
                    if(len==maxLen || len==minLen){
                        count++;
                    }
                }
            }
        }
        return count;
    }

    // 0 1 2 3 4
    // 2 4 4 5 5

    public static int question_zscaler(int[] arr,int n){
        int total=0;

        HashMap<Integer,Integer> m1 = new HashMap<>();
        for(int x:arr){
            m1.put(x,m1.getOrDefault(x,0)+1);
        }
        Arrays.sort(arr);
        int i = n-1;

        while(i>1){
            int check = arr[i];
            int ind = i-1;
            int count = 0;
            while(arr[ind]==check){
                ind--;
                count++;
            }
            i = ind;
            total+=(i-ind+1)+count;
        }
        return total;

    }
    public static  int digit_sum(int digit){
        int temp = digit;
        int sum = 0;
        while(temp>0){
            int check = temp%10;
            sum+=check;
            temp/=10;
        }
        return sum;
    }
    // this works on example test cases but will not pass completely
    public static int question_microsoft_oa(int[] arr,int n) {
        int max = -1;
       for(int i=0;i<n;i++){
           int digit_1 = digit_sum(arr[i]);
           for(int j=i+1;j<n;j++){
                 int digit_2 = digit_sum(arr[j]);
                 if(digit_2==digit_1){
                     max = Math.max(max,arr[i]+arr[j]);
                 }
           }
       }
       return max;
    }
    public  static  int question_microsoft_oa_optimise(int[] arr,int n){
        int max = -1;
        HashMap<Integer,Integer> m1 = new HashMap<>();
        for(int i=0;i<n;i++){
            int digit_sum = digit_sum(arr[i]);
            if(m1.containsKey(digit_sum)){
                int number = m1.get(digit_sum);
                max = Math.max(max,arr[i]+number);
            }
            else m1.put(digit_sum,arr[i]);
        }
        return max;
    }
    // TODO follow up of above question
    // TODO also leetcode problem of count no of nice subarray
//    public  static  int question_microsoft_oa_optimise_follow_up(int[] arr,int n){
//        int max = -1;
//        int count = 0;
//        HashMap<Integer,Integer> m1 = new HashMap<>();
//        for(int i=0;i<n;i++){
//            int digit_sum = digit_sum(arr[i]);
//            if(m1.containsKey(digit_sum)){
//                int number = m1.get(digit_sum);
//
//            }
//            else m1.put(digit_sum,arr[i]);
//        }
//        return max;
//    }

    // bruteforce done by hint
    public static int question_amazon_oa_intern(String s,String t){
        int n = s.length();
        int m = t.length();
        HashMap<Character,Integer> m1 = new HashMap<>();
        for (int i=0;i<n;i++){
            char f = s.charAt(i);
            m1.put(f,m1.getOrDefault(f,0)+1);
        }
        int total = Integer.MAX_VALUE;
        for(int i=0;i<m;i++){
           char f = t.charAt(i);
           if(m1.containsKey(f)){
               int res = m1.get(f);
               total = Math.min(res,total);
           }
           else total = 0;

        }
        return total;
    }
//    public static int question_amazon_oa_intern_optimise(String s,String t){
//        int n = s.length();
//        int m = t.length();
//
//    }
        // arr = [2,4,6,6,4,2,4]
    public static  int question_amazon_oa(int[] packageWeight,int n){
        HashMap<Integer,Integer> m1 = new HashMap<>();
        for(int i=0;i<n;i++){
            m1.put(packageWeight[i],m1.getOrDefault(packageWeight[i],0)+1);
        }
        int count = 0;
        for(int i=0;i<n;i++){
            if(m1.containsKey(packageWeight[i])){
                int ans = m1.get(packageWeight[i]);
                if(ans==2 || ans==3){
                    count++;
                    m1.remove(packageWeight[i]);
                }
                else return -1;
            }
        }
        return count;
    }
    // Hashing 25 lecture
    public static  int question_amazon_oa_2(int[] arr,int n,int k){
        int count = 0;
        for(int i=0;i<n;i++){
            int sum = 0;
            for(int j=i;j<n;j++){
                sum+=arr[j];
                int count_of_no = j-i+1;
                if(sum%k==count_of_no) count++;
            }
        }
        return count;
    }
    // INFO:-
    public static  int question_amazon_oa_2_optimise(int[] arr,int n,int k){
        int count = 0;
        HashMap<Integer,Integer> m1  = new HashMap<>();
        int sum = 0;
        m1.put(0,1);
        for(int i=0;i<n;i++){
            sum+=arr[i];
            if(m1.containsKey(sum-i)){
                count+=m1.get(sum-i);
            }
            m1.put(sum,m1.getOrDefault(sum,0)+1);

        }
        return count;
    }

    public static int[] question_visa_oa(int[][] grid,int[] points,int n,int length){
        //  grid.length = n  points.length = length;
        int[] ans = new int[length];
        Arrays.fill(ans,0);
        for(int i=0;i<length;i++){
            int check = points[i];
            int count = 0;
            for(int j=0;j<n;j++){
                int start = grid[i][0];
                int end = grid[i][1];
                if(check>=start && check<=end){
                    count++;
                }
            }
            ans[i] = count;
        }
        return ans;
    }
    public static int[] question_visa_oa_way_2(int[][] grid,int[] points,int n,int length){
        //  grid.length = n  points.length = length;
       int[] arr = new int[n];
       Arrays.fill(arr,0);
       for(int i=0;i<n;i++){
           int l = grid[i][0];
           int r = grid[i][1];
           for(int j=l;j<=r;j++){
               arr[j]++;
           }
       }
       return arr;
    }
    public static int[] question_visa_oa_way_2_optimise(int[][] grid,int[] points,int n,int length){
        int max = -1;
        for(int i=0;i<n;i++){
            int x = grid[i][0];
            int y = grid[i][1];
            max = Math.max(max,Math.max(x,y));
        }
        int[] ans = new int[max];
        Arrays.fill(ans,0);

        for(int i=0;i<n;i++){
            int x  = grid[i][0];
            int y = grid[i][1];
            ans[x] +=ans[x]+ 1;
            if(y+1<max) ans[y+1]-=1;
        }
        for(int i=1;i<n;i++){
            ans[i] = ans[i-1]+ans[i];
        }
        return ans;

    }

    public static int question_rubrik_question(int[] arr,int x,int y,int n ){
        int count = 0;
        for(int i=0;i<n;i++){
            int count_x=0;
            int count_y=0;
            for(int j=i;j<n;j++){
                if(arr[i]==x) count_x++;
                if(arr[i]==y) count_y++;
                if(count_x==count_y) count++;
            }
        }
        return count;
    }
    public static int question_rubrik_question_optimise(int[] arr,int x,int y,int n){
         HashMap<Integer,Integer> m1 = new HashMap<>();
         for(int i=0;i<n;i++){
             if(arr[i]==x || arr[i]==y) arr[i]=-1;
         }
         int sum = 0;
         int count = 0;
         for(int i=0;i<n;i++){
             sum+=arr[i];
             if(sum==0) count+=m1.get(sum);
             m1.put(sum,m1.getOrDefault(sum,0)+1);
         }
         return count;
    }
    public static int question_hackerank_question_oa_question(String[] arr,int n,int k){
            int[] newArr  = new int[1440];
            for(int i=0;i<n;i++){
                String[] input  = arr[i].split("");
                int start  = convertToMin(input[2]);
                int end = convertToMin(input[3]);
                newArr[start]+=1;
                newArr[end+1]-=1;
            }
            for (int i=0;i<1440;i++){
                newArr[i]=newArr[i-1]+newArr[i];
            }
            int count = 0;
            for(int i=0;i<1440;i++){
                if(newArr[i]==0){
                    count++;
                    if(count==k) return i-k+1;
                }
            }
            return -1;
    }
    public static int convertToMin(String input){
        // 00:40
        String[] arr = input.split(":");
        int hr = Integer.parseInt(arr[0]);
        int min = Integer.parseInt(arr[1]);
        return hr*60+min;
    }
   //  A = {10,5,2,7,1,9,8,7}   k = 15
    public static int question_oa_contest_30(int[] arr,int k,int n){
        int count = 0;
        int min = Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            int sum = 0;
            for(int j=i;j<n;j++){
                sum+=arr[j];
                if(sum==k){
                    int len = j-i+1;
                    if(len<min){
                        min = len;
                        count++;
                    }
                    else if(min==len) count++;
                }
            }
        }
        return count;
    }
    public static int question_oa_contest_homework(int[] arr,int k,int n){
        int count = 0;
        int max = Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            int sum = 0;
            for(int j=i;j<n;j++){
                sum+=arr[j];
                if(sum==k){
                    int len = j-i+1;
                    if(len>max){
                        max = len;
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public static int question_oa_contest_30_optimise(int[] arr,int k,int n){
       HashMap<Integer,Integer> m1 = new HashMap<>();
       m1.put(0,1);
       int sum = 0;
       int count = 0;
       int min = Integer.MAX_VALUE;
       for(int i=0;i<n;i++){
           sum+=arr[i];
           if(m1.containsKey(sum-k)){
               int len = i-m1.get(sum-k);
                if(min>len){
                    len = min;
                    count++;
                }
                else if(len==min) count++;
           }
           else m1.put(sum,i);
       }
       return count;
    }
    public static int question_oa_contest_homework_optimise(int[] arr,int k,int n){
        HashMap<Integer,Integer> m1 = new HashMap<>();
        m1.put(0,0);
        int sum = 0;
        int count = 0;
        int max = Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            sum+=arr[i];
            if(m1.containsKey(sum-k)){
                int len = i-m1.get(sum-k);
                if(max<len){
                    len = max;
                    count++;
                }
            }
            else m1.put(sum,i);
        }
        return count;
    }
    // TODO  code for THIS QUESTION IS NOT YET OPTIMISED
    public static int question_google_oa(int[] arr,int n){
        int count = 0;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                if(arr[arr[arr[i]]]==arr[arr[arr[j]]]){
                    count++;
                }
            }
        }
        return count;
    }
//    public static int question_google_oa_optimise(int[] arr,int n){
//
//
//    }
    public static int question_deShaw_oa(int[] arr,int n,int target){
        int count = 0;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                int first = Math.abs(arr[i]-arr[j]);
                int second = Math.abs(arr[i]+arr[j]);
                if(first+second==target) count++;
            }
        }
        return count;
    }
    // TODO Needs to be done by myself as there are many cases for a[i] & a[j]
//    public static int question_deShaw_oa_optimise(int[] arr,int n,int target){
//        /**
//         *
//         *1.) a[i] > a[j]
//         *   a[i] + a[j] + a[i] - a[j] = target
//         *   2*a[i] = target
//         *   a[i] = target/2
//         *
//         *  2.) a[i] == a[j]
//         *  a[i]+a[i] + 0 =target
//         *  a[i] = target/2;
//         *
//         *  3.) a[i] < a[j]
//         *
//         */
//        HashMap<Integer,Integer> m1 = new HashMap<>();
//        m1.put(0,1);
//        int count = 0;
//        for(int i=0;i<n;i++){
//            if(m1.containsKey(target/2)){
//                count++;
//            }
//            m1.put(arr[i],i);
//        }
//        return count;
//    }

    public static void swap(char[] input,int x,int y){
        char temp = input[x];
        input[x] = input[y];
        input[y] = temp;
    }

    // TODO  code for THIS QUESTION IS NOT YET OPTIMISED
    public static void question_arcesium_oa(String a1,String b1){
        if(a1.length()!=b1.length()){
            System.out.println("NO");
            return;
        }
        char[] arr1 = a1.toCharArray();
        char[] arr2 = b1.toCharArray();
        int n = arr1.length;

        for(int i=0;i<n-1;i++){
            char f = arr1[i];
            char s = arr2[i];

            if(((i&1)==0) && i<n-2){
               if(arr1[i+2]==arr2[i]){
                   swap(arr1,i,i+2);
               }
               if(arr2[i+2]==arr1[i]){
                   swap(arr2,i,i+2);
               }
            }
            else if((i&1)!=0 && i<n-2){
                if(arr1[i+2]==arr2[i]){
                    swap(arr1,i,i+2);
                }
                if(arr2[i+2]==arr1[i]){
                    swap(arr2,i,i+2);
                }
            }
        }
        String newA1 = new String(arr1);
        String newA2 = new String(arr2);
        if(newA1.equals(newA2) || newA2.equals(newA1)){
            System.out.println("YES");
        }
        else System.out.println("NO");
    }

    // Arr[i]>Arr[j]<Arr[k]  i<j<k
    public static int question_google_sde3_interview(int[] arr,int n){
        int count = 0;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                for(int k=j+1;k<n;k++){
                    if(arr[i]>arr[j] && arr[j]<arr[k]) count++;
                }
            }
        }
        return count;

    }
    // TODO follow-up question for 4 variables a[i]>a[j]<a[k]>a[l]
    public static int question_google_sde3_interview_followup(int[] arr,int n){
        int count = 0;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                for(int k=j+1;k<n;k++){
                    for(int l=k+1;l<n;l++) {
                        if (arr[i] > arr[j] && arr[j] < arr[k] && arr[k]>arr[l]) count++;
                    }
                }
            }
        }
        return count;
    }
    // fix - j
    // 2 1 3 7 9 4 6 10
    //-1 2 3 7 9 9 9 10
    public static  int question_google_sde3_interview_optimise(int[] arr,int n){
        int[] prefix = new int[n];
         prefix[0] = 0;
         for(int i=1;i<n;i++){
             int j = 0;
             int count = 0;
             while(j<=i-1){
                 if(arr[i]<arr[j]){
                     count++;
                 }
                 j++;
             }
             prefix[i] = count;
         }
         int[] suffix = new int[n];
         suffix[n-1] = 0;

         for(int i=n-2;i>=0;i--){
             int j = i+1;
             int count = 0;
             while(j<n){
                 if(arr[i]<arr[j]) count++;
                 j++;
             }
             suffix[i] = count;
         }


        int count = 0;
        for(int x=0;x<n;x++){
            count= count + prefix[x]*suffix[x];
        }
        return count;
    }

    // x + y + z + a + b == 0 =>  x + y = - (z + a + b)
    // time - O(nˆ5)
    public static int question_nutanix_sde_us_brute(int[] arr1,int[] arr2,int[] arr3,int[] arr4,int[] arr5,int n){
        int count = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                for(int k=0;k<n;k++){
                    for(int x=0;x<n;x++){
                        for(int y=0;y<n;y++){
                            int sum = arr1[i]+arr2[j] + arr3[k] + arr4[x] + arr5[y];
                            if(sum==0) count++;
                        }
                    }
                }
            }
        }
        return count;
    }
    public static int question_nutanix_sde_us_optimise_1(int[] arr1,int[] arr2,int[] arr3,int[] arr4,int[] arr5,int n){
        HashMap<Integer,Integer>  m1 = new HashMap<>();
        for(int i=0;i<n;i++){
            m1.put(arr5[i],m1.getOrDefault(arr5[i],0)+1);
        }
        int count = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                for(int k=0;k<n;k++){
                    for(int x=0;x<n;x++){
                        int sum = arr1[i] + arr2[j] + arr3[k] + arr4[x];
                        int check = -1 * sum;
                        if(m1.containsKey(check)){
                            count+=m1.get(check);
                        }
                    }
                }
            }
        }
        return count;
    }
    public static int question_nutanix_sde_us_optimise_2(int[] arr1,int[] arr2,int[] arr3,int[] arr4,int[] arr5,int n){
        HashMap<Integer,Integer> m1 = new HashMap<>();
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                int sum = arr4[i] + arr5[j];
                m1.put(sum,m1.getOrDefault(sum,0)+1);
            }
        }
        int count = 0;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                for(int k=j+1;k<n;k++){
                    int sum = arr1[i] + arr2[j] + arr3[k];
                    int check = -1*sum;
                    if(m1.containsKey(check)){
                        count++;
                    }
                }
            }
        }
        return count;
    }
    // k == 3
    // [ 18,5 ,4,3,2,1,8,10]
    public static int question_amazon_intern_1(int[] arr,int n,int k){
        // in this k == 3 we can use 3 for loops
        int count = 0;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                for(int x=j+1;x<n;x++){
                    if(arr[i]<arr[j] && arr[j]<arr[x]){
                        count++;
                    }
                }
            }
        }
        return count;
    }
    // finding the count will not work for this question it does not optimise only adds space
    public static int question_amazon_intern_optimise(int[] arr,int n,int k){
        int[] pMin = new int[n];
        int[] sMax = new int[n];
        pMin[0]=Integer.MAX_VALUE;
        for(int i=1;i<n;i++){
          pMin[i] = Math.min(pMin[i-1],arr[i]);
        }
        sMax[n-1] = -1;
        for(int i=n-2;i>=0;i--){
            sMax[i] = Math.max(sMax[i+1],arr[i]);
        }
        int count = 0;
        for(int i=1;i<n;i++){
            if(pMin[i]<arr[i] && sMax[i]>arr[i]){
                count++;
            }
        }
        return count;
    }
    public static int question_atlassian(int[] arr,int n,int k){
        int sum = 0;
        for(int x:arr){
            sum+=x;
        }
       if(sum%k!=0) return 0;

        int count = 0;

        int total = 0;
        for(int i=0;i<n-1;i++){
            total+=arr[i];

           if(total==sum/k){
               count++;
           }
        }
        return count;

    }
    public static int question_microsoft_oa_2(int[] arr,int x,int y,int n){
        int min = Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            int check = 0;
            int count = 0;
            int j = i;
            while(j<n){
                check+=arr[j];
                count++;
                j=j+y;
            }
            if(count==x){
                min = Math.min(min,check);
            }

        }

        return min;
    }

//    public static int question_microsoft_oa_2_optimise(int[] arr,int x,int y,int n){
//        int[] prefx = new int[n];
//        prefx[0] = 0;
//        for(int i=1;i<n;i++){
//            prefx[i] = prefx[i-1]+arr[i];
//        }
//
//    }
    // question - 44
    public static int question_microsoft_sde2_interview(int[] arr,int n){
        int count = 0;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                for(int k=j+1;k<n;k++){
                    for(int x=k+1;x<n;x++){
                        int sum = arr[i] + arr[j] + arr[k] + arr[x];
                        if(sum==0) count++;
                    }
                }
            }
        }
        return count;
    }
    // question -44
    public static  int question_microsoft_sde2_interview_optimise_1(int[] arr,int n){
         int count = 0;
         HashMap<Integer,Integer> m1 = new HashMap<>();
         for(int i=0;i<n;i++){
             for(int j=i+1;j<n;j++){
                 for(int k=j+1;k<n;k++){
                     int sum = arr[i] + arr[j] + arr[k];
                     int check = -1 * sum;
                     if(m1.containsKey(check)){
                         count+=m1.get(check);
                     }
                     m1.put(check,m1.getOrDefault(check,0)+1);
                 }
             }
         }
         return count;
    }
    // question- 44
    public static int question_microsoft_sde2_interview_optimise_2(int[] arr,int n){
        HashMap<Integer,Integer> m1 = new HashMap<>();
        for(int i=0;i<n;i++){

            for(int j=i+1;j<n;j++){
                int sum=arr[j] + arr[i];
                m1.put(sum,m1.getOrDefault(sum,0)+1);
            }
        }

        int count = 0;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                int sum = arr[j] + arr[i];
                int check = -1*sum;
                if(m1.containsKey(check)){
                    count+=m1.get(check);
                }
            }
        }
        return count;
    }

        // question 42
    public static int question_amazon_interview(int[] arr,int k,int n){
        int min = 1000;
        for(int i=0;i<n-1;i++){
            for(int j=i+1;j<n;j++){
                if(arr[i] + arr[j]==k){
                    int elements = j-i+1;
                    min = Math.min(min,elements);
                }
            }
        }
        return min;
    }
    public static int question_amazon_interview_optimise(int[] arr,int k,int n){
        HashMap<Integer,Integer> m1 = new HashMap<>();
        // 0 11 19 27 37 41 43 44
        int sum = 0;
        int min = Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            int find = k - arr[i];
            if(m1.containsKey(find)){
                int ind = m1.get(find);
                min = Math.min(min,i-ind+1);
            }
            m1.put(arr[i],i);
        }
        return min;
    }
    public static long question_49_brute(int[] arr,int n){
        long max = -1;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                for(int k=j+1;k<n;k++){
                    long diff = arr[i]-arr[j];
                    long prod = diff*arr[k];
                    max = Math.max(max,prod);
                }
            }
        }
        if(max<0) return -1;
        return max;
    }

//    public static long question_49_optimise(int[] arr,int n){
//        int[] prefix = new int[n];
//        int max = 0;
//        prefix[0]=0;
//
//    }
    // question - 45 -this is a easy version
    public static int question_codechef(int[] arr,int n){
        int count = 0;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                for(int k=j+1;k<n;k++){
                    for(int x=k+1;x<n;x++){
                        int sum = arr[i]^arr[j]^arr[k]^arr[x];
                        if(sum==0) count++;
                    }
                }
            }
        }
        return count;
    }

    // question - 46 -> needs to be optimised
    // 3 , 14 , 15 -> 314 + 315 + 1415
    public static int question_amazon_hackon(int[] arr,int n){
        int sum = 0;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                StringBuilder ans = new StringBuilder();
                ans.append(arr[i]);
                ans.append(arr[j]);
                sum+=Integer.parseInt(ans.toString());
            }
        }
        return sum;
    }
    public static long question_amazon_hackon_optimise(int[] arr,int n){
        int sum = 0;
        int previous  = arr[0];
        // 3 14 15
        // 0 17 32
        for(int i=1;i<n;i++){

            int digit = count_no_of_digits(arr[i]);
            int total = previous * (int) (Math.pow(10, digit)) + arr[i] * (i-1);

            sum+=total;
            previous+=arr[i];
        }
        return sum;
    }

    public static int count_no_of_digits(int n){
       return Integer.toString(n).length();
    }

    // question 47 -> needs to be optimised
    public static int question_uber_oa_sde1(int[] arr,int n){
        int sum = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                StringBuilder ans = new StringBuilder();
                ans.append(arr[i]);
                ans.append(arr[j]);
                sum+=Integer.parseInt(ans.toString());
            }
        }
        return sum;
    }

    public static int question_uber_oa_sde1_optimise(int[] arr,int n){
        int sum = 0;
        int previous = 0;
        int[] suffix = new int[n+1];
        suffix[n] = 0;
        for(int i=n-1;i>=0;i--){
            suffix[i] = suffix[i+1] + arr[i];
        }

        for(int i=1;i<n;i++){
            int digits = count_no_of_digits(arr[i]);
            int res = previous*((int) Math.pow(10,digits)) + arr[i]*(i-1) + suffix[i]*((int) Math.pow(10,digits)) + arr[i]*(n-i);
            sum+=res;
            previous+=arr[i];
        }
        return sum;
    }

    // amazon interview problem
    public static int amazon_interview_question(int[] arr,int n,int k){
        int[] prefix = new int[n];
        prefix[0] = arr[0];
        for(int i=1;i<n;i++){
            prefix[i] = prefix[i-1] + arr[i];
        }
        HashMap<Integer,Integer> m1  = new HashMap<>();

        int max = 0;
        for(int i=0;i<n;i++){
            int option1 = arr[i] + k;
            int option2 = Math.abs(arr[i]-k);
            if(m1.containsKey(option1) || m1.containsKey(option2)) {
                if (m1.containsKey(option1)) {
                    int ind = m1.get(option1);
                    int sum = prefix[i];
                    if (ind != 0) sum = prefix[i] - prefix[ind - 1];

                    max = Math.max(sum, max);
                }
                if (m1.containsKey(option2)) {
                    int ind = m1.get(option2);
                    int sum = prefix[i];
                    if (ind != 0) sum = prefix[i] - prefix[ind - 1];
                    max = Math.max(max, sum);
                }
            }
            else{
                if(m1.containsKey(arr[i])){
                    m1.put(arr[i],Math.min(i,m1.get(arr[i])));
                }
                else m1.put(arr[i],i);
            }

        }
        return max;
    }


    // question - 48
//   public static int question_google_swe_oa(int[] arr,int n,int k){
//
//   }

    // question 50
//    public static int question_google_sde_interview(int[] arr,int n){
//        int count = 0;
//        for(int i=0;i<n;i++){
//            boolean flag = false;
//            for(int j=i;j<n;j++){
//                if(flag==false && arr[j]>arr[j-1]){
//
//                }
//            }
//        }
//    }

}
