import java.util.*;
import java.io.*;

class pair {
	int y, x;

	pair() {
	}

	pair(int y, int x) {
		this.y = y;
		this.x = x;
	}
}

public class BJ_17135 {

	static Scanner sc = new Scanner((System.in));
	static int n, m, d, ret = 0;
	static int[][] map;
	static boolean[][] visit;
	static List<pair> monster, bow;

	public static void main(String[] args) throws IOException {

		n = sc.nextInt();
		m = sc.nextInt();
		d = sc.nextInt();

		map = new int[n][m];
		visit = new boolean[n][m];
		monster = new ArrayList<>();
		bow = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				map[i][j] = sc.nextInt();

				if (map[i][j] == 1)
					monster.add(new pair(i, j));
			}
		}

		dfs(0);

		System.out.println(ret);

	}

	static void dfs(int start) {
		if (bow.size() == 3) {
			// 각 위치에 궁수가 선택됨.
			attack();
			return;
		}

		// 궁수가 몇번쨰 열에 있는지 체크
		for (int i = start; i < m; i++) {
			bow.add(new pair(n, i));
			dfs(i + 1);
			bow.remove(bow.size() - 1);

		}
	}

	// 궁수는 n행에 머물러있음
	static void attack() {
		int atkCnt = 0;
		int result = 0;

		// 복사본
		List<pair> copyMonster = new ArrayList<>();
		for (pair p : monster) {
			copyMonster.add(new pair(p.y, p.x));
		}

		for (int index = 0; index < n; index++) {

			pair[] target = new pair[3];
			atkCnt = 0;

			for (int i = 0; i < 3; i++) {
				pair archer = bow.get(i);

				pair ret = null;
				int dis = 1000;
				for (pair mon : copyMonster) {
					int len = Math.abs(mon.y - archer.y) + Math.abs(mon.x - archer.x);

					if (len > d)
						continue;

					if (len < dis) {
						dis = len;
						ret = mon;
					} else if (len == dis) {
						if (mon.x < ret.x) {
							ret = mon;
						}
					}
				}

				target[i] = ret;
			}

			// 몬스터 없애기

			for (int i = copyMonster.size() - 1; i >= 0; i--) {
				for (pair t : target) {
					if (t == null)
						continue;

					if (copyMonster.get(i).equals(t)) {
						copyMonster.remove(i);
						atkCnt++;
						break;
					}
				}
			}

			// 몬스터 움직이기
			monsterMove(copyMonster);
			result += atkCnt;

		}

		ret = Math.max(ret, result);

	}

	static void monsterMove(List<pair> copyMonster) {
		for (int i = copyMonster.size() - 1; i >= 0; i--) {
		
			pair mon = copyMonster.get(i);

			mon.y += 1;
			if (mon.y == n) {
				copyMonster.remove(i);
				continue;
			}

		}
	}
}
