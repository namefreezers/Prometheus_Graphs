import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class Graph {
	// ArrayList<HashSet<Integer>>
	// list;/////////////////////////////////////////////////////////
	HashMap<Integer, HashSet<Integer>> map;

	Graph() {

	}

	Graph(String file) {
		// list = new ArrayList<HashSet<Integer>>();
		// //////////////////////////////////////
		map = new HashMap<Integer, HashSet<Integer>>();
		try (BufferedReader in = new BufferedReader(new FileReader(file))) {
			while (in.ready()) {
				try (Scanner sc = new Scanner(in.readLine())) {

					int first = sc.nextInt(), second = sc.nextInt();
					HashSet<Integer> set = null;
					if (!map.containsKey(first)) {
						// list.set(first, new
						// HashSet<Integer>());////////////////////////////////////////
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
		// res.list = new
		// ArrayList<HashSet<Integer>>();///////////////////////////////////////////////
		res.map = new HashMap<Integer, HashSet<Integer>>();

		for (Integer i : map.keySet())
			for (Integer j : map.get(i)) {
				if (!res.map.containsKey(j))
					res.map.put(j, new HashSet<Integer>());
				if (!res.map.containsKey(i))
					res.map.put(i, new HashSet<Integer>());
				// res.list.get(j).add(list.indexOf(i));///////////////////////////////////////////
				res.map.get(j).add(i);
			}
		return res;
	}

	void StronglyConnected() {
		ArrayList<Integer> fin = new ArrayList<Integer>();
		DFSLoop(fin);
		Graph g2 = reverse();
		ArrayList<Set<Integer>> res = g2.DFSLoop2(new Iterable<Integer>() {

			@Override
			public Iterator<Integer> iterator() {
				return new Iterator<Integer>() {
					int n = 0;

					@Override
					public boolean hasNext() {
						return n < fin.size();
					}

					@Override
					public Integer next() {
						// return
						// list.get(fin.get(n++));/////////////////////////////////////
						return fin.get(n++);
					}

				};

			}
		}, fin);
		for (Set<Integer> s : res)
			System.out.println(s.size());
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

	ArrayList<Set<Integer>> DFSLoop2(Iterable<Integer> iter, ArrayList<Integer> fin) {
		Set<Integer> researched = new HashSet<Integer>();
		ArrayList<Set<Integer>> listConnected = new ArrayList<>();
		int i = 0;
		while (i < fin.size())
			if (!researched.contains(fin.get(i))) {
				Set<Integer> setConnected = new HashSet<Integer>();
				i = DFSRUtilCon2(i, researched, setConnected, fin);
				listConnected.add(setConnected);
			}
		/*
		 * for (Integer i : iter) if (!researched.contains(i)) { Set<Integer>
		 * setConnected = new HashSet<Integer>(); DFSRUtilCon2(i, researched,
		 * setConnected, fin); listConnected.add(setConnected); }
		 */
		return listConnected;

	}

	int DFSRUtilCon2(int startInd, Set<Integer> researched, Set<Integer> setConnected, ArrayList<Integer> fin) {
		researched.add(fin.get(startInd));
		setConnected.add(startInd);
		Set<Integer> startSet = map.get(fin.get(startInd));
		if (!researched.contains(fin.get(startInd+1)) && startSet.contains(fin.get(startInd + 1)))
			while (!researched.contains(fin.get(startInd))
					&& startSet.contains(fin.get(startInd + 1)))
				startInd = DFSRUtilCon2(startInd + 1, researched, setConnected, fin);
		else
			startInd = startInd + 1;
		return startInd;
	}
}

public class Prometheus_Heaps_Main {

	public static void main(String[] args) {
		Graph g = new Graph("test_08/test_08_1.txt");
		g.StronglyConnected();

	}

}
