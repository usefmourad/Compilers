package csen1002.main.task6;

import java.util.*;

/**
 * Write your info here
 * 
 * @name Youssef Atef Mourad
 * @id 49-5215
 * @labNumber 18
 */

class CFG {

	String var;
	ArrayList<String> ter;

	public CFG(String v, ArrayList<String> t) {

		var = v;
		ter = t;

	}

	public String toString() {

		String s = "";

		for (int i = 0; i < ter.size(); i++)
			s += ter.get(i) + ",";

		s = s.substring(0, s.length() - 1);

		return var + "/" + s;
	}

}

class FnF {

	String var;
	ArrayList<String> first;
	ArrayList<String> follow;

	public FnF(String var) {

		this.var = var;
		first = new ArrayList<String>();
		follow = new ArrayList<String>();

	}

	public String printVar() {
		return var;
	}

	public String printFirst() {

		String s = "";

		for (int i = 0; i < first.size(); i++)
			s += first.get(i) + ",";

		s = s.substring(0, s.length() - 1);

		return var + "/" + s;
	}

	public String toString() {

		String s = "";

		for (int i = 0; i < follow.size(); i++)
			s += follow.get(i) + ",";

		s = s.substring(0, s.length() - 1);
		return var + "/" + s;
	}

}

public class CfgFirstFollow {

	ArrayList<String> var = new ArrayList<String>();
	ArrayList<String> term = new ArrayList<String>();
	ArrayList<String> rules = new ArrayList<String>();

	ArrayList<CFG> trn = new ArrayList<CFG>();

	ArrayList<FnF> FF = new ArrayList<FnF>();

	public static void removeDupString(ArrayList<String> List) {

		ArrayList<String> newList = new ArrayList<String>();

		for (String ele : List)
			if (!newList.contains(ele))
				newList.add(ele);

		List.clear();

		for (String ele : newList)
			List.add(ele);

	}

	public CfgFirstFollow(String cfg) {

		String[] split = cfg.split("#");

		for (int i = 0; i < split[0].split(";").length; i++)

			var.add(split[0].split(";")[i]);

		for (int i = 0; i < split[1].split(";").length; i++)

			term.add(split[1].split(";")[i]);

		for (int i = 0; i < split[2].split(";").length; i++)

			rules.add(split[2].split(";")[i]);

		for (int i = 0; i < var.size(); i++) {

			String[] ter = rules.get(i).substring(2).split(",");

			ArrayList<String> s = new ArrayList<String>();

			for (int j = 0; j < ter.length; j++)
				s.add(ter[j]);

			trn.add(new CFG(var.get(i), s));

		}

		for (int i = 0; i < var.size(); i++) {
			FF.add(new FnF(var.get(i)));
		}

	}

	public String first() {

		for (int i = 0; i < FF.size(); i++)
			for (int j = 0; j < trn.get(i).ter.size(); j++)
				if (term.contains(trn.get(i).ter.get(j).charAt(0) + "") || trn.get(i).ter.get(j).equals("e"))
					FF.get(i).first.add(trn.get(i).ter.get(j).charAt(0) + "");

		boolean change = true;

		while (change) {

			change = false;

			for (int i = 0; i < trn.size(); i++) {

				CFG gram = trn.get(i);

				for (int j = 0; j < gram.ter.size(); j++) {

					ArrayList<String> gramTerm = gram.ter;

					for (int k = 0; k < gramTerm.get(j).length(); k++) {

						String currString = gramTerm.get(j);

						boolean breakString = false;

						if (term.contains(currString.charAt(k) + "") || (currString.charAt(k) + "").equals("e")) {

							if (!FF.get(i).first.contains(currString.charAt(k) + "")) {
								FF.get(i).first.add(currString.charAt(k) + "");
								change = true;
								break;
							}
							breakString = true;

						}

						else {

							for (int u = 0; u < FF.size(); u++) {
								if (FF.get(u).var.equals(currString.charAt(k) + "")) {
									if (FF.get(u).first.isEmpty()) {
										breakString = true;
										break;
									} else {
										for (int y = 0; y < FF.get(u).first.size(); y++) {
											if (!FF.get(i).first.contains(FF.get(u).first.get(y))) {
												if (FF.get(u).first.get(y).equals("e")
														&& (k == currString.length() - 1)) {
													FF.get(i).first.add(FF.get(u).first.get(y));
													change = true;
												} else if (!FF.get(u).first.get(y).equals("e")) {
													FF.get(i).first.add(FF.get(u).first.get(y));
													change = true;
												}
											}
										}

										if (!FF.get(u).first.contains("e"))
											breakString = true;
										break;
									}

								}

							}

						}
						if (breakString)
							break;
					}

				}

			}

		}

		for (int i = 0; i < FF.size(); i++) {
			Collections.sort(FF.get(i).first);
			removeDupString(FF.get(i).first);
		}

		String s = "";

		for (int i = 0; i < FF.size(); i++) {

			s += FF.get(i).var + "/";

			for (int j = 0; j < FF.get(i).first.size(); j++) {
				s += FF.get(i).first.get(j);
			}

			s += ";";

		}

		return s.substring(0, s.length() - 1);
	}

	public String follow() {

		FF.get(0).follow.add("$");

		first();

		boolean change = true;

		while (change) {

			change = false;

			for (int j = 0; j < trn.size(); j++) {

				for (int k = 0; k < trn.get(j).ter.size(); k++) {

					String curStr = trn.get(j).ter.get(k);

					for (int x = 0; x < curStr.length(); x++) {

						if (var.contains(curStr.charAt(x) + "")) {

							for (int i = 0; i < FF.size(); i++) {

								String tarVar = FF.get(i).var;

								if ((curStr.charAt(x) + "").equals(tarVar)) {

									int pos = x;

									String nxt = curStr.substring(pos + 1);

									if (nxt.isEmpty()) {

										for (int e = 0; e < FF.size(); e++) {
											if (FF.get(e).var.equals(trn.get(j).var)) {

												for (String ele : FF.get(e).follow) {

													if (!FF.get(i).follow.contains(ele)) {
														change = true;
														FF.get(i).follow.add(ele);
													}

												}
												break;
											}
										}

									} else {

										for (int n = 0; n < nxt.length(); n++) {
											if (term.contains(nxt.charAt(n) + "")) {
												if (!FF.get(i).follow.contains(nxt.charAt(n) + "")) {
													FF.get(i).follow.add(nxt.charAt(n) + "");
													change = true;

												}
												break;
											}

											else {

												ArrayList<String> hereFirst = null;

												for (int e = 0; e < FF.size(); e++) {
													if (FF.get(e).var.equals(nxt.charAt(n) + "")) {

														hereFirst = FF.get(e).first;
														break;
													}
												}

												if (!hereFirst.contains("e")) {

													for (String ele : hereFirst) {
														if (!FF.get(i).follow.contains(ele) && !ele.equals("e")) {
															change = true;
															FF.get(i).follow.add(ele);
														}
													}
													break;
												}

												else if (n == nxt.length() - 1 && hereFirst.contains("e")) {
													for (int e = 0; e < FF.size(); e++) {
														if (FF.get(e).var.equals(trn.get(j).var)) {

															for (String ele : FF.get(e).follow) {

																if (!FF.get(i).follow.contains(ele)) {
																	change = true;
																	FF.get(i).follow.add(ele);
																}

															}
															break;
														}
														for (String ele : hereFirst) {
															if (!FF.get(i).follow.contains(ele) && !ele.equals("e")) {
																change = true;
																FF.get(i).follow.add(ele);
															}
														}
													}
													break;
												} else {
													for (String ele : hereFirst) {
														if (!FF.get(i).follow.contains(ele) && !ele.equals("e")) {
															change = true;
															FF.get(i).follow.add(ele);
														}
													}
												}

											}
										}

									}

								}

							}
						}
					}
				}

			}
		}
		for (int i = 0; i < FF.size(); i++) {
			Collections.sort(FF.get(i).follow);
			removeDupString(FF.get(i).follow);
		}
		String s = "";
		for (int i = 0; i < FF.size(); i++) {
			s += FF.get(i).var + "/";
			for (int j = 0; j < FF.get(i).follow.size(); j++) {
				s += FF.get(i).follow.get(j);
			}
			s += ";";
		}
		return s.substring(0, s.length() - 1);
	}

	public static void main(String[] args) {
		CfgFirstFollow cfgFirstFollow = new CfgFirstFollow(
				"S;V;Z;Q;O;M#g;j;l;r;s;w#S/wV,V,VVSQs,sV,O;V/l,VMgVO,V;Z/SrQl,SsVOj,e,Q,O,V;Q/sZgZg,Z,MM,gOVVj,MVw,O;O/lSVV,S,e;M/QQO,rZSOV,SsSw");
		System.out.println(cfgFirstFollow.follow());
	}
}