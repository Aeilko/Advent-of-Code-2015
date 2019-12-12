package day09;

import utils.Permutations;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {

	public static final String[] INPUT = {"Faerun to Norrath = 129","Faerun to Tristram = 58","Faerun to AlphaCentauri = 13","Faerun to Arbre = 24","Faerun to Snowdin = 60","Faerun to Tambi = 71","Faerun to Straylight = 67","Norrath to Tristram = 142","Norrath to AlphaCentauri = 15","Norrath to Arbre = 135","Norrath to Snowdin = 75","Norrath to Tambi = 82","Norrath to Straylight = 54","Tristram to AlphaCentauri = 118","Tristram to Arbre = 122","Tristram to Snowdin = 103","Tristram to Tambi = 49","Tristram to Straylight = 97","AlphaCentauri to Arbre = 116","AlphaCentauri to Snowdin = 12","AlphaCentauri to Tambi = 18","AlphaCentauri to Straylight = 91","Arbre to Snowdin = 129","Arbre to Tambi = 53","Arbre to Straylight = 40","Snowdin to Tambi = 15","Snowdin to Straylight = 99","Tambi to Straylight = 70"};

	public static void main(String[] args){
		Pattern pat = Pattern.compile("([a-zA-Z]+) to ([a-zA-Z]+) = ([0-9]+)");

		ArrayList<String> locations = new ArrayList<>();
		TreeMap<String, TreeMap<String, Integer>> distances = new TreeMap<>();

		// Parse input
		for(String s: INPUT){
			Matcher m = pat.matcher(s);
			m.find();
			String from = m.group(1);
			String to = m.group(2);
			int d = Integer.parseInt(m.group(3));
			if(!locations.contains(from))
				locations.add(from);
			if(!locations.contains(to))
				locations.add(to);

			if(distances.containsKey(from))
				distances.get(from).put(to, d);
			else{
				TreeMap<String, Integer> tmp = new TreeMap<>();
				tmp.put(to, d);
				distances.put(from, tmp);
			}

			if(distances.containsKey(to))
				distances.get(to).put(from, d);
			else{
				TreeMap<String, Integer> tmp = new TreeMap<>();
				tmp.put(from, d);
				distances.put(to, tmp);
			}
		}

		String[] loc = new String[locations.size()];
		for(int i = 0; i < locations.size(); i++)
			loc[i] = locations.get(i);
		ArrayList<String[]> perms = Permutations.getAll(loc);

		int shortest = Integer.MAX_VALUE;
		int longest = 0;
		for(String[] route: perms){
			int d = getDistance(route, distances);
			if(d < shortest)
				shortest = d;
			if(d > longest)
				longest = d;
		}

		System.out.println("Part 1 - Success");
		System.out.println("Answer\t" + shortest + "\n");

		System.out.println("Part 2 - Success");
		System.out.println("Answer\t" + longest);
	}

	private static int getDistance(String[] route, TreeMap<String, TreeMap<String, Integer>> distance){
		int result = 0;

		for(int i = 0; i < route.length-1; i++){
			result += distance.get(route[i]).get(route[i+1]);
		}

		return result;
	}
}
