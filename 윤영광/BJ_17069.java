import java.util.*;
import java.io.*;

public class Main {

    static Scanner sc = new Scanner((System.in));
    static int n;
    static long[][] map;
    static long[][][] dp;

    public static void BJ_17069(String[] args) throws IOException {

        n = sc.nextInt();
        map = new long[n + 1][n + 1];
        dp = new long[3][n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        //0 : 가로 , 1 : 대각선 , 2 : 세로
        dp[0][1][2] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 2; j <= n; j++) {

                //벽이 있으면 안됨
                if (map[i][j] == 1) continue;

                //첫 파이프는 넘기기
                if (i == 1 && j == 2) continue;

                if (map[i][j - 1] == 0) {
                    dp[0][i][j] = dp[0][i][j - 1] + dp[1][i][j - 1];
                }
                if (map[i - 1][j] == 0 && map[i - 1][j - 1] == 0 && map[i][j - 1] == 0) {
                    dp[1][i][j] = dp[0][i - 1][j - 1] + dp[1][i - 1][j - 1] + dp[2][i - 1][j - 1];
                }
                if (map[i-1][j] == 0) {
                    dp[2][i][j] = dp[1][i - 1][j] + dp[2][i - 1][j];
                }
            }
        }


        long ret = 0;
        for (int i = 0; i < 3; i++) {
            ret += dp[i][n][n];
        }
        System.out.println(ret);

    }

}
