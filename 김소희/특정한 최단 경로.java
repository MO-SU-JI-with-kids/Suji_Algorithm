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
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        List<Edge>[] graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[a].add(new Edge(b, cost));
            graph[b].add(new Edge(a, cost));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        final int INF = Integer.MAX_VALUE;

        // ---------- case1 : 1 -> v1 -> v2 -> N ----------
        long cost1 = 0, cost2 = 0, cost3 = 0;

        // case1 - cost1 : 1 -> v1
        if (1 != v1) {
            int[] dist = new int[N + 1];
            Arrays.fill(dist, INF);
            dist[1] = 0;

            PriorityQueue<int[]> pq =
                    new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
            pq.offer(new int[]{0, 1});

            while (!pq.isEmpty()) {
                int[] cur = pq.poll();
                int cost = cur[0];
                int node = cur[1];

                if (dist[node] < cost) continue;

                dist[node] = cost;
                for (Edge e : graph[node]) {
                    int newCost = cost + e.cost;
                    if (dist[e.to] > newCost) {
                        dist[e.to] = newCost;
                        pq.offer(new int[]{newCost, e.to});
                    }
                }
            }
            cost1 = dist[v1];
        }

        // case1 - cost2 : v1 -> v2
        {
            int[] dist = new int[N + 1];
            Arrays.fill(dist, INF);
            dist[v1] = 0;

            PriorityQueue<int[]> pq =
                    new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
            pq.offer(new int[]{0, v1});

            while (!pq.isEmpty()) {
                int[] cur = pq.poll();
                int cost = cur[0];
                int node = cur[1];

                if (dist[node] < cost) continue;

                dist[node] = cost;
                for (Edge e : graph[node]) {
                    int newCost = cost + e.cost;
                    if (dist[e.to] > newCost) {
                        dist[e.to] = newCost;
                        pq.offer(new int[]{newCost, e.to});
                    }
                }
            }
            cost2 = dist[v2];
        }

        // case1 - cost3 : v2 -> N
        if (v2 != N) {
            int[] dist = new int[N + 1];
            Arrays.fill(dist, INF);
            dist[v2] = 0;

            PriorityQueue<int[]> pq =
                    new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
            pq.offer(new int[]{0, v2});

            while (!pq.isEmpty()) {
                int[] cur = pq.poll();
                int cost = cur[0];
                int node = cur[1];

                if (dist[node] < cost) continue;

                dist[node] = cost;
                for (Edge e : graph[node]) {
                    int newCost = cost + e.cost;
                    if (dist[e.to] > newCost) {
                        dist[e.to] = newCost;
                        pq.offer(new int[]{newCost, e.to});
                    }
                }
            }
            cost3 = dist[N];
        }

        long case1 = cost1 + cost2 + cost3;

        // ---------- case2 : 1 -> v2 -> v1 -> N ----------
        cost1 = cost2 = cost3 = 0;

        // case2 - cost1 : 1 -> v2
        {
            int[] dist = new int[N + 1];
            Arrays.fill(dist, INF);
            dist[1] = 0;

            PriorityQueue<int[]> pq =
                    new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
            pq.offer(new int[]{0, 1});

            while (!pq.isEmpty()) {
                int[] cur = pq.poll();
                int cost = cur[0];
                int node = cur[1];

                if (dist[node] < cost) continue;

                dist[node] = cost;
                for (Edge e : graph[node]) {
                    int newCost = cost + e.cost;
                    if (dist[e.to] > newCost) {
                        dist[e.to] = newCost;
                        pq.offer(new int[]{newCost, e.to});
                    }
                }
            }
            cost1 = dist[v2];
        }

        // case2 - cost2 : v2 -> v1
        {
            int[] dist = new int[N + 1];
            Arrays.fill(dist, INF);
            dist[v2] = 0;

            PriorityQueue<int[]> pq =
                    new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
            pq.offer(new int[]{0, v2});

            while (!pq.isEmpty()) {
                int[] cur = pq.poll();
                int cost = cur[0];
                int node = cur[1];

                if (dist[node] < cost) continue;

                dist[node] = cost;
                for (Edge e : graph[node]) {
                    int newCost = cost + e.cost;
                    if (dist[e.to] > newCost) {
                        dist[e.to] = newCost;
                        pq.offer(new int[]{newCost, e.to});
                    }
                }
            }
            cost2 = dist[v1];
        }

        // case2 - cost3 : v1 -> N
        {
            int[] dist = new int[N + 1];
            Arrays.fill(dist, INF);
            dist[v1] = 0;

            PriorityQueue<int[]> pq =
                    new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
            pq.offer(new int[]{0, v1});

            while (!pq.isEmpty()) {
                int[] cur = pq.poll();
                int cost = cur[0];
                int node = cur[1];

                if (dist[node] < cost) continue;

                dist[node] = cost;
                for (Edge e : graph[node]) {
                    int newCost = cost + e.cost;
                    if (dist[e.to] > newCost) {
                        dist[e.to] = newCost;
                        pq.offer(new int[]{newCost, e.to});
                    }
                }
            }
            cost3 = dist[N];
        }

        long case2 = cost1 + cost2 + cost3;

        long result = Math.min(case1, case2);
        if (result >= INF) {
            System.out.println(-1);
        } else {
            System.out.println(result);
        }
    }
}
