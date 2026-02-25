import java.util.*;
import java.lang.*;
import java.io.*;

//[모수지] week4 BJ_14500

class State{
    int y, x, value;
    State(int y, int x, int value){
        this.y = y;
        this.x = x;
        this.value = value;
    }
}

// The main method must be in a class named "Main".
class Main {
    static Scanner sc = new Scanner(System.in);
    static int n, m, max_cnt;
    static int[][] info;
    static int[] dy1 = {0,0,0,0}, dy2 = {0,0,1,1};
    static int[] dx1 = {0,1,2,3}, dx2 = {0,1,0,1};
    static State[] value = new State[6];
    
    public static void main(String[] args) {
        n = sc.nextInt();
        m = sc.nextInt();
        info = new int[n][m];
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                info[i][j] = sc.nextInt();
            }
        }

        solve();
        System.out.println(max_cnt);
    }

    public static void solve(){
        int cur_max = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                int cur = 0;
                
                // 1번 도형(세로 버전)
                for(int d = 0; d<4; d++){
                    int ny = i+dy1[d];
                    int nx = j+dx1[d];
                    if(ny <0 || ny >=n || nx<0 || nx>=m){
                        cur = 0;
                        break;
                    }
                    cur+=info[ny][nx];
                }
                cur_max = Math.max(cur_max, cur);

                cur = 0;
                // 1번 도형(가로 버전)
                for(int d = 0; d<4; d++){
                    int ny = i+dx1[d];
                    int nx = j+dy1[d];
                    if(ny <0 || ny >=n || nx<0 || nx>=m){
                        cur = 0;
                        break;
                    }
                    cur+=info[ny][nx];
                }
                cur_max = Math.max(cur_max, cur);

                // 2번 도형
                cur = 0;
                for(int d = 0; d<4; d++){
                    int ny = i+dy2[d];
                    int nx = j+dx2[d];
                    if(ny <0 || ny >=n || nx<0 || nx>=m){
                        cur = 0;
                        break;
                    }
                    cur+=info[ny][nx];
                }
                cur_max = Math.max(cur_max, cur);

                // 3~5번 도형 : 2*3
                boolean iscan = true;
                int idx = 0;
                cur = 0;
                for(int y = i; y<i+2; y++){
                    for(int x = j; x<j+3; x++){
                        if(y <0 || y >=n || x<0 || x>=m){
                            iscan = false;
                            break;
                        }
                        value[idx] = new State(y,x,info[y][x]);
                        idx++; 
                    }
                }
                if(iscan){
                    cur_max = Math.max(cur_max, value[2].value + value[3].value+ value[4].value + value[5].value);
                     cur_max = Math.max(cur_max, value[0].value + value[3].value + value[4].value + value[5].value);
                     cur_max = Math.max(cur_max, value[0].value + value[1].value + value[2].value + value[5].value);
                     cur_max = Math.max(cur_max, value[0].value + value[1].value + value[2].value + value[3].value);
                     cur_max = Math.max(cur_max, value[1].value + value[3].value + value[4].value + value[5].value);
                     cur_max = Math.max(cur_max, value[0].value + value[1].value + value[2].value + value[4].value);
                     cur_max = Math.max(cur_max, value[1].value + value[2].value + value[3].value + value[4].value);
                     cur_max = Math.max(cur_max, value[0].value + value[1].value + value[4].value + value[5].value);
                }

                // 3~5번 도형 : 3*2
                iscan = true;
                idx = 0;
                cur = 0;
                for(int y = i; y<i+3; y++){
                    for(int x = j; x<j+2; x++){
                        if(y <0 || y >=n || x<0 || x>=m){
                            cur = 0;
                            iscan = false;
                            break;
                        }
                        value[idx] = new State(y,x,info[y][x]);
                        idx++;
                    }
                }

                if(iscan){
                    cur_max = Math.max(cur_max, value[1].value + value[3].value + value[4].value + value[5].value);
                    cur_max = Math.max(cur_max, value[0].value + value[1].value + value[3].value + value[5].value);
                    cur_max = Math.max(cur_max, value[0].value + value[2].value + value[4].value + value[5].value);
                    cur_max = Math.max(cur_max, value[0].value + value[1].value + value[2].value + value[4].value);
                    cur_max = Math.max(cur_max, value[1].value + value[2].value + value[3].value + value[5].value);
                    cur_max = Math.max(cur_max, value[0].value + value[2].value + value[4].value + value[3].value);
                    cur_max = Math.max(cur_max, value[1].value + value[2].value + value[3].value + value[4].value);
                    cur_max = Math.max(cur_max, value[0].value + value[2].value + value[3].value + value[5].value);
                }
            }
        }
        max_cnt = cur_max;
    }
}
