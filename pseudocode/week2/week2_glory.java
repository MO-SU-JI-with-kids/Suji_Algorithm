import java.util.*;
import java.io.*;

class Robot{
    int y, x;

    public Robot(){
    }
    public Robot( int y, int x){
        this.y = y;
        this.x = x;
    }
}

class pair{
    int y, x, d;
    public pair(){}
    public pair(int y, int x, int d){
        this.y = y;
        this.x = x;
        this.d = d;
    }
}

public class Main {

    //격자크기, 청소기 개수, 테스트 횟수
    static int n, k, l;
    static Scanner sc = new Scanner(System.in);
    static int[][] arr;
    static int[] dy = {-1, 0, 0, 1};  // 상, 좌, 우, 하  / 우선순위
    static int[] dx = {0, -1, 1, 0};

    static Robot[] list;
  
    static void input(){
        n = sc.nextInt();
        k = sc.nextInt();
        l = sc.nextInt();

        arr = new int[n+1][n+1];
        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                arr[i][j] = sc.nextInt();
            }
        }

        list = new Robot[k];

        for(int i=0; i < k; i++){
            int y = sc.nextInt(), x = sc.nextInt();
            list[i] = new Robot( y, x);
        }
    }

    public static void main(String[] args) {
        input();

        //L번 이 과정을 반복
        for(int i = 0 ; i < l; i++){
            move();
            clean();
            spread();
            calc();
        }
    }

    //청소기를 제일 가까운 격자로 이동
    static void move(){
        //로봇마다 이동하기
        for(Robot robot : list)
            bfs(robot);
           
    }

    static void bfs(Robot robot){
        // 내 자리에 이미 먼지가 있으면 안감.
        if(arr[robot.y][robot.x] > 0) return;

        //pair을 사용하여 로봇 옮기기
        PriorityQueue<pair> pq = new PriorityQueue<>((p1, p2) -> {
            if (p1.d != p2.d) return Integer.compare(p1.d, p2.d); // 거리 순
            if (p1.y != p2.y) return Integer.compare(p1.y, p2.y); // 행
            return Integer.compare(p1.x, p2.x);                  //열
        });
        
        boolean[][] visit = new boolean[n+1][n+1];

        pq.add(new pair(robot.y, robot.x, 0));
        visit[robot.y][robot.x] = true;

        while(pq.size() > 0){
            pair p = pq.poll();

            int y = p.y, x = p.x;

            // 만약 먼지를 만났을 때, 그리고 그 자리에 다른 로봇이 없을 때 종료
            if(arr[y][x] > 0 && !checkRobot(y, x, robot)){
                robot.y = y;
                robot.x = x;
                return;
            }

            for(int i=0; i<4; i++){
                int ny = y + dy[i], nx = x + dx[i];

                //못가는 조건
                if(ny <=0 || nx <=0 || ny > n || nx > n) continue;
                if(visit[ny][nx]) continue;
                if(arr[ny][nx] == -1) continue;
                if(checkRobot(ny, nx, robot)) continue; 

                //빈 공간이거나 먼지일 때         
                pq.add(new pair(ny, nx, p.d+1));
                visit[ny][nx] = true;
                                
            }    
        }
    }

    //해당 위치에 로봇이 있는지 확인
    static boolean checkRobot(int y, int x, Robot current){
        for(Robot rb : list){
            if(rb == current) continue;
            if(y == rb.y && x == rb.x){
                return true;
            }
        }
        return false;
    }

    static void clean(){
        
        int[][] t_dy = {
            {-1, 0, 1}, // 우 (dir: 0)
            {0, 1, 0},  // 하 (dir: 1)
            {-1, 1, 0}, // 좌 (dir: 2)
            {-1, 0, 0}  // 상 (dir: 3)
        };
        int[][] t_dx = {
            {0, 1, 0},  // 우
            {1, 0, -1}, // 하
            {0, 0, -1}, // 좌
            {0, 1, -1}  // 상
        };

        //로봇마다 이동
        for(Robot robot : list){
            int y = robot.y, x = robot.x;
            int[] sumArr = new int[4];
            
            // 4방향에 대해 한 번에 먼지 합산 계산
            for(int dir = 0; dir < 4; dir++){
                sumArr[dir] = Math.min(arr[y][x], 20); 
                
                for(int i = 0; i < 3; i++){
                    int ny = y + t_dy[dir][i];
                    int nx = x + t_dx[dir][i];

                    if(ny <= 0 || ny > n || nx <= 0 || nx > n ) continue;
                    if(arr[ny][nx] == -1) continue;
                    
                    sumArr[dir] += Math.min(arr[ny][nx], 20);
                }
            }

            //  최종 청소 방향 결정
            int bestDir = 0, maxDust = sumArr[0];
            for(int i = 1; i < 4; i++){
                if(maxDust < sumArr[i]){
                    bestDir = i;
                    maxDust = sumArr[i];
                }
            }
            
            //  결정된 방향으로 먼지 깎기 
            for(int i = 0; i < 3; i++){
                int ny = y + t_dy[bestDir][i];
                int nx = x + t_dx[bestDir][i];
                if(ny <= 0 || ny > n || nx <= 0 || nx > n || arr[ny][nx] == -1) continue;
                
                arr[ny][nx] = Math.max(0, arr[ny][nx] - 20);
            }
            // 내 발밑 먼지 깎기
            arr[y][x] = Math.max(0, arr[y][x] - 20);
        }
    }

    static void spread(){
        // 1. 모든 먼지 격자에 5씩 축적
        for(int y = 1; y <= n; y++){
            for(int x = 1; x <= n; x++){
                if(arr[y][x] > 0) arr[y][x] += 5;
            }
        }

        // 2. 확산될 양만 담아둘 tempArr (0으로 초기화 됨)
        int[][] tempArr = new int[n+1][n+1];

        // 3. 확산 계산
        for(int y = 1; y <= n; y++){
            for(int x = 1; x <= n; x++){
                if(arr[y][x] == 0) { // 깨끗한 칸만 확산을 받음
                    int sum = 0;
                    for(int i = 0; i < 4; i++){
                        int ny = y + dy[i], nx = x + dx[i];
                        if(ny > 0 && nx > 0 && ny <= n && nx <= n && arr[ny][nx] > 0) {
                            sum += arr[ny][nx]; // 주변의 '먼지량'을 합산!
                        }
                    }
                    tempArr[y][x] = sum / 10;
                }
            }
        }
        
        // 4. 원본 맵에 확산 결과 한꺼번에 덮어씌우기
        for(int y = 1; y <= n; y++){
            for(int x = 1; x <= n; x++){
                if(arr[y][x] == -1) continue;
                arr[y][x] += tempArr[y][x];
            }
        }
    }

    static void calc(){
        int ret = 0;
        for(int y = 1; y<=n; y++){
            for(int x=1; x<=n; x++){
                if(arr[y][x] > 0) ret += arr[y][x];
            }
        }
        //전체 먼지를 출력
        System.out.println(ret);
    }
}
