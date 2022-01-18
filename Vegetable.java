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
 * This is a sub class for the vegetables present in an inventory. It inherits
 * field from parent class and has an additional field called farmName.
 * 
 * @author Krushang Patel
 * @version Java 11
 *
 */
public class Vegetable extends FoodItem {
	/*
	 * This field contains the name of farm name from where vegetable import takes
	 * place
	 */
	/**
	 * This field contains the name of farm name from where vegetable import takes
	 * place
	 */
	private String farmName;

	/*
	 * This is a default constructor used for instantiation and polymorphism in
	 * further class.
	 */
	/**
	 * This is a default constructor used for instantiation and polymorphism in
	 * further class.
	 */
	public Vegetable() {
		super();
		farmName = "";
	}

	/*
	 * This item is inherited from parent class with an implementation of new field
	 * to it.
	 */
	/**
	 * This method is used to add item details to an inventory. It is overridden by
	 * adding farm name and is inherited from FoodItem Class.
	 */
	@Override
	public boolean addItem(Scanner scanner, boolean fromFile) {
		if (super.addItem(scanner, fromFile)) {
			if (!fromFile)
				System.out.print("Enter the name of the farm supplier: ");
			farmName = scanner.next();
		}
		return true;
	}

	/*
	 * This method is inherited from parent class with an implementation of new
	 * field to it.
	 */
	/**
	 * This method is used to add item details to a file. It is overridden by adding
	 * farm name and is inherited from FoodItem Class.
	 */
	@Override
	public void outputItem(Formatter writer) {
		writer.format("v\n");
		super.outputItem(writer);
		writer.format("%s\n", farmName);
	}

	/*
	 * Displays the all data members in the class
	 */
	/**
	 * Displays the all data members in the class
	 */
	@Override
	public String toString() {
		return super.toString() + " farm supplier: " + farmName;
	}

}
