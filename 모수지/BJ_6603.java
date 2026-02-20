import java.util.*;
import java.lang.*;
import java.io.*;

// [모수지] week3 BJ_6603
// 조합을 모두 출력하기
class Main {

    static Scanner sc = new Scanner(System.in);
    static int k = 1;
    static int[] info, selected;
    public static void main(String[] args) {

        while(k != 0){
            k = sc.nextInt();
            info = new int[k];
            selected = new int[6];
            for(int i=0; i<k; i++)info[i] = sc.nextInt();
            
            combi(0,0);   
            System.out.println();
        }
    }

    public static void combi(int idx, int depth){
        if(depth == 6){
            for(int i=0; i<6; i++) System.out.print(selected[i]+" ");
            System.out.println();
            return;
        }

        for(int i= idx; i<k; i++){
            selected[depth] = info[i];
            combi(i+1, depth+1);
        } 
    }
}
