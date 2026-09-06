package dsacourse851;

import java.util.Scanner;

public class UnderStandingBitManipulation {
    public static void main(String[] args) {
        Scanner  sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n+1];
        for(int i=1;i<=n;i++){
            arr[i] = sc.nextInt();
        }
        // 3 1 7
        // 1 1 3 3

        // 8 6 5 9 7 7 9 3 8
        // 3 5 5 5
        int m = sc.nextInt();
        int[][] mat = new int[m][4];
        for(int i=0;i<m;i++){
            mat[i][0] = sc.nextInt();
            mat[i][1] = sc.nextInt();
            mat[i][2] = sc.nextInt();
            mat[i][3] = sc.nextInt();
        }
        question_8_brute(mat,arr);
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
}
