import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BJ_15650 {

    static Scanner input = new Scanner(System.in);
    static int n, m;
    static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {

        n = input.nextInt();
        m = input.nextInt();

        combi(1);
    }

    static void combi(int start) {
        if (list.size() == m) {
            for(int i = 0; i < list.size(); i++) {
                System.out.print(list.get(i) + " ");
            }
            System.out.println();
            return;
        }

        for (int i = start; i <= n; i++) {
            list.add(i);
            combi(i + 1);
            list.remove(list.size() - 1);
        }
    }
}
