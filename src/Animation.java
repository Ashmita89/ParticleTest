import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class Animation {
	
public static void main(String args[]) {
	Animation animation=new Animation();
	int speed =1;
	String init="..LR..RL";
	String[] output=animation.animate(speed, init);
	for(int ind=0;ind<output.length;ind++) {
		System.out.println(output[ind]);
	}
}
public String[] animate (int speed,String init) {
	HashMap<Integer,ArrayList<Character>> currPosit= new HashMap<Integer,ArrayList<Character>>();
	ArrayList<String> output=new ArrayList<String>();
	int index=0;
	int time=0;
	Boolean empty=false;
	while(!empty) {
		if(time==0) {
		for(int i=0;i<init.length();i++) {
			Character currChar=init.charAt(i);
			ArrayList characters =new ArrayList();
			if(currChar == 'R' || currChar == 'L') {
				characters.add(currChar);
				
			}
			currPosit.put(i, characters);
		}
		}
		else {
			HashMap<Integer,ArrayList<Character>> nextPosit=new HashMap<Integer,ArrayList<Character>>();
			nextPosit=(HashMap<Integer, ArrayList<Character>>) currPosit.clone();
			nextPosit.replaceAll((key,oldValue)->new ArrayList());
			Iterator iter= currPosit.keySet().iterator();
		    while(iter.hasNext()) {
		    	Integer currIndex=(Integer) iter.next();
		    	ArrayList<Character> currIndexValues=new ArrayList<Character>();
		    	currIndexValues= currPosit.get(currIndex);
		    	for(int i=0;i<currIndexValues.size();i++) {
		    		if(currIndexValues.get(i)=='R') {
		    			Integer nextInt= currIndex+speed;
		    			if(nextInt < init.length()) {
		    				ArrayList<Character> cList= new ArrayList<Character>();
		    				cList=nextPosit.get(nextInt);
		    				cList.add(currIndexValues.get(i));
		    				nextPosit.put(nextInt, cList);
		    			}
		    			
		    		}
		    		else if(currIndexValues.get(i)=='L') {
		    			Integer nextInt= currIndex-speed;
		    			if(nextInt >=0) {
		    				ArrayList<Character> cList= new ArrayList<Character>();
		    				cList=nextPosit.get(nextInt);
		    				cList.add(currIndexValues.get(i));
		    				nextPosit.put(nextInt, cList);
		    			}
		    		}
		    	}
		    }
		    currPosit.clear();
		    currPosit.putAll(nextPosit);
		}
		output=UpdateOutput(output,currPosit);
		empty= CheckEmpty(currPosit);
		time++;
	}
	
	String[] outputStringArr= new String[output.size()];
	outputStringArr=createStringArray(output);	
	
	return outputStringArr;
	
}

private String[] createStringArray(ArrayList<String> output) {
	// TODO Auto-generated method stub
	String[] outputStringArr= new String[output.size()];
	for(int i=0;i<output.size();i++) {
		outputStringArr[i]=output.get(i);
	}
	return outputStringArr;
}

private ArrayList<String> UpdateOutput(ArrayList<String> output, HashMap<Integer, ArrayList<Character>> currPosit) {
	ArrayList<Integer> keyList= new ArrayList<Integer>();
	Iterator iter=currPosit.keySet().iterator();
	while(iter.hasNext()) {
	    keyList.add((Integer)iter.next());
	}
	Collections.sort(keyList);
	Iterator iterNew=keyList.iterator();
    String outString= new String();
	while(iterNew.hasNext()) {
		ArrayList<Character> currIndexValues=new ArrayList<Character>();
    	currIndexValues=currPosit.get(iterNew.next());
    	if(currIndexValues.isEmpty()) {
    		outString=outString+".";
    	}
    	else {
    		outString=outString+"X";
    	}
	}
	output.add(outString);
	return output;
}

private Boolean CheckEmpty(HashMap<Integer, ArrayList<Character>> currPosit) {
	int count=0;
    Iterator iter= currPosit.keySet().iterator();
    while(iter.hasNext()) {
    	ArrayList<Character> currIndexValues=new ArrayList<Character>();
    	currIndexValues=currPosit.get(iter.next());
    	if(currIndexValues.isEmpty()) {
    		count++;
    	}
    }
    if(count== currPosit.keySet().size()) {
    	return true;
    }
    return false;
}
}
