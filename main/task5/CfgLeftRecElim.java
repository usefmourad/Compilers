package csen1002.main.task5;

import java.util.*;

/**
 * Write your info here
 *
 * @name Youssef Atef Mourad Gad
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

public class CfgLeftRecElim {

	String result = "";
	ArrayList<String> var = new ArrayList<String>();
	ArrayList<String> term = new ArrayList<String>();
	ArrayList<String> rules = new ArrayList<String>();

	ArrayList<CFG> trn = new ArrayList<CFG>();

	ArrayList<String> epsilon = new ArrayList<String>();

	ArrayList<CFG> dashes = new ArrayList<CFG>();

	public CfgLeftRecElim(String cfg) {

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

		for (int i = 0; i < trn.size(); i++)

			if (trn.get(i).ter.contains("e"))

				epsilon.add(trn.get(i).var);
	}

	public String toString() {

		String s = "";

		for (int i = 0; i < trn.size(); i++) {

			s += trn.get(i).var + ";";

		}

		if (dashes.isEmpty())

			s = s.substring(0, s.length() - 1);

		for (int i = 0; i < dashes.size(); i++) {

			s += dashes.get(i).var + ";";

		}
		s = s.substring(0, s.length() - 1);
		s += "#";

		for (int i = 0; i < term.size(); i++) {

			s += term.get(i) + ";";

		}

		s = s.substring(0, s.length() - 1);
		s += "#";

		for (int i = 0; i < trn.size(); i++) {

			s += trn.get(i) + ";";

		}

		if (dashes.isEmpty())

			s = s.substring(0, s.length() - 1);

		for (int i = 0; i < dashes.size(); i++) {

			s += dashes.get(i) + ";";

		}
		s = s.substring(0, s.length() - 1);

		return s;
	}

	public void eliminateLeftRecursion() {

		directElim(trn.get(0));

		for (int i = 1; i < trn.size(); i++) {
			for (int j = 0; j < i; j++) {

				for (int u = 0; u < trn.get(i).ter.size(); u++) {
					if (trn.get(j).var.equals(trn.get(i).ter.get(u).charAt(0) + "")) {
						int loc = u;
						String opp = trn.get(i).ter.get(u);
						opp = opp.substring(1);
						trn.get(i).ter.remove(u);

						for (int h = 0; h < trn.get(j).ter.size(); h++) {
							String no = trn.get(j).ter.get(h) + opp;
							trn.get(i).ter.add(loc, no);
							loc++;
						}

					}
				}

			}

			directElim(trn.get(i));
		}

	}

	public void directElim(CFG rul) {

		String dash = rul.var + "'";

		ArrayList<String> dashRule = new ArrayList<String>();

		for (int i = 0; i < rul.ter.size(); i++) {

			if (rul.var.equals(rul.ter.get(i).charAt(0) + "")) {
				dashRule.add(rul.ter.get(i));
				rul.ter.remove(i);
				i--;
			}

		}

		if (!dashRule.isEmpty()) {
			for (int i = 0; i < rul.ter.size(); i++) {

				String opp = rul.ter.get(0) + dash;

				rul.ter.remove(0);

				rul.ter.add(opp);
			}

			for (int i = 0; i < dashRule.size(); i++) {
				String opp = dashRule.get(0).substring(1) + dash;

				dashRule.remove(0);

				dashRule.add(opp);
			}

			dashRule.add("e");

			dashes.add(new CFG(dash, dashRule));
		}

	}

	public static void main(String[] args) {
		CfgLeftRecElim cfgLeftRecElim = new CfgLeftRecElim("S;T;L#a;b;c;d;i#S/ScTi,La,Ti,b;T/aSb,LabS,i;L/SdL,Si");
		cfgLeftRecElim.eliminateLeftRecursion();
		System.out.println(cfgLeftRecElim);
	}

}