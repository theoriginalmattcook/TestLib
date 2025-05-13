package Providers;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;


class Permute_TestCase {

	@ParameterizedTest
	@MethodSource("permuteTo2D_Array_provider")
	@DisplayName("permuteTo2D_Array")
	public void permuteTo2D_Array_test(int inArrSize, int inArr2DSize_Expected) {
		System.out.printf("## Testing Array Size %d%n", inArrSize);

		System.out.printf("  # Tesing for Correct Number of Permutations%n");
		int[][] arrs = Permute.permuteTo2D_Array(inArrSize);
		System.out.printf("    > Expected Size => Actual Size: %d => %d%n", inArr2DSize_Expected, arrs.length);
		assertEquals(arrs.length, inArr2DSize_Expected);

		System.out.printf("  # Testing Array For Null Values%n", inArrSize);
		int nullCount = countNull(arrs);
		System.out.printf("    > Expected Null Count => Actual Null Count: %d => %d%n", 0, nullCount);
		assertEquals(0, nullCount);
		
		System.out.printf("  # Testing Array For Duplicate Permutations - Please Be Patient!!%n", inArrSize);
		int duplicatePermutationCount = countDuplicatePermutations(arrs, inArrSize);
		System.out.printf("    > Expected Duplicate Count => Actual Duplicate Permutation Count: %d => %d%n", 0, duplicatePermutationCount);
		assertEquals(0, duplicatePermutationCount);

	}

	public static Stream<Integer[]> permuteTo2D_Array_provider() {
		System.out.printf("%nTest permuteTo2D_Array:%n");
		return Stream.of(
				new Integer[] {Integer.valueOf(2), Integer.valueOf(factorial(2))}, 
				new Integer[] {Integer.valueOf(3), Integer.valueOf(factorial(3))},
				new Integer[] {Integer.valueOf(4), Integer.valueOf(factorial(4))}, 
				new Integer[] {Integer.valueOf(5), Integer.valueOf(factorial(5))}, 
				new Integer[] {Integer.valueOf(6), Integer.valueOf(factorial(6))},
				new Integer[] {Integer.valueOf(7), Integer.valueOf(factorial(7))},
				new Integer[] {Integer.valueOf(8), Integer.valueOf(factorial(8))}
				);
		}

	private static int countNull(int[][] arrs) {
		int nullCount = 0;
		boolean allZeros;
		for (int[] arr : arrs) {
			if (arr == null) {
				nullCount++;
			}
			allZeros = true;
			for (int member : arr) {
				allZeros = allZeros && (member == 0);
			}
			if (allZeros) {
				nullCount++;
			}
		}
		return nullCount;
	}

	private static int countDuplicatePermutations(int[][] arrs, int size) {
		int countDuplicates = 0;
		for (int[] arr1 : arrs) {
			for (int[] arr2 : arrs) {
				if(Arrays.equals(arr1, arr2)) {
					countDuplicates++;
				}
			}
		}
		
		// There should be one duplicate per row because each array will equal itself.
		countDuplicates = countDuplicates - factorial(size);
		return countDuplicates;
	}
	
	private static int factorial(int x) {
		int y = 1;
		for (int n = 1; n <= x; n++) {
			y = y * n;
		}
		return y;
	}
}
