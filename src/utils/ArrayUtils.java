package utils;

public class ArrayUtils {

	public static byte[] append(byte[] a, byte[] b){
		byte[] result = new byte[a.length+b.length];

		for(int i = 0; i < a.length; i++){
			result[i] = a[i];
		}
		for(int i = 0; i < b.length; i++){
			result[a.length+i] = b[i];
		}

		return result;
	}

	public static String arrayToString(String[] in, int start, int end){
		String result = "";
		for(int i = start; i <= end; i++){
			result += in[i];
		}
		return result;
	}

	public static String arrayToString(String[] in){
		return arrayToString(in, 0, in.length-1);
	}

	public static int[] add(int[] a, int[] b){
		int[] result = new int[a.length];

		for(int i = 0; i < a.length; i++){
			result[i] = a[i]+b[i];
		}

		return result;
	}

	public static int sum(int[] a){
		int result = 0;

		for(int i: a){
			result += i;
		}

		return result;
	}

	public static void display(Object[] a){
		for(int i = 0; i < a.length; i++){
			System.out.println(a[i].toString());
		}
	}

	public static void display(byte[] a){
		for(int i = 0; i < a.length; i++){
			String s1 = String.format("%8s", Integer.toBinaryString(a[i] & 0xFF)).replace(' ', '0');
			System.out.println(s1);
		}
	}

	public static void display(int[] a){
		for(int i: a){
			System.out.print(i + ",");
		}
		System.out.print("\n");
	}
}
