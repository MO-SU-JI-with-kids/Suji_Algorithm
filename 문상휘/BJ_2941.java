import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		String line = br.readLine();
		List<String> list = new ArrayList<>();
		list.add("c=");
		list.add("c-");
		list.add("dz=");
		list.add("d-");
		list.add("lj");
		list.add("nj");
		list.add("s=");
		list.add("z=");

		for (String listElement : list) {
			line = line.replaceAll(listElement, "1");
		}

		System.out.print(line.toCharArray().length);
	}
}
