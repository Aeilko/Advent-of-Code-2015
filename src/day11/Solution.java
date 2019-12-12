package day11;

import java.util.TreeSet;

public class Solution {

	public static final String INPUT = "cqjxjnds";

	public static void main(String[] args){
		String cur = INPUT;
		while(!isLegal(cur)){
			cur = increaseString(cur);
		}
		System.out.println("Part 1 - Success");
		System.out.println("Answer\t" + cur + "\n");

		cur = increaseString(cur);
		while(!isLegal(cur)){
			cur = increaseString(cur);
		}
		System.out.println("Part 2 - Success");
		System.out.println("Answer\t" + cur);
	}

	public static String increaseString(String in){
		String result = "";
		char c = in.charAt(in.length()-1);
		if(c == 'z'){
			if(in.length() == 1)
				result = "aa";
			else
				result = increaseString(in.substring(0, in.length()-1)) + 'a';
		}
		else{
			c++;
			// Already skip some illegal characters to increase performance
			if(c == 'i' || c == 'l' || c == 'o')
				c++;
			result = in.substring(0, in.length()-1) + c;
		}

		return result;
	}

	public static boolean isLegal(String in){

		char prev = ' ';
		boolean prevIncreased = false;
		boolean increasingStraight = false;
		TreeSet<Character> doubles = new TreeSet<>();
		for(char c: in.toCharArray()){
			if(c == prev+1){
				if(prevIncreased)
					increasingStraight = true;
				prevIncreased = true;
			}
			else{
				prevIncreased = false;
				if (c == prev)
					doubles.add(c);
			}

			prev = c;
		}

		return (doubles.size() >= 2 && increasingStraight);
	}
}
