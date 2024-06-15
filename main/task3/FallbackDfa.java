package csen1002.main.task3;

import java.util.*;

/**
 * Write your info here
 *
 *
 * @name
 * @id
 * @labNumber
 */

public class FallbackDfa {

	String[] transition;

	String[] start;

	String[] goal;



	public FallbackDfa(String fdfa) {


		transition = fdfa.split("#")[2].split(";");

		start = fdfa.split("#")[3].split(";");

		goal = fdfa.split("#")[4].split(";");



	}

	public String run(String input) {

		ArrayList<String> twr = new ArrayList<String>();

		String output = "";

		while (true) {

			int left = 0;
			int right = 0;

			twr.clear();

			twr.addFirst(start[0]);

			int i = 0;

			while (left < input.length()) {

				for (int j = 0; j < transition.length; j++) {

					String[] miniSplit = transition[j].split(",");

					if ((twr.getLast()+"").equals(miniSplit[0]+"") && (miniSplit[1]+"").equals(input.charAt(left) + "")) {
						twr.addLast(miniSplit[2]+"");
						left++;
						break;
					}
				}
			}

			String fail = twr.getLast();

			String tmp = "";

			for (left = input.length() - 1; left >= 0; left--) {

				Boolean f = false;

				String succ = twr.removeLast();

				for (int j = 0; j < goal.length; j++) {

					if (goal[j].equals(succ)) {

						for (; i <= left; i++) {
							tmp += input.charAt(i);
						}

						output += tmp +"," + succ + ";";
						f = true;
					}

				}
				if(f)
					break;
			}

			if (left == right - 1) {
				output += input + "," + fail;
				break;
			}

			left++;

			if (left >= input.length()) {
				break;
			}

			input = input.substring(left);




		}

		if ((output.charAt(output.length() - 1) + "").equals(";"))
			return output.substring(0, output.length() - 1);


		return output;
	}

}
