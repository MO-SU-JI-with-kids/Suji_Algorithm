import java.io.*;
import java.util.*;

public class Main {

    static int N, K, L;
    static int[][] Map2L;
    static int[][] Robot2L;
    static ArrayList<int[]> RobotL = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        Map2L = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                Map2L[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Robot2L = new int[N + 1][N + 1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(Robot2L[i], -1);
        }

        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            RobotL.add(new int[]{r, c});
            Robot2L[r][c] = k;
        }

        int[] dr = {-1, 0, 0, 1};
        int[] dc = {0, 1, -1, 0};

        for (int l = 0; l < L; l++) {

            // 1. 이동
            for (int k = 0; k < K; k++) {

                int r = RobotL.get(k)[0];
                int c = RobotL.get(k)[1];

                if (Map2L[r][c] > 0) continue;

                boolean[][] visitedL = new boolean[N + 1][N + 1];
                Queue<int[]> Q = new LinkedList<>();
                Q.add(new int[]{r, c});
                visitedL[r][c] = true;

                ArrayList<int[]> checkL = new ArrayList<>();
                boolean find = false;

                while (!Q.isEmpty() && !find) {

                    int length = Q.size();

                    for (int t = 0; t < length; t++) {

                        int[] cur = Q.poll();
                        int cr = cur[0];
                        int cc = cur[1];

                        for (int i = 0; i < 4; i++) {

                            int nr = cr + dr[i];
                            int nc = cc + dc[i];

                            if (0 < nr && nr <= N && 0 < nc && nc <= N
                                    && !visitedL[nr][nc]
                                    && Map2L[nr][nc] != -1
                                    && Robot2L[nr][nc] == -1) {

                                visitedL[nr][nc] = true;

                                if (Map2L[nr][nc] > 0) {
                                    checkL.add(new int[]{nr, nc});
                                    find = true;
                                } else {
                                    Q.add(new int[]{nr, nc});
                                }
                            }
                        }
                    }
                }

                checkL.sort((a, b) -> {
                    if (a[0] == b[0]) return a[1] - b[1];
                    return a[0] - b[0];
                });

                if (checkL.size() > 0) {

                    Robot2L[r][c] = -1;

                    int nr = checkL.get(0)[0];
                    int nc = checkL.get(0)[1];

                    Robot2L[nr][nc] = k;
                    RobotL.set(k, new int[]{nr, nc});

                } else {
                    Robot2L[r][c] = k;
                }
            }

            // 2. 청소
            int[] xr = {0, -1, 0, 1};
            int[] xc = {-1, 0, 1, 0};

            for (int k = 0; k < K; k++) {

                int r = RobotL.get(k)[0];
                int c = RobotL.get(k)[1];

                int index = 0;
                int maxClean = 0;

                for (int x = 0; x < 4; x++) {

                    int clean = Map2L[r][c];
                    if (Map2L[r][c] > 20) clean = 20;

                    for (int i = 0; i < 4; i++) {

                        int nr = r + dr[i];
                        int nc = c + dc[i];

                        if (dr[i] == xr[x] && dc[i] == xc[x]) continue;

                        if (0 < nr && nr <= N && 0 < nc && nc <= N) {

                            if (Map2L[nr][nc] > 20) clean += 20;
                            else if (Map2L[nr][nc] > 0) clean += Map2L[nr][nc];
                        }
                    }

                    if (maxClean < clean) {
                        maxClean = clean;
                        index = x;
                    }
                }

                // 실제 청소
                if (Map2L[r][c] > 20) Map2L[r][c] -= 20;
                else Map2L[r][c] = 0;

                for (int i = 0; i < 4; i++) {

                    int nr = r + dr[i];
                    int nc = c + dc[i];

                    if (dr[i] == xr[index] && dc[i] == xc[index]) continue;

                    if (0 < nr && nr <= N && 0 < nc && nc <= N) {

                        if (Map2L[nr][nc] > 20) Map2L[nr][nc] -= 20;
                        else if (Map2L[nr][nc] > 0) Map2L[nr][nc] = 0;
                    }
                }
            }

            // 3. 먼지 축적
            for (int r = 1; r <= N; r++) {
                for (int c = 1; c <= N; c++) {
                    if (Map2L[r][c] > 0) Map2L[r][c] += 5;
                }
            }

            // 4. 먼지 확산
            int[][] NewMap2L = new int[N + 1][N + 1];

            for (int r = 1; r <= N; r++) {
                for (int c = 1; c <= N; c++) {

                    if (Map2L[r][c] == 0) {

                        for (int i = 0; i < 4; i++) {

                            int nr = r + dr[i];
                            int nc = c + dc[i];

                            if (0 < nr && nr <= N && 0 < nc && nc <= N
                                    && Map2L[nr][nc] > 0) {

                                NewMap2L[r][c] += Map2L[nr][nc];
                            }
                        }

                        NewMap2L[r][c] /= 10;
                    }
                }
            }

            int Sum = 0;

            for (int r = 1; r <= N; r++) {
                for (int c = 1; c <= N; c++) {

                    if (Map2L[r][c] == 0) {
                        Map2L[r][c] = NewMap2L[r][c];
                    }

                    if (Map2L[r][c] > 0) {
                        Sum += Map2L[r][c];
                    }
                }
            }

            System.out.print(Sum);
            if (l < L - 1) System.out.println();
        }
    }
}
