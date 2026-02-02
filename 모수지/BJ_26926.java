import java.util.*;

public class Main {
    static int n, m, r;
    static int[][] a;
    static int[][] ans;

    public static void turn() {
        int layers = Math.min(n, m) / 2;

        for (int i = 0; i < layers; i++) {
            //  시작점은 (i, i)

            // 1. 윗 줄을 왼쪽으로 이동
            for (int x = m - i - 1; x >= i + 1; x--) {
                ans[i][x - 1] = a[i][x];
            }

            // 2. 왼쪽 줄을 아래로 이동
            for (int y = i; y < n - i - 1; y++) {
                ans[y + 1][i] = a[y][i];
            }

            // 3. 맨 아래쪽 줄을 오른쪽으로 이동
            for (int x = i; x < m - i - 1; x++) {
                ans[n - i - 1][x + 1] = a[n - i - 1][x];
            }

            // 4. 오른쪽 줄을 위로 이동
            for (int y = n - i - 1; y >= i + 1; y--) {
                ans[y - 1][m - i - 1] = a[y][m - i - 1];
            }
        }

        // 결과값을 원본 배열로 복사
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                a[i][j] = ans[i][j];
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        r = sc.nextInt();

        a = new int[n][m];
        ans = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                a[i][j] = sc.nextInt();
            }
        }

        // R번 회전 수행
        for (int i = 0; i < r; i++) {
            turn();
        }

        StringBuilder sb = new StringBuilder(); // ... 이게 모람
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(a[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
        
        sc.close();
    }
}
