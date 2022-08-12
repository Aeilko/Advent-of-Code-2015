package day19;

import utils.ArrayUtils;

import java.math.BigInteger;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
	public static final String[] RULES = {"Al => ThF","Al => ThRnFAr","B => BCa","B => TiB","B => TiRnFAr","Ca => CaCa","Ca => PB","Ca => PRnFAr","Ca => SiRnFYFAr","Ca => SiRnMgAr","Ca => SiTh","F => CaF","F => PMg","F => SiAl","H => CRnAlAr","H => CRnFYFYFAr","H => CRnFYMgAr","H => CRnMgYFAr","H => HCa","H => NRnFYFAr","H => NRnMgAr","H => NTh","H => OB","H => ORnFAr","Mg => BF","Mg => TiMg","N => CRnFAr","N => HSi","O => CRnFYFAr","O => CRnMgAr","O => HP","O => NRnFAr","O => OTi","P => CaP","P => PTi","P => SiRnFAr","Si => CaSi","Th => ThCa","Ti => BP","Ti => TiTi","e => HF","e => NAl","e => OMg"};

	public static final String INPUT = "CRnCaCaCaSiRnBPTiMgArSiRnSiRnMgArSiRnCaFArTiTiBSiThFYCaFArCaCaSiThCaPBSiThSiThCaCaPTiRnPBSiThRnFArArCaCaSiThCaSiThSiRnMgArCaPTiBPRnFArSiThCaSiRnFArBCaSiRnCaPRnFArPMgYCaFArCaPTiTiTiBPBSiThCaPTiBPBSiRnFArBPBSiRnCaFArBPRnSiRnFArRnSiRnBFArCaFArCaCaCaSiThSiThCaCaPBPTiTiRnFArCaPTiBSiAlArPBCaCaCaCaCaSiRnMgArCaSiThFArThCaSiThCaSiRnCaFYCaSiRnFYFArFArCaSiRnFYFArCaSiRnBPMgArSiThPRnFArCaSiRnFArTiRnSiRnFYFArCaSiRnBFArCaSiRnTiMgArSiThCaSiThCaFArPRnFArSiRnFArTiTiTiTiBCaCaSiRnCaCaFYFArSiThCaPTiBPTiBCaSiThSiRnMgArCaF";

	public static void main(String[] args){
		// Process input to replaceable parts
		Pattern pat = Pattern.compile("([A-Z][a-z]?)");
		Matcher m = pat.matcher(INPUT);
		ArrayList<String> in = new ArrayList<>();
		while(m.find())
			in.add(m.group());
		String[] input = new String[in.size()];
		for(int i = 0; i < in.size(); i++){
			input[i] = in.get(i);
		}

		// Process Rules
		TreeMap<String, TreeSet<String>> rules = new TreeMap<>();
		// Reversed rules for part 2
		TreeMap<String, String> reversedRules = new TreeMap<>();

		for(int i = 0; i < RULES.length; i++){
			String s = RULES[i];
			String[] sa = s.split(" => ");
			if(rules.containsKey(sa[0]))
				rules.get(sa[0]).add(sa[1]);
			else{
				TreeSet<String> tmp = new TreeSet<>();
				tmp.add(sa[1]);
				rules.put(sa[0], tmp);
			}

			reversedRules.put(sa[1], sa[0]);
		}

		// Part 1
		TreeSet<String> result = new TreeSet<>();
		for(int i = 0; i < input.length; i++){
			if(rules.containsKey(input[i])){
				String pre = ArrayUtils.arrayToString(input, 0, i-1);
				String post = ArrayUtils.arrayToString(input, i+1, input.length-1);
				for(String s: rules.get(input[i]))
					result.add(pre + s + post);
			}
		}

		System.out.println("Part 1 - Success");
		System.out.println("Answer\t" + result.size() + "\n");

		// Part 2
		String cur = INPUT;
		Set<String> keys = reversedRules.keySet();
		int result2 = 0;
		while(!cur.equals("e")){
			// Constantly apply the longest rule, and hope that's enough to get the answer
			String r = findLongestRule(cur, keys);
			cur = cur.replaceFirst(r, reversedRules.get(r));
			result2++;
		}

		System.out.println("Part 2 - Success");
		System.out.println("Answer\t" + result2 + "\n");
	}

	public static String findLongestRule(String line, Set<String> rules) {
		String longest = "";
		int longestLength = 0;
		for (String r: rules) {
			if (r.length() > longestLength && line.contains(r)){
				longest = r;
				longestLength = longest.length();
			}
		}
		return longest;
	}
}
