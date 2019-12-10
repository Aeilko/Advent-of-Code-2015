package day04;

import utils.Crypto;

import java.util.Scanner;

public class Solution {

	public static final String INPUT = "bgvyzdsv";

	public static void main(String[] args){
		Scanner in = new Scanner(System.in);

		System.out.print("Input prefix: ");
		String input = in.nextLine();
		if(input.equals("def"))
			input = INPUT;

		int part1 = -1;
		int part2 = -1;

		for(int i = 0; i < Integer.MAX_VALUE; i++){
			byte[] md5 = Crypto.toMD5(input + i);
			if(md5[0] == 0 && md5[1] == 0){
				if(md5[2] == 0){
					part2 = i;
					break;
				}
				else if(md5[2] <= 16 && part1 == -1)
					part1 = i;
			}
		}

		System.out.println("Part 1 - Success");
		System.out.println("Answer\t" + part1 + "\n");

		System.out.println("Part2 - Success");
		System.out.println("Answer\t" + part2);
	}
}
