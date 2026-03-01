import java.io.*;
import java.util.*;

public class Main {

    static int M, N;
    static char[][] map;
    static int[][] dist;

    static class Node {
        int r, c, cost;
        Node(int r, int c, int cost) {
            this.r = r;
            this.c = c;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        dist = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        dist[0][0] = 0;

        PriorityQueue<Node> pq =
                new PriorityQueue<>(Comparator.comparingInt(a -> a.cost));
        pq.add(new Node(0, 0, 0));

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (dist[cur.r][cur.c] < cur.cost) continue;

            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];

                if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;

                int newCost = cur.cost;
                if (map[nr][nc] == '1') newCost++;

                if (dist[nr][nc] > newCost) {
                    dist[nr][nc] = newCost;
                    pq.add(new Node(nr, nc, newCost));
                }
            }
        }

        System.out.println(dist[N-1][M-1]);
    }
}
