import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class Graph{
	ArrayList<HashSet<Integer>> list;
	
	Graph(String file){
		list = new ArrayList<HashSet<Integer>>();
		try (BufferedReader in = new BufferedReader(new FileReader(file))) {
			while (in.ready()) {
				try (Scanner sc = new Scanner(in.readLine())) {
					
					int first = sc.nextInt(), second = sc.nextInt();
					Set<Integer> set;
					if ((set = list.get(first)) == null)
						list.add(first, new HashSet<Integer>());
					if (list.get(second) == null)
						list.add(second, new HashSet<Integer>());
					set.add(second);
				}

			}
		} catch (IOException e) {
			System.out.println("IOExc " + e);
		}
		
	}

}

public class Prometheus_Heaps_Main {
	
	public static void main(String[] args) {
		Graph g = new Graph("test_08/test_08_1.txt");
		

	}

}
