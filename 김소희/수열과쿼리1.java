import java.io.*;
import java.util.*;

public class 수열과쿼리1 {
	
	static int N, M;
	static int[] arr;
	static ArrayList<Integer>[] tree;
	
	public static void main(String[] args) throws Exception {
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		tree = new ArrayList[4 * N];
		build(1, 1, N);
		
		M = Integer.parseInt(br.readLine());
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int I = Integer.parseInt(st.nextToken());
			int J = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			sb.append(query(1, 1, N, I, J, K)).append('\n');			
		}		
		
		System.out.print(sb);		
	}
	
	
//	build(node, start, end)
//	node번 노드가 담당하는 [start, end] 구간의 정렬 리스트를 만든다	
	static void build(int node, int start, int end) {
        
//		현재 노드가 가질 리스트 공간을 만든다.
		tree[node] = new ArrayList<>();

		
//		start == end면 원소가 1개짜리 구간 [3,3]
        if (start == end) {
            tree[node].add(arr[start]);
            return;
        }

//      현재 구간을 반으로 쪼개서
//      왼쪽 자식이 왼쪽 절반 담당 / 오른쪽 자식이 오른쪽 절반 담당 -> 각각 먼저 build
        int mid = (start + end) / 2;
        build(node * 2, start, mid);
        build(node * 2 + 1, mid + 1, end);

//      왼쪽 자식이 만든 정렬 리스트와 오른쪽 자식이 만든 정렬 리스트를 합쳐서
//      현재 노드의 정렬 리스트를 만든다.
        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

	
//	merge(left, right)
//	왼쪽 구간 정렬리스트 + 오른쪽 구간 정렬리스트를 합쳐서 하나의 정렬리스트로 만드는 함수
    static ArrayList<Integer> merge(ArrayList<Integer> left, ArrayList<Integer> right) {
        
//    	합쳐진 결과를 담을 리스트 생성.
    	ArrayList<Integer> merged = new ArrayList<>(left.size() + right.size());

//    	i : left를 가리키는 포인터 / j : right를 가리키는 포인터
        int i = 0, j = 0;
        
//      둘 다 아직 남아 있을 동안, 더 작은 값을 결과에 넣고 그 쪽 포인터를 한 칸 이동
    	while (i < left.size() && j < right.size()) {
            if (left.get(i) <= right.get(j)) {
                merged.add(left.get(i++));
            } else {
                merged.add(right.get(j++));
            }
        }
        
//    	한쪽이 먼저 끝났으면 	남은 다른 쪽 원소를 전부 뒤에 붙이면 됨.
        while (i < left.size()) merged.add(left.get(i++));
        while (j < right.size()) merged.add(right.get(j++));

        return merged;
    }

    static int query(int node, int start, int end, int left, int right, int k) {
        if (right < start || end < left) return 0;

        if (left <= start && end <= right) {
            int idx = upperBound(tree[node], k);
            return tree[node].size() - idx;
        }

        int mid = (start + end) / 2;
        return query(node * 2, start, mid, left, right, k)
             + query(node * 2 + 1, mid + 1, end, left, right, k);
    }

    // 타겟보다 큰 지점 찾기
    static int upperBound(ArrayList<Integer> list, int target) {
        int lo = 0;
        int hi = list.size();

        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (list.get(mid) <= target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }
}
