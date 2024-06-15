package csen1002.main.task4;

import java.util.*;

/**
 * Write your info here
 *
 * @name
 * @id
 * @labNumber
 */

public class CfgEpsUnitElim {

	String inp = "";

	public static void removeDupString(ArrayList<String> List) {

		ArrayList<String> newList = new ArrayList<String>();

		for (String ele : List)
			if (!newList.contains(ele))
				newList.add(ele);

		List.clear();

		for (String ele : newList)
			List.add(ele);

	}

	public CfgEpsUnitElim(String cfg) {

		inp = cfg;



		}


	@Override
	public String toString() {



		return inp;
	}

	public void eliminateUnitRules() {

		String[] variable = inp.split("#")[0].split(";");

		List<String> var = List.of(variable);

		String[] ry = inp.split("#")[2].split(";");

		List<String> rule = new ArrayList<>(List.of(ry));

		ArrayList<ArrayList<String>> repeat = new ArrayList<ArrayList<String>>();

		for(int i = 0; i<rule.size(); i++){
			repeat.add(new ArrayList<String>());
			String[] curRule = rule.get(i).substring(2).split(",");
			List<String> curRuleList = List.of(curRule);
			for(int j = 0; j<var.size(); j++){
				if(curRuleList.contains(var.get(j))){
					repeat.get(i).add(var.get(j));
				}
			}
		}


		boolean unit = true;

		while (unit) {


			for(int i = 0; i<rule.size(); i++){
				String[] curRule = rule.get(i).substring(2).split(",");
				List<String> curRuleList = new ArrayList<>(List.of(curRule));
				curRuleList.remove(rule.get(i).charAt(0)+"");
				String s = rule.get(i).charAt(0)+"/";
				for(int j = 0; j<curRuleList.size(); j++) {
					s += curRuleList.get(j)+",";
				}
				rule.set(i, s.substring(0,s.length()-1));

			}

			unit = false;

			for(int i = 0; i<rule.size(); i++) {
				String[] curRule = rule.get(i).substring(2).split(",");
				ArrayList<String> curRuleList = new ArrayList<>(List.of(curRule));

				for (int j = 0; j < var.size(); j++) {

					if (curRuleList.contains(var.get(j))) {

						curRuleList.remove(var.get(j));

						for (int u = 0; u < rule.size(); u++)
							if ((rule.get(u).charAt(0) + "").equals(var.get(j))) {
								List<String> replce = new ArrayList<>(List.of(rule.get(u).substring(2).split(",")));
								for (int y = 0; y < replce.size(); y++)
									if (!repeat.get(i).contains(replce.get(y))
											&& !(rule.get(i).charAt(0) + "").equals(var.get(j))) {
										curRuleList.add(replce.get(y));
										if (replce.get(y).length() == 1)
											repeat.get(i).add(replce.get(y));

									}

							}
					}

					removeDupString(curRuleList);
					Collections.sort(curRuleList);

				}
				String s = rule.get(i).charAt(0)+"/";
				for(int j = 0; j<curRuleList.size(); j++) {
					s += curRuleList.get(j)+",";
				}
				rule.set(i, s.substring(0,s.length()-1));
			}
			for (int i = 0; i < rule.size(); i++) {
				String[] curRule = rule.get(i).substring(2).split(",");
				ArrayList<String> curRuleList = new ArrayList<>(List.of(curRule));

				for (int j = 0; j < var.size(); j++)

					if (curRuleList.contains(var.get(j))) {
						unit = true;
						break;

					}
			}



			if (!unit)
				break;

		}

		int co = 0;

		for(int i = 0; i<inp.length(); i++){
			if((inp.charAt(i)+"").equals("#"))
				co++;

			if(co == 2){
				inp = inp.substring(0, i+1);
				break;
			}
		}

		for(String e : rule){
			inp += e + ";";
		}

		inp = inp.substring(0, inp.length()-1);

	}


	public static char findLongestSingleSlot(List<List<Integer>> leaveTimes) {

		ArrayList<ArrayList<Integer>> wrk = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> now = new ArrayList<>();
		now.add(leaveTimes.get(0).get(0));
		now.add(leaveTimes.get(0).get(1));
		wrk.add(now);



		for(int i = 1; i<leaveTimes.size(); i++){

			ArrayList<Integer> now1 = new ArrayList<>();
			now1.add(leaveTimes.get(i).get(0));
			now1.add(leaveTimes.get(i).get(1) - leaveTimes.get(i-1).get(1));
			wrk.add(now);

		}

		int maxEmp = 0;
		int maxhrs = -1;

		for(int i = 0; i<wrk.size(); i++){
			if(wrk.get(i).get(1) > maxhrs){
				maxhrs = wrk.get(i).get(1);
				maxEmp = wrk.get(i).get(0);
			}
		}

		char[] apl = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};

		return apl[maxEmp];
	}




}