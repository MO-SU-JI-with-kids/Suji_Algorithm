import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int r, c, cost;

        Node(int r, int c, int cost) {
            this.r = r;
            this.c = c;
            this.cost = cost;
        }
    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int count = 0;

        while (true) {
            int N = Integer.parseInt(br.readLine());
            if (N == 0) break;
            count++;

            int[][] cave = new int[N][N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    cave[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int[][] dist = new int[N][N];
            for (int i = 0; i < N; i++) {
                Arrays.fill(dist[i], Integer.MAX_VALUE);
            }

            dist[0][0] = cave[0][0];

            PriorityQueue<Node> pq =
                    new PriorityQueue<>(Comparator.comparingInt(n -> n.cost));
            pq.offer(new Node(0, 0, dist[0][0]));

            while (!pq.isEmpty()) {
                Node cur = pq.poll();
                int r = cur.r;
                int c = cur.c;
                int cost = cur.cost;

                if (dist[r][c] < cost) continue;

                for (int i = 0; i < 4; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];

                    if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;

                    int newCost = dist[r][c] + cave[nr][nc];
                    if (newCost < dist[nr][nc]) {
                        dist[nr][nc] = newCost;
                        pq.offer(new Node(nr, nc, newCost));
                    }
                }
            }

            sb.append("Problem ")
              .append(count)
              .append(": ")
              .append(dist[N - 1][N - 1])
              .append('\n');
        }

        System.out.print(sb);
    }
}
