package day08;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {

	public static void main(String[] args){
		try {
			Scanner in = new Scanner(new File("src/day08/input.txt"));
			int pre = 0;
			int post1 = 0;
			int post2 = 0;
			while(in.hasNextLine()){
				String line = in.nextLine();
				pre += line.length();

				String p1 = parse(line);
				post1 += p1.length();

				String p2 = encode(line);
				post2 += p2.length();
			}

			System.out.println("Part 1 - Success");
			System.out.println("Answer\t" + (pre-post1) + "\n");

			System.out.println("Part 2 - Success");
			System.out.println("Answer\t" + (post2-pre));
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static String parse(String in){
		// Does not actually parses the input, just replaces the escaped characters with random values

		// Remove start and end
		String result = in.substring(1, in.length()-1);

		// Escapes
		result = result.replaceAll("\\\\\"", ")");
		result = result.replaceAll("[\\\\]{2}", "(");

		// Hexadecimals
		Pattern hexadecimals = Pattern.compile("\\\\x([a-z0-9]{2})");
		result = hexadecimals.matcher(result).replaceAll(mr -> hexadecimalConverter(mr));

		return result;
	}

	public static String encode(String in){
		// Backslash
		String result = in.replaceAll("\\\\", "BS");
		// Double Quotes
		result = result.replaceAll("\\\"", "QQ");
		// Start and end
		result = "\"" + result + "\"";
		return result;
	}

	public static String hexadecimalConverter(MatchResult m) {
		/*String in = m.group(1);
		int num = Integer.parseInt(in, 16);
		char c = (char) num;
		return "" + c;*/
		return "H";
	}
}
