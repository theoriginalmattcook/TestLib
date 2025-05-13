package Providers;

import java.util.Arrays;

// Generates all permutations of;
// 		- a generated sequential array of integers
//		- a generated set of sequential arrays of integers between a max and min length
// 		- a provided array of integers
public class Permute {
	private static int[][] arrs;
	private static int verbosity = 0; // set to 0, 1, or 2 for console output detail.
	private static int curRow = 0;

	public static int[][] permuteTo2D_Array(int size) {
		permute(size);
		return arrs;
	}

	public static int[][] permuteTo2D_Array(int minSize, int maxSize) {
		permute(minSize, maxSize);
		return arrs;
	}
	
	public static int[][] permuteTo2D_Array(int[] arr) {
		permute(arr);
		return arrs;
	}

	public static void permuteToPrint(int size) {
		String str = permuteToString(size);
		System.out.printf("%s%n", str);
	}

	public static void permuteToPrint(int[] arr) {
		String str = permuteToString(arr);
		System.out.printf("%s%n", str);
	}

	public static String permuteToString(int size) {
		permute(size);
		String str = "";
		for (int row = 0; row < arrs.length; row++) {
			str = str + arrayToString(arrs[row]);
			str = str + String.format("%n");
		}
		return str;
	}

	public static String permuteToString(int[] arr) {
		permute(arr);
		String str = "";
		for (int row = 0; row < arrs.length; row++) {
			str = str + arrayToString(arrs[row]);
			str = str + String.format("%n");
		}
		return str;
	}

	// Generates 1 based sequential arrays of integers between lengths minSize and
	// maxSize
	// Then generates every permutation of each array
	// and stores them as rows of a 2D array
	// Example Input : minSize = 1, maxSize = 2
	// Example Output : arrs = { {1}, {1, 2}, {2, 1} }
	public static void permute(int minSize, int maxSize) {
		// JAVA BUG?: Setting curRow = 0 is required because assigning zero at the
		// class level declaration
		// does not work in consecutive runs.
		curRow = 0;
		int rows = 0;
		if (minSize >= maxSize) {
			System.out.printf("ERROR: Permute maxSize must be greater than minSize .");
			System.exit(11);
		}

		if (minSize < 0) {
			System.out.printf("ERROR: Permute size must be greater than or equal to 0.");
			System.exit(12);
		}

		for (int curSize = minSize; curSize <= maxSize; curSize++) {
			rows = rows + factorial(curSize);
		}

		arrs = new int[rows][];

		for (int size = minSize; size <= maxSize; size++) {
			if (size == 0) {
				arrs[curRow] = new int[] {};
				curRow++;
			} else if (size == 1) {
				arrs[curRow] = new int[] { 1 };
				curRow++;
			} else {
				int[] baseArray = genSequentialArray(size);
				verbose(verbosity, 1, String.format("Base Array:%n%s%n%n", arrayToString(baseArray)));
				genPermutations_recursive(baseArray, 0, 0);
			}
		}

	}

	// Generates a 1 based sequential array of integers of size,
	// then generates all permutations of that array
	// and stores them in a 2d array.
	// Example Input : size = 2
	// Example Output : arrs = { {1, 2}, {2, 1} }
	private static void permute(int size) {
		// JAVA BUG?: Setting curRow = 0 is required because assigning zero at the
		// class level declaration
		// does not work in consecutive runs.
		curRow = 0;
		if (size < 0) {
			System.out.printf("ERROR: Permute size must be greater than or equal to 0.");
			System.exit(1);
		} else if (size == 0) {
			arrs = new int[1][1];
			return;
		} else if (size == 1) {
			arrs = new int[1][1];
			arrs[0][0] = 1;
			return;
		}

		int rows = factorial(size);
		arrs = new int[rows][size];
		int[] baseArray = genSequentialArray(size);
		verbose(verbosity, 1, String.format("Base Array:%n%s%n%n", arrayToString(baseArray)));
		genPermutations_recursive(baseArray, 0, 0);
	}

	// Generates all permutations of the provided array of integers
	// and stores them in a 2d array.
	// Example Input : arr = {5, 9}
	// Example Output : arrs = { {5, 9}, {9, 5} }
	private static void permute(int[] arr) {
		// JAVA BUG?: Setting curRow = 0 is required because assigning zero at the
		// class level declaration
		// does not work in consecutive runs.
		curRow = 0;
		if (arr.length == 0) {
			arrs = new int[1][1];
			return;
		} else if (arr.length == 1) {
			arrs = new int[1][1];
			arrs[0][0] = arr[0];
		}
		int rows = factorial(arr.length);
		arrs = new int[rows][arr.length];
		verbose(verbosity, 1, String.format("Base Array:%n%s%n%n", arrayToString(arr)));
		genPermutations_recursive(arr, 0, 0);
	}

	// Recursively generates all permutations of the provided array
	// using two moving pointers to swap members.
	private static void genPermutations_recursive(int[] arr, int index1, int row) {
		int[] arrNew = new int[arr.length];
		if (index1 < arr.length - 1) {
			for (int index2 = index1; index2 < arr.length; index2++) {
				if (index1 != index2 || index1 == 0) {
					arrNew = swapIndex(arr, index1, index2);
					arrs[curRow] = arrNew;
					curRow = curRow + 1;
				}
				genPermutations_recursive(swapIndex(arr, index1, index2), (index1 + 1), 0);
			}
		}

	}

	private static int[] swapIndex(int[] arr, int index1, int index2) {
		int[] arrNew = Arrays.copyOf(arr, arr.length);
		int hold = arrNew[index1];
		arrNew[index1] = arrNew[index2];
		arrNew[index2] = hold;
		return arrNew;
	}

	private static int[] genSequentialArray(int size) {
		int[] baseArray = new int[size];
		for (int n = 0; n < size; n++) {
			baseArray[n] = n + 1;
		}
		return baseArray;
	}

	private static void verbose(int verbosity, int level, String str) {
		if (verbosity >= level) {
			System.out.printf("%s", str);
		}
	}

	private static String arrayToString(int[] arr) {
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

	private static int factorial(int x) {
		if(x < 0) {
			System.out.printf("ERROR: factorial() paramater must be greater than zero.");
			System.exit(1);;
		}
		int y = 1;
		for (int n = 1; n <= x; n++) {
			y = y * n;
		}
		return y;
	}

}
