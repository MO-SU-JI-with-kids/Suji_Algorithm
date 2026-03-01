import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] A;
    static int[] op;   // +, -, *, /
    static int minCost = Integer.MAX_VALUE;
    static int maxCost = Integer.MIN_VALUE;

    static void dfs(int index, int cost) {

        if (index == N) {
            minCost = Math.min(minCost, cost);
            maxCost = Math.max(maxCost, cost);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (op[i] > 0) {
                op[i]--;

                if (i == 0) {          // +
                    dfs(index + 1, cost + A[index]);
                } else if (i == 1) {   // -
                    dfs(index + 1, cost - A[index]);
                } else if (i == 2) {   // *
                    dfs(index + 1, cost * A[index]);
                } else if (i == 3) {   // /
                    dfs(index + 1, cost / A[index]); // Java는 0쪽으로 버림 → Python int()와 동일
                }

                op[i]++; // 복구
            }
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        op = new int[4];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            op[i] = Integer.parseInt(st.nextToken());
        }

        dfs(1, A[0]);

        System.out.println(maxCost);
        System.out.println(minCost);
    }
}
