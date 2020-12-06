package day19;

import utils.ArrayUtils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
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
	}

	// Dont replace all, find all possibilities each time replacing just one, and than calling the update again.
	public String replaceAll(String in, Map<String, String> replaces){
		StringBuilder sb = new StringBuilder();

		outer: for(int i = 0; i < in.length(); i++){
			for(String k: replaces.keySet()){
				if(in.startsWith(k, i)){
					sb.append(replaces.get(k));
					i += replaces.get(k).length()-1;
					continue outer;
				}
			}
			// Only reached if this is not part of a key, so just leave it
			sb.append(in.charAt(i));
		}

		return sb.toString();
	}
}
