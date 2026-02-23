import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static ArrayList<int[]>[] cities;
    static int[] visited;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        cities = new ArrayList[N + 1];

        for (int i = 0; i <= N; i++) {
            cities[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            cities[s].add(new int[]{e, w});
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        visited = new int[N + 1];
        Arrays.fill(visited, Integer.MAX_VALUE);
        dijkstra(start, end);

        System.out.println(visited[end]);
    }

    private static void dijkstra(int start, int end) {
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparing(arr -> arr[1]));
        q.offer(new int[]{start, 0});
        visited[start] = 0;

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int s = now[0];
            int w = now[1];

            if(w > visited[s]) {
                continue;
            }
            
            for (int[] city : cities[s]) {
                int next = city[0];
                int nextW = city[1] + w;

                if (nextW < visited[next]) {
                    visited[next] = nextW;

                    if(next == end) {
                        continue;
                    }

                    q.offer(new int[]{next, nextW});
                }
            }
        }
    }
}
