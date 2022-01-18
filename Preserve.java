/*
 * CET-CS Academic Level 3 
 * Declaration : I declare that this is my own original work and is free from Plagiarism.
 * Student Name : Krushang Patel 
 * Student Id : 041-021-848 
 * Section# : 302 
 * Course : CST8130 - Data Structures 
 * Professor : James Mwangi Phd.
 */

import java.util.Scanner;
import java.util.Formatter;

/**
 * This is a sub class for the preserves present in an inventory. It inherits
 * field from parent class and has an additional field called jarSize. *
 * 
 * @author Krushang Patel
 * @version Java 11
 *
 */
public class Preserve extends FoodItem {
	/*
	 * This field contains the size of jar in which preserved materials are present
	 */
	/**
	 * This field contains the size of jar in which preserved materials are present
	 */
	private int jarSize;

	/*
	 * This is a default constructor used for instantiation and polymorphism in
	 * further class.
	 */
	/**
	 * This is a default constructor used for instantiation and polymorphism in
	 * further class.
	 */
	public Preserve() {
		super();
		jarSize = 0;
	}

	/*
	 * This item is inherited from parent class with an implementation of new field
	 * to it.
	 */
	/**
	 * This method is used to add item details to an inventory. It is overridden by
	 * adding jar's size and is inherited from FoodItem Class.
	 */
	@Override
	public boolean addItem(Scanner scanner, boolean fromFile) {
		boolean valid = false;
		if (super.addItem(scanner, fromFile)) {
			// Input quantity
			while (!valid) {
				if (!fromFile)
					System.out.print("Enter the size of the jar in millilitres: ");
				if (scanner.hasNextInt()) {
					jarSize = scanner.nextInt();
					if (jarSize < 0) {
						valid = false;
						System.out.println("Invalid input");
						jarSize = 0;
					} else
						valid = true;
				} else {
					System.out.println("Invalid input");
					scanner.next();
					valid = false;
				}
			}
		}
		return true;
	}

	/*
	 * This method is inherited from parent class with an implementation of new
	 * field to it.
	 */
	/**
	 * This method is used to add item details to a file. It is overridden by adding
	 * jar size and is inherited from FoodItem Class.
	 */
	@Override
	public void outputItem(Formatter writer) {
		writer.format("p\n");
		super.outputItem(writer);
		writer.format("%d\n", jarSize);
	}

	/*
	 * Displays the all data members in the class
	 */
	/**
	 * Displays the all data members in the class
	 */
	@Override
	public String toString() {
		return super.toString() + " size: " + jarSize + "mL";
	}

}
