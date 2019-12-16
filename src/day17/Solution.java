package day17;

import utils.ArrayUtils;

import java.util.ArrayList;

public class Solution {

	public static final int[] INPUT = {50,44,11,49,42,46,18,32,26,40,21,7,18,43,10,47,36,24,22,40};

	public static void main(String[] args){
		ArrayList<int[]> perms = getPermutationsOf(150, 0, INPUT);
		int result = perms.size();

		System.out.println("Part 1 - Success");
		System.out.println("Answer\t" + result + "\n");

		int minNumberOfContainers = Integer.MAX_VALUE;
		int permCounter = 0;
		for(int[] ia: perms){
			int c = ArrayUtils.sum(ia);
			if(c < minNumberOfContainers){
				minNumberOfContainers = c;
				permCounter = 1;
			}
			else if(c == minNumberOfContainers){
				permCounter++;
			}
		}

		System.out.println("Part 2 - Success");
		System.out.println("Answer\t" + permCounter);
	}

	public static ArrayList<int[]> getPermutationsOf(int left, int index, int[] sizes){
		ArrayList<int[]> result = new ArrayList<>();
		int cont = sizes[index];

		if(index == sizes.length-1) {
			if (left == 0 || left == cont) {
				int[] r = new int[sizes.length];
				r[index] = left/cont;
				result.add(r);
			}
			else{
				result = null;
			}
		}
		else{
			// Cannot have more than 1 of each container
			for(int i = 0; i <= Math.min(left/cont, 1); i++){
				ArrayList<int[]> tmp = getPermutationsOf(left-(cont*i), index+1, sizes);
				if(tmp == null)
					continue;
				else {
					for (int[] ia: tmp) {
						ia[index] = i;
						result.add(ia);
					}
				}
			}
		}

		if(result == null || result.size() == 0)
			return null;
		else
			return result;
	}
}
