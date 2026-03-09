import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".

// [모수지] week6 BJ_2470
class Main {

    static Scanner sc = new Scanner(System.in);
    static int n, num1, num2, ans;
    static int[] info;
    
    public static void main(String[] args) {
        n = sc.nextInt();
        info = new int[n];
        for(int i=0; i<n; i++){
            info[i] = sc.nextInt();
        }

        Arrays.sort(info);
        int left = 0, right = n-1;
        num1 = info[left]; num2 = info[right];
        ans = Math.abs(num1 + num2);
        while(left < right){

            int cur = info[right] + info[left];
            if(cur == 0){
                System.out.println(info[left] + " " + info[right]);
                System.exit(0);
            }

            if(Math.abs(cur) < ans){
                ans = Math.abs(cur);
                num1 = info[left];
                num2 = info[right];
            }

            if(cur < 0){
                left++;
            }
            else{
                right--;
            }
  
        }

        System.out.println(num1 +" " + num2);
        
    }
}
