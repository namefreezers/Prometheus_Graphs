import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Prometheus_Heaps_Main {
	static ArrayList<HashSet<Integer>> input(String file){
		ArrayList<HashSet<Integer>> res = new ArrayList<HashSet<Integer>>();
		try (BufferedReader in = new BufferedReader(new FileReader(file))) {
			while (in.ready()) {
				try (Scanner sc = new Scanner(in.readLine())) {
					
					int first = sc.nextInt(), second = sc.nextInt();
					Set<Integer> set;
					if ((set = res.get(first)) == null)
						res.add(first, new HashSet<Integer>());
					if (res.get(second) == null)
						res.add(second, new HashSet<Integer>());
					set.add(second);
				}

			}
		} catch (IOException e) {
			System.out.println("IOExc " + e);
		}
		return res;
	}

	public static void main(String[] args) {
		ArrayList<HashSet<Integer>> list = input("test_08/test_08_1.txt");
		

	}

}
