import java.util.*;
import java.io.*; 

// [모수지] week1 BJ_17413

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
    public static void main(String[] args) throws IOException {
    	String s = br.readLine();
    	
    	String ans="";
    	int flag = 0; // 새로운 단어의 시작
    	String word;
    	int tag = 0; // 현재 검사하는 것이 태그인가
    	for(int i=0; i<s.length(); i++) {
    		char cur = s.charAt(i);
        
    		// 만약 <가 들어왔다면, 
    		if(cur == '<') {
    			// 이전까 저장된 flag~i-1 까지 저장하기 
    			tag = 1;
    			if(flag < i-1) {
    				String reverse = s.substring(flag, i);
    				ans += new StringBuilder(reverse).reverse().toString();
    				flag=i;
    			}
    		}
    		else if(cur == '>') {
    			String forward = s.substring(flag, i+1); // 태그는 그냥 그대로 이어붙이기
    			ans+=forward;
    			flag = i+1; //
    			tag = 0; // 하나의 태그가 끝남.
    		}
    		
    		//  태그가 아니면서 공백인 경우
    		else if(cur == ' ' && tag == 0) {
    			// 이전까지 저장된 단어 뒤집어 붙이기
    			String reverse = s.substring(flag, i);
    			ans+= new StringBuilder(reverse).reverse().toString();
				flag=i+1;
				ans+=' ';
    		}
    	}
    	
    	// 아직 남은 단어가 있다면
    	if(flag <= s.length()) {
    		String reverse = s.substring(flag, s.length());
    		ans+=new StringBuilder(reverse).reverse().toString();
    	}
    	
    	System.out.println(ans);
    	
    }
}
