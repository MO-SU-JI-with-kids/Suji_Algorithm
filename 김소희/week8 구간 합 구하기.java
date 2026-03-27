import java.io.*;
import java.util.*;

public class Main {

    static long[] arr;
    static long[] tree;

    
    static long build(int node, int start, int end){
        if (start == end) {
            return tree[node] = arr[start];
        }
        int mid = (start + end) / 2;
        return tree[node] = build(node*2, start, mid) + build(node*2+1, mid+1, end);
    }
    
    static long query(int node, int start, int end, int left, int right){
        if (right < start || end < left) return 0;
        if (left <= start && end <= right) return tree[node];
        int mid = (start + end) / 2;
        return query(node*2, start, mid, left, right) + query(node*2+1, mid+1, end, left, right);
    }

    static void update(int node, int start, int end, int index, long value){
        if (index < start || end < index) return;
        if (start == end) {
            arr[index] = value;
            tree[node] = value;
            return;
        }
        int mid = (start + end) / 2;
        update(node*2, start, mid, index, value);
        update(node*2+1, mid+1, end, index, value);
        tree[node] = tree[node*2] + tree[node*2+1]; 
    }

    
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken()); // 변경
        int K = Integer.parseInt(st.nextToken()); // 구간 합

        arr = new long[N];
        for (int n = 0; n < N; n++) {
            arr[n] = Long.parseLong(br.readLine());
        }

        tree = new long[N*4];

        build(1, 0, N-1);
        
        for (int n = 0; n < M+K; n++) {
            // a, b, c 1 : 업데이트 b->c, 2 : b~c 구간 합
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken()); // 변경
            long c = Long.parseLong(st.nextToken()); // 구간 합

            if (a == 1) {
                update(1, 0, N-1, b - 1, c);
            }
            else {
                System.out.println(query(1, 0, N-1, b-1, (int)c-1));
            }
        }
    }
}
