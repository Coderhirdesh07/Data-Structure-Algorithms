package dsacourse851.livesession;

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
}
