package dsacourse851;

import java.util.Arrays;
import java.util.Scanner;

public class UnderStandingBitManipulation {
    public static void main(String[] args) {
        Scanner  sc = new Scanner(System.in);
        int n = sc.nextInt();
//        int[] arr = new int[n+1];
//        for(int i=1;i<=n;i++){
//            arr[i] = sc.nextInt();
//        }
//        // 3 1 7
//        // 1 1 3 3
//
//        // 8 6 5 9 7 7 9 3 8
//        // 3 5 5 5
//        int m = sc.nextInt();
//        int[][] mat = new int[m][4];
//        for(int i=0;i<m;i++){
//            mat[i][0] = sc.nextInt();
//            mat[i][1] = sc.nextInt();
//            mat[i][2] = sc.nextInt();
//            mat[i][3] = sc.nextInt();
//        }
//        question_8_brute(mat,arr);
        int res  = question_10_optimise(n);
        System.out.println(res);
    }
    public static boolean question_2(int[] num){
        int count = 0;
        for(int x:num){
            if((x&1)==0){
                count++;
            }
        }
        return (count>=2);
    }
    public static int question_3(int num1,int num2){
        int count_1 = Integer.bitCount(num1);
        int count_2 = Integer.bitCount(num2);
        int res = num1;
        for(int i=0;i<32;i++){
            if(count_1 > count_2 && ((1<<i) & num1)>0){
                res^= 1<<i;
                count_1--;
            }
            if(count_1 < count_2 && ((1<<i) & num1)==0){
                res^= 1<<i;
                count_1++;
            }
        }
        return res;
    }
    public static int question_4_part_1(int[] arr,int x){
        int n = arr.length;
        int count = 0;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                int res = arr[i] ^ arr[j];
                if(res == x){
                    count++;
                }
            }
        }
        return count;
    }
    public static int question_4_part_2(int[] arr,int n){
        int sum = 0;
        for(int i=0;i<n;i++){
            for(int j=i;j<n;j++){
                sum+=arr[i]|arr[j];
            }
        }
        return sum;
    }

    public static void question_8_brute(int[][] mat,int[] arr){
        int n = mat.length;

        for(int i=0;i<n;i++){
            int s1 = mat[i][0];
            int e1 = mat[i][1];
            int s2 = mat[i][2];
            int e2 = mat[i][3];
            int x1 = arr[s1];
            for(int j=s1+1;j<=e1;j++){
                x1&=arr[j];
            }
            int x2 = arr[s2];
            for(int k=s2+1;k<=e2;k++){
                x2&=arr[k];
            }
            System.out.println(x1^x2);
        }

    }
    public static void question_8_optimise(int[][] mat,int[] arr){
      int n  = mat.length;
      int m = arr.length;
      int[][] binary = new int[m][10];
      for(int i=0;i<m;i++){
          int val = arr[i];
          binaryConversion(binary[i],val);
      }



    }
    public static void binaryConversion(int[] a,int x){
        int m = a.length;
        Arrays.fill(a,0);
        int ind = m-1;
        while(ind>=0 && x>=1){
            int rem = x%2;
            a[ind] = rem;
            ind--;
            x=x/2;
        }
    }


    public static int question_9_brute(int[] arr,int n){
        int count = 0;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                int val1 = arr[i]^arr[j];
                int val2 = arr[i]&arr[j];
                if(val1 > val2){
                    count++;
                }
            }
        }
        return count;
    }
    public static int question_9_optimise(int[] arr,int n){
        //       011 -> 3
        //       110 -> 6
        //a1^a2= 101 -> 5
        //a1&a2= 010 -> 2
        int[] count_set_bits = new int[32];
        int count = 0;
        for(int i=1;i<=n;i++){
            int val = LeftSetBit(arr[i]);

            count+=count_set_bits[val];
            count_set_bits[val]++;
        }
        int total = ((n-1)*n)/2;

        return total-count;
    }
    public static int LeftSetBit(int x){
        int ind = 31;
        while(((x >> ind)&1) == 0 && ind>0){
           ind--;
        }
        return ind;
    }

    // salesforce oa problem
    public static int question_10(int a){
        // 7 -> 111
        // 6 -> 110
        // 5 -> 101
        // 4 -> 100
        // 3 -> 011

        int res = a;
        int ans = -1;
        for(int i=a-1;i>=1;i--){
            res&=i;
            if(res==0){
                ans = i;
                break;
            }
        }

        return  ans;
    }
    public static int question_10_optimise(int n){
        int y = 0;
        for(int i=30;i>=0;i--){
            int res = (n>>i)&1;
            if(res==1){
                y=i;
                break;
            }
        }
        return (1<<y) -1;
    }

    public static int question_11(int[] arr,int n){
        // 3   1    6   2   2
        // 011 001  110 010 010

        //  2 1 0
        //  4 1 3

        int[] res = new int[30];
        int max = -1;
        for(int i=0;i<n;i++){
            for(int j=0;j<=30;j++){
                if( ((arr[i] >> j) &1) ==1){
                    res[j]++;
                    max = Math.max(max,res[j]);
                }
            }
        }
        return max;
    }
    public static int question_12_brute(int[] a,int[] b){
        int n = a.length;
        int m = b.length;
        int res = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                res += a[i]^b[j];
            }
        }
        return res;
    }

    // find xor of all sub array
    public static int question_14_brute(int[] arr,int n){
        int val = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<i;j++){
                val+=arr[i]^arr[j];
            }
        }
        return val;
    }

    public static int question_15_brute(int[] arr,int n){
        int val = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<i;j++){
                val+=arr[i]&arr[j];
            }
        }
        return val;
    }
}
