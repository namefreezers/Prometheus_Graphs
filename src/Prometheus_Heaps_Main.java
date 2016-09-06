import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class Graph {
	ArrayList<HashSet<Integer>> list;

	Graph() {

	}

	Graph(String file) {
		list = new ArrayList<HashSet<Integer>>();
		try (BufferedReader in = new BufferedReader(new FileReader(file))) {
			while (in.ready()) {
				try (Scanner sc = new Scanner(in.readLine())) {

					int first = sc.nextInt(), second = sc.nextInt();
					Set<Integer> set;
					if ((set = list.get(first)) == null)
						list.add(first, new HashSet<Integer>());
					// if (list.get(second) == null)
					// list.add(second, new HashSet<Integer>());
					set.add(second);
				}
			}
		} catch (IOException e) {
			System.out.println("IOExc " + e);
		}
	}
	
	Graph reverse(){
		Graph res = new Graph();
		res.list = new ArrayList<HashSet<Integer>>();

		for (Set<Integer> s : list)
			for (Integer i : s) {
				if (res.list.get(i) == null)
					res.list.add(i, new HashSet<Integer>());
				res.list.get(i).add(list.indexOf(s));
			}
		return res;
	}

	// NEDODELANO
	void StronglyConnected() {
		ArrayList<Integer> fin = new ArrayList<Integer>();
		Graph g2 = reverse();
		
		
	}

	void DFSLoop(ArrayList<Integer> fin) {
		Set<Integer> researched = new HashSet<Integer>();
		for (Set<Integer> s : list)
			if (!researched.contains(list.indexOf(s)))
				DFSRUtilCon(list.indexOf(s), researched, -1, fin);

	}

	int DFSRUtilCon(int start, Set<Integer> researched, int t, ArrayList<Integer> fin) {
		researched.add(start);
		for (Integer i : list.get(start))
			if (!researched.contains(i))
				t = DFSRUtilCon(i, researched, t, fin);
		t = t + 1;
		fin.add(t, start);
		return t;
	}

}

public class Prometheus_Heaps_Main {

	public static void main(String[] args) {
		Graph g = new Graph("test_08/test_08_1.txt");

	}

}
