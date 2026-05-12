package DSA.desiqna.recursion;

import java.util.Scanner;

public class Recursion {
    public static void main(String[] args) {
        // Find Sum of all the Numbers from 0 til N.
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int ans = question_1(0,n);
        System.out.println(ans);
    }
    public static int question_1(int ind,int n){
        if(ind>n){
            return 0;
        }
        return ind+question_1(ind+1,n);
    }
}
