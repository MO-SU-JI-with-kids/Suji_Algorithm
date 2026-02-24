import java.util.*;
import java.io.*;

public class BJ_14888 {

    static Scanner sc = new Scanner((System.in));
    static int n;
    static int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
    static int[] arr;
    static Map<Integer, Integer> map = new HashMap<>();
    static int[] operator;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {

        n = sc.nextInt();
        arr = new int[n];
        operator = new int[n - 1];
        visit = new boolean[n - 1];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        //0 : + / 1 : - / 2 : x / 3: %
        int idx = 0;
        for (int i = 0; i < 4; i++) {
            int cnt = sc.nextInt();
            for (int j = 1; j <= cnt; j++) {
                operator[idx++] = i;
            }
        }

        //연산자 정렬
        Arrays.sort(operator);

        permutation(0, arr[0]);
        System.out.println(max);
        System.out.println(min);

    }

    static void permutation(int idx, int sum) {

        if (idx == operator.length) {
            max = Math.max(max, sum);
            min = Math.min(min, sum);
            return;
        }


        for (int i = 0; i < operator.length; i++) {
            if (visit[i]) continue;

            visit[i] = true;


            //계산
            //덧셈
            int temp = sum;
            if (operator[i] == 0) {
                temp += arr[idx + 1];
            }
            //뺼셈
            if (operator[i] == 1) {
                temp -= arr[idx + 1];
            }
            //곱셈
            if (operator[i] == 2) {
                temp *= arr[idx + 1];
            }
            //나눗셈
            if (operator[i] == 3) {
                temp /= arr[idx + 1];
            }

            permutation(idx + 1, temp);
            visit[i] = false;
        }
    }

}
