import java.util.*;

// [모수지] week1 BJ_17143

class State{
	int y, x, velocity, d, size;

	public State(int y, int x, int velocity, int d, int size) {
		super();
		this.y = y; this.x = x;
		this.velocity = velocity; this.d = d;
		this.size = size;
	}
}

public class Main {
	static Scanner sc = new Scanner(System.in);

	static int R, C, M;
	static List<State> shark = new ArrayList<>();
	static int cnt = 0;
	static int[] dy = {0, -1, 1, 0, 0};
	static int[] dx = {0, 0, 0, 1, -1};
	
	
	// cur 상러가 헤엄친다. 헤엄진 이후 위치를 반환한다.
	public static State swim(State cur) {
	
		int v = cur.velocity;
	    int d = cur.d;
	    int ny = cur.y;
	    int nx = cur.x;

	    int mod; // 주기 저장 변수
	    if (d == 1 || d == 2)  mod = (R - 1) * 2; // 위아래로 움직이는 경우
      else mod = (C - 1) * 2; // 좌우로 움직이는 경우

	    // 0이 될 수 있는 경우를 방지 
	    if (mod > 0) v %= mod;
	    

	    // 이제 훨씬 줄어든 v만큼만 반복문 실행
	    for (int i = 0; i < v; i++) {
	        int nextY = ny + dy[d];
	        int nextX = nx + dx[d];

	        if (nextY <= 0 || nextY > R || nextX <= 0 || nextX > C) {
	            // 방향 반전 (1<->2, 3<->4)
	            d = (d == 1) ? 2 : (d == 2) ? 1 : (d == 3) ? 4 : 3;
	            
	            // 반전 방향으로 실제 이동
	            ny += dy[d];
	            nx += dx[d];
	        } else {
	            ny = nextY;
	            nx = nextX;
	        }
	    }
	    return new State(ny, nx, cur.velocity, d, cur.size);
	}
	
	// 상어들이 이동한다.
	public static void move() {
		for(int i=0; i<shark.size(); i++) {
			State cur = shark.get(i); // 해당 상어를 이동시킬 것임.
			State next = swim(cur); // 상어가 헤엄친다.
			shark.set(i, next); // 사어 위치 갱신
			
		}
		
		// 이동 후 상어의 위치를 고려해 잡아먹기 진행 
		Collections.sort(shark, (s1, s2)->{ // List 정렬 시 Arrays 가 아닌 Collections 를 이용함.
			if(s1.x == s2.x) {
				if(s1.y == s2.y) {
					return s2.size - s1.size;
				}
				return s1.y - s2.y;
			}
			return s1.x - s2.x;
		});
		
		
		// 각 위치별 중복이 있는 경우, 상어 삭제.
		for (int i = shark.size() - 1; i > 0; i--) {
		    State current = shark.get(i);
		    State prev = shark.get(i - 1);
		    
		    // 앞의 상어와 좌표가 같다면, 현재(i) 상어는 size가 더 작거나 같으므로 삭제
		    if (current.x == prev.x && current.y == prev.y) {
		        shark.remove(i);
		    }
		}
	}
	
	// 낙시를 한다.
	public static void fishhook(int lo) {
		// 현재 lo 에서의 상어를 모두 잡는다.
		int target = -1; // 잡을 상어의 idx
		int target_y = 1001;
		for(int i=0; i<shark.size(); i++) {
			State cur = shark.get(i);
			if(cur.x == lo) {
				if(cur.y < target_y) {
					target = i;
					target_y = cur.y;
				}
			}
		}
		if(target != -1) {
			cnt+= shark.get(target).size;
			shark.remove(target);
		}
		// 상어가 움직인다.
		move();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// 입력 받기
		R = sc.nextInt();
		C = sc.nextInt();
		M = sc.nextInt();
		if(M == 0) {
			System.out.println(0);
			System.exit(0);
		}
		for(int i=0; i<M; i++) {
			int r = sc.nextInt();
			int c = sc.nextInt();
			int s = sc.nextInt();
			int d = sc.nextInt();
			int z = sc.nextInt();
			shark.add(new State(r,c,s,d,z));
		}

		// 총 R 초 동안 낙시왕은 이동한다.
		for(int i=1; i<=C; i++) fishhook(i); // 현재 위치에서 낙시왕이 낙시를 한다.
		System.out.println(cnt);
	}
}
