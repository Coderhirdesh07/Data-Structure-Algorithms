package dsacourse851.livesession;

import java.util.ArrayList;
import java.util.Arrays;

public class LiveSessionCode {
    public static void main(String[] args) {

    }
    // find no of pairs such that product is perfect square
    public static int bruteforce_google_swe2_interview(int[] arr,int n){
        int count = 0;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                int product = arr[i] * arr[j];
                int sq_root  = (int) Math.sqrt((double) product);
                int res = sq_root*sq_root;
                if(res == product) count++;
            }
        }
        return count;
    }
    // Codeforces problem E round 1107
//    public static int codeforces_problem_e(){
//
//    }

    // find the largest maximum palidrome  subarray sum
    public static int leetcode_question_4(int[] arr,int n){
        int sum = 0;
        for(int i=0;i<n;i++){
            int curSum  = 0;
            for(int j=i;j<n;j++){
                curSum+=arr[j];
                boolean isPalidrome = helper(arr,n,i,j);
                if(isPalidrome){
                    sum = Math.max(curSum,sum);
                }
            }
        }
        return sum;
    }
    public static boolean helper(int[] arr,int n,int start,int end){
        int i = start;
        int j = end;
        while(i<=j){
            if(arr[i]!=arr[j]) return false;
            i++;
            j--;
        }
        return true;
    }

    public static void leetcode_contest_question_4_optimised(int[] arr,int n) {

        int[] pSum = prefixSum(arr);
        int[] palidromeArray = manacher(arr);

        int max = -1;
        int j = 1;
        int m = palidromeArray.length;
        for(int i=0;i<m;i++){
            int left = j-palidromeArray[i]+1;
            int right = j+palidromeArray[i]+1;
            int sum = 0;
            sum+=pSum[right];
            if(left>0){
                sum-=pSum[left-1];
            }

            max = Math.max(max,sum);
            j++;
        }
        System.out.println(max);
    }
    public static int[] manacher_odd(ArrayList<Integer> list){
        int n = list.size();
        int l = 0;
        int r = 1;
        int[] p  = new int[n+2];

        for(int i=1;i<=n;i++){
            if(i<=r){
                p[i] = Math.min(r-i,p[l+(r-i)]);
            }
            while(list.get(i-p[i]) == list.get(i+p[i])){
                p[i]++;
            }
            if(i+p[i] > r){
                l = i-p[i];
                r = i+p[i];
            }
        }
        return p;

    }
    public static int[] manacher(int[] arr){
        ArrayList<Integer> list = new ArrayList<>();
        int n = arr.length;
        list.add(0);
        for(int i=0;i<n;i++){
            list.add(arr[i]);
            list.add(0);
        }
        int[] result = manacher_odd(list);
        return result;
    }
    public static int[] prefixSum(int[] arr){
        int n = arr.length;
        int[] prefix = new int[n];
        prefix[0] = arr[0];
        for(int i=1;i<n;i++){
            prefix[i] = prefix[i-1] + arr[i];
        }
        return prefix;
    }

    public static int question_zomato_tree(ArrayList<ArrayList<Integer>> list,int n,int[] value){
         int[] count_of_nodes = new int[n+1];
         boolean[] vis = new boolean[n+1];
         int[] parent = new int[n+1];
         int[] result = new int[n+1];

         question_zomato_tree_dfs(1,n,list,value,count_of_nodes,vis,parent,result);
        int final_ans = 0;
        for(int x:result){
            final_ans+=x;
        }

         return final_ans;
    }

    public static void question_zomato_tree_dfs(int node,int n,ArrayList<ArrayList<Integer>> list,int[] value,int[] count_node,boolean[] vis,int[] parent,int[] result){
        vis[node] = true;

        for(int it:list.get(node)){
            if(!vis[it]){
                parent[it] = node;
                question_zomato_tree_dfs(it,n,list,value,count_node,vis,parent,result);
            }
        }

        for(int it:list.get(node)){
            if(parent[node]!=it){
                count_node[it]++;
            }
        }

        ArrayList<Integer> temp = new ArrayList<>();
        for(int it:list.get(node)){
            if(parent[node]==it){
                int val = n - count_node[node];
                temp.add(val);
            }
            else temp.add(count_node[it]);
        }
        int res = helper(temp);
        result[node] = res;

    }
    public static int helper(ArrayList<Integer> temp){
        int sum = 0;
        int sum_square = 0;
        int sum_cube = 0;
        for(int i=0;i<temp.size();i++){
            int product = temp.get(i)*temp.get(i);
            sum+=temp.get(i);
            sum_square+=product;
            sum_cube+=product*temp.get(i);
        }
        int numerator = sum_cube - 3 * sum * sum_square + 2*sum_cube;
        int ans = numerator/6;
        return ans;
    }

    public static int[] agoda_oa(int[][] queries,int k,int[][] mat){
        int q = queries.length;
        int[] result = range_update_queries_optimized(queries);

        int count = 0;
        int m = mat.length;
        int[] newArray = new int[q];
        for(int i =0;i<q;i++){
            if(result[i]>=k) {
                newArray[i] = 1;
            }
            else newArray[i]=0;
        }

        for(int i=1;i<q;i++){
            newArray[i] += newArray[i-1];
        }

        int[] ans = new int[q];
        // traversing mat
        for(int i=0;i<m;i++){
            int left = mat[i][0];
            int right = mat[i][1];
            if(left!=0){
                ans[i] = newArray[right] - newArray[left-1];
            }
            else ans[i] = newArray[right];
        }
        return ans;
    }
    public static int[] range_update_queries_optimized(int[][] queries){
        int q = queries.length;

        int[] arr = new int[q];
        Arrays.fill(arr,0);

        for(int i=0;i<q;i++){
            int left = queries[i][0];
            int right = queries[i][1];
            arr[left]+=1;
            arr[right+1]-=1;
        }
        int[] prefix = new int[q];
        prefix[0] = arr[0];

        for(int i=1;i<q;i++){
            prefix[i] = prefix[i-1] + arr[i];
        }
        return prefix;
    }
}
