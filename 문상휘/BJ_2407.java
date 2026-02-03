import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int N = input.nextInt();
		int[] nums = new int[N];
		
		for(int i = 0 ; i< N ; i++) {
			nums[i] = input.nextInt();
		}
		
		Arrays.sort(nums);
		
		int min = Integer.MAX_VALUE;
		int eNum = 0;
		int sNum = 0;

		int s = 0;
		int e = N - 1;

		while (s < e) {
			int sum = nums[e] + nums[s];

			if (Math.abs(sum) < min) {
				eNum = nums[e];
				sNum = nums[s];
				min = Math.abs(sum);
			}
			
			if(sum == 0) {
				break;
			}
			
			if(sum > 0) {
				e--;
			}
			
			if(sum < 0) {
				s++;
			}
		}

		System.out.println(sNum + " " + eNum);
	}
}
