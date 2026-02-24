import java.util.*;
import java.io.*;


public class BJ_13459 {
    static Scanner sc = new Scanner((System.in));
    static int n, m, ret = 0;
    static char[][] map;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    static int[] endPosition = new int[2];
    static int RY, RX, BY, BX;

    public static void main(String[] args) {
        n = sc.nextInt();
        m = sc.nextInt();
        map = new char[n][m];

        for (int i = 0; i < n; i++) {
            String s = sc.next();
            for (int j = 0; j < m; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'O') {
                    endPosition[0] = i;
                    endPosition[1] = j;
                }
                if (map[i][j] == 'B') {
                    BY = i;
                    BX = j;
                }
                if (map[i][j] == 'R') {
                    RY = i;
                    RX = j;
                }
            }
        }

        dfs(0, RY, RX, BY, BX);
        System.out.println(ret);
    }

    //왼쪽 오른쪽 위쪽 아래쪽 기울이기.
    // 빨간색 빠지면 성공 but 파란생 같이 빠지면 실패.
    // 더이상 안움직일때 까지.
    //10번 이하로 뺼수 있을 때.

    //빨강색이 들어가면 같은 방향으로 파랑색이 들어갈 수 잇는지 체크

    static void dfs(int cnt, int ry, int rx, int by, int bx) {

        //10번 이하일 경우
        if (cnt == 10) {
            return;
        }
//
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                System.out.print(map[i][j] + " ");
//            }
//            System.out.println();
//        }
//

        for (int i = 0; i < 4; i++) {

            //처음에 한번 옮기기
            int nby = by, nbx = bx;
            int nry = ry, nrx = rx;
            int rlen = 0, blen = 0;
            boolean red = false, blue = false;

            //각 방향마다 R,B를 끝까지 옮기기
            //빨강색 옮기기
            // 빨간 구슬 굴리기
            while (true) {
                // 1. 내가 다음에 디딜 곳을 미리 계산해본다.
                int nextY = nry + dy[i];
                int nextX = nrx + dx[i];

                // 2. 거기가 벽(#)이면? 안 가고 지금 위치에서 멈춘다(break).
                if (map[nextY][nextX] == '#') {
                    break;
                }

                // 3. 거기가 구멍(O)이면? 빠져야 하니 거기로 이동하고 멈춘다.
                if (map[nextY][nextX] == 'O') {
                    nry = nextY;
                    nrx = nextX;
                    red = true;
                    break;
                }

                // 4. 벽도 구멍도 아니면(빈 칸이면)? 안심하고 한 칸 전진!
                nry = nextY;
                nrx = nextX;
                rlen++;
            }
            //파랑색 옮기기
            // 빨간 구슬 굴리기
            while (true) {
                // 1. 내가 다음에 디딜 곳을 미리 계산해본다.
                int nextY = nby + dy[i];
                int nextX = nbx + dx[i];

                // 2. 거기가 벽(#)이면? 안 가고 지금 위치에서 멈춘다(break).
                if (map[nextY][nextX] == '#') {
                    break;
                }

                // 3. 거기가 구멍(O)이면? 빠져야 하니 거기로 이동하고 멈춘다.
                if (map[nextY][nextX] == 'O') {
                    nby = nextY;
                    nbx = nextX;
                    blue = true;
                    break;
                }

                // 4. 벽도 구멍도 아니면(빈 칸이면)? 안심하고 한 칸 전진!
                nby = nextY;
                nbx = nextX;
                blen++;
            }

            if(blue)continue;
                
            
            
            //둘다 들어가면 안됨
            if (red && blue) {
                continue;
            }
            //빨강색만 들어갔을 떄
            if (red && !blue) {
                ret = 1;
                return;
            }

            //만약 둘이 겹쳤을 경우
            if (nry == nby && nrx == nbx) {
                if (rlen > blen) {
                    nry -= dy[i];
                    nrx -= dx[i];
                } else {
                    nby -= dy[i];
                    nbx -= dx[i];
                }

            }


            dfs(cnt + 1, nry, nrx, nby, nbx);


        }
    }
}
