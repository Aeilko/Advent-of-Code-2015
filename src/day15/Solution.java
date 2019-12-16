package day15;

import utils.ArrayUtils;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {

	public static final String[] INPUT = {"Sugar: capacity 3, durability 0, flavor 0, texture -3, calories 2","Sprinkles: capacity -3, durability 3, flavor 0, texture 0, calories 9","Candy: capacity -1, durability 0, flavor 4, texture 0, calories 1","Chocolate: capacity 0, durability 0, flavor -2, texture 2, calories 8"};

	public static void main(String[] args){
		Ingredient[] items = new Ingredient[INPUT.length];
		for(int i = 0; i < INPUT.length; i++){
			items[i] = new Ingredient(INPUT[i]);
		}

		ArrayList<int[]> perms = getPermutationsOf(0, 100, 0, items);
		int result = 0;
		for(int[] ia: perms){
			int s = calculateScore(ia);
			if(s > result)
				result = s;
		}

		System.out.println("Part 1 - Success");
		System.out.println("Answer\t" + result + "\n");

		result = 0;
		for(int[] ia: perms){
			if(ia[4] == 500){
				int s = calculateScore(ia);
				if(s > result)
					result = s;
			}
		}

		System.out.println("Part 2 - Success");
		System.out.println("Answer\t" + result);

	}

	public static int calculateScore(int[] stats){
		int result = 1;
		for(int i = 0; i < 4; i++){
			if(stats[i] < 1)
				result *= 0;
			else
				result *= stats[i];
		}
		return result;
	}

	public static ArrayList<int[]> getPermutationsOf(int start, int end, int index, Ingredient[] items){
		ArrayList<int[]> result = new ArrayList<>();
		Ingredient it = items[index];

		if(index == items.length-1){
			result.add(it.getStatsMultiple(end-start));
		}
		else{
			for(int i = start; i < end; i++){
				ArrayList<int[]> tmp = getPermutationsOf(i, end, index+1, items);
				for(int[] ia: tmp){
					result.add(ArrayUtils.add(it.getStatsMultiple(i-start), ia));
				}
			}
		}

		return result;
	}
}

class Ingredient{

	private String name;
	private int[] stats;

	public Ingredient(String s){
		Pattern pat = Pattern.compile("([A-Za-z]+): capacity (-?[0-9]+), durability (-?[0-9]+), flavor (-?[0-9]+), texture (-?[0-9]+), calories (-?[0-9]+)");
		Matcher m = pat.matcher(s);
		m.find();
		this.name = m.group(1);
		this.stats = new int[5];
		for(int i = 0; i < 5; i++)
			stats[i] = Integer.parseInt(m.group(i+2));
	}

	public String getName() {
		return name;
	}

	public int[] getStats(){
		return this.stats;
	}

	public int[] getStatsMultiple(int k){
		int[] s = new int[this.stats.length];
		for(int i = 0; i < this.stats.length; i++){
			s[i] = k*this.stats[i];
		}
		return s;
	}

	public int getStat(int i){
		return this.stats[i];
	}

	@Override
	public String toString() {
		return this.name + " " + this.stats[0] + " " + this.stats[1] + " " + this.stats[2] + " " + this.stats[3] + " " + this.stats[4];
	}
}
