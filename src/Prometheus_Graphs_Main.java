import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class Graph {
	HashMap<Integer, HashSet<Integer>> map;

	Graph() {

	}

	Graph(String file) {
		map = new HashMap<Integer, HashSet<Integer>>();
		try (BufferedReader in = new BufferedReader(new FileReader(file))) {
			while (in.ready()) {
				try (Scanner sc = new Scanner(in.readLine())) {

					int first = sc.nextInt(), second = sc.nextInt();
					HashSet<Integer> set = null;
					if (!map.containsKey(first)) {
						set = new HashSet<Integer>();
						map.put(first, set);
					} else {
						set = map.get(first);
					}
					set.add(second);
					if (!map.containsKey(second))
						map.put(second, new HashSet<Integer>());
				}
			}
		} catch (IOException e) {
			System.out.println("IOExc " + e);
		}
	}

	Graph reverse() {
		Graph res = new Graph();
		res.map = new HashMap<Integer, HashSet<Integer>>();

		for (Integer i : map.keySet())
			for (Integer j : map.get(i)) {
				if (!res.map.containsKey(j))
					res.map.put(j, new HashSet<Integer>());
				if (!res.map.containsKey(i))
					res.map.put(i, new HashSet<Integer>());
				res.map.get(j).add(i);
			}
		return res;
	}

	ArrayList<Set<Integer>> StronglyConnected() {
		ArrayList<Integer> fin = new ArrayList<Integer>();
		DFSLoop(fin);
		System.out.println(new java.sql.Timestamp(new java.util.Date().getTime()));

		Graph g2 = reverse();
		System.out.println(new java.sql.Timestamp(new java.util.Date().getTime()));

		ArrayList<Set<Integer>> res = g2.DFSLoop2(fin);
		System.out.println(new java.sql.Timestamp(new java.util.Date().getTime()));
		return res;
	}

	void DFSLoop(ArrayList<Integer> fin) {
		Set<Integer> researched = new HashSet<Integer>();
		int t = -1;
		for (Integer i : map.keySet())
			if (!researched.contains(i))
				t = DFSRUtilCon(i, researched, t, fin);

	}

	int DFSRUtilCon(int start, Set<Integer> researched, int t, ArrayList<Integer> fin) {
		researched.add(start);
		for (Integer i : map.get(start))
			if (!researched.contains(i))
				t = DFSRUtilCon(i, researched, t, fin);
		t = t + 1;
		fin.add(t, start);
		return t;
	}

	ArrayList<Set<Integer>> DFSLoop2(ArrayList<Integer> fin) {
		Set<Integer> researched = new HashSet<Integer>();
		ArrayList<Set<Integer>> listConnected = new ArrayList<>();
		for (int i = fin.size() - 1; i >= 0; i--)
			if (!researched.contains(fin.get(i))) {
				Set<Integer> setConnected = new HashSet<Integer>();
				DFSRUtilCon2(fin.get(i), researched, setConnected, fin);
				listConnected.add(setConnected);
			}
		return listConnected;

	}

	void DFSRUtilCon2(int start, Set<Integer> researched, Set<Integer> setConnected, ArrayList<Integer> fin) {
		researched.add(start);
		setConnected.add(start);
		Set<Integer> startSet = map.get(start);
		for (int i : startSet)
			if (!researched.contains(i))
				DFSRUtilCon2(i, researched, setConnected, fin);
	}
}

public class Prometheus_Graphs_Main {

	public static void main(String[] args) {
		System.out.println(new java.sql.Timestamp(new java.util.Date().getTime()));
		Graph g = new Graph("test_08/input_08.txt");
		System.out.println(new java.sql.Timestamp(new java.util.Date().getTime()));
		ArrayList<Set<Integer>> list = g.StronglyConnected();
		ArrayList<Integer> sizes = new ArrayList<Integer>();
		for(Set<Integer> s : list)
			sizes.add(s.size());
		Collections.sort(sizes);
		for (int i = sizes.size()-1; i>sizes.size()-6 ;i--)
			System.out.println(sizes.get(i));
	}
}
