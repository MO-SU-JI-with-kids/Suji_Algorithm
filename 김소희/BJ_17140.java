// 이차원배열과연산_17240

import java.util.*;
import java.io.*;

public class BJ_17140 {
	
	static List<List<Integer>> R_operation(List<List<Integer>> map) {
        List<List<Integer>> newMap = new ArrayList<>();
        int maxRowLength = 0;

        for (List<Integer> row : map) {
            Map<Integer, Integer> cnt = new HashMap<>();

            for (int v : row) {
                if (v == 0) continue;
                cnt.put(v, cnt.getOrDefault(v, 0) + 1);
            }
            
            // <num, count> 계산
            List<int[]> list = new ArrayList<>();
            for (int key : cnt.keySet()) {
                list.add(new int[]{key, cnt.get(key)});
            }

            // 조건에 따라 정렬 (count 오름차순, num 오름차순)
            list.sort((a, b) -> {
                if (a[1] != b[1]) return a[1] - b[1];
                return a[0] - b[0];
            });

            // 딕셔너리 형태를 다시 배열 한줄로 변경
            List<Integer> newRow = new ArrayList<>();
            for (int[] p : list) {
                newRow.add(p[0]);
                newRow.add(p[1]);
            }

            // 행 또는 열의 크기가 100을 넘어가는 경우에는 처음 100개를 제외한 나머지는 버린다.
            if (newRow.size() > 100) {
                newRow = newRow.subList(0, 100);
            }

            maxRowLength = Math.max(maxRowLength, newRow.size());
            newMap.add(newRow);
        }

        // 0 padding
        for (List<Integer> row : newMap) {
            while (row.size() < maxRowLength) {
                row.add(0);
            }
        }

        return newMap;
    }

    static List<List<Integer>> C_operation(List<List<Integer>> map) {
        // 전치
        List<List<Integer>> trans = new ArrayList<>();
        int row = map.size();
        int col = map.get(0).size();

        for (int c = 0; c < col; c++) {
            List<Integer> newRow = new ArrayList<>();
            for (int r = 0; r < row; r++) {
                newRow.add(map.get(r).get(c));
            }
            trans.add(newRow);
        }

        // R 연산
        trans = R_operation(trans);

        // 전치
        List<List<Integer>> newMap = new ArrayList<>();
        row = trans.size();
        col = trans.get(0).size();

        for (int c = 0; c < col; c++) {
            List<Integer> newRow = new ArrayList<>();
            for (int r = 0; r < row; r++) {
                newRow.add(trans.get(r).get(c));
            }
            newMap.add(newRow);
        }

        return newMap;
    }
    
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		List<List<Integer>> map = new ArrayList<>();
		for (int row = 0; row < 3; row++) {
			List<Integer> rowList = new ArrayList<>();
			for (String col : br.readLine().split(" ")) {
				rowList.add(Integer.parseInt(col));
			}			
			map.add(rowList);
		}

		int row, col, time = 0;
		
		while (time <= 100) {
			
//			for (List<Integer> i : map) {
//				for (Integer S : i) {
//					System.out.print(S + " ");
//				}
//				System.out.println();
//			}
//			
			row = map.size();
			col = map.get(0).size();

		    if (r-1 >= 0 && r-1 < row && c-1 >= 0 && c-1 < col && map.get(r-1).get(c-1) == k) {
		        System.out.println(time);
		        return;
		    }

		    if (row >= col) {
		        map = R_operation(map);
		    } else {
		        map = C_operation(map);
		    }

		    time++;
		}

		System.out.println(-1);
	}	

}
