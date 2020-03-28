package recursion;

import java.util.Scanner;
import java.util.Stack;

/**
 * Tower of Hanoi
 * This program plays the game of Tower of Hanoi with a number of disks
 * specified by the user. The game solves itself recursively, showing
 * the user each move along the way.
 * 
 * @author Juan O. López
 */
public class TowerOfHanoiSimple {

	@SuppressWarnings("unchecked")
	public static void towerOfHanoi(int numOfDisks) {
		Stack<Integer>[] towers = new Stack[3];

		/* Create the towers */
		for (int i = 0; i < towers.length; i++)
			towers[i] = new Stack<Integer>();

		/* Add the initial disks */
		for (int disk = numOfDisks; disk >= 1; disk--)
			towers[0].push(disk);

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
	private static void moveDisks(Stack<Integer>[] towers, int fromTower, int toTower, int tmpTower, int numDisks) {
		if (numDisks == 1) { // If only disk, just move it
			int diskNum = towers[fromTower].pop();
			towers[toTower].push(diskNum);
			System.out.printf("Disk %d: Tower %d -> Tower %d\n", diskNum, (fromTower+1), (toTower+1));
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
	

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		System.out.print("Enter number of disks: ");
		int num = reader.nextInt();
		reader.close();
		if (1 <= num)
			towerOfHanoi(num);
		else
			System.out.println("Invalid number!");
	}

}