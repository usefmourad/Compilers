package csen1002.main.task1;
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
	
	
	
	public Trans(int state0, int state1, String Link) {
		this.Link = Link;
		this.start = state0;
		this.end = state1;
		
		
	}
	
	
	
	public String toString() {
		
		return this.start + "," + this.Link + "," + this.end;
	}
	
}

class MainT{
	int in;
	int go;
	String type;
	
	public MainT(int in, int go, String type) {
		this.in = in;
		this.go = go;
		this.type = type;
	}
}


public class RegExToNfa {

	Stack<String> stc = new Stack<String>();
	String[] spl;
	Stack<Trans> trn = new Stack<Trans>();
	ArrayList<MainT> bigtrans= new ArrayList<MainT>();
	int initial;
	int goal;
	
	
	
	 public RegExToNfa(String input) {
	  
	  spl = input.split("#");
	  
	  int j = -1;
	  
	  String exp = spl[1];
	  
	  for(int i = 0 ; i<spl[1].length(); i++) {
		  
		  
		  String alph = exp.charAt(i) +"";
		  stc.push(alph);
		  switch (alph) {
		  
		  case "*":
		
			  
			  stc.pop();
			  String op = stc.pop();
			  stc.push(op + "*");
			  MainT lstar = bigtrans.remove(bigtrans.size()-1);
		  
		  
			  Trans t = new Trans(lstar.go, lstar.in, "e");
			  trn.push(t);
			  
			  
			  j++;
			  Trans p = new Trans(j, lstar.in, "e");
			  trn.push(p);
			  initial = j;
			  
			  j++;
			  Trans o = new Trans(lstar.go, j, "e");
			  trn.push(o);
			  goal = j;
			  
			  trn.push(new Trans(initial, goal, "e"));
			  
			  MainT liststar = new MainT(initial, goal, "Star");
			  bigtrans.add(liststar);
			  
			  
			  
			  
			  break;
		  case ".":
				 
			  stc.pop();
			  String op2 = stc.pop();
			  String op1 = stc.pop();
			  stc.push(op1 + "." + op2);
			  
			  MainT re = bigtrans.remove(bigtrans.size()-1);
			  MainT ui = bigtrans.remove(bigtrans.size()-1);
			  
			  Stack<Trans> temp = new Stack<Trans>();
			  
			  Stack<Trans> po = new Stack<Trans>();
			  
			  while(!trn.isEmpty()) {
				  Trans e = trn.pop();
				  if(re.in != e.start) {
					  temp.push(e);
				  }
				  else {
					  po.push(e);
				  }
			  }
			  
			  while(!temp.isEmpty()) {
				  trn.push(temp.pop());
			  }
			  
			  while(!po.isEmpty()) {
				  Trans ik = po.pop();
				  trn.push(new Trans(ui.go, ik.end, ik.Link));
			  }
			  bigtrans.add(new MainT(ui.in, re.go, "Concat"));
			  
			  
			  
			  
			  break;
			  
			  
			  
			  
		  case "|":
			  stc.pop();
			  String oprand1 = stc.pop();
			  String oprand2 = stc.pop();
			  stc.push(oprand1 + "|" + oprand2);
			  
			 
			  
			  MainT last = bigtrans.remove(bigtrans.size()-1);
			  MainT Blast = bigtrans.remove(bigtrans.size()-1);
			  
			  
			  j++;
			  
			  Trans st1 = new Trans(j, last.in, "e");
			  Trans st2 = new Trans(j, Blast.in, "e");
			  
			  initial = j;
			  
			  j++;
			  
			  
			  Trans en1 = new Trans( last.go, j, "e");
			  Trans en2 = new Trans( Blast.go, j, "e");
			  
			  goal = j;
			  
			  trn.push(en1);
			  trn.push(en2);
			  trn.push(st1);
			  trn.push(st2);
			  
			  MainT listor = new MainT(initial, goal, "Union");
			  bigtrans.add(listor);
			  
			  break;
		  default:
			  j++;
			  Trans tp = new Trans(j, ++j, alph);
			  trn.push(tp);
			  int g = j;
			  MainT list = new MainT(g-1, g, "Normal");
			  bigtrans.add(list);
		  
		  }  
	  }
	 }
	 
	 
	 public ArrayList<Integer> removeDup(ArrayList<Integer> List){
		 
		ArrayList<Integer> newList = new ArrayList<Integer>();
		 
		for(int ele : List) {
			if(!newList.contains(ele)) {
				newList.add(ele);
			}
		}
		 
		return newList;
		 
	 }
	 

	
	@Override
	public String toString() {
		
		String s = ""; 
		
		ArrayList<Integer> states = new ArrayList<Integer>();
		ArrayList<Trans> temp = new ArrayList<Trans>();
		
		while(!trn.isEmpty()) {
			
			Trans tru = trn.pop();
			states.add(tru.start);
			states.add(tru.end);
			temp.add(tru);
		}
		
		Collections.sort(states);
		
		ArrayList<Integer> filterStates = removeDup(states);
		
		for(int i = 0; i<filterStates.size(); i++) {
			s = s + filterStates.get(i) + ";";
		}
		s = s.substring(0, s.length()-1);
		s += "#"+spl[0]+"#";
		
		ArrayList<Trans> tempMin = new ArrayList<Trans>();
		ArrayList<Integer> tempMinInt = new ArrayList<Integer>();
		
		for(int i = 0; i<filterStates.size(); i++) {
			for(int j=0; j<temp.size(); j++) {
				if(filterStates.get(i) == temp.get(j).start) {
					
					tempMin.add(temp.get(j));
				
				}
			}
			for(int u = 0; u<tempMin.size(); u++) {
				tempMinInt.add(tempMin.get(u).end);
			}
			
			Collections.sort(tempMinInt);
			
			for(int r = 0; r<tempMinInt.size(); r++) {
				for(int l = 0; l<tempMin.size(); l++)
				if(tempMinInt.get(r) == tempMin.get(l).end) {
					s = s+tempMin.get(l).toString()+";";
					
				}
			}
	     	tempMin = new ArrayList<Trans>();
	     	tempMinInt = new ArrayList<Integer>();
		}
		
		
		s = s.substring(0, s.length()-1);
		s += "#"+bigtrans.get(0).in+"#"+bigtrans.get(0).go;
		
		
		return s;
		 
		
		
	}
	
	
}
