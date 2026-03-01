import java.io.*;
import java.util.*;

public class Main {

    static int K, C, R;
    static int[][] map;

    // 말 이동
    static int[] hr = {-2,-1,2,1,-2,-1,2,1};
    static int[] hc = {-1,-2,-1,-2,1,2,1,2};

    // 원숭이 이동
    static int[] mr = {-1,1,0,0};
    static int[] mc = {0,0,-1,1};

    static class State {
        int r, c, horse, dist;
        State(int r, int c, int horse, int dist) {
            this.r = r;
            this.c = c;
            this.horse = horse;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        K = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // visited[r][c][horse_used]
        boolean[][][] visited = new boolean[R][C][K+1];

        Queue<State> q = new ArrayDeque<>();
        q.add(new State(0, 0, 0, 0));
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            State cur = q.poll();

            if (cur.r == R-1 && cur.c == C-1) {
                System.out.println(cur.dist);
                return;
            }

            // 말 이동
            if (cur.horse < K) {
                for (int i = 0; i < 8; i++) {
                    int nr = cur.r + hr[i];
                    int nc = cur.c + hc[i];

                    if (nr < 0 || nc < 0 || nr >= R || nc >= C) continue;
                    if (map[nr][nc] == 1) continue;
                    if (visited[nr][nc][cur.horse + 1]) continue;

                    visited[nr][nc][cur.horse + 1] = true;
                    q.add(new State(nr, nc, cur.horse + 1, cur.dist + 1));
                }
            }

            // 원숭이 이동
            for (int i = 0; i < 4; i++) {
                int nr = cur.r + mr[i];
                int nc = cur.c + mc[i];

                if (nr < 0 || nc < 0 || nr >= R || nc >= C) continue;
                if (map[nr][nc] == 1) continue;
                if (visited[nr][nc][cur.horse]) continue;

                visited[nr][nc][cur.horse] = true;
                q.add(new State(nr, nc, cur.horse, cur.dist + 1));
            }
        }

        System.out.println(-1);
    }
}
