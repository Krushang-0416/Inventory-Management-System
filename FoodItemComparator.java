/*
 * CET-CS Academic Level 3 
 * Declaration : I declare that this is my own original work and is free from Plagiarism.
 * Student Name : Krushang Patel 
 * Student Id : 041-021-848 
 * Section# : 302 
 * Course : CST8130 - Data Structures 
 * Professor : James Mwangi Phd.
 */

import java.util.Comparator;

/**
 * This class is used to compare the objects of type FoodItem
 * 
 * @author Krushang Patel
 * @version Java 11
 *
 */
public class FoodItemComparator implements Comparator<FoodItem> {

	/*
	 * Compares its two arguments for order. Returns a negative integer, zero, or a
	 * positive integer as the first argument is less than, equal to, or greater
	 * than the second.
	 */
	/**
	 * Compares its two arguments for order. Returns a negative integer, zero, or a
	 * positive integer as the first argument is less than, equal to, or greater
	 * than the second.
	 */
	@Override
	public int compare(FoodItem o1, FoodItem o2) {
		// Compare by item code.
		return o1.getItemCode() - o2.getItemCode();
	}
}
