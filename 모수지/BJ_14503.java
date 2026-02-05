import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n, m, r_y, r_x,d, cnt;
    static int info[][], visited[][];
    
    static int[] dy = {-1,0,1,0};
    static int[] dx = {0,1,0,-1};

    public static void main(String[] args) throws IOException {
    	
    	// 입력
        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s);
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        s = br.readLine(); st = new StringTokenizer(s);
        r_y = Integer.parseInt(st.nextToken());
        r_x = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        info = new int[n][m]; visited = new int[n][m];
        
        for(int i=0; i<n; i++) {
        	s = br.readLine();
        	st = new StringTokenizer(s);
        	for(int j=0; j<m; j++) info[i][j] = Integer.parseInt(st.nextToken());
        }

        clean(r_y, r_x, d);
        System.out.println(cnt);
    }

    public static void clean(int y, int x, int cur_d) {
    	// 현재 칸을 청소한다.
    	if(visited[y][x] == 0) {
    		visited[y][x] = 1;   // 청소 표시
        	cnt++;  // 청소 구역 증가
    	}
    	
    	boolean isMore = false;
    	int next_d = 0, ny=0, nx=0;
    	for(int i = 1; i<=4; i++) {
    		next_d = (cur_d -i + 4)%4;
    		ny = y + dy[next_d];  
    		nx = x + dx[next_d];
    		
    		if(ny < 0 || ny >= n || nx < 0 || nx>=m) continue; // 범위를 벗어난 경우
    		
    		if(info[ny][nx] == 0 && visited[ny][nx] == 0) { // 청소 가능하고 아직 청소 안 한 경우
    			isMore = true;  // 청소 가능한 구역이 있음
    			clean(ny, nx, next_d);
    			break;
    		}
    	}
    	
    	// 청소할 구역이 없는 경우 - 2번
    	if(isMore == false) {
    		// 후진하기
    		ny = y -dy[cur_d];
    		nx = x- dx[cur_d];
    		
    		// 후진한 곳이 벽이면 return
    		if(info[ny][nx] == 1) return;
    		
    		// 아니라면 1번으로 이동
    		else clean(ny, nx, cur_d);
    	}
    } 
}
