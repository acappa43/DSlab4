package recursion;

import java.util.Random;

public class InsertionSort {
	
	private static final int NUMINT = 10;  // Amount of integers to sort
	private static final int MAXINT = 50;  // Largest integer to use

	/**
	 * Creates the initial array with random integers, per class constants.
	 * 
	 * @return  Array with NUMINT random integers
	 */
	public static int[] getInitArray() {
		int[] vals = new int[NUMINT];
		Random rand = new Random();
		for (int i = 0; i < vals.length; i++)
			vals[i] = rand.nextInt(MAXINT);
		return vals;
	}

	/**
	 * Print array contents using an auxiliary recursive method.
	 * 
	 * @param values  Array of integer values to be printed
	 */
	public static void printArray(int[] values) {
		recPrintArray(values, 0, values.length - 1);
		System.out.print("\n");
	}

	/**
	 * Recursive auxiliary method to print array contents.
	 * 
	 * @param values  Array of integer values to be printed
	 * @param first   Index of first value to be printed
	 * @param last    Index of last value to be printed
	 */
	public static void recPrintArray(int[] values, int first, int last) {
		if (first == last)
			System.out.print(values[first] + " ");
		else {
			int mid = first + (last - first) / 2;
			recPrintArray(values, first, mid);
			recPrintArray(values, mid + 1, last);
		}
			
	}

	/**
	 * Perform Insertion Sort on an array of integers,
	 * using an auxiliary recursive method.
	 * 
	 * @param values  Array of integer values to be sorted
	 */
	public static void insertionSort(int[] values) {
		recInsertionSort(values, values.length - 1);
	}

	/**
	 * Recursive auxiliary method to perform Insertion Sort on an array of integers.
	 * The array is only sorted up to (and including) a specified index.
	 * 
	 * @param values  Array of integer values to be sorted
	 * @param last    Last index of the array to be sorted (from 0 up to last)
	 */
	public static void recInsertionSort(int[] values, int last) {
		int pos, valToInsert;
		if (last > 0) {
			/* First we recursively sort the elements before last */
			recInsertionSort(values, last - 1);
			/* Now move element at index last into its rightful position. */
			valToInsert = values[last];
			/* Compare valToInsert with values in the sorted part of the array.
			 * As we find values that are bigger, "shift" them one position
			 * further down the array, until we find the right position for
			 * valToInsert.
			 */
			for (pos = last - 1; pos >= 0 && values[pos] > valToInsert; pos--)
				values[pos + 1] = values[pos];
			values[pos + 1] = valToInsert;
		}
		// else... the first element by itself is already sorted
	}

	public static void main(String[] args) {
		int[] values;

		values = getInitArray();
		System.out.println("Array before sorting: ");
		printArray(values);

		insertionSort(values);
		System.out.println("Array after sorting: ");
		printArray(values);
	}

}