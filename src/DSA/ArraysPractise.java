package DSA;

import java.util.ArrayList;
import java.util.Arrays;

public class ArraysPractise {
    public static void main(String[] args) {
        int[] ans = {10,20,3,40,50};
 //        int[] array = insertAtParticularIndex(ans,2,5,25);
//          seperateEvenOdd(ans,5);
          int[] arr = {1 ,1 ,5 ,5 ,2 ,2 ,3 ,3 ,4 ,4};
          int[] arr1 = {2,5,1,7,3,9};
//          int res = removeDuplicates(arr,10);
//          for(int x:arr){
//              System.out.println(x);
//          }
        int[] result = countingSort(arr1,6);
        for(int i=0;i<6;i++){
            System.out.print(result[i] + "");
        }


    }
    public static int[] countingSort(int[] arr,int n){
        int max = -1;
        for(int i=0;i<n;i++){
            max = Math.max(max,arr[i]);
        }
        int[] ans = new int[max+1];
        Arrays.fill(arr,0);
        for(int num:arr){
             ans[num]++;
        }
        for(int i=1;i<ans.length;i++){
            ans[i]+=ans[i-1];
        }
       int[] result = new int[n];
        for(int i=n-1;i>=0;i--){
            result[ans[arr[i]]-1] = arr[i];
            ans[arr[i]]--;
        }
        for(int i=0;i<n;i++){
            arr[i] = result[i];
        }
        return arr;

    }

    public static int[] insertAtParticularIndex(int[] array, int position, int size, int value) {
        int[] result = new int[size + 1];
        int i = 0; // array
        int j = 0; // result
        while (j < size+1) {
          if(j==position-1){
              result[j] = value;
              j++;
          }
          else{
              result[j] = array[i];
              i++;
              j++;

          }

        }
        return result;
    }

    public static ArrayList<ArrayList<Integer>> printingAllSubArray(int[] array, int size){
        ArrayList<ArrayList<Integer>> list  = new ArrayList<>();
        for(int i=0;i<size;i++){
            ArrayList<Integer> temp = new ArrayList<>();
            for(int j=i;j<size;j++){
             temp.add(array[i]);
            }
            list.add(temp);
        }
        return list;
    }

    public static void seperateEvenOdd(int[] array,int size){
        int evenCount = 0;

        for(int i=0;i<size;i++){
            if((array[i]&1)==0){
                evenCount++;
            }

        }
        int oddCount = size-evenCount;
        System.out.println(evenCount + " " + oddCount);
        int[] even = new int[evenCount];
        int[] odd = new int[oddCount];

        int j=0;
        int k = 0;
        for(int i=0;i<size;i++){
            if((array[i]&1)==0){
                even[j++] = array[i];
            }
            else{
                odd[k++] = array[i];
            }
        }

        for(int x:even){
            System.out.print(x + " ");
        }
        System.out.println("");
        for(int y:odd){
            System.out.print(y + " ");
        }
    }


    // two pointers and prefix sum array problems starts here

    public static int removeDuplicates(int[] array,int n){
        // 1 1 1 2 2 2 3 3 3 3
        int i = 0;

        int index = 0;
        while(i<n){
           array[index]= array[i];
           index++;
           while(i<n && array[index-1]==array[i]){
               i++;
           }



        }
        return index;
    }


    // leetCode practise question starts here



}
