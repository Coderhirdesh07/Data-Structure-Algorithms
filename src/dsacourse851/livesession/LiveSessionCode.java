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
}
