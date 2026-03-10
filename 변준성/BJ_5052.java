import java.util.*;
import java.io.*;

public class BJ_5052 {
	
	static class TrieNode{
		Map<Integer, TrieNode> children = new HashMap<>();
		boolean isEnd;
	}
	
	static class Trie {
		TrieNode root = new TrieNode();
		boolean insert(String s) {
			TrieNode curr = root;
			for(char c : s.toCharArray()) {
				int idx = c-'0';
				if(!curr.children.containsKey(idx)) {
					curr.children.put(idx, new TrieNode());
				}
				
				if(curr.isEnd) return false;
				curr = curr.children.get(idx);
			}
			curr.isEnd = true;
			
			return true;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		StringBuilder sb = new StringBuilder();
		
		for(int tc=1;tc<=T;tc++) {
			int M = Integer.parseInt(br.readLine().trim());
			
			Trie tr = new Trie();
			String[] str = new String[M];
			
			for(int i=0;i<M;i++) {
				String s = br.readLine();
				str[i] = s;
			}
			
			Arrays.sort(str);
			boolean ans = true;
			
			for(int i=0;i<M;i++) {
				ans = tr.insert(str[i]);
				if(!ans) break;
			} 
			
			String res = ans ? "YES" : "NO";
			sb.append(res).append("\n");
			
		}
		System.out.println(sb);
	}
}
