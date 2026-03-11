import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
// [모수지] week6 BJ_1477
class Main {

    static Scanner sc = new Scanner(System.in);
    static int n, m, l, ans = 10000;
    static List<Integer> info = new ArrayList<>();
    
    public static void main(String[] args) {
        n = sc.nextInt();
        m = sc.nextInt();
        l = sc.nextInt();
        for(int i=0; i<n; i++){
            int num = sc.nextInt();
            info.add(num);
        }

        info.add(l);
        info.sort((a,b) -> a-b); // 오름차순 정렬

        int left = 1, right = l;
        while(left <= right){

            int mid = (left+right)/2;

            if(check(mid)){
                ans = Math.min(ans,mid);
                right = mid-1;
            }
            else{
                left = mid + 1;
            }  
        }

        System.out.println(ans);
    }

    public static boolean check(int mid){
        int cnt = 0;

        int left = 0;
        for(int i=0; i<info.size(); i++){
            int right = info.get(i);
            int distance = right - left;
            
            if(distance % mid == 0) cnt += (distance/mid)-1;
            else cnt+= distance/mid;

            left = right;
        }
        
        if(cnt <= m) return true;
        else return false;  
    }  
}
