import java.io.*;
import java.util.*;

public class BJ_15684 {
    static Scanner sc = new Scanner(System.in);
    static int n, m, h, a, b, ret = Integer.MAX_VALUE;
    static int[][] ladder;

    public static void main(String[] args) {
        n = sc.nextInt();
        m = sc.nextInt();
        h = sc.nextInt();

        ladder = new int[h + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            a = sc.nextInt();
            b = sc.nextInt();
            ladder[a][b] = 1;
        }

        dfs(1, 1,0);

        ret = ret == Integer.MAX_VALUE ? -1 : ret;

        System.out.println(ret);
    }

    static void dfs(int y,int x, int cnt) {

        if (cnt >= ret) return;
        if (cnt > 3) return;


        // 현재 사다리에서 조건을 맞출 수 있는지 체크
        boolean flag = true;
        for (int start = 1; start <= n; start++) {
            if (!move(start)) {
                flag = false;
                break;
            }
        }
        //i번째에서 시작한것이 i번째 도달했으면.
        if (flag) {
            ret = Math.min(ret, cnt);
            return;
        }
        if (cnt == 3) return;

        //행을 1~h까지 돌면서
        //행마다 사다리를 연결해야함.
        for (int i = y; i <= h; i++) {

            int startx = i==y ? x : 1;

            //j,j+1 표시를 할것이므로 n-1까지
            for (int j = startx; j < n; j++) {
                //j ~ j+1 사다리가 이미 연결되어 있으면 pass
                if (ladder[i][j] == 0 && ladder[i][j - 1] == 0 && ladder[i][j + 1] == 0) {
                    //연결 안되있으면 연결하기
                    ladder[i][j] = 1;

                    //다음 행으로 이동하기
                    dfs(i, j+ 2,cnt + 1);

                    //사다리 지우기
                    ladder[i][j] = 0;
                }
            }
        }
    }

    static boolean move(int start) {

        int x = start;

        for (int y = 1; y <= h; y++) {

            //오른쪽에 연결되어있으면
            if (x + 1 <= n && ladder[y][x] == 1)
                x = x + 1;
                //왼쪽에 연결되어있으면
            else if (x - 1 >= 1 && ladder[y][x] == 0 && ladder[y][x - 1] == 1) {
                x = x - 1;
            }
            //연결 안되있으면`
            else {
                continue;
            }
        }
        return start == x;
    }
}
