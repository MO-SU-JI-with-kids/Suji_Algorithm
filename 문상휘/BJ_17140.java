import java.io.*;
import java.util.*;

class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] map;
	static int xSize = 3;
	static int ySize = 3;
	static int count = -1;

	public static void main(String[] args) throws IOException {
		map = new int[100][100];
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		for (int y = 0; y < 3; y++) {
			st = new StringTokenizer(br.readLine());

			for (int x = 0; x < 3; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}

		calculate(r - 1, c - 1, k);
		System.out.println(count);
	}

	private static void calculate(int r, int c, int k) {
		int cnt = 0;

		while (map[r][c] != k && cnt <= 100) {
			Map<Integer, Integer> pair = new HashMap<>();

			if (ySize >= xSize) { // R 연산
				int xsize = 0;

				for (int y = 0; y < ySize; y++) {
					pair = new HashMap<>();

					for (int x = 0; x < xSize; x++) {
						if (map[y][x] == 0) {
							continue;
						}
						// pair 에 넣기
						pair.put(map[y][x], pair.getOrDefault(map[y][x], 0) + 1);
					}

					List<Integer> list = makePairMap(pair);

					// 초기화
					for (int x = 0; x < xSize; x++) {
						map[y][x] = 0;
					}

					// 채우기
					int length = list.size() > 100 ? 100 : list.size();

					for (int i = 0; i < length; i++) {
						map[y][i] = list.get(i);
					}

					xsize = Math.max(xsize, length);
				}

				xSize = xsize;
				cnt++;

				continue;
			}

			int ysize = 0;

			for (int x = 0; x < xSize; x++) {
				pair = new HashMap<>();

				for (int y = 0; y < ySize; y++) {
					if (map[y][x] == 0) {
						continue;
					}

					// pair 에 넣기
					pair.put(map[y][x], pair.getOrDefault(map[y][x], 0) + 1);
				}

				List<Integer> list = makePairMap(pair);
				
				// 초기화
				for (int y = 0; y < ySize; y++) {
					map[y][x] = 0;
				}

				// 채우기
				int length = list.size() > 100 ? 100 : list.size();

				for (int i = 0; i < length; i++) {
					map[i][x] = list.get(i);
				}

				ysize = Math.max(ysize, length);
			}

			ySize = ysize;
			cnt++;
		}

		if (cnt <= 100) {
			count = cnt;
		} else {
			count = -1;
		}
	}

	private static List<Integer> makePairMap(Map<Integer, Integer> pair) {
		PriorityQueue<Pair> pq = new PriorityQueue<>();

		// pq 에 넣기
		for (int key : pair.keySet()) {
			pq.add(new Pair(key, pair.get(key)));
		}

		// pq 에서 빼기
		List<Integer> list = new ArrayList<>();

		while (!pq.isEmpty()) {
			Pair p = pq.poll();
			list.add(p.num);
			list.add(p.count);
		}

		return list;
	}
}

class Pair implements Comparable<Pair> {

	int num;
	int count;

	public Pair(int num, int count) {
		this.num = num;
		this.count = count;
	}

	@Override
	public int compareTo(Pair other) {
		if (count == other.count) {
			return Integer.compare(num, other.num);
		}

		return Integer.compare(count, other.count);
	}
}
