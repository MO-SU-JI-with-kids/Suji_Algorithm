import java.util.*;
import java.io.*; // BufferedReader 사용을 위한 import

class State {
    int node, cnt;

    State(int node, int cnt) {
        this.node = node;
        this.cnt = cnt;
    }
}

public class Main {
    // Scanner 대신 BufferedReader 사용
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    // long 사용
    static long ans; 
    static List<List<Integer>> list = new ArrayList<>();
    static int[] leaf = new int[500001];
    static int[] visited = new int[500001];

    public static void BFS(int idx) {
        Queue<State> q = new ArrayDeque<>();
        q.add(new State(idx, 0));
        visited[idx] = 1; // 시작 노드 방문 처리

        while (!q.isEmpty()) {
            State cur = q.poll();
            
            // 리프 노드 판별 (연결된 노드가 1개이고 루트가 아님)
            if (!list.get(cur.node).isEmpty() && list.get(cur.node).size() == 1 && cur.node != 1) {
                if (leaf[cur.node] == 0) {
                    leaf[cur.node] = 1;
                    ans += cur.cnt;
                }
            } else {
                for (Integer next : list.get(cur.node)) {
                    if (visited[next] == 1) continue;

                    q.add(new State(next, cur.cnt + 1));
                    visited[next] = 1;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // 첫 번째 줄 읽기 (N)
        String line = br.readLine();
        if (line == null) return;
        int n = Integer.parseInt(line);

        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        // 간선 정보 읽기
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            list.get(from).add(to);
            list.get(to).add(from);
        }

        BFS(1); // 루트 노드에서 단말 노드 찾기 시작

        if (ans % 2 == 0) System.out.println("No");
        else System.out.println("Yes");
    }
}
