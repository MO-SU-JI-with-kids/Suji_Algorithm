import java.io.*;
import java.util.*;

class Main{
	
	static class edge {
		StringBuilder s;
		int x,y;
		
		public edge(StringBuilder s, int x, int y) {
			this.s = s;
			this.x = x;
			this.y = y;
		}
		
		
	}
	
	static class TrieNode {
		TrieNode[] children = new TrieNode[26];
		
		int isEnd;
	}
	
	static class Trie {
		TrieNode root = new TrieNode();
		
		public void insert(String w) {
			TrieNode curr = root;
			
			for(char c : w.toCharArray()) {
				int idx = c - 'A';
				
				if(curr.children[idx] == null) {
					curr.children[idx] = new TrieNode();
				}
				
				curr = curr.children[idx];
			}
			
			curr.isEnd = 1;
		}
		
		public int find(String w) {
			TrieNode curr = root;
			
			for(char c : w.toCharArray()) {
				int idx = c - 'A';
				
				if(curr.children[idx] == null) return -1;
				
				curr = curr.children[idx];
			}
			
			return curr.isEnd;
		}
	}
	
	static Trie tr;
	static final int N = 4;
	static int[] point = {0,0,0,1,1,2,3,5,11};
	static int[] dx = {1,-1,0,0,1,1,-1,-1};
	static int[] dy = {0,0,1,-1,1,-1,1,-1};
	static char[][] map;
	static boolean[][] visited;
	static StringBuilder sb;
	static TreeSet<String> ans;
	
	static void dfs(int i, int j, String s) {
		if(s.length() == 9) return; 
		
		for(int k=0;k<8;k++) {
			int nx = i + dx[k];
			int ny = j + dy[k];
			
			if(nx<0 || ny<0 || nx>=N || ny>=N || visited[nx][ny]) continue;
			
			int t = tr.find(s+map[nx][ny]);
			if(t == -1) continue;
			else {
				if(t==1) {
					ans.add(s+map[nx][ny]);
				}
				visited[nx][ny] = true;
				dfs(nx, ny, s+map[nx][ny]);
				visited[nx][ny] = false;
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		tr = new Trie();
		sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine().trim());
		
		for(int i=0;i<n;i++) {
			tr.insert(br.readLine().trim());
		}
		
		br.readLine();
		int t = Integer.parseInt(br.readLine());
		map = new char[N][N];
		visited = new boolean[N][N];
		ans = new TreeSet<>();
		
		for(int tc=0;tc<t;tc++) {
			for(int i=0;i<N;i++) {
				map[i] = br.readLine().trim().toCharArray();
			}
			ans.clear();
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					visited[i][j] = true;
					dfs(i,j, ""+map[i][j]);
					visited[i][j] = false;
				}
			}
			
			int sum = 0;
			String l = "";
			
			for(String s : ans) {
				sum += point[s.length()];
				if(s.length() > l.length()) l = s;
			}
			
			
			sb.append(sum).append(" ").append(l).append(" ").append(ans.size()).append("\n");
			
			if(tc != t-1)br.readLine();
		}
		
		System.out.println(sb);
	}
}


