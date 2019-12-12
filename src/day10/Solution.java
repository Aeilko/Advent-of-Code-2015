package day10;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {

	public static final String INPUT = "1113122113";

	public static void main(String[] args){
		String part1 = "";
		String cur = INPUT;
		for(int i = 0; i < 50; i++){
			System.out.println("Round " + i);
			cur = transform(cur);
			if(i == 39)
				part1 = cur;
		}

		System.out.println("Part 1 - Success");
		System.out.println("Answer\t" + part1.length() + "\n");

		System.out.println("Part 2 - Success");
		System.out.println("Answer\t" + cur.length());
	}

	public static String transform(String in){
		String result = "";

		Pattern pat = Pattern.compile("(\\d)\\1*");
		Matcher m = pat.matcher(in);
		result = m.replaceAll(mr -> replace(mr));

		return result;
	}

	public static String replace(MatchResult mr){
		String t = mr.group();
		return "" + t.length() + t.charAt(0);
	}
}
