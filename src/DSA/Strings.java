package DSA;

import java.util.Stack;

public class Strings {
    public static void main(String[] args) {
//
        String input = "HELLO";
       // find OccurrencesOfCharacter(input,'L');
        char ans = findMaxOccurences(input);
        System.out.println(ans);


    }
    // find the index for the Occurences of the character
    public static  void findOccurencesOfCharacter(String input,char x){
        int len = input.length();
        for(int i=0;i<len;i++){
            if(input.charAt(i)==x){
                System.out.print(i+"  ");
            }
        }

    }
    // find the character which occurs maximum times
    public static char findMaxOccurences(String input){
        int n = input.length();
        int count = 0;

        char max = '@';
        for(int i=0;i<n;i++){
            char f = input.charAt(i);
            int maxf = 1;
            for(int j=i;j<n;j++){
                if(input.charAt(j)==f)
                {
                   maxf++;
                }
            }
            if(maxf>count){
                count = maxf;
                max = input.charAt(i);
            }
        }
        return max;

    }


}
