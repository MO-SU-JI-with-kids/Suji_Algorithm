//[모수지] week2 BJ_17070
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Pipe{
	int y1, x1, y2, x2, d;
	Pipe(int y1, int x1, int y2, int x2, int d){
		this.y1 = y1;
		this.x1 = x1;
		this.y2 = y2;
		this.x2 = x2;
		this.d = d;
	}
}

public class BJO {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int n, cnt;
	static int[][] info;
	static int[] dy = {0, 1, 1}, dx = {1, 0, 1};
	static Queue<Pipe> q;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub	
		
		// 입력
		n = Integer.parseInt(br.readLine());
		info = new int[n][n];
		for(int i=0; i<n; i++) {
			String s = br.readLine();
			StringTokenizer st = new StringTokenizer(s);
			for(int j=0; j<n; j++) {
				info[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		// BFS
		q = new ArrayDeque<>();
		q.add(new Pipe(0,0,0,1, 0));
		while(!q.isEmpty()) {
			
			Pipe cur = q.poll();
			
			// 왼쪽(0) 방향으로 있다면 : 0 또는 2 방향만 가능 
			if(cur.d == 0) {
				move(cur.y2, cur.x2, 0);
				move(cur.y2, cur.x2, 2);
			}
			
			// 아래(1)방향이라면 1 또는 2만 가능
			else if(cur.d == 1) {
				move(cur.y2, cur.x2, 1);
				move(cur.y2, cur.x2, 2);
			}
			
			// 대각선 방향이라면 모든 방향 가능
			else {
				move(cur.y2, cur.x2, 0);
				move(cur.y2, cur.x2, 1);
				move(cur.y2, cur.x2, 2);
			}
		}
		
		System.out.println(cnt);
	}
	
	//2 좌표 이용해서 d 방향을 움직이기 
	static void move(int y, int x, int d) {
		 
		int ny = y + dy[d];
		int nx = x + dx[d];
		
		if(ny >= 0 && ny < n && nx >=0 && nx < n) {
			if(d == 2) {
				if(info[ny-1][nx] == 1 || info[ny][nx-1] == 1) return; // 이 경우 뭘 못함. 
			}
			
			if(info[ny][nx] == 1) return; // 벽을 만났다면
			
			
			// 도착했다면 
			if(ny == n-1 && nx == n-1) cnt++; // 방법 횟수 늘리기
			
			
			// 벽이 아니라면
			else if(info[ny][nx]!= 1) q.add(new Pipe(y,x,ny,nx, d));
		}
	}
}
