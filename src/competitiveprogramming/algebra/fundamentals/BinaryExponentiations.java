package competitiveprogramming.algebra.fundamentals;

import java.util.Scanner;

public class BinaryExponentiations {
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
        /**
         *
         * TODO
         *      This helps to find n raised to any power
         *      within O(log n) time
         *
         *      IMP -  (x.y).z = x.(y.z)
         *
         *      Applicable to :-
         *
         *      muliplication of matrices
         *
         */
        long ans = binaryExponent(13,5);
        System.out.println(ans);
    }
    public static long binaryExponent(long a,long b) {
         long res = 1;
        while (b > 0) {
            if ((b & 1)==0)
                res = res * a;

            a = a * a;
            b >>= 1;
        }
        return res;
    }

}
