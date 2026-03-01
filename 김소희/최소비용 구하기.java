import java.io.*;
import java.util.*;

public class Main {

    static class Edge {
        int to;
        int cost;

        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        List<Edge>[] graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[a].add(new Edge(b, cost));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int[] costL = new int[N + 1];
        Arrays.fill(costL, Integer.MAX_VALUE);
        costL[A] = 0;

        // (비용, 노드)
        PriorityQueue<int[]> pq =
                new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        pq.offer(new int[]{0, A});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int cost = cur[0];
            int n = cur[1];

            // 이미 더 좋은 경로가 있으면 skip
            if (costL[n] < cost) continue;

            // 현재 노드에서 다음 노드 탐색
            for (Edge e : graph[n]) {
                int next = e.to;
                int newCost = cost + e.cost;

                if (newCost < costL[next]) {
                    costL[next] = newCost;
                    pq.offer(new int[]{newCost, next});
                }
            }
        }

        System.out.println(costL[B]);
    }
}
