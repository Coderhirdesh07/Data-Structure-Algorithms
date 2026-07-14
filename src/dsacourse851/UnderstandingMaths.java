package dsacourse851;

public class UnderstandingMaths {
    public static void main(String[] args) {
        int ans = question_3(25,50);
        System.out.println(ans);
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

}
