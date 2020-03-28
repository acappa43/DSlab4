package recursion;

import java.util.Scanner;

public class Countdown {
	/**
	 * Counts down from a given positive integer.
	 * 
	 * @param integer  An integer > 0.
	 */
	public static void countDown(int integer) {
		System.out.println(integer);
		if (integer > 1)
			countDown(integer -1);
	}

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		System.out.print("Enter a positive integer: ");
		int num = reader.nextInt();
		reader.close();
		countDown(num);
	}

}