import java.util.*;
import java.io.*;

public class BJ_17413 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        String input = br.readLine().trim();

        StringBuilder result = new StringBuilder();

        List<Character> charList = new LinkedList<>();
        char[] arr = input.toCharArray();

        StringBuilder sb = new StringBuilder();

        boolean isTag = false;

        for (int i = 0; i < arr.length; i++) {
            char c = arr[i];

            if (c == '>') {
                // 태그 끝남.
                sb.append(c);
                result.append(sb.toString());
                sb = new StringBuilder();
                isTag = false;
            } else if (c == '<') {
                // 이전 문자열 끝남, 태그 시작
                isTag = true;
                if (i != 0) {

                    char[] stringArr = sb.toString().toCharArray();
                    StringBuilder sb2 = new StringBuilder();
                    for (int j = 0; j < stringArr.length; j++) {
                        sb2.append(stringArr[stringArr.length - j - 1]);
                    }
                    result.append(sb2.toString());
                    sb = new StringBuilder();
                }
                sb.append(c);

            } else if (c == ' ') {
                if (!isTag) { // 태그 밖 -> 문자열 바뀌는 구분점.
                    char[] stringArr = sb.toString().toCharArray();
                    StringBuilder sb2 = new StringBuilder();
                    for (int j = 0; j < stringArr.length; j++) {
                        sb2.append(stringArr[stringArr.length - j - 1]);
                    }
                    result.append(sb2.toString());
                    sb = new StringBuilder();
                    result.append(c);
                }else {
                    sb.append(c);
                }
            } else {
                sb.append(c);
            }

        }
        if (arr[arr.length - 1] != '>') {
            char[] stringArr = sb.toString().toCharArray();
            StringBuilder sb2 = new StringBuilder();
            for (int j = 0; j < stringArr.length; j++) {
                sb2.append(stringArr[stringArr.length - j - 1]);
            }
            result.append(sb2.toString());
        }

        System.out.println(result);
    }
}
