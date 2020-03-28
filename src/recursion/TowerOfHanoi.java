package recursion;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 * Tower of Hanoi
 * This program plays the game of Tower of Hanoi with a number of disks
 * specified by the user (with a maximum determined by class constants).
 * The game solves itself recursively, showing the user each move along
 * the way.
 * The "simple" version uses stacks, but this version uses lists to
 * simulate stacks, because this version prints a visual representation
 * of the stacks after each move.  Using stacks would cause all stacks
 * to be emptied when printing the towers after each move.  Although
 * they could be stored in temporary stacks as they're printed, and
 * then re-populated, lists are much more efficient since we never
 * actually remove anything while printing.
 * 
 * @author Juan O. López
 */
public class TowerOfHanoi {

	private static final int COLUMNWIDTH = 30;
	private static final int MAXNUMDISKS = (COLUMNWIDTH - 1) / 2;

	/**
	 * Play the game of Tower of Hanoi with the specified number of disks.
	 *  
	 * @param numOfDisks  Number of disks
	 */
	@SuppressWarnings("unchecked")
	public static void towerOfHanoi(int numOfDisks) {
		List<Integer>[] towers = new ArrayList[3];

		/* Create the towers */
		for (int i = 0; i < towers.length; i++)
			towers[i] = new ArrayList<Integer>();

		/* Add the initial disks */
		for (int disk = numOfDisks; disk >= 1; disk--)
			towers[0].add(disk); // "Push"

		printTowers(towers); // Show initial state of the towers before any moves

		/* Now just move them all to the third tower, using second as needed */
		moveDisks(towers, 0, 2, 1, numOfDisks);
	}

	
	/**
	 * Move disks from one tower (fromTower) to another (toTower),
	 * temporarily using the other tower (tmpTower) if needed.
	 * 
	 * @param towers     Array of towers
	 * @param fromTower  Index of tower to move disks from
	 * @param toTower    Index of tower to move disks to
	 * @param tmpTower   Index of tower to use to temporarily place disks
	 * @param numDisks   Amount of disks to be moved
	 */
	private static void moveDisks(List<Integer>[] towers, int fromTower, int toTower, int tmpTower, int numDisks) {
		if (numDisks == 1) { // If only disk, just move it
			int diskNum = towers[fromTower].remove(towers[fromTower].size() - 1); // "Pop"
			towers[toTower].add(diskNum); // "Push"
			printTowers(towers);
		}
		else {
			/* Recursive strategy:
			     1) Move top n-1 disks from fromTower to tmpTower
			     2) Move remaining disk from fromTower to toTower
			     3) Move n-1 disks from tmpTower to toTower */
			moveDisks(towers, fromTower, tmpTower, toTower, numDisks - 1);
			moveDisks(towers, fromTower, toTower, tmpTower, 1);
			moveDisks(towers, tmpTower, toTower, fromTower, numDisks - 1);
		}
	}
	

	/**
	 * Receives a disk number and formats it as a sequence of repeated
	 * digits, depending on the number.  For example, 1 generates "1", 
	 * 2 generates "222", 3 generates "33333", and so on.  This way, a
	 * "bigger disk" (larger number) will result in a longer string.
	 * Note: Resulting string will be a centered string of width COLUMWIDTH; 
	 *       results are unreliable if num > MAXNUMDISKS.
	 *
	 * @param num  Disk number (0 <= num <= MAXNUMDISKS)
	 * @return     String representing a disk as sequence of repeated digits.
	 */
	private static String formatDiskNumber(int num) {
		String disk;
		if (num == 0)
			/* We use '|' to represent a part of the tower with no disk covering it */
			return String.format("%-" + COLUMNWIDTH  + "s", String.format("%" + (COLUMNWIDTH / 2) + "c", '|'));

		disk = String.valueOf(Character.forDigit(num, 36)).toUpperCase();       // Convert num to digit base 36
		disk = String.format("%0" + (2 * num + 1) + "d", 0).replace("0", disk); // Consecutive sequence of digits
		return String.format("%-" + COLUMNWIDTH  + "s", String.format("%" + (COLUMNWIDTH + disk.length()) / 2 + "s", disk));
	}


	/**
	 * Prints a line of asterisks to help visually separate the different moves.
	 */
	private static void printSeparator() {
	    System.out.println(String.format("%0" + (COLUMNWIDTH * 3) + "d", 0).replace("0", "*"));
	}


	/**
	 * Prints a visual representation of the towers so that the user can
	 * better understand the moves being performed.
	 * 
	 * @param towers   Array of towers (represented as Lists)
	 */
	private static void printTowers(List<Integer>[] towers) {
	    /* To keep the look consistent, we always use the same amount of rows to
	     * display every move.  Determine the amount of disks and then calculate
	     * how high a tower would need to be if it had all of those disks. */
		int totalHeight = 0;
		for (int towerNum = 0; towerNum < towers.length; towerNum++)
			totalHeight += towers[towerNum].size();
	    /* Since the smaller disks are at the end of the lists but should appear
	     * at the top of the towers, we start at the end of the lists and work our
	     * way to the beginning, so that we may print from the top to the bottom. */
		for (int towerHeight = totalHeight - 1; towerHeight >= 0; towerHeight--) {
			/* For every tower, print the disk it has (if any) at this height */
			for (int towerNum = 0; towerNum < towers.length; towerNum++)
	            /* There will always be towers with empty space on top, since it's
	             * impossible for all towers to be full, so determine, for each
	             * tower, whether it has any disks at this level. */
	            if (towerHeight < towers[towerNum].size())
	                System.out.print(formatDiskNumber(towers[towerNum].get(towerHeight)));
	            else
	                System.out.print(formatDiskNumber(0));
	        System.out.print("\n");
		}
		printSeparator();
	}
	
	
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		System.out.print("Enter number of disks (up to " + MAXNUMDISKS + "): ");
		int num = reader.nextInt();
		reader.close();
		if (1 <= num && num <= MAXNUMDISKS)
			towerOfHanoi(num);
		else
			System.out.println("Invalid number!");
	}

}