package csen1002.main.task8;

import java.util.*;

/**
 * Write your info here
 *
 * @name Youssef Atef Mourad
 * @id 49-5215
 * @labNumber 18
 */


public class CfgLl1Parser {


    ArrayList<String> var = new ArrayList<String>();
    ArrayList<String> term = new ArrayList<String>();
    ArrayList<String> rules = new ArrayList<String>();

    ArrayList<String> first = new ArrayList<String>();

    ArrayList<String> follow = new ArrayList<String>();

    public ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();

    Stack<String> stc = new Stack<String>();


    public CfgLl1Parser(String input) {

        String[] split = input.split("#");


        for (int i = 0; i < split[0].split(";").length; i++)

            var.add(split[0].split(";")[i]);


        for (int i = 0; i < split[1].split(";").length; i++)

            term.add(split[1].split(";")[i]);

        term.add("$");


        for (int i = 0; i < split[2].split(";").length; i++)

            rules.add(split[2].split(";")[i]);

        for (int i = 0; i < split[3].split(";").length; i++)

            first.add(split[3].split(";")[i]);

        for (int i = 0; i < split[4].split(";").length; i++)

            follow.add(split[4].split(";")[i]);


        for (int i = 0; i < var.size(); i++) {

            ArrayList<String> now = new ArrayList<String>();

            for (int j = 0; j < term.size(); j++) {
                now.add(null);
            }

            table.add(now);

        }

        for (int i = 0; i < var.size(); i++) {


            String cfg = rules.get(i).substring(2, rules.get(i).length());
            String fir = first.get(i).substring(2, first.get(i).length());

            ArrayList<String> tmpCfg = new ArrayList<String>();
            ArrayList<String> tmpFir = new ArrayList<String>();

            for (int j = 0; j < cfg.split(",").length; j++) {
                tmpCfg.add(cfg.split(",")[j]);
                tmpFir.add(fir.split(",")[j]);
            }

            for (int j = 0; j < tmpFir.size(); j++) {

                if (!tmpFir.get(j).equals("e")) {

                    String curr = tmpFir.get(j);

                    for (int o = 0; o < curr.length(); o++) {

                        int idx = 0;

                        for (int g = 0; g < term.size(); g++) {
                            if (term.get(g).equals(curr.charAt(o) + "")) {
                                idx = g;
                                break;
                            }
                        }

                        table.get(i).set(idx, tmpCfg.get(j));

                    }

                } else {
                    String tmpFol = follow.get(i).substring(2, follow.get(i).length());

                    for (int o = 0; o < tmpFol.length(); o++) {

                        int idx = 0;

                        for (int g = 0; g < term.size(); g++) {
                            if (term.get(g).equals(tmpFol.charAt(o) + "")) {
                                idx = g;
                                break;
                            }
                        }

                        table.get(i).set(idx, "e");

                    }
                }

            }


        }


    }

    public int getIdx(String strVar, ArrayList<String> var) {

        for (int i = 0; i < var.size(); i++)

            if (strVar.equals(var.get(i)))
                return i;


        return 0;
    }


    public String parse(String input) {


        input += "$";

        stc.push("$");
        stc.push(var.get(0));

        int idx = 0;

        String derv = var.get(0) + ";";
        while (true) {


            if (var.contains(stc.peek())) {

                String pop = stc.pop();

                int varIdx = getIdx(pop, var);
                int lkIdx = getIdx(input.charAt(idx) + "", term);

                String tableStr = table.get(varIdx).get(lkIdx);

                if (tableStr == null) {
                    derv += "ERROR";
                    break;
                }

                String[] now = derv.split(";");

                String lastDer = now[now.length - 1];

                for (int i = 0; i < lastDer.length(); i++) {
                    if (pop.equals(lastDer.charAt(i) + "")) {

                        if (tableStr.equals("e"))

                            lastDer = lastDer.substring(0, i) + lastDer.substring(i + 1, lastDer.length());


                        else {
                            lastDer = lastDer.substring(0, i) + tableStr + lastDer.substring(i + 1, lastDer.length());
                            for (int j = tableStr.length() - 1; j >= 0; j--)
                                stc.push(tableStr.charAt(j) + "");
                        }


                        derv += lastDer + ";";

                        break;
                    }
                }


            } else {

                if (!stc.peek().equals(input.charAt(idx) + "")) {

                    derv += "ERROR";
                    break;
                }

                stc.pop();
                idx++;


            }

            if (!stc.isEmpty() && idx >= input.length()) {
                derv += "ERROR";
                break;
            }

            if (stc.isEmpty() && idx < input.length()) {
                derv += "ERROR";
                break;
            }

            if (stc.isEmpty() && idx >= input.length())
                break;


        }


        if ((derv.charAt(derv.length() - 1) + "").equals(";"))
            derv = derv.substring(0, derv.length() - 1);


        return derv;
    }


}