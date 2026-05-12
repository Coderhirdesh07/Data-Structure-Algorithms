package dsacourse851.Hashing;

import java.util.HashMap;
import java.util.Scanner;

public class UnderStandingPrefixSum {
    public static void main(String[] args) {
        // Find Sum of Range  [l……….r] where(l<=r) using Prefix sum.
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        int q = sc.nextInt();
        int[][] queries = new int[q][2];
        for (int i = 0; i < q; i++) {
            queries[i][0] = sc.nextInt();
            queries[i][1] = sc.nextInt();
        }
//        int l = sc.nextInt();
//        int r = sc.nextInt();
//        int k = sc.nextInt();
//
//        int[] arr = new int[n];
//        for(int i=0;i<n;i++){
//            arr[i] = sc.nextInt();
//        }
//        int[] ans = question_3_optimise(arr,k);
//        System.out.println("minLength " + ans[0] + " " + "maxLength "+ans[1]);
//        System.out.println("maxLength "+ans[0]);
        for (int i = 0; i < q; i++) {
            int x = queries[i][0];
            int y = queries[i][1];
            int ans = question_1_optimise(arr, x, y);
            System.out.println(ans);
        }

    }

    public static int question_1(int[] arr, int l, int r) {
        int sum = 0;
        for (int i = l; i <= r; i++) {
            sum += arr[i];
        }
        return sum;
    }


    /**
     * job/interview  -> do linkedin course / resume course / offcampus course
     * Beginner-DSA   Pro DSA Plan
     * Development Dev Session
     */

    public static int question_1_optimise(int[] arr, int l, int r) {
        int n = arr.length;

        int[] prefixSum = new int[n];

        prefixSum[0] = arr[0];

        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + arr[i];
        }
        if (l != 0) return prefixSum[r] - prefixSum[l - 1];
        if (l == r) return arr[l];
        return prefixSum[r];

        // 1 2 4 6 2   l=2 , r=4
        // 1 3 7 13 15
        // sum_l = 7
        // sum_r = 15

    }

    public static int question_2(int[] arr, int k) {
        int n = arr.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                if (sum == k) count++;
            }
        }
        return count;
    }

    public static int question_2_optimise(int[] arr, int k) {
        int n = arr.length;
        HashMap<Integer, Integer> m1 = new HashMap<>();
        m1.put(0, 1);
        int count = 0;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            if (m1.containsKey(sum - k)) {
                count += m1.get(sum - k);
            }
            m1.put(sum, m1.getOrDefault(sum, 0) + 1);
        }
        return count;

    }

    public static int[] question_3(int[] arr, int k) {
        int n = arr.length;

        int max_length = -1;
        int min_length = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += arr[j];
                if (sum == k) {
                    max_length = Math.max(max_length, j - i + 1);
                    min_length = Math.min(min_length, j - i + 1);
                }
            }
        }
        int[] ans = new int[2];
        ans[1] = max_length;
        ans[0] = min_length;
        return ans;
    }

    // TODO to be done again  question 8  and quetion 9 has to be done
    public static int[] question_3_optimise(int[] arr, int k) {
        int n = arr.length;
        int max_length = 0;
        int min_length = Integer.MAX_VALUE;
        HashMap<Integer, Integer> m1 = new HashMap<>(); // for min length;
        HashMap<Integer, Integer> m2 = new HashMap<>(); // for max length;
        int sum = 0;
        m1.put(0, 0);
        m2.put(0, 1);
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            if (m2.containsKey(sum - k)) {
                int ind = m2.get(sum - k);
                int curLen = ind - i + 1;
                if (curLen > max_length) {
                    max_length = curLen;
                }
            }
            if (m1.containsKey(sum - k)) {
                int ind = m1.get(sum - k);
                int curLen = ind - i + 1;
                if (curLen < min_length) {
                    min_length = curLen;
                }
            }
            if (!m2.containsKey(sum)) {
                m2.put(sum, i);
            }

            m1.put(sum, i);
        }
        int[] ans = new int[2];
        ans[0] = min_length;
        ans[1] = max_length;
        return ans;
    }


    // find two max sum subarray which are not intersecting
    // -10 -5 2 4-15 -20 1 2 -> here subarray 1 -> 2,4   and subarray 2->1,2
//    public static int question_kadane_algo(int[] arr, int n) {
////
////    }
//
//    }
}
