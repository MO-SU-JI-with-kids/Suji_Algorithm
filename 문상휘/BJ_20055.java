import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static Belt[] belts;
    static int start = 0;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        belts = new Belt[N * 2];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N * 2; i++) {
            int A = Integer.parseInt(st.nextToken());
            belts[i] = new Belt(A);
        }

        int result = 0;

        while (true) {
            result++;

            rotate(N);
            moveRobot(N);
            on();

            if (count >= K) {
                break;
            }
        }

        System.out.println(result);
    }

    private static void on() { // 벨트에 로봇 올리기
        if (!belts[start].isRobot && belts[start].cnt > 0) {
            belts[start].isRobot = true;
            belts[start].cnt--;

            if (belts[start].cnt == 0) {
                count++;
            }
        }
    }

    private static void rotate(int N) {
        start = (start - 1 + 2 * N) % (2 * N);
        int last = (start + N - 1) % (2 * N);

        if (belts[last].isRobot) {
            belts[last].isRobot = false;
        }
    }

    private static void moveRobot(int N) {
        int down = (start + N - 1) % (2 * N);
        int last = (start + N - 1) % (2 * N);
        int R = 0;

        while (R < N - 1) {
            int ast = (last - 1 + 2 * N) % (2 * N);

            if (belts[ast].isRobot && belts[last].cnt > 0 && !belts[last].isRobot) {
                belts[ast].isRobot = false;
                belts[last].isRobot = true;
                belts[last].cnt--;

                if (belts[last].cnt == 0) {
                    count++;
                }

                if (last == down) {
                    belts[last].isRobot = false;
                }
            }

            R++;
            last = ast;
        }
    }

}

class Belt {

    int cnt;
    boolean isRobot;

    public Belt(int cnt) {
        this.cnt = cnt;
        isRobot = false;
    }
}
