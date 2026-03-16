import java.util.*;
import java.io.*;

public class Main {
	static class Point {
		int x, y;
		
		public Point (int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int h, w, cnt;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	static boolean[] key;
	static boolean[][] visited;
	static char[][] map;
	
	static void bfs() {
		Queue<Point> q = new LinkedList<>();
		
		visited[0][0] = true;
		q.add(new Point(0,0));
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			
			for(int i=0;i<4;i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				
				if(nx < 0 || ny < 0 || nx >= h || ny >= w || visited[nx][ny] || map[nx][ny] == '*') continue;
				
				if(Character.isLowerCase(map[nx][ny])) {
			        // 소문자 로직
			        key[map[nx][ny] - 'a'] = true;
			        map[nx][ny] = '.';
			        visited[nx][ny] = true;
			        q.add(new Point(nx, ny));
			        clear(); 
			    } 
			    else if(Character.isUpperCase(map[nx][ny])) {
			        if(key[map[nx][ny] - 'A']) {
			            map[nx][ny] = '.';
			            visited[nx][ny] = true;
			            q.add(new Point(nx, ny));
			        }
			    } 
			    else if(map[nx][ny] == '$') {
			        map[nx][ny] = '.';
			        cnt++;
			        visited[nx][ny] = true;
			        q.add(new Point(nx, ny));
			    } 
			    else {
			        visited[nx][ny] = true;
			        q.add(new Point(nx, ny));
			    }
			}
		}
	}
	
	static void viewK() {
		for(int i=0;i<26;i++) {
			if(key[i]) {
				System.out.print(i+ " ");
			}
		}
		System.out.println();
	}
	
	static void view() {
		for(int i=0;i<h;i++) {
			for(int j=0;j<w;j++) System.out.print(map[i][j] + " ");
			System.out.println();
		}
		
		System.out.println();
	}
	
	static void clear() {
		for(int i=0;i<h;i++) {
			Arrays.fill(visited[i], false);
		}
	}
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            h = Integer.parseInt(st.nextToken())+2;
            w = Integer.parseInt(st.nextToken())+2;
            
            key = new boolean[26];
            map = new char[h][w];
            visited = new boolean[h][w];
            
            for(int i=0;i<w;i++) {
            	map[0][i] = '.';
            	map[h-1][i] = '.';
            }
            
            for(int i=0;i<h;i++) {
            	map[i][0] = '.';
            	map[i][w-1] = '.';
            }
            
            for(int i=1;i<h-1;i++) {
            	char[] c = br.readLine().toCharArray();
            	for(int j=1;j<w-1;j++) {
            		map[i][j] = c[j-1];
            	}
            }
            
            String s = br.readLine();
            
            if(!s.equals("0")) {
            	for(int i=0;i<s.length();i++) {
                	key[s.charAt(i) - 'a'] = true;
                }
                
            }
            
            cnt = 0;
            
            bfs();

            System.out.println(cnt);
        }
    }
}