import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static int result = 0;
    static boolean[][] Queen2L;

    static void dfs(int r) {

        if (r == N) {
            result++;
            return;
        }

        for (int c = 0; c < N; c++) {

            boolean can = true;

            // 세로 검사
            for (int nr = r - 1; nr >= 0; nr--) {
                if (Queen2L[nr][c]) {
                    can = false;
                    break;
                }
            }

            // 대각선 검사
            if (can) {
                for (int i = 1; i <= r; i++) {
                    int nr = r - i;

                    int nc = c + i;
                    if (nc < N && Queen2L[nr][nc]) {
                        can = false;
                        break;
                    }

                    nc = c - i;
                    if (nc >= 0 && Queen2L[nr][nc]) {
                        can = false;
                        break;
                    }
                }
            }

            // 배치 가능
            if (can) {
                Queen2L[r][c] = true;
                dfs(r + 1);
                Queen2L[r][c] = false;
            }
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        Queen2L = new boolean[N][N];

        dfs(0);

        System.out.println(result);
    }
}
