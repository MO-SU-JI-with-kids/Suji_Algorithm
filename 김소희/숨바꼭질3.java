import java.io.*;
import java.util.*;

public class Main {

    static final int MAX = 200001;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] dist = new int[MAX];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[N] = 0;

        Deque<Integer> dq = new ArrayDeque<>();
        dq.add(N);

        while (!dq.isEmpty()) {
            int x = dq.pollFirst();

            if (x == K) break;

            // 순간이동 (비용 0)
            int nx = x * 2;
            if (nx < MAX && dist[nx] > dist[x]) {
                dist[nx] = dist[x];
                dq.addFirst(nx);
            }

            // x - 1 (비용 1)
            nx = x - 1;
            if (nx >= 0 && dist[nx] > dist[x] + 1) {
                dist[nx] = dist[x] + 1;
                dq.addLast(nx);
            }

            // x + 1 (비용 1)
            nx = x + 1;
            if (nx < MAX && dist[nx] > dist[x] + 1) {
                dist[nx] = dist[x] + 1;
                dq.addLast(nx);
            }
        }

        System.out.println(dist[K]);
    }
}
