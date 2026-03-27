import java.io.*;
import java.util.*;

public class Main {

    static long[] arr;
    static long[] treeMin;
    static long[] treeMax;

    public static long buildMin(int node, int start, int end) {
        if (start == end) {
            return treeMin[node] = arr[start];
        }
        int mid = (start + end) / 2;
        return treeMin[node] = Math.min(buildMin(node*2, start, mid), buildMin(node*2+1, mid+1, end));
    }

    public static long buildMax(int node, int start, int end) {
        if (start == end) {
            return treeMax[node] = arr[start];
        }
        int mid = (start + end) / 2;
        return treeMax[node] = Math.max(buildMax(node*2, start, mid), buildMax(node*2+1, mid+1, end));
    }

    public static long queryMin(int node, int start, int end, int left, int right) {
        if (right < start || end < left) return Long.MAX_VALUE;
        if (left <= start && end <= right) return treeMin[node];
        int mid = (start + end) / 2;
        return Math.min(queryMin(node*2, start, mid, left, right), queryMin(node*2+1, mid+1, end, left, right));        
    }

    public static long queryMax(int node, int start, int end, int left, int right) {
        if (right < start || end < left) return 0;
        if (left <= start && end <= right) return treeMax[node];
        int mid = (start + end) / 2;        
        return Math.max(queryMax(node*2, start, mid, left, right), queryMax(node*2+1, mid+1, end, left, right));        
    }
    
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arr = new long[N];
        treeMin = new long[N*4];
        treeMax = new long[N*4];
        
        for (int n = 0; n < N; n++) {
            arr[n] = Integer.parseInt(br.readLine());
        }

        buildMin(1, 0, N-1);
        buildMax(1, 0, N-1);
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            System.out.println(queryMin(1, 0, N-1, a-1, b-1) + " " + queryMax(1, 0, N-1, a-1, b-1));
        }
            
        
    }
    
}
