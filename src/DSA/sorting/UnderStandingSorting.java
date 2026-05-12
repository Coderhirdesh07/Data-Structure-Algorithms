package DSA.sorting;

import java.util.Scanner;

public class UnderStandingSorting {

    public static void main(String[] args){
//        Scanner  sc = new Scanner(System.in);
//        int length = sc.nextInt();
//        int[] arr = new int[length];
//        for(int i=0;i<length;i++){
//            arr[i]  = sc.nextInt();
//        }
        int[] input = {5,4,3,2,1,7,8,2,4,8,4,3,6,12,13,42};
//        BubbleSort(input,5);
//        InsertionSort(input,input.length);
        SelectionSort(input,input.length);
        for(int i=0;i<input.length;i++){
            System.out.print (input[i] + " ");
        }
        // All sorting algorithm 1.Bubble  2.Insertion  3.Selection 4.Merge  5.Quick


    }
    public static void swap(int[] arr,int x,int y){
       arr[x] = arr[x]^arr[y];
       arr[y] = arr[x]^arr[y];
       arr[x] = arr[y]^arr[x];
    }
    public static void InsertionSort(int[] arr,int n){

        for(int i=0;i<n;i++){
            int j = i;
            while(j>0 && arr[j-1]>arr[j]){
                swap(arr,j,j-1);
                j--;
            }
        }

    }
    public static void SelectionSort(int[] arr,int n){

        for(int i=0;i<n-2;i++){
            int min = i;
            for(int j=i+1;j<n-1;j++){
                if(arr[j]<arr[min]){
                    min = j;
                }
            }
            swap(arr,i,min);
        }



    }
    public static void BubbleSort(int[] arr,int n){
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                if(arr[i]>arr[j]) swap(arr, i, j);
            }
        }


    }
}
