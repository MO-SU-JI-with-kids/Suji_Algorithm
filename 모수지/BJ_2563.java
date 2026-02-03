import java.util.*;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static int[][] white = new int[101][101];
	static int cnt = 0;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int t = sc.nextInt();
		for(int tc=0; tc<t; tc++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			
			for(int i = y; i<y+10; i++) {
				for(int j=x; j<x+10; j++) {
					white[i][j]=1;
				}
			}
		}
		
		for(int i=1; i<=100; i++) {
			for(int j=1; j<=100; j++) {
				if(white[i][j] == 1) cnt++;
			}
		}
		
		System.out.println(cnt);
  }
}
