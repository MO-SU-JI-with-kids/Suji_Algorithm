import java.util.*;

class State{
	int y, x, cnt;
	State(int y, int x, int cnt){
		this.y = y;
		this.x = x;
		this.cnt = cnt;
	}
}

public class Main {
	static Scanner sc = new Scanner(System.in);
	static int R, C, T; // R : y
	static int[][] info = new int[51][51];

	static List<State> dust = new ArrayList<>();
	static State[] cleaner = new State[2];
	
	static int[] dy = {0,0,1,-1};
	static int[] dx = {1,-1,0,0};
	
	public static void diffuse() {
		
		for(State cur : dust) {
			
			int diffuse_cnt = 0;
			for(int i=0; i<4; i++) {
				int ny = cur.y+ dy[i];
				int nx= cur.x + dx[i];
				
				if(ny<0 || ny >= R || nx <0 || nx >= C) continue; // 범위를 벗어난 경우
				else if(info[ny][nx] == -1) continue; // 공기청정기가 설치된 곳인 경우 
				
				diffuse_cnt++;
			}
			
			int diffuse_amount = cur.cnt / 5;
			info[cur.y][cur.x] -= diffuse_amount * diffuse_cnt; // 현재 위치의 양을 줄인다.
			
			// 확산시킨다 
			for(int i=0; i<4; i++) {
				int ny = cur.y+ dy[i];
				int nx = cur.x + dx[i];
				
				if(ny<0 || ny >= R || nx <0 || nx >= C) continue; // 범위를 벗어난 경우
				else if(info[ny][nx] == -1) continue; // 공기청정기가 설치된 곳인 경우 
				
				info[ny][nx]+= diffuse_amount;
			}
		}
	}
	
	// 공기청정기를 작동시킨다.
	public static void cleaner_on() {
		
		int[][] arr = new int[R][C];
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				arr[i][j] = info[i][j];
			}
		}

		/* 위에서 부는 바람은 시계 반대 방향 회전 */
		int cleaner_y = cleaner[0].y;
		int cleaner_x = cleaner[0].x;
		
		// 왼 : 아래로 이동 
		for(int i = cleaner_y - 1; i > 0; i--) {
		    info[i][0] = arr[i - 1][0];
		}

		// 위 : <- 이동 
		for(int i = 0; i < C - 1; i++) {
		    info[0][i] = arr[0][i + 1];
		}

		// 오 : 위로 이동 
		for(int i = 0; i < cleaner_y; i++ ) {
		    info[i][C - 1] = arr[i + 1][C - 1];
		}

		// 하 : -> 이동 
		for(int i = C - 1; i > 1; i--) {
		    info[cleaner_y][i] = arr[cleaner_y][i - 1];
		}
		info[cleaner_y][1] = 0;
		
		
		
		/* 아래에서 부는 바람은 시계방향 회전 */
		cleaner_y = cleaner[1].y;
		cleaner_x = cleaner[1].x;
		
		// 위 : -> 이동 
		for(int i = C - 1; i > 1; i--) {
		    info[cleaner_y][i] = arr[cleaner_y][i - 1];
		}
		info[cleaner_y][1] = 0;

		// 아래 : <- 이동 
		for(int i = 0; i < C - 1; i++) {
		    info[R - 1][i] = arr[R - 1][i + 1];
		}

		// 왼 : 위로 이동 
		for(int i = cleaner_y + 1; i < R - 1; i++) {
		    info[i][0] = arr[i + 1][0];
		}

		// 오 : 아래로 이동 
		for(int i = R - 1; i > cleaner_y; i--) {
		    info[i][C - 1] = arr[i - 1][C - 1];
		}
		
		
		// 현재까지 남아있는 미세먼지 구역만(이때 미세먼지가 0인 것은 카운트 안함) 다시 list 초기화한 후 저장하기
		dust = new ArrayList<>();
		
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(info[i][j] > 0) dust.add(new State(i,j,info[i][j]));
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		R = sc.nextInt();
		C = sc.nextInt();
		T = sc.nextInt();
		
		int clean_cnt = 0;
		for(int i=0;i<R; i++) {
			for(int j=0; j<C; j++) {
				info[i][j] = sc.nextInt();
				
				if(info[i][j]>0) dust.add(new State(i,j, info[i][j]));

				if(info[i][j] == -1) {
					cleaner[clean_cnt] = new State(i,j, -1);
					clean_cnt++;
				}
			}
		}
		
		// t초 동안 진행한다
		for(int t=0; t<T; t++) {
			
			// 미세먼지를 퍼뜨리기. : dust 안에 들어있는 위치에서만 확산된다. 이때 0은 제외
			diffuse();

			// 공기청정기를 작동! :  dust 배열을 재설정해야 함.
			cleaner_on();
		}
		
		int ans = 0;
		for(int i=0;i<R; i++) {
			for(int j=0; j<C; j++) {
				if(info[i][j] > 0) ans += info[i][j];
			}
		}
		
		System.out.println(ans);
	}
}
