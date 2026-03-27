import java.io.*;
import java.util.*;

public class Main {

    static long[] arr;
    static long[] tree;
    static long[] lazy;

    public static long build(int node, int start, int end) {
        if (start == end) {
            return tree[node] = arr[start];
        }
        int mid = (start + end) / 2;        
        return tree[node] = build(node*2, start, mid) + build(node*2+1, mid+1, end);
    }

    // 미뤄둔 lazy 값을 현재 노드에 반영
    static void propagate(int node, int start, int end) {
        if (lazy[node] != 0) {
            tree[node] += (end - start + 1) * lazy[node];

            if (start != end) { // 리프가 아니면 자식에게 넘김
                lazy[node * 2] += lazy[node];
                lazy[node * 2 + 1] += lazy[node];
            }

            lazy[node] = 0;
        }
    }

    public static long query(int node, int start, int end, int left, int right) {
        
        propagate(node, start, end);

        if (right < start || end < left) return 0;
        if (left <= start && end <= right) return tree[node];
        
        int mid = (start + end) / 2;                
        return query(node*2, start, mid, left, right) + query(node*2+1, mid+1, end, left, right);
    }

    public static void update(int node, int start, int end, int left, int right, long value) {
        
        propagate(node, start, end);
        if (right < start || end < left) return;
        if (left <= start && end <= right) {
            lazy[node] += value;
            propagate(node, start, end);
            return;
        }
        int mid = (start + end) / 2;                
        update(node*2, start, mid, left, right, value);
        update(node*2+1, mid+1, end, left, right, value);        
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }
    
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken()); // 변경
        int K = Integer.parseInt(st.nextToken()); // 구간 합

        arr = new long[N];
        tree = new long[N*4];
        lazy = new long[N*4];
        for (int n = 0; n < N; n++) {
            arr[n] = Long.parseLong(br.readLine());
        }

        build(1, 0, N-1);
        for (int n = 0; n < M+K; n++) {
        
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            if (a == 1) { // 업데이트
                int idx1 = Integer.parseInt(st.nextToken()); 
                int idx2 = Integer.parseInt(st.nextToken()); 
                long value = Long.parseLong(st.nextToken()); 
                update(1, 0, N-1, idx1-1, idx2-1, value);
            }
            else {
                int idx1 = Integer.parseInt(st.nextToken()); 
                int idx2 = Integer.parseInt(st.nextToken()); 
                System.out.println(query(1, 0, N-1, idx1-1, idx2-1));
            }        
        }
        
    }    
}
