import java.util.*;
import java.io.*;

public class Main {
    static class Robot {
        int x, y;
        public Robot(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Node implements Comparable<Node> {
        int x, y, dist;
        public Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            if (this.dist != o.dist) return this.dist - o.dist;
            if (this.x != o.x) return this.x - o.x;
            return this.y - o.y;
        }
    }

    static int N, K, L;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    static int[][] map;
    static ArrayList<Robot> robots;

    static void moveRobots() {
        for (int i = 0; i < K; i++) {
            Robot curr = robots.get(i);
            Node target = bfs(curr, i);

            if (target != null) {
                curr.x = target.x;
                curr.y = target.y;
            }
        }
    }

    static Node bfs(Robot start, int myIdx) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[][] visited = new boolean[N][N];

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(map[i][j] == -1) visited[i][j] = true;
            }
        }

        for(int i=0; i<K; i++) {
            if(i == myIdx) continue;
            Robot r = robots.get(i);
            visited[r.x][r.y] = true;
        }

        pq.offer(new Node(start.x, start.y, 0));
        visited[start.x][start.y] = true;

        while (!pq.isEmpty()) {
            Node curr = pq.poll();

            if (map[curr.x][curr.y] > 0) {
                return curr;
            }

            for (int d = 0; d < 4; d++) {
                int nx = curr.x + dx[d];
                int ny = curr.y + dy[d];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    pq.offer(new Node(nx, ny, curr.dist + 1));
                }
            }
        }
        return null;
    }

    static void cleanRobots() {
        for (Robot r : robots) {
            int maxDust = -1;
            int bestDir = -1;

            for (int d = 0; d < 4; d++) {
                int currentSum = getDustSum(r, d);
                if (currentSum > maxDust) {
                    maxDust = currentSum;
                    bestDir = d;
                }
            }

            if (bestDir != -1) {
                doClean(r, bestDir);
            }
        }
    }

    static int getDustSum(Robot r, int dir) {
        int sum = 0;

        int[] checkDirs = {
                dir,                // 앞 (바라보는 방향)
                (dir + 3) % 4,      // 왼쪽 (반시계 90도)
                (dir + 1) % 4       // 오른쪽 (시계 90도)
        };

        sum += Math.min(map[r.x][r.y], 20);

        for (int d : checkDirs) {
            int nx = r.x + dx[d];
            int ny = r.y + dy[d];

            if (nx >= 0 && ny >= 0 && nx < N && ny < N && map[nx][ny] != -1) {
                sum += Math.min(map[nx][ny], 20);
            }
        }
        return sum;
    }

    static void doClean(Robot r, int dir) {
        int[] checkDirs = {dir, (dir + 3) % 4, (dir + 1) % 4};

        if(map[r.x][r.y] > 0) {
            map[r.x][r.y] -= Math.min(map[r.x][r.y], 20);
        }

        for (int d : checkDirs) {
            int nx = r.x + dx[d];
            int ny = r.y + dy[d];
            if (nx >= 0 && ny >= 0 && nx < N && ny < N && map[nx][ny] != -1) {
                if(map[nx][ny] > 0) {
                    map[nx][ny] -= Math.min(map[nx][ny], 20);
                }
            }
        }
    }

    static void addDust() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] > 0) map[i][j] += 5;
            }
        }
    }

    static void spreadDust() {
        int[][] addMap = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0) {
                    int neighborSum = 0;

                    for (int d = 0; d < 4; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];

                        if (nx >= 0 && ny >= 0 && nx < N && ny < N && map[nx][ny] > 0) {
                            neighborSum += map[nx][ny];
                        }
                    }
                    addMap[i][j] = neighborSum / 10;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] += addMap[i][j];
            }
        }
    }

    static void printTotalDust() {
        int total = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] > 0) total += map[i][j];
            }
        }
        System.out.println(total);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        robots = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            robots.add(new Robot(x, y));
        }

        for (int i = 0; i < L; i++) {
            moveRobots();
            cleanRobots();
            addDust();
            spreadDust();
            printTotalDust();
        }
    }
}