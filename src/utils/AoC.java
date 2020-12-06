package utils;

public class AoC {

	public static void main(String[] args){
		stringLinesToArray();
	}

	public static void stringLinesToArray(){
		String[] in = LinesToArray.split("\n");
		String res = String.join("\",\"", in);
		System.out.println("{\"" + res + "\"}");
	}

	public static final String LinesToArray = "Al => ThF\n" +
			"Al => ThRnFAr\n" +
			"B => BCa\n" +
			"B => TiB\n" +
			"B => TiRnFAr\n" +
			"Ca => CaCa\n" +
			"Ca => PB\n" +
			"Ca => PRnFAr\n" +
			"Ca => SiRnFYFAr\n" +
			"Ca => SiRnMgAr\n" +
			"Ca => SiTh\n" +
			"F => CaF\n" +
			"F => PMg\n" +
			"F => SiAl\n" +
			"H => CRnAlAr\n" +
			"H => CRnFYFYFAr\n" +
			"H => CRnFYMgAr\n" +
			"H => CRnMgYFAr\n" +
			"H => HCa\n" +
			"H => NRnFYFAr\n" +
			"H => NRnMgAr\n" +
			"H => NTh\n" +
			"H => OB\n" +
			"H => ORnFAr\n" +
			"Mg => BF\n" +
			"Mg => TiMg\n" +
			"N => CRnFAr\n" +
			"N => HSi\n" +
			"O => CRnFYFAr\n" +
			"O => CRnMgAr\n" +
			"O => HP\n" +
			"O => NRnFAr\n" +
			"O => OTi\n" +
			"P => CaP\n" +
			"P => PTi\n" +
			"P => SiRnFAr\n" +
			"Si => CaSi\n" +
			"Th => ThCa\n" +
			"Ti => BP\n" +
			"Ti => TiTi\n" +
			"e => HF\n" +
			"e => NAl\n" +
			"e => OMg";
}
