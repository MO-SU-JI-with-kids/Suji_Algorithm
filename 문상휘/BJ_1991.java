import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static Map<String, Integer> alphabets = new HashMap<>();
	static Node[] nodes = new Node[27];

	public static void main(String[] args) throws IOException {
		buildAlphabets();
		int N = Integer.parseInt(br.readLine());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String me = st.nextToken();
			String left = st.nextToken();
			String right = st.nextToken();

			nodes[alphabets.get(me)] = new Node(me, left, right);
		}

		pre("A");
		System.out.println();
		in("A");
		System.out.println();
		post("A");
	}

	private static void pre(String s) {
		if (!alphabets.containsKey(s)) {
			return;
		}

		Node now = nodes[alphabets.get(s)];

		System.out.print(now.me);
		pre(now.left);
		pre(now.right);
	}
	
	private static void in(String s) {
		if (!alphabets.containsKey(s)) {
			return;
		}

		Node now = nodes[alphabets.get(s)];
		
		in(now.left);
		System.out.print(now.me);
		in(now.right);
	}
	
	private static void post(String s) {
		if (!alphabets.containsKey(s)) {
			return;
		}

		Node now = nodes[alphabets.get(s)];
		
		post(now.left);
		post(now.right);
		System.out.print(now.me);
	}

	private static void buildAlphabets() {
		for (char c = 'A'; c <= 'Z'; c++) {
			alphabets.put(String.valueOf(c), c - 'A' + 1);
		}
	}
}

class Node {

	String me;
	String left;
	String right;

	public Node(String me, String left, String right) {
		this.me = me;
		this.left = left;
		this.right = right;
	}
}
