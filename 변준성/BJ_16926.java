
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_16926 {
	static int N, M, R;
	static int[] size;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer (br.readLine());
			for(int j=0;j<M;j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0;i<R;i++) {
			rotate();
		}
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	static void rotate() {
	    int s = Math.min(N, M) / 2;
	    for (int i = 0; i < s; i++) {
	        int sx = i;
	        int sy = i;
	        
	        
	        int tmp = map[sx][sy];

	       
	        for (int j = sy; j < M - 1 - i; j++) {
	            map[sx][j] = map[sx][j + 1];
	        }
	        
	        for (int j = sx; j < N - 1 - i; j++) {
	            map[j][M - 1 - i] = map[j + 1][M - 1 - i];
	        }
	        
	        for (int j = M - 1 - i; j > i; j--) {
	            map[N - 1 - i][j] = map[N - 1 - i][j - 1];
	        }
	       
	        for (int j = N - 1 - i; j > i; j--) {
	            map[j][i] = map[j - 1][i];
	        }
	        
	        
	        map[i + 1][i] = tmp;
	    }
	}
}
