import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".

// [이름] week5 BJ_11000
class State{
    int s, e;
    State(int s, int e){
        this.s = s;
        this.e = e;
    }
}
class Main {
    static Scanner sc = new Scanner(System.in);
    static int n;
    static State[] state;
    
    public static void main(String[] args) {
        n = sc.nextInt();
        state = new State[n];
        for(int i=0; i<n; i++){
            int s = sc.nextInt();
            int e = sc.nextInt();
            state[i] = new State(s,e);
        }

        Arrays.sort(state, (s1,s2)->{
            if(s1.s == s2.s) return s1.e - s2.e;
            return s1.s - s2.s;
        });

        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 종료 시간 기준 오름차순
        
        pq.offer(state[0].e);
        
        for(int i=1; i<n; i++){
            int cur = state[i].s;
            
            int e = pq.peek();
            if(cur >= e){
                pq.poll();
                pq.offer(state[i].e);
            }
            else{
                pq.offer(state[i].e);
            } 
        }

        System.out.println(pq.size());
        
    }
}
