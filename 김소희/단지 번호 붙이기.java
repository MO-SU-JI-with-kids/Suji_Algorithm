import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static char[][] map;
    static boolean[][] visited;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static int count; // 현재 단지 집 개수

    static void dfs(int r, int c) {
        visited[r][c] = true;

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
                if (!visited[nr][nc] && map[nr][nc] == '1') {
                    dfs(nr, nc);
                    count++;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new char[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int village = 0;
        ArrayList<Integer> countList = new ArrayList<>();

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (!visited[r][c] && map[r][c] == '1') {
                    count = 1;
                    dfs(r, c);
                    village++;
                    countList.add(count);
                }
            }
        }

        Collections.sort(countList);

        StringBuilder sb = new StringBuilder();
        sb.append(village).append('\n');
        for (int x : countList) {
            sb.append(x).append('\n');
        }

        System.out.print(sb.toString());
    }
}
