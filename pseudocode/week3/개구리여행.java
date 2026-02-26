import java.util.*;
import java.io.*;

public class 개구리여행 {
    static int N, Q;
    static char[][] Map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static class State implements Comparable<State> {
        int time, r, c, jump;

        State(int time, int r, int c, int jump) {
            this.time = time;
            this.r = r;
            this.c = c;
            this.jump = jump;
        }

        @Override
        public int compareTo(State o) {
            return Integer.compare(this.time, o.time);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        Map = new char[N+1][N+1];
        for (int i = 1; i <= N; i++) {
            String line = br.readLine();
            for (int j = 1; j <= N; j++) {
                Map[i][j] = line.charAt(j-1);
            }
        }

        Q = Integer.parseInt(br.readLine());
        int[][] TravelL = new int[Q][4];
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            TravelL[i][0] = Integer.parseInt(st.nextToken());
            TravelL[i][1] = Integer.parseInt(st.nextToken());
            TravelL[i][2] = Integer.parseInt(st.nextToken());
            TravelL[i][3] = Integer.parseInt(st.nextToken());
        }

        for (int q = 0; q < Q; q++) {
            int r1 = TravelL[q][0];
            int c1 = TravelL[q][1];
            int r2 = TravelL[q][2];
            int c2 = TravelL[q][3];

            int[][][] dist = new int[N+1][N+1][6];
            for (int i = 0; i <= N; i++) {
                for (int j = 0; j <= N; j++) {
                    Arrays.fill(dist[i][j], Integer.MAX_VALUE);
                }
            }
            dist[r1][c1][1] = 0;

            PriorityQueue<State> pq = new PriorityQueue<>();
            pq.add(new State(0, r1, c1, 1));

            while (!pq.isEmpty()) {
                State cur = pq.poll();
                int time = cur.time, r = cur.r, c = cur.c, jump = cur.jump;

                if (r == r2 && c == c2) break;
                if (time > dist[r][c][jump]) continue;

                // 이동
                for (int i = 0; i < 4; i++) {
                    int nr = r + dr[i]*jump;
                    int nc = c + dc[i]*jump;
                    if (nr <= 0 || nr > N || nc <= 0 || nc > N) continue;
                    if (Map[nr][nc] != '.') continue;

                    boolean can = true;
                    for (int step = 1; step <= jump; step++) {
                        int check_r = r + dr[i]*step;
                        int check_c = c + dc[i]*step;
                        if (Map[check_r][check_c] == '#') {
                            can = false;
                            break;
                        }
                    }

                    if (can) {
                        int new_time = time + 1;
                        if (new_time < dist[nr][nc][jump]) {
                            dist[nr][nc][jump] = new_time;
                            pq.add(new State(new_time, nr, nc, jump));
                        }
                    }
                }

                // 점프력 증가
                if (jump < 5) {
                    int newJump = jump + 1;
                    int new_time = time + newJump*newJump;
                    if (new_time < dist[r][c][newJump]) {
                        dist[r][c][newJump] = new_time;
                        pq.add(new State(new_time, r, c, newJump));
                    }
                }

                // 점프력 감소
                for (int newJump = 1; newJump < jump; newJump++) {
                    int new_time = time + 1;
                    if (new_time < dist[r][c][newJump]) {
                        dist[r][c][newJump] = new_time;
                        pq.add(new State(new_time, r, c, newJump));
                    }
                }
            }

            int ans = Integer.MAX_VALUE;
            for (int j = 1; j <= 5; j++) {
                ans = Math.min(ans, dist[r2][c2][j]);
            }
            System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
        }
    }
}
