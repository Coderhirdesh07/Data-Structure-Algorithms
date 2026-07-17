package dsacourse851;

import java.util.ArrayList;
import java.util.HashMap;

public class UnderstandingMaths {
    public static void main(String[] args) {
        int ans = question_3(25,50);
        int[] res = {3,4,3};
        int val = question_8(res,res.length);
        System.out.println(val);
    }
    public static int factors(int n){
        int value = (int) Math.sqrt(n);
        int count = 0;
        for(int i=1;i<=value;i++){
            if(i*i == n) count+=1;
            else if(n%i==0){
                count+=2;
            }
        }
        return count;
    }
    public static int question_3(int x,int y){
        int length = Math.max(x,y);
        int[] prime = new int[length+1];
        prime[1] = 1;
        prime[2] = 2;
        prime[3] = 2;

        for(int i=4;i<=length;i++){
            int res = factors(i);
            prime[i] = res;
        }
        int firstVal = -1;
        int temp = x;
        while(temp>=1){
            if(prime[temp]==2){
                firstVal = temp;
                break;
            }
            temp--;
        }
        int secondVal = -1;
        int temp2 = y;
        while(temp2>=1){
            if(prime[temp2]==2){
                secondVal = temp2;
                break;
            }
            temp2--;
        }
        return Math.abs(secondVal - firstVal);

    }

    public static int question_4_easy(int[] arr,int n){
        int max = -1;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                int res = gcd(arr[i],arr[j]);
                max = Math.max(max,res);
            }
        }
        return max;
    }
    public static int gcd(int x,int y){
        if(y==0) return x;
        return gcd(y,x%y);
    }
    public static int question_4_easy_optimised(int[] arr,int n){
        int max = -1;
        for(int x:arr){
            max = Math.max(max,x);
        }
        int limit = max;
        HashMap<Integer,Integer> m1 = new HashMap<>();
        for(int i=0;i<n;i++){
            int x = arr[i];
            for(int j=1;j<x;i++){
                if(x%i==0){
                    m1.put(i,m1.getOrDefault(i,0)+1);
                }
            }
        }
        int ans = -1;
        for(int it:m1.keySet()){
            if(m1.containsKey(it)){
                if(m1.get(it)>=2){
                    ans = Math.max(ans,m1.get(it));
                }
            }
        }
        return ans;

    }

    public static int question_4_actual_problem(int[] arr,int n){
        int max = -1;
        for(int x:arr){
            max = Math.max(max,x);
        }
        int limit = max;
        HashMap<Integer,Integer> m1 = new HashMap<>();
        for(int i=0;i<n;i++){
            int x = arr[i];
            for(int j=1;j<limit;i++){
                if( i<limit && x%i==0){
                    m1.put(i,m1.getOrDefault(i,0)+1);
                }
            }
        }
        int ans = -1;
        for(int it:m1.keySet()){
            if(m1.containsKey(it)){
                if(m1.get(it)>=3){
                    ans = Math.max(ans,m1.get(it));
                }
            }
        }
        return ans;

    }
    public static int question_6(int[] energy,int[] count,int[] cost,int s){
        int min = Integer.MAX_VALUE;
       for(int i=0;i<=count[0];i++){
           for(int j=0;j<=count[1];j++){
               for(int k=0;k<=count[2];k++){
                   int total_energy = i*energy[0] + j*energy[1] + k*energy[2];
                   int total_cost = i*cost[0] + j*cost[1] + k * cost[2];
                   if(total_energy==s){
                       min = Math.min(min,total_cost);
                   }
               }
           }
       }
       return min;

    }
    public static int question_6_optimised(int[] energy,int[] count,int[] cost,int s){
        int min = Integer.MAX_VALUE;
        for(int i=0;i<=count[0];i++){
            for(int j=0;j<=count[1];j++){
                int k = (s - (energy[0]*i + energy[1]*j))/energy[2];
                if(k>=0 && k<=count[2]){
                    if(i*cost[0] + j*cost[1] + k*cost[2] == s){
                        int total_cost = i*cost[0] + j*cost[1] + k*cost[2];
                        min = Math.min(total_cost,min);
                    }
                }
            }
        }
        return min;

    }

    public static int question_8(int[] arr,int n){
        int count = 0;

        int max = arr[n-1];
        int ind = n-2;
        while(ind>=0){
            ind--;
        }


        return count;
    }


}
