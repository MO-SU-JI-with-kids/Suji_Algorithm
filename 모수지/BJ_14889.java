import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    // [모수지] week3 BJ_14889
    static Scanner sc = new Scanner(System.in);
    static int n, ans = Integer.MAX_VALUE;
    static int[][] info;
    public static void main(String[] args) {
        n = sc.nextInt();
        info = new int[n][n];
        visited = new int[n/2];
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                info[i][j] = sc.nextInt();
            }
        }

        // 11111 이렇게 n 개의 조합을 고려할 때가지
        for(int i=0; i<(1<<n); i++){

            // 1이 n/2개만 존재하고 나머지는 0인 경우만 계산함. = 즉 절반만큼의 팀원을 모두 골랐을 때
            if(Integer.bitCount(i) == n/2){

                // 중복 계산을 줄이기 위해
                // 0번 비트가 1인 경우만 계산한다.
                if((i&1) == 0) continue; // 0번 비트와의 &1 이 0 이라면 0번 비트 선택하지 않은 경우 = 무시
                calculate(i);
            }
        }
        System.out.println(ans);
    }

    public static void calculate(int mask){
        List<Integer> teamA = new ArrayList<>();
        List<Integer> teamB = new ArrayList<>();

        for(int j=0; j<n; j++){
            if((mask & (1<<j)) != 0){
                teamA.add(j); // j번째 비트가 1이면 A
            }
            else{
                teamB.add(j); // j 번째 비트가 0 이면 B
            }
        }

        int scoreA = 0, scoreB = 0;
        for(int i=0; i<n/2; i++){
            for(int j= i+1; j<n/2; j++){
                int p1 = teamA.get(i);
                int p2 = teamA.get(j);
                scoreA+= info[p1][p2] + info[p2][p1];

                int p3 = teamB.get(i);
                int p4 = teamB.get(j);
                scoreB += info[p3][p4] + info[p4][p3];
            }
            
        }

        ans = Math.min(ans, Math.abs(scoreA - scoreB));

        if(ans == 0){
            System.out.println(0);
            System.exit(0);
        }
    } 
}
