import java.util.*;
import java.io.*;

public class BJ_1244 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[] switches;

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());
        switches = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < n + 1; i++) {
            switches[i] = Integer.parseInt(st.nextToken());
        }

        int stuCount = Integer.parseInt(br.readLine());

        for (int i = 0; i < stuCount; i++) {
            st = new StringTokenizer(br.readLine());
            int gender = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            if (gender == 1) {
                // 남자
                for (int j = value; j < n + 1; j += value) {
                    switches[j] = switches[j] == 0 ? 1 : 0;
                }
            } else if (gender == 2) {
                // 여자
                switches[value] = switches[value] == 0 ? 1 : 0;

                for (int j = 1; j < n + 1; j++) {
                    if (value + j <= n && value - j > 0) {
                        if (switches[value + j] == switches[value - j]) {
                            switches[value + j] = switches[value + j] == 0 ? 1 : 0;
                            switches[value - j] = switches[value + j];

                        }else {
                            break;
                        }
                    }
                }
            }
        }
        StringBuilder sb =new StringBuilder();

        for(int i = 1 ; i < n + 1 ; i++) {
            sb.append(switches[i]);
            if(i % 20 == 0)
                sb.append("\n");
            else
                sb.append(" ");
        }
        System.out.println(sb);
    }
}
