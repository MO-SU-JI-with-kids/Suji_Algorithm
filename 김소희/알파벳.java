import java.io.*;
import java.util.*;

public class Main {

    static int R, C;
    static char[][] map;
    static boolean[][] visited;
    static boolean[] alpha = new boolean[26];
    static int maxCount = 1;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static void dfs(int r, int c, int count) {

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue;
            if (visited[nr][nc]) continue;

            int idx = map[nr][nc] - 'A';
            if (!alpha[idx]) {
                visited[nr][nc] = true;
                alpha[idx] = true;

                maxCount = Math.max(maxCount, count + 1);
                dfs(nr, nc, count + 1);

                visited[nr][nc] = false;
                alpha[idx] = false;
            }
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        // init
        visited[0][0] = true;
        alpha[map[0][0] - 'A'] = true;

        dfs(0, 0, 1);
        System.out.println(maxCount);
    }
}
