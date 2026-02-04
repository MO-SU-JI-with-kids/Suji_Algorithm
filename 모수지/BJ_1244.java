import java.util.*;
import java.io.*; // BufferedReader 사용을 위한 import


// [모수지] week1 BJ_1244


public class Main {
    // Scanner 대신 BufferedReader 사용
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] light = new int[101];
    static int n;
    
    public static void boy(int pos) {
    	//System.out.println("boy");
    	for(int i=0; i<n; i++) {
    		if((i+1) % pos == 0) {
    			
    			if(light[i] == 1) light[i] = 0;
    			else light[i] = 1;
    		}
    	}
    	
    }
    
    public static void girl(int pos) {
    	//System.out.println("girl");
    	if(light[pos-1] == 1) light[pos-1] = 0;
    	else light[pos-1] = 1;
    	
    	int next = 1;
    	while(true) {
    		if(pos-next-1 >=0 && pos+next-1 <n) {
    			if(light[pos-next-1] == light[ pos+next-1]) {
    				if(light[pos-next-1] == 1) {
    					light[pos-next-1] = 0;
    					light[ pos+next-1] = 0;
    				}
    				else {
    					light[pos-next-1] = 1;
    					light[ pos+next-1] = 1;
    				}
    				
    				next++;
    			}
    			else {
    				break;
    			}
    		}
    		else {
    			break;
    		}
    	}
    	
    }
   

    public static void main(String[] args) throws IOException {
        String s = br.readLine();
        n = Integer.parseInt(s);
        
        s = br.readLine();
        StringTokenizer st = new StringTokenizer(s);
        for(int i=0; i<n; i++) {
        	light[i] = Integer.parseInt(st.nextToken());
        }
        
        s = br.readLine();
        int student = Integer.parseInt(s);
        for(int i=0; i<student; i++){
        	String cur = br.readLine();
        	st = new StringTokenizer(cur);
        	int gender = Integer.parseInt(st.nextToken());
        	int pos = Integer.parseInt(st.nextToken());
        
        	if(gender == 1) boy(pos);
        	else girl(pos);	
        }   
        
     // 결과 출력: 한 줄에 20개씩 출력
        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < n; k++) {
            sb.append(light[k]).append(" ");
            
            // 20번째 스위치를 출력한 후에는 줄바꿈 
            if ((k + 1) % 20 == 0) {
                sb.append("\n");
            }
        }
        
    }
}
