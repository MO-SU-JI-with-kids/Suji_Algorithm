import java.util.*;
import java.io.*; // BufferedReader 사용을 위한 import

// [모수지] week1 BJ_17413

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] info = new int[9][9];
    static int[][] visited_y = new int[9][10]; // 세로 방향 방문 체크
    static int[][] visited_x = new int[9][10]; // 가로 방향 방문 체크
    
    // 3*3 방문 체크
    static int[][][] visited_box = new int[3][3][10];
    

    public static void solve(int y, int x) {
    	if(y == 9) {
    		for(int i=0; i<9; i++) {
    			for(int j=0; j<9; j++) {
    				System.out.print(info[i][j]);
    			}System.out.println();
    		}
    		System.exit(0);
    	}
    	
    	// 다음 칸으로 넘어가기
    	else if(info[y][x] != 0) {
    		if(x ==8) {
				solve(y+1, 0);
			}
			else {
				solve(y, x+1);
			}
    	}
    	
    	else {
    		boolean canFill = false; // 현재 칸을 채울 수 있는 방법이 있는가
        	
        	// 현재 칸을 채운다 -> 다음 칸을 채운다 -> 현재 칸 채운 것을 취소한다.(백트래킹 이용)
        	for(int i=1; i<=9; i++) {
        		
        		// 현재 칸을 i로 채울 수 있다면, 
        		if(visited_y[y][i]==0 && visited_x[x][i] == 0 && visited_box[y/3][x/3][i] == 0) {
        			visited_y[y][i]=1;  visited_x[x][i]=1; visited_box[y/3][x/3][i] =1; // 사용 표시
        			info[y][x] = i; // i를 사용!
        			
        			canFill = true;
        			
        			// 다음 번호를 찾아 떠나요~
        			if(x ==8) {
        				solve(y+1, 0);
        			}
        			else {
        				solve(y, x+1);
        			}

        			visited_y[y][i]=0;  visited_x[x][i]=0; visited_box[y/3][x/3][i] =0;
        			info[y][x] = 0;// 사용 취소(백트래킹)
        		}
        	}
        	
        	// 그 어떤 숫자로도 풀 수 없다면, return
        	if(canFill == false) {
        		info[y][x] = 0;
        		return;
        	}
    	}
    }

    public static boolean check() {
    	boolean done = true;
    	for(int i=0; i<9; i++) {
    		for(int j=0; j<9; j++) {
    			if(info[i][j] ==0) {
    				done = false;
    				break;
    			}
    		}
    	}
    	
    	// 모든 칸을 채웠다면
    	if(done == true) {
    		for(int i=0; i<9; i++) {
    			for(int j=0; j<9; j++) {
    				System.out.print(info[i][j]);
    			}System.out.println();
    		}
    		
    	}	
    	return done;
    }
 
    public static void main(String[] args) throws IOException {
    	int fillCnt = 0;
    	for(int i=0; i<9; i++) {
    		String s = br.readLine();
    		for(int j=0; j<9; j++) {
    			int in = info[i][j] = s.charAt(j) - '0';
    			visited_y[i][in] = 1; // 세로 방향 방문 체크
    			visited_x[j][in] = 1; // 가로 방향 방문 체크
    			visited_box[i/3][j/3][in] = 1;
    		}
    	}
    	
    	solve(0,0);
    }
}
