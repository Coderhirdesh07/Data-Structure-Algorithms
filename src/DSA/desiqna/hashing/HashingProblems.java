package DSA.desiqna.hashing;

import java.util.HashMap;
import java.util.Scanner;

public class HashingProblems {
    public static void main(String[] args){
      Scanner sc = new Scanner(System.in);
//       int n = sc.nextInt();
       int[] arr = {3, 2, 3, 2, 4, 3};
//       int[] result = usingHashMap(arr,6);
//        System.out.println("min element: " + result[0] + " max element: " + result[1]);
//        boolean result = question_2(arr,6,3);
        int result = question_4(arr,6,1);

        System.out.println(result);

    }
    // Question 1 :-
    public static int[] question_1(int[] arr,int n){
        HashMap<Integer,Integer> m1 = new HashMap<>();
        for(int i=0;i<n;i++){
           m1.put(arr[i],m1.getOrDefault(arr[i],0)+1);
        }
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int maxElement = 0;
        int minElement = 0;
        int[] ans = new int[2];
        for(int it:m1.keySet()){
            int freq = m1.get(it);
            if(m1.get(it)>max){
                max = m1.get(it);
                maxElement = it;
            }
            if(m1.get(it)<min){
                min = m1.get(it);
                minElement = it;
            }
        }
        ans[0] = minElement;
        ans[1] = maxElement;
        return ans;
    }

    // Question 2 :-
    public static boolean question_2(int[] arr,int n,int k){
        HashMap<Integer,Integer> m1 = new HashMap<>();
        for(int i=0;i<n;i++){
           if(m1.containsKey(arr[i])){
                int curInd = m1.get(arr[i]);
                if(Math.abs(i-curInd)<=k) return true;
           }
           else m1.put(arr[i],i);
        }
        return false;

    }

    // question_3
    public  static  int question_3(int[] arr,int n,int k){
        HashMap<Integer,Integer> m1 = new HashMap<>();
        int count = 0;
        for(int i=0;i<n;i++){
            int findElement = k-arr[i];
            if(m1.containsKey(findElement) && m1.get(findElement)<i) {
                count++;
            }
            else m1.put(arr[i],i);
        }
        return count;
    }

    // question_4
    public static  int question_4(int[] arr,int n,int k){
        HashMap<Integer,Integer> m1 = new HashMap<>();
        int count = 0;
        for(int i=0;i<n;i++){
            int findElement = k+arr[i];
            if(m1.containsKey(findElement) && m1.get(findElement)<i) {
                count++;
            }
            else m1.put(arr[i],i);
        }
        return count;
    }
}
