package dsacourse851.Greedy;

import java.util.Arrays;

public class UnderstandingGreedy {
    public static void main(String[] args) {

    }
    // max happiness
    public static int question_1(int[] arr,int k){
        Arrays.sort(arr);
        int n = arr.length;
        int ind = n - 1;
        int sum = 0;
        while(k-->0){
            sum+=arr[ind];
            ind--;
        }
        return sum;
    }
    public static int question_1_part_2(int[] arr,int k){
        Arrays.sort(arr);
        int ind = 0;
        int sum = 0;
        while(k-->0){
            sum+=arr[ind];
            ind++;
        }
        return sum;
    }

}
