package Providers;

public class Permute_Main {

	public static void main(String[] args) {
		int[][] seqArrPermutations = Permute.permuteTo2D_Array(0, 4);
		System.out.printf("%s%n",  arraysToString(seqArrPermutations));
	}
	
	public static void sampleFeatures() {
		// Generate and print all permutations of a sequential array
		Permute.permuteToPrint(2);
		
		// Generate and print all permutations of your own array
		Permute.permuteToPrint(new int[] {14, 7, 99});
		
		// Save all permutations of a sequential array to a 2D array
		int[][] seqArrPermutations = Permute.permuteTo2D_Array(3);
		System.out.printf("%s%n",  arraysToString(seqArrPermutations));
		
		// Save all permutations of a custom array to a 2D array
		int[][] customArrPermutations = Permute.permuteTo2D_Array(new int[] {3, 5, 9});
		System.out.printf("%s%n", arraysToString(customArrPermutations));
		
		// Save all permutations of a sequential array to a 2D array
		String seqStrPermutations = Permute.permuteToString(4);
		System.out.printf("%s%n", seqStrPermutations);
		
		// Save all permutations of a custom array to a 2D array
		String customStrPermutations = Permute.permuteToString(new int[] {7, 9, 11, 13});
		System.out.printf("%s%n", customStrPermutations);
	}
	
	public static String arraysToString(int[][] arrs) {
		String str = "";
		for (int row = 0; row < arrs.length; row++) {
			str = str + arrayToString(arrs[row]);
			str = str + String.format("%n");
		}
		return str;
	}
	
	public static String arrayToString(int[] arr) {
			String str = "[";
			for (int i = 0; i < arr.length; i++) {
				str = str + arr[i] + ", ";
			}
			if (str.length() > 1) {
				str = str.substring(0, str.length() - 2);
			}
			str = str + "]";
			return str;

	}

}
