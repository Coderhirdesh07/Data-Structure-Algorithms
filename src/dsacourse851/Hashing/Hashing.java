package dsacourse851.Hashing;

import java.util.HashMap;
import java.util.Scanner;

public class Hashing {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();
        int[] queries = new int[q];
        int[] arr = new int[n];

        for(int i=0;i<q;i++){
            queries[i] = sc.nextInt();
        }
        for(int i=0;i<n;i++){
            arr[i] = sc.nextInt();
        }
        question_1_brute(arr,queries,n,q);
    }
    public static void question_1_brute(int[] arr,int[] queries,int n,int m){

        for(int i=0;i<m;i++){
            int count = 0;
            for(int j=0;j<n;j++){
                if(arr[j] == queries[i]){
                    count++;
                }
            }
            System.out.println(count);
        }
    }
    public static void question_1_optimise(int[] arr,int[] queries,int n,int m){
        int[] freq = new int[50];
        for(int i=0;i<n;i++){
            int val = arr[i];
            freq[val]++;
        }

        for(int i=0;i<m;i++){
            int find = queries[i];
            System.out.println(freq[find]);
        }
    }

    public static void question_1_optimised(int[] arr,int[] queries,int n,int m){
        HashMap<Integer,Integer> m1 = new HashMap<>();
        for(int i=0;i<n;i++){
            int val = arr[i];
            m1.put(val,m1.getOrDefault(val,0)+1);
        }

        for(int i=0;i<m;i++){
            int find = queries[i];
            if(m1.containsKey(find)){
                System.out.println(m1.get(find));
            }
            else System.out.println(-1);
        }
    }

    public static void question_2_brute(int[] arr,int n){
        int maxElement = -1;
        for(int x:arr){
            maxElement = Math.max(x,maxElement);
        }
        int[] freq = new int[maxElement+1];
        for(int i=0;i<n;i++){
            int val = arr[i];
            freq[val]++;
        }
        int maxIndex = 0;
        int maxFreq = Integer.MIN_VALUE;
        int minFreq = Integer.MAX_VALUE;
        int minIndex = 0;
        for(int i=0;i<=maxElement;i++){
            if(maxFreq<freq[i]){
                maxFreq = freq[i];
                maxIndex = i;
            }
            if(minFreq>freq[i]){
                minFreq = freq[i];
                minIndex = i;
            }
        }
        System.out.println(maxIndex + "" + minIndex);

    }
    public static void question_2_optimise(int[] arr,int n){

        HashMap<Integer,Integer> m1 = new HashMap<>();
        for(int i=0;i<n;i++){
            int val = arr[i];
            m1.put(val,m1.getOrDefault(val,0)+1);
        }

        int maxFreq = Integer.MIN_VALUE;
        int minFreq = Integer.MAX_VALUE;
        int maxIndex = 0;
        int minIndex = 0;
        for(int it:m1.keySet()){
            if(m1.get(it) > maxFreq){
                maxFreq = m1.get(it);
                maxIndex = it;
            }
            if(m1.get(it)<minFreq){
                minFreq = m1.get(it);
                minIndex = it;
            }
        }
        System.out.println( maxIndex + "" + minIndex);

    }

    public static boolean question_3_optimise(int[] arr,int n,int k){
        HashMap<Integer,Integer> m1 = new HashMap<>();
        boolean flag = false;
        for(int i=0;i<n;i++){
            int val  = arr[i];
            if(m1.containsKey(val) == true && i - m1.get(val) <= k){
                    flag = true;
                    break;
            }
            m1.put(val,i);
        }
        return flag;
    }
    public static int question_4_brute(int[] arr,int n,int k){
        int count = 0;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                int sum = arr[i] + arr[j];
                if(sum == k){
                    count++;
                }
            }
        }
        return count;
    }
    public static int question_4_optimise(int[] arr,int n,int k){
        int count = 0;
        HashMap<Integer,Integer> m1 = new HashMap<>();
        m1.put(0,0);
        for(int j=0;j<n;j++){
            int find = k - arr[j];
            if(m1.containsKey(find) && m1.get(find) != j){
                count++;
            }
            m1.put(arr[j],j);
        }
        return  count;
    }

    public static int question_5_brute(int[] arr,int n,int k){
        int count = 0;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                int diff = arr[i] - arr[j];
                if(diff == k){
                    count++;
                }
            }

        }
        return count;
    }
    public static int question_5_optimise(int[] arr,int n,int k){
        int count = 0;
        HashMap<Integer,Integer> m1 = new HashMap<>();
        m1.put(0,0);
        for(int j=0;j<n;j++){
            int find = k + arr[j];
            if(m1.containsKey(find) && m1.get(find) != j){
                count++;
            }
            m1.put(arr[j],j);
        }
        return  count;
    }

    public static int question_6_brute(int[] arr,int n,int k){
        int count = 0;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                int diff = arr[i] - arr[j];
                if(diff == k || diff == -1*k){
                    count++;
                }
            }
        }
        return  count;
    }
    public static int question_6_optimise(int[] arr,int n,int k){
        int count = 0;
        HashMap<Integer,Integer> m1 = new HashMap<>();
        m1.put(0,0);
        for(int j=0;j<n;j++){
            int find_1 = k - arr[j];
            int find_2 = k + arr[j];
            if(m1.containsKey(find_1) && m1.get(find_1) !=j || m1.containsKey(find_2) && m1.get(find_2)!=j ){
                count++;
            }
            m1.put(arr[j],j);
        }
        return  count;
    }
    public static int question_7_brute(int[] arr,int n,int k){
        int count = 0;
        for(int i=0;i<n;i++){
            int sum = 0;
            for(int j=i;j<n;j++){
                sum+=arr[j];
                if(sum==k){
                    count++;
                }
            }
        }
        return  count;
    }

    public static int question_7_optimise(int[] arr,int n,int k){
        HashMap<Integer,Integer> m1 = new HashMap<>();
        int sum = 0;
        int count = 0;
        m1.put(0,1);
        for(int i=0;i<n;i++){
            sum+=arr[i];
            if(m1.containsKey(sum - k)){
                count+=m1.get(sum - k);
            }
            m1.put(sum,m1.getOrDefault(sum,0)+1);
        }

        return count;
    }

}
