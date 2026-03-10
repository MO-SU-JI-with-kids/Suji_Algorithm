import java.util.*;
import java.lang.*;
import java.io.*;

// [모수지] week6 BJ_2110
class Main {

    static Scanner sc = new Scanner(System.in);
    static int n,c, m, M;
    static List<Integer> info = new ArrayList<>();
    static List<Integer> selected = new ArrayList<>();
    
    
    public static void main(String[] args) {
        n = sc.nextInt();
        c = sc.nextInt();
        
        for(int i=0; i<n; i++){
            int num = sc.nextInt();
            info.add(num);
        }

        Collections.sort(info);

        int low = 1; // 최소 가능 거리
        int high = info.get(n-1) - info.get(0); // 최대 가능 거리
        int ans = 0;

        while(low <= high){
            int mid = (low+high)/2;

            if(canInstall(mid)){
                ans = mid;
                low = mid+1;
            }
            else{
                high = mid-1;
            }
        }

        System.out.println(ans);
        
    }

    
    public static boolean canInstall(int distance){
        int cur = 0, next = 1;
        int cnt = 1;
        while(next < info.size()){

            if(info.get(next) - info.get(cur) >= distance){
                cur = next;
                cnt++;
                if(cnt == c) return true;
            }
            next++;
        }

        return false; 
    }
}
