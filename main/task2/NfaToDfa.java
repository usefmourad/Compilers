package csen1002.main.task2;

import java.util.*;

/**
 * Write your info here
 * 
 * @name Youssef Atef Mourad Gad
 * @id 49-5215
 * @labNumber 18
 */

class Trans {

	int start;
	int end;
	String Link;

	boolean goal = false;
	boolean ini = false;

	public Trans(int state0, String Link, int state1) {
		this.Link = Link;
		this.start = state0;
		this.end = state1;

	}

	public String toString() {

		return this.start + "," + this.Link + "," + this.end;
	}

}

class TransDfa {

	ArrayList<Integer> state0;
	String link;
	ArrayList<Integer> state1;
	boolean goal = false;
	boolean ini = false;

	public TransDfa(ArrayList<Integer> state0, String link, ArrayList<Integer> state1) {

		this.state0 = state0;
		this.link = link;
		this.state1 = state1;
	}

	public String toString() {

		String s = "";

		for (int i = 0; i < this.state0.size(); i++) {
			s = s + this.state0.get(i) + "/";
		}

		s = s.substring(0, s.length() - 1);

		s += "," + this.link + ",";

		for (int i = 0; i < this.state1.size(); i++) {
			s = s + this.state1.get(i) + "/";
		}

		s = s.substring(0, s.length() - 1);

		return s;
	}

}

public class NfaToDfa {

	ArrayList<Trans> trn = new ArrayList<Trans>();

	ArrayList<ArrayList<Integer>> eClosTable = new ArrayList<ArrayList<Integer>>();

	String[] split;
	boolean changed = true;

	ArrayList<String> alphabet = new ArrayList<String>();

	ArrayList<ArrayList<Integer>> finalStates = new ArrayList<ArrayList<Integer>>();

	ArrayList<TransDfa> answerSates = new ArrayList<TransDfa>();

	ArrayList<ArrayList<Integer>> states = new ArrayList<ArrayList<Integer>>();

	ArrayList<Integer> startState = new ArrayList<Integer>();
	ArrayList<Integer> goalState = new ArrayList<Integer>();

	public static void removeDup(ArrayList<Integer> List) {

		ArrayList<Integer> newList = new ArrayList<Integer>();

		for (int ele : List) {
			if (!newList.contains(ele)) {
				newList.add(ele);
			}
		}

		List.clear();

		for (int ele : newList) {
			List.add(ele);
		}

	}

	public static void removeDupString(ArrayList<String> List) {

		ArrayList<String> newList = new ArrayList<String>();

		for (String ele : List) {
			if (!newList.contains(ele)) {
				newList.add(ele);
			}
		}

		List.clear();

		for (String ele : newList) {
			List.add(ele);
		}

	}

	public static void removeDupArrArr(ArrayList<ArrayList<Integer>> List) {

		ArrayList<ArrayList<Integer>> newList = new ArrayList<ArrayList<Integer>>();

		for (ArrayList<Integer> ele : List) {
			if (!newList.contains(ele)) {
				newList.add(ele);
			}
		}

		List.clear();

		for (ArrayList<Integer> ele : newList) {
			List.add(ele);
		}

	}

	public static void sortListOfArrays(ArrayList<ArrayList<Integer>> finalStates2) {
		finalStates2.sort(Comparator.comparingInt(arr -> arr.stream().min(Integer::compareTo).orElse(0)));
	}

	public NfaToDfa(String input) {

		split = input.split("#");

		boolean negative = false;

		for (int i = 0; i < split[0].split(";").length; i++) {

			ArrayList<Integer> clos = new ArrayList<Integer>();

			clos.add(Integer.parseInt(split[0].split(";")[i]));

			eClosTable.add(Integer.parseInt(split[0].split(";")[i]), clos);

		}

		for (int i = 0; i < split[2].split(";").length; i++) {

			String[] tr = split[2].split(";")[i].split(",");

			trn.add(new Trans(Integer.parseInt(tr[0]), tr[1], Integer.parseInt(tr[2])));

		}

		System.out.println(eClosTable);
		System.out.println(trn);

		while (true) {

			changed = false;

			for (int i = 0; i < eClosTable.size(); i++) {

				ArrayList<Integer> clos = eClosTable.get(i);

				for (int j = 0; j < trn.size(); j++) {

					Trans curr = trn.get(j);

					if (clos.contains(curr.start) && curr.Link.equals("e") && !clos.contains(curr.end)) {

						changed = true;

						clos.add(curr.end);

					}
				}

				Collections.sort(clos);

			}

			if (!changed) {
				break;
			}
		}

		System.out.println(eClosTable);

		for (int i = 0; i < split[1].split(";").length; i++) {

			alphabet.add(split[1].split(";")[i]);

		}

		for (int i = 0; i < eClosTable.size(); i++) {
			ArrayList<String> pres = new ArrayList<String>();

			for (int j = 0; j < trn.size(); j++) {

				if (eClosTable.get(i).contains(trn.get(j).start) && !trn.get(j).Link.equals("e")) {

					pres.add(trn.get(j).Link);

				}
			}

			Collections.sort(pres);

			removeDupString(pres);

			if (!alphabet.equals(pres)) {
				negative = true;
				break;
			}
		}

		trn.clear();

		for (int i = 0; i < split[2].split(";").length; i++) {

			String[] tr = split[2].split(";")[i].split(",");

			if (!tr[1].equals("e"))

				trn.add(new Trans(Integer.parseInt(tr[0]), tr[1], Integer.parseInt(tr[2])));

		}

		System.out.println("TRANSSSS" + trn);

		ArrayList<Integer> neg1 = new ArrayList<Integer>();
		neg1.add(-1);

		if (negative)

			states.add(neg1);

		states.add(eClosTable.get(Integer.parseInt(split[3])));

		for (int i = 0; i < states.size(); i++) {

			ArrayList<Integer> newSt = new ArrayList<Integer>();



			for (int y = 0; y < alphabet.size(); y++) {
			for (int j = 0; j < states.get(i).size(); j++) {
					int st = states.get(i).get(j);

					boolean neg = false;

					for (int u = 0; u < trn.size(); u++) {

						if (st == trn.get(u).start && alphabet.get(y).equals(trn.get(u).Link)) {

							neg = true;

							newSt.addAll(eClosTable.get(trn.get(u).end));

							removeDup(newSt);

							Collections.sort(newSt);

							System.out.println("NEWSTATE" + newSt);

							if (!states.contains(newSt))

								states.add(newSt);

						}
						
					}
									
					
					if(!neg) {
						answerSates.add(new TransDfa(states.get(i), alphabet.get(y), neg1));
					}
					
					else {
						answerSates.add(new TransDfa(states.get(i), alphabet.get(y), newSt));
					}

				}
			}
			
			
			  if(states.contains(newSt)) break;
			 

		}
		
		
		System.out.println(states);

		System.out.println("NEGATIVE" + answerSates);

		/*
		 * for (int i = 0; i < answerSates.size(); i++) {
		 * 
		 * Collections.sort(answerSates.get(i).state0);
		 * Collections.sort(answerSates.get(i).state1);
		 * 
		 * if (!states.contains(answerSates.get(i).state0))
		 * 
		 * states.add(answerSates.get(i).state0); }
		 */

		for (int i = 0; i < split[3].split(";").length; i++) {
			startState.add(Integer.parseInt(split[3].split(";")[i]));
		}

		for (int i = 0; i < split[4].split(";").length; i++) {
			goalState.add(Integer.parseInt(split[4].split(";")[i]));
		}

	}

	@Override
	public String toString() {

		String s = "";

		for (int i = 0; i < states.size(); i++) {
			for (int j = 0; j < states.get(i).size(); j++) {
				s += states.get(i).get(j) + "/";
			}

			s = s.substring(0, s.length() - 1);

			s += ";";

		}

		s = s.substring(0, s.length() - 1);

		s += "#" + split[1] + "#";

		for (int i = 0; i < states.size(); i++) {
			for (int j = 0; j < answerSates.size(); j++) {
				if (states.get(i).equals(answerSates.get(j).state0)) {
					s += answerSates.get(j).toString() + ";";
				}
			}
		}

		s = s.substring(0, s.length() - 1);

		s += "#";

		for (int i = 0; i < startState.size(); i++) {
			for (int j = 0; j < states.size(); j++) {
				if (states.get(j).contains(startState.get(i))) {
					for (int u = 0; u < states.get(j).size(); u++) {
						s += states.get(j).get(u) + "/";
					}
					s = s.substring(0, s.length() - 1);

					s += ";";
				}
			}
		}

		s = s.substring(0, s.length() - 1);

		s += "#";

		for (int i = 0; i < goalState.size(); i++) {
			for (int j = 0; j < states.size(); j++) {
				if (states.get(j).contains(goalState.get(i))) {
					for (int u = 0; u < states.get(j).size(); u++) {
						s += states.get(j).get(u) + "/";
					}
					s = s.substring(0, s.length() - 1);

					s += ";";
				}
			}
		}

		s = s.substring(0, s.length() - 1);
		return s;

	}

	public static void main(String[] args) {

		NfaToDfa nfaToDfa = new NfaToDfa(
				"0;1;2;3;4;5;6;7;8;9;10#a;b#0,e,1;1,b,2;2,e,3;3,e,4;3,e,9;4,e,5;4,e,7;5,a,6;6,e,4;6,e,9;7,b,8;8,e,4;8,e,9;9,a,10#0#10");
		System.out.println(nfaToDfa.toString());

	}

}
