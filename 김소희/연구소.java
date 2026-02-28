import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] map;
    static List<int[]> empties = new ArrayList<>();
    static List<int[]> virus = new ArrayList<>();
    static int answer = 0;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    // 바이러스 확산 BFS
    static int bfs() {
        boolean[][] visited = new boolean[N][M];
        Queue<int[]> q = new ArrayDeque<>();

        for (int[] v : virus) {
            q.offer(v);
            visited[v[0]][v[1]] = true;
        }

        int infected = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1];

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;

                if (!visited[nr][nc] && map[nr][nc] == 0) {
                    visited[nr][nc] = true;
                    infected++;
                    q.offer(new int[]{nr, nc});
                }
            }
        }
        return infected;
    }

    // 벽 3개 조합 DFS
    static void dfs(int idx, int wall) {

        if (wall == 3) {
            int infected = bfs();
            int safe = empties.size() - 3 - infected;
            answer = Math.max(answer, safe);
            return;
        }

        for (int i = idx; i < empties.size(); i++) {
            int r = empties.get(i)[0];
            int c = empties.get(i)[1];

            map[r][c] = 1;          // 벽 세우기
            dfs(i + 1, wall + 1);   // 다음 인덱스부터
            map[r][c] = 0;          // 복구
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) empties.add(new int[]{i, j});
                else if (map[i][j] == 2) virus.add(new int[]{i, j});
            }
        }

        dfs(0, 0);
        System.out.println(answer);
    }
}
