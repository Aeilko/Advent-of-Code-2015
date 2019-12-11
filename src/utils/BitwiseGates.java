package utils;

public class BitwiseGates {

	public static int AND(int x, int y){
		return (x & y);
	}

	public static int OR(int x, int y){
		return (x | y);
	}

	public static int LSHIFT(int x, int shift){
		return  (x << shift);
	}

	public static int RSHIFT(int x, int shift){
		return  (x >> shift);
	}

	public static int NOT(int x){
		return (~x);
	}
}
