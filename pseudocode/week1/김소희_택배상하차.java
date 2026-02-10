import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = input[0];
        int M = input[1];

        int[][] Map = new int[N+1][N+1];
        int[] height = new int[N+1];
        int[][] BoxList = new int[101][4];

        for (int m = 1; m < M+1; m++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int k= input[0];
            int h= input[1];
            int w= input[2];
            int c= input[3];

            

            int r = height[c];
            for (int col = c; col < c+w; col++) {
                r = Math.max(r, height[col]);
            }

            // row, col, height, width
            BoxList[k] = new int[] {N-r-h+1, c, h, w};
            
            // Map 배열에 입력
            for (int row = N-r; row > N-r-h; row--) {
                for (int col = c; col < c+w; col++) {
                    Map[row][col] = k;
                }
            }    

            for (int col = c; col < c+w; col++) { height[col] += h; }
        
            
            // for (int row = 1; row <= N; row++) {
            //     for (int col = 1; col <= N; col++) {
            //         System.out.print(Map[row][col] + " ");
            //     }
            //     System.out.println("");
            // }
            // System.out.println("");
        }

        // 왼쪽 하차 찾기
        int[] toTakeL = new int[N+1];
        int count = 0;
        int minNumBox = 100;
        for (int col = 1; col <= N; col++) {
            for (int row = 1; row <= N; row++) {
                if (toTakeL[row] == 0 && Map[row][col] != 0) {
                    toTakeL[row] = Map[row][col];
                    count++;
                    minNumBox = Math.min(minNumBox, Map[row][col]);
                }
                if (count >= N) break;
            }
            if (count >= N) break;
        } 

        // 가장 낮은 숫자의 박스 제거
        int MinR = BoxList[minNumBox][0];
        int MinC = BoxList[minNumBox][1];
        int MinH = BoxList[minNumBox][2];
        int MinW = BoxList[minNumBox][3];

        for (int row = MinR; row < MinR+MinH; row++) {
            for (int col = MinC; col < MinC+MinW; col++) {
                Map[row][col] = 0;
            }
        }
        BoxList[minNumBox] = new int[] {-1, -1, -1, -1};

        // 중력 적용 (박스 크기 적용)
        int[] toTakeD = new int[101];
        List<Integer> toTakeDL = new ArrayList<>();

        for (int row = MinR+1; row >=0; row--) {
            for (int col = 0; col < N; col++) {
                if (Map[row][col] != 0 && toTakeD[Map[row][col]] == 0) {
                    toTakeD[Map[row][col]] = 1;
                    toTakeDL.add(Map[row][col]);
                }
            }
        }

        for (int i = 0; t < toTakeDL.size(); i++) {
            int R = BoxList[toTakeDL[i]][0];
            int C = BoxList[toTakeDL[i]][1];
            int H = BoxList[toTakeDL[i]][2];
            int W = BoxList[toTakeDL[i]][3];
            
            int dig = 0;
            boolean canDig;
            for (int r = R+H-1; r < N+1; r++) {
                canDig = true;
                for (int c = C; c < C+W; c++) {
                    if (Map[r][c] != 0) canDig = false;
                }
                if (canDig) dig++;
                else break;
            }

            for (int r = R; r < R+dig; r++) {
                for (int c = C; c < C+W; c++) {
                    Map[r][c] = 0;
                }
            }
            for (int r = R+dig; r < R+H+dig; r++) {
                for (int c = C; c < C+W; c++) {
                    Map[r][c] = toTakeDL[i];
                }
            }
        }

        // 오른쪽 하차 찾기
        int[] toTakeR = new int[N+1];
        count = 0;
        minNumBox = 100;
        for (int col = N; col > 0; col--) {
            for (int row = 1; row <= N; row--) {
                if (toTakeR[row] == 0 && Map[row][col] != 0) {
                    toTakeR[row] = Map[row][col];
                    count++;
                    minNumBox = Math.min(minNumBox, Map[row][col]);
                }
                if (count >= N) break;
            }
            if (count >= N) break;
        } 

        // 가장 낮은 숫자의 박스 제거
        MinR = BoxList[minNumBox][0];
        MinC = BoxList[minNumBox][1];
        MinH = BoxList[minNumBox][2];
        MinW = BoxList[minNumBox][3];

        for (int row = MinR; row < MinR+MinH; row++) {
            for (int col = MinC; col < MinC+MinW; col++) {
                Map[row][col] = 0;
            }
        }
        BoxList[minNumBox] = new int[] {-1, -1, -1, -1};

        // 중력 적용 (박스 크기 적용)
        toTakeD = new int[101];
        toTakeDL = new ArrayList<>();

        for (int row = MinR+1; row >=0; row--) {
            for (int col = 0; col < N; col++) {
                if (Map[row][col] != 0 && toTakeD[Map[row][col]] == 0) {
                    toTakeD[Map[row][col]] = 1;
                    toTakeDL.add(Map[row][col]);
                }
            }
        }

        for (int i = 0; t < toTakeDL.size(); i++) {
            int R = BoxList[toTakeDL[i]][0];
            int C = BoxList[toTakeDL[i]][1];
            int H = BoxList[toTakeDL[i]][2];
            int W = BoxList[toTakeDL[i]][3];
            
            int dig = 0;
            boolean canDig;
            for (int r = R+H-1; r < N+1; r++) {
                canDig = true;
                for (int c = C; c < C+W; c++) {
                    if (Map[r][c] != 0) canDig = false;
                }
                if (canDig) dig++;
                else break;
            }

            for (int r = R; r < R+dig; r++) {
                for (int c = C; c < C+W; c++) {
                    Map[r][c] = 0;
                }
            }
            for (int r = R+dig; r < R+H+dig; r++) {
                for (int c = C; c < C+W; c++) {
                    Map[r][c] = toTakeDL[i];
                }
            }
        }
        
 
    }
}
