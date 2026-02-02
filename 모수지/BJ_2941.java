import java.util.*;


public class Main {
	
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String s = sc.next();
		String[] arr = {
            "c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="
        };
        
		int cnt = 0;
		
		for(int i=0; i< s.length(); i++) {
			int found = 0;
			
			if(i+2 < s.length()) {
				String cur = s.substring(i, i+3);
				for(String check : arr) {
					if(check.equals(cur)) {
						cnt++;
						found = 1;
						i+=2;
						break;
					}
				}
				
			}

            if(found == 0 && i+1 < s.length()){
                String cur = s.substring(i, i+2);
				for(String check : arr) {
					if(check.equals(cur)) {
						cnt++;
						found = 1;
						i++;
						break;
					}
				}
            }
			
			
			if(found == 0) {
				cnt++;
			}
		}
		
		System.out.println(cnt);

	}

}
