import java.util.*;
import java.io.*;

public class BJ_16926 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[][] board;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++)
                board[i][j] = Integer.parseInt(st.nextToken());
        }

        // 각 레이어에 대해
        for (int i = 0; i < Math.min(n, m) / 2; i++) {
            int startX = i;
            int startY = i;
            int currentN = (n - i * 2);
            int currentM = (m - i * 2);
            int mod = (currentN + currentM) * 2 - 4;
            int rMod = r % mod;

            // 돌리기 수행해야함. rMod만큼 1칸씩 돌려보기
            for (int a = 0; a < rMod; a++) {
                int temp = board[startX][startY]; // 첫 시작점 일단 저장해두고, 나중에 한칸 아래로 내려서 저장하면 됨.

                for (int b = startY + 1; b < currentM + startY; b++) // 위에 있는걸 하나씩 왼쪽으로 옮
                    board[startX][b - 1] = board[startX][b];

                for (int b = startX + 1; b < startX + currentN ; b++) // 오른쪽
                    board[b - 1][startY + currentM - 1] = board[b][startY + currentM - 1];

                for (int b = currentM + startY - 2; b >= startY; b--) // 위
                    board[startX + currentN - 1][b + 1] = board[startX + currentN - 1][b];

                for (int b = currentN + startX - 2; b >= startX; b--) // 왼쪽
                    board[b+1][startY] = board[b][startY];

                board[startX + 1][startY] = temp;
            }

        }
        for (int[] row : board) {
            for(int i : row) {
                System.out.print(i + " ");
            }
            System.out.println();
        }

    }
}
