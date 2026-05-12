package dsacourse851.BinarySearch;

import java.util.*;

public class UnderstandingBinarySearch {
    public static void main(String[] args) {

//        int[] composition = {2,2,3,1};
//        int[] stock = {3,2,1,4};
//        int[] cost = {2,3,1,6};
//        int budget = 30;
        int[] cache = {1, 1, 3, 1, 1,};

//        int[] parcels = {1,3,5,2,1,3,1};0
//        // 1 1 1 2 3 3 5
//        //
//        int extra = 3;
////         int[] arr = {5,3,20,16,18,1,10,10,9,8};

//        int res = question_34(3, 5, cache);
        int[] arr  = {1,2,3,4};
        int[] nums = {10,1,2,7,1,3};
        int res = question_37(10,arr.length,arr);
        System.out.println(res);

    }

    public static int[] question_1(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);


        int[] ans = new int[2];
        int firstIndex = lb(nums, n, target);
        int lastIndex = up(nums, n, target);
        if (firstIndex == lastIndex) return new int[]{-1, -1};
        ans[0] = firstIndex;
        ans[1] = lastIndex - 1;
        return ans;
    }

    public static int lb(int[] nums, int n, int target) {
        int low = 0;
        int high = n - 1;
        int ans = n;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] >= target) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    public static int up(int[] nums, int n, int target) {
        int low = 0;
        int high = n - 1;
        int ans = n;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] > target) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;

    }

    // search in a rotated sorted array
//    public static int question_3(int[] nums,int target){
//
//    }
//    // single element in an array
//    public static int question_4(int[] nums,int target){
//
//    }
//    // find n-th root of number
//    public static int question_5(int n,int m){
//
//    }

    // find first and last occurence of 1
    public static int question_6(int[] nums, int n) {
        int low = 0;
        int high = n - 1;

        int target = 1;
        int ans = 0;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] >= target) {
                ans = mid;
                high = mid - 1;
            } else low = mid + 1;
        }
        return ans;
    }

    public static int question_8(int[] nums, int n, int capacity) {
        int max = 0;
        int low = 1;
        for (int x : nums) {
            max = Math.max(max, x);
        }
        int high = max;
        int ans = max;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (isPossible(mid, nums, capacity)) {
                ans = mid;
                high = mid - 1;
            } else low = mid + 1;
        }
        return ans;
    }

    public static boolean isPossible(int speed, int[] nums, int k) {
        int count = 0;
        for (int it : nums) {
            if (it % speed == 0) count += it / speed;
            else count += it / speed + 1;
        }
        return count <= k;
    }

    // amazon sde intern interview
    public static int question_8_approach_1(int[] arr, int k, int n) {
        HashMap<Integer, Integer> m1 = new HashMap<>();
        for (int i = 0; i < n; i++) {
            m1.put(arr[i], m1.getOrDefault(arr[i], 0) + 1);
        }
        int ans = 0;
        for (int it : m1.keySet()) {
            if (m1.get(it) < k) {
                ans = it;
            }
        }
        return ans;
    }

    //    public static int question_8_approach_2(int[] arr,int k,int n){
//
//
//    }
    // arranging coins
    public static int question_10(int n) {
        // if(n==1) return 1;
        long low = 1;
        long high = n;

        while (low <= high) {
            long mid = (low + high) / 2;
            long needed = mid * (mid + 1) / 2;
            if (needed == n) return (int) mid;
            else if (needed > n) {
                high = mid - 1;
            } else low = mid + 1;
        }
        return (int) high;
    }

    public static char question_11(char[] letters, char target) {
        int n = letters.length;
        int low = 0;
        int high = n - 1;

        char ans = letters[0];
        while (low <= high) {
            int mid = (low + high) / 2;
            int check = letters[mid] - 'a';
            int find = target - 'a';
            if (check > find) {
                ans = letters[mid];
                high = mid - 1;
            } else low = mid + 1;
        }
        return ans;

    }

    // find insert position
    public static int question_12(int[] nums, int target) {
        int n = nums.length;
        int low = 0;
        int high = n - 1;
        int ans = n;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] >= target) {
                ans = mid;
                high = mid - 1;
            } else low = mid + 1;
        }
        if (ans != n && nums[ans] == target) {
            return ans;
        }
        return ans;
    }

    // middle occurrences
    public static int question_14(int[] nums, int n, int target) {

        int firstInd = lb(nums, n, target);
        int lastInd = up(nums, n, target);

        return (firstInd + lastInd) / 2;
    }

    public boolean question_13(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;

        int low = 0;
        int high = n * m - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int row = mid / m;
            int col = mid % m;
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] > target) {
                high = mid - 1;
            } else low = mid + 1;
        }
        return false;
    }


    // agressive cows
    public static int question_16(int[] stalls, int k) {
        // code here
        int n = stalls.length;

        Arrays.sort(stalls);

        int low = 1;
        int high = stalls[n - 1] - stalls[0];


        int ans = high;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (isPossible_2(mid, stalls, k)) {
                ans = mid;
                low = mid + 1;
            } else high = mid - 1;
        }
        return ans;
    }

    public static boolean isPossible_2(int distance, int[] stalls, int k) {
        int prev = -1;
        int count = 1;
        int n = stalls.length;
        for (int i = 0; i < n; i++) {
            if (prev == -1) {
                prev = stalls[i];
            } else {
                int diff = stalls[i] - prev;
                if (diff >= distance) {
                    prev = stalls[i];
                    count++;
                }
            }
        }
        return count >= k;
    }

    // de- shaw oa question  decrement operations
    public static int question_22(int[] arr, int moves) {
        int n = arr.length;
        int max = -1;
        int min = Integer.MAX_VALUE;

        for (int x : arr) {
            max = Math.max(max, x);
            min = Math.min(min, x);
        }
        int low = min;
        int ans = max;
        int high = max;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (isPossible_3(mid, arr, moves)) {
                ans = mid;
                high = mid - 1;
            } else low = mid + 1;
        }
        return ans;
    }

    public static boolean isPossible_3(int decrease, int[] arr, int moves) {
        int noOfOperation = 0;
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            if (arr[i] % decrease == 0) {
                noOfOperation += arr[i] / decrease;
            } else noOfOperation += arr[i] / decrease + 1;
        }

        return noOfOperation <= moves;
    }

    public static int question_20(int[] arr, int k, int n) {
        int low = 0;
        int sum = 0;
        for (int x : arr) {
            sum += x;
        }
        int ans = 0;
        int high = sum;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (isPossible_4(mid, arr, k)) {
                ans = mid;
                high = mid - 1;
            } else low = mid + 1;
        }
        return ans;
    }


    public static boolean isPossible_4(int portfolio, int[] arr, int k) {
        int count = 0;
        return true;
    }

    // microsoft oa question about stocks and alloy with budget
    public static int question_21(int[] stock, int[] composition, int[] cost, int budget, int n) {
        int low = 0;
        int high = 10000;
        int ans = high;
        while (low < high) {
            int mid = (low + high) / 2;
            if (isPossible_5(mid, stock, composition, cost, budget, n)) {
                ans = mid;
                low = mid + 1;
            } else high = mid - 1;
        }
        return ans;
    }

    public static boolean isPossible_5(int toProduce, int[] stock, int[] composition, int[] cost, int budget, int n) {

        int cost_to_product_1 = 0;
        for (int i = 0; i < n; i++) {
            int diff = (composition[i] * toProduce - stock[i]);
            if (diff > 0) {
                cost_to_product_1 += diff * cost[i];
            } else cost_to_product_1 += 0;
        }

        return cost_to_product_1 <= budget;
    }

    public static int question_23(int cats, int length, int[] holes) {
        int low = 1;
        int sum = 0;
        for (int i = 0; i < length; i++) {
            sum += holes[i];
            low = Math.max(low, holes[i]);
        }
        int high = sum;
        int ans = sum;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (isPossible_6(mid, holes, cats)) {
                ans = mid;
                high = mid + 1;
            } else low = mid + 1;
        }
        return ans;
    }

    // m is cats
    // values is total to dig
    public static boolean isPossible_6(int value, int[] holes, int n) {
        int count = 1;

        int sum = 0;
        for (int i = 0; i < holes.length; i++) {
            sum += holes[i];
            if (sum > value) {
                count++;
                sum = holes[i];
            }
        }
        return count <= n;
    }

    public static int question_25(int[] arr, int n, int maxScore) {
        int low = 1;
        int high = n;
        int ans = high;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (isPossible_11(mid, arr, maxScore)) {
                ans = mid;
                low = mid + 1;
            } else high = mid - 1;
        }
        return ans;
    }

    public static boolean isPossible_11(int k, int[] arr, int maxScore) {
        int total = 0;
        int length = arr.length;
        for (int i = 0; i < k; i++) {
            total += ((i + 1) * k + arr[i]);
        }
        return (total <= maxScore);
    }

//    public static int question_26(int[] arr,int n){
//
//    }

    // question_27 amazon oa question
    public static int question_27(int[] parcels, int n, int extra) {
        int sum = 0;
        for (int x : parcels) {
            sum += x;
        }
        sum += extra;

        int low = 0;
        int high = sum;
        int ans = sum;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (isPossible_7(mid, parcels, n, extra)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    public static boolean isPossible_7(int mid, int[] parcels, int n, int extra) {

        for (int i = 0; i < n; i++) {
            if (parcels[i] < mid) {
                int more = mid - parcels[i];
                if (extra >= more) {
                    extra -= more;
                } else extra -= extra;
            }
        }
        if (extra == 0) return true;
        return false;
    }


    // question deshaw
    // passes given test case
    public static int question_29(int[] arr, int n, int k) {
        Arrays.sort(arr);
        int low = 0;
        int high = arr[n - 1] - arr[0];

        int ans = 0;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (isPossible_8(mid, arr, n, k)) {
                ans = mid;
                low = mid + 1;
            } else high = mid - 1;
        }
        return ans;
    }

    public static boolean isPossible_8(int speed, int[] arr, int n, int k) {
        int count = 0;
        int prev = -1;
        for (int i = 0; i < n; i++) {
            if (prev == -1) {
                prev = arr[i];
            } else {
                int diff = arr[i] - prev;
                if (diff >= speed) {
                    count++;
                }
            }
        }

        return (count >= k);
    }


    // int n         int m      int k
    public static int question_28(int processors, int tasks, int efficient) {
        int low = 0;
        int high = processors;
        int ans = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (isPossible_10(mid, tasks, processors, efficient)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    public static boolean isPossible_10(int mid, int tasks, int processors, int efficient) {
        int[] arr = new int[processors];

        tasks -= (processors - 1);
        Arrays.fill(arr, 1);
        tasks -= mid;
        arr[efficient] = mid;

        int i = efficient - 1;
        int j = efficient + 1;
        int prev_1 = mid;
        int prev_2 = mid;

        // arr[mid] = 3 ;
        // arr[i] = 1;
        // 3-1 = 2
        while (i >= 0 && j < processors && tasks > 0) {
            int diff_1 = prev_1 - arr[i];
            int diff_2 = prev_2 - arr[j];

            if (diff_1 > 1) {
                arr[i] += diff_1 - 1;
                tasks -= diff_1 - 1;
            }
            if (diff_2 > 1) {
                arr[j] += diff_2 - 1;
                tasks -= diff_2 - 1;
            }
            prev_1 = arr[i];
            prev_2 = arr[j];
            i--;
            j++;
        }

        return (tasks == 0);

    }

    // question of zscaler with 1-based indexing
    public static int question_zscaler(int[] arr, int n) {
        int low = 1;
        int high = 0;

        for (int x : arr) {
            high = Math.max(high, x);
        }

        int ans = low;

        while (low < high) {
            int mid = (low + high) / 2;
            if (isPossible_12(mid, arr, n)) {
                ans = mid;
                high = mid - 1;
            } else low = mid + 1;
        }

        return ans;
    }

    // 5
    // 2 8
    public static boolean isPossible_12(int toDeduct, int[] arr, int n) {

        for (int i = n - 1; i > 0; i--) {
            if (arr[i] > toDeduct) {
                int toremove = Math.abs(arr[i] - toDeduct);
                arr[i - 1] = arr[i - 1] + toremove;
                arr[i] -= toDeduct;
            }

        }
        return (arr[0] <= toDeduct);
    }


    public static int question_30(int n){
        if(n==1) return 1;

        int low = 1;
        int high = n;

        int ans = n;

        while (low <=high){
            int mid = (low + high)/2;
            int result = isPossible_9(mid,n);
            if(ans>result){
                ans = result;
                low = mid+1;
            }
            else high = mid-1;
        }

        return ans;
    }
    public static int isPossible_9(int canEat,int n){
        int count = 2;
        n = n-2;
        while(n>0){
           if(canEat+1<=n){
               n-=(canEat+1);
               count++;
           }
            if(canEat<=n){
               n-=canEat;
               count++;
           }
           else{
               int diff = canEat-1;
               if(diff!=0 && diff<=n){
                   n-=diff;
                   count++;
               }
           }
        }
        return count;
    }


    // blackrock oa question
    public static int question_31(int[] arr, int n) {
        int low = 0;
        int high = n;
        int ans = high;
        Arrays.sort(arr);
        while (low <= high) {
            int mid = (low + high) / 2;
            if (isPossible_question_31(mid, arr, n)) {
                ans = mid;
                low = mid + 1;
            } else high = mid - 1;
        }
        return ans;

    }

    public static boolean isPossible_question_31(int possible, int[] arr, int n) {
        int i = n - possible;
        int ind = 0;
        int count = 0;
        while (i < n) {
            if (arr[ind] < arr[i]) count++;
            ind++;
            i++;
        }
        return count >= possible;
    }

    public static int question_32(int numServers, int money, int sell, int upgrade) {

        int low = 0;
        int high = numServers;

        int ans = numServers;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (isPossible_question_32(mid, numServers, money, sell, upgrade)) {
                ans = mid;
                low = mid - 1;
            } else low = mid + 1;
        }
        return ans;
    }

    public static boolean isPossible_question_32(int mid, int numServers, int money, int sell, int upgrade) {
        numServers -= mid;
        int money_sold = sell * mid;
        money += money_sold;
        int total_upgrade = numServers * upgrade;
        return money >= total_upgrade;
    }

    public static int question_34(int n, int m, int[] cache) {
        HashMap<Integer, Integer> m1 = new HashMap<>();
        for (int i = 1; i < n; i++) {
            m1.put(i, 0);
        }

        for (int i = 0; i < m; i++) {
            m1.put(cache[i], m1.getOrDefault(cache[i], 0) + 1);
        }

        int low = 1;
        int high = 2 * m;

        int ans = 0;
        while (low < high) {
            int mid = (low + high) / 2;
            if (isPossible_question_34(mid, n, m, cache, m1)) {
                ans = mid;
                high = mid - 1;
            } else low = mid + 1;
        }
        return ans;

    }

    public static boolean isPossible_question_34(int mid, int n, int m, int[] cache, HashMap<Integer, Integer> freq) {
        int extra = 0;

        for (int it : freq.keySet()) {
           int key = it;
           int val = freq.get(key);
           if(val<=mid){
               int minExtra = (mid - val)/2;
               extra-=minExtra;
               if(extra <= 0) extra=0;
           }
           else{
                extra+=Math.abs(val-mid);
           }
        }
        return (extra<=0);
    }
    public static int question_35(int[] throughput,int[] cost,int budget,int n){
        int low = 0;
       Arrays.sort(throughput);
        int high = budget/throughput[0];

        int ans = 0;
        while(low<=high){
            int mid = (low + high)/2;
            if(isPossible_question_35(mid,throughput,cost,budget,n)){
                ans = mid;
                low = mid+1;
            }
            else high = mid-1;
        }
        return throughput[0] + ans*throughput[0];
    }
    public static boolean isPossible_question_35(int scale_for,int[] throughput,int[] cost,int budget,int n){
            int total = 0;
            total+=cost[0]*scale_for;
        for(int i=1;i<n;i++){
            total+=cost[i];
        }
        return total<=budget;
    }

    // Winzo oa problems brute
    public static int question_36_brute(int[] arr,int k,int n){
        int count = 0;
        for(int i=0;i<n;i++){
            int sum  = 0;
            for(int j=i;j<n;j++){
                sum+=arr[j];
                if(sum<=k){
                    count++;
                }
            }
        }
        return count;
    }
    // winzo oa problems optimised
//    public static int question_36_optimise(int[] arr,int k,int n){
//
//    }

    // this is question of infosys
    // concept is binary search and greedy
    public static int question_37(int total,int n,int[] arr){
        int low = Integer.MAX_VALUE;
        int high = Integer.MIN_VALUE;
        Arrays.sort(arr);
       for(int i=0;i<n;i++){
           int res = arr[i]*(total*total-total+1);
           low = Math.min(low,res);
           high = Math.max(high,res);
       }
       int ans = 0;

        while (low<=high){
            int mid = (low + high)/2;
            if(isPossible_question_37(mid,arr,total,n)){
                ans = mid;
                high = mid-1;
            }
            else low = mid+1;
        }

        return ans;
    }
    public static boolean isPossible_question_37(int maxTime,int[] arr,int total,int n){

        for(int i=0;i<n;i++){
            int ind = 1;
            while(ind<total && total!=0){
                int time = arr[i]*(ind*ind-ind+1);
                if(time<=maxTime && total>0){
                    total--;
                    ind++;
                }
                else break;
            }
        }
        return total>0;
    }
    // question amazon oa
    public static int question_38_brute(int[] arr,int n){
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0;i<n;i++){
            HashSet<Integer> s1 = new HashSet<>();
            for(int j=i;j<n;j++){
                s1.add(arr[j]);
                list.add(s1.size());
            }
        }
        Collections.sort(list);
        int length = list.size();
        int mid = (length-1)/2;
        return list.get(mid);
    }
//    public static int question_38_optimise(int[] arr,int n){
//
//    }

    public static int question_40(int[] arr,int n,int p){

        Arrays.sort(arr);
        int low = 0;
        int high  = Math.abs(arr[n-1]-arr[0]);

        int ans = low;
        while(low<=high){
            int mid = (low + high)/2;
            if(isPossible_question_40(mid,arr,n,p)){
                ans = mid;
                high = mid-1;
            }
            else low = mid+1;
        }
        return ans;
    }
    public static boolean isPossible_question_40(int diff,int[] arr,int n,int pairs){
        int count = 0;
        int i = 1;
        while(i<n){
                int dif = Math.abs(arr[i-1] - arr[i]);
                if(dif<=diff){
                    count++;
                    i=i+2;
                }
               else i++;

        }
        return count>=pairs;
    }


}
