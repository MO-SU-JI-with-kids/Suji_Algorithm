import java.util.*;
import java.lang.*;
import java.io.*;

// [모수지] week4 BJ_15684
class Main {
    static Scanner sc = new Scanner(System.in);
    static int n, m, h, ans;
    static boolean[][] isConnedted;
    
    public static void main(String[] args) {
        
        n = sc.nextInt();
        m = sc.nextInt();
        h = sc.nextInt();

        isConnedted = new boolean[h][n];
        for(int i=0; i<m; i++){
            int y = sc.nextInt()-1; // 1씩 줄여서 실제 배열 인덱스와 맞도록... 
            int x = sc.nextInt()-1;
            isConnedted[y][x] = true;
        }

        if(isSuccess()) {
            System.out.println(0);
            System.exit(0);
        }
        else{
            combi(0,0,0,1);
            combi(0,0,0,2);
            combi(0,0,0,3);
        }

        // 3개로는 불가능하다면 -1을 출력
        System.out.println(-1);
    }

    public static void combi(int y, int x, int depth, int size){

        // size 만큼의 사다리를 추가로 연결했다
        if(depth == size){
            if(isSuccess()){
                System.out.println(depth);
                System.exit(0);
            }
            return;  
        }

        for(int ny=y; ny<h; ny++){
            for(int nx=0; nx<n-1; nx++){

                // 이미 연결되어 있다면 continue
                if(isConnedted[ny][nx]) continue;

                // 왼쪽에 이미 다른 사다리가 연결되어있다면
                if(nx-1>=0 && isConnedted[ny][nx-1]) continue;

                // 오른쪽에 다른 사다리가 연결되어있다면 
                if(nx+1<n && isConnedted[ny][nx+1]) continue;

                // 사다리를 놓을 수 있다면 놓기 
                isConnedted[ny][nx] = true;
                combi(ny,nx,depth+1, size);
                isConnedted[ny][nx] = false;    
            }
        } 
    }

    // 사다리 연결 확인 
    public static boolean isSuccess(){

        // 0~n-1 개의 사다리의 목적지를 조사한다. 
        for(int i=0; i<n; i++){
            if(i != destination(i))return false; 
        }
        return true;
    }

    // 사다리를 타고 내려간다. 
    public static int destination(int from){
        int x = from;
        for(int y = 0; y<h; y++){
            if(x-1 >=0 && isConnedted[y][x-1]) x = x-1;
            else if(x+1 < n && isConnedted[y][x]) x = x+1;    
        }
        return x; 
    } 
}
