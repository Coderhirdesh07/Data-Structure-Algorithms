package DSA.desiqna.twopointer;

import java.util.Scanner;
import java.util.Stack;

public class TwoPointer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        int k = sc.nextInt();

//        Valid string is a string where any pair of characters have diff<=k
        int n = input.length();
       Stack<String> s1 = new Stack<>();
       for(int i=0;i<n;i++){
           for(int j=i;j<n;j++){
               int diff = (input.charAt(i)-'A') - (input.charAt(j)-'A');
               if(diff<=k){
                   s1.push(input.substring(i,j+1));

               }
               else break;
           }
       }
       System.out.println(s1.peek());

    }
}
