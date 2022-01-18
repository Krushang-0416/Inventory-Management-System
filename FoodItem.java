
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
 * This is a parent class for the food items that are present in an inventory.
 * It has some common fields between varieties of products that are stored in
 * inventory.
 * 
 * @author Krushang Patel
 * @version Java 11
 *
 */
public class FoodItem {
	/**
	 * This field contains the code of an item
	 */
	private int itemCode; /* This field contains the code of an item */
	/**
	 * This field contains name of an item
	 */
	private String itemName; /* This field contains name of an item */
	/**
	 * This field contains item price of the product
	 */
	private float itemPrice; /* This field contains item price of the product */
	/**
	 * This field contains item cost of the product
	 */
	private float itemCost; /* This field contains item cost of the product */
	/**
	 * This field contains the quantity of item currently present in inventory
	 */
	private int itemQuantityInStock; /* This field contains the quantity of item currently present in inventory */

	/*
	 * This is a default constructor used for instantiation and inheritence in
	 * further class.
	 */
	/**
	 * This is a default constructor used for instantiation and inheritence in
	 * further class.
	 */
	public FoodItem() {
		itemCode = 0;
		itemName = "";
		itemPrice = 0.0f;
		itemCost = 0.0f;
		itemQuantityInStock = 0;
	}

	/*
	 * This method returns the item code so it may be used to compare objects
	 */
	/**
	 * This method is used to return the item code so it may be used to compare
	 * objects
	 *
	 * @return Value of itemCode data member
	 */
	public int getItemCode() {
		return itemCode;
	}

	/*
	 * Reads from the Scanner object passed in and fills the data member fields of
	 * the class with valid data; Method returns true if program successfully reads
	 * in all fields, otherwise returns false
	 */
	/**
	 * This method is used to read the scanner object passed in and fills the data
	 * member fields of the class with valid data.
	 * 
	 * @param scanner  It is the scanner object used for user's input.
	 * @param fromFile When code is true, the console output is not performed as
	 *                 existed in file.
	 * @return It returns true if the details are added successfully or else it
	 *         returns a false.
	 */
	public boolean addItem(Scanner scanner, boolean fromFile) {
		boolean valid = false;

		if (!fromFile)
			System.out.print("Enter the name for the item: ");
		itemName = scanner.next();

		/*
		 * Inputs quantity of the item. validating it to be only positive integers.
		 */
		while (!valid) {
			if (!fromFile)
				System.out.print("Enter the quantity for the item: ");
			if (scanner.hasNextInt()) {
				itemQuantityInStock = scanner.nextInt();
				if (itemQuantityInStock < 0) {
					valid = false;
					System.out.println("Invalid input");
					itemQuantityInStock = 0;
				} else
					valid = true;
			} else {
				System.out.println("Invalid input");
				scanner.next();
				valid = false;
			}
		}

		/*
		 * Inputs cost of the item. validating it to be only positive decimal.
		 */
		valid = false;
		while (!valid) {
			if (!fromFile)
				System.out.print("Enter the cost of the item: ");
			if (scanner.hasNextFloat()) {
				itemCost = scanner.nextFloat();
				if (itemCost < 0) {
					valid = false;
					System.out.println("Invalid input");
					itemCost = 0;
				} else
					valid = true;
			} else {
				System.out.println("Invalid input");
				scanner.next();
				valid = false;
			}
		}

		/*
		 * Inputs selling price of the item. validating it to be only positive decimal.
		 */
		valid = false;
		while (!valid) {
			if (!fromFile)
				System.out.print("Enter the sales price of the item: ");
			if (scanner.hasNextFloat()) {
				itemPrice = scanner.nextFloat();
				if (itemPrice < 0) {
					valid = false;
					System.out.println("Invalid input");
					itemPrice = 0;
				} else
					valid = true;
			} else {
				System.out.println("Invalid input");
				scanner.next();
				valid = false;
			}
		}
		return true;
	}

	/*
	 * Reads a valid itemCode from the Scanner object and returns true/false if
	 * successful
	 */
	/**
	 * Reads a valid itemCode from the Scanner object and returns true/false if
	 * successful
	 * 
	 * @param scanner  - Scanner to use for input
	 * @param fromFile - When true, we will not display the questions to the console
	 * @return <code>true</code> if code was populated, <code>false</code> otherwise
	 */
	public boolean inputCode(Scanner scanner, boolean fromFile) {
		boolean validInput = false;
		while (!validInput) {
			if (!fromFile)
				System.out.print("Enter the code for the item: ");
			if (scanner.hasNextInt()) {
				itemCode = scanner.nextInt();
				validInput = true;
			} else {
				System.out.println("Invalid code");
				// Clear the invalid input
				scanner.next();
			}
		}
		return validInput;
	}

	/*
	 * This method is used to format the output items to the formatter that is
	 * specified. It is used to pass the details into the file.
	 */
	/**
	 * Outputs this object to the formatter specified
	 * 
	 * @param writer - Formatter to output to
	 */
	public void outputItem(Formatter writer) {
		writer.format("%d\n", itemCode);
		writer.format("%s\n", itemName);
		writer.format("%d\n", itemQuantityInStock);
		writer.format("%.2f\n", itemCost);
		writer.format("%.2f\n", itemPrice);
	}

	/*
	 * Method returns true if the itemCode of the object being acted on and the item
	 * object parameter are the same value
	 */
	/**
	 * This method returns true if the itemCode of the object being acted on and the
	 * item object parameter are the same value
	 * 
	 * @param item It represents the item object of FoodItem Class, to compare the
	 *             item code
	 * @return It returns true if item code is equal, else returns false.
	 */
	public boolean isEqual(FoodItem item) {
		return itemCode == item.itemCode;
	}

	/*
	 * Updates the quantity field by amount (note amount could be positive or
	 * negative); Method returns true if successful, otherwise returns false
	 * itemQuantityInStock field can never be less than 0.
	 * 
	 */
	/**
	 * The method updates the quantity field by amount. Amount can be positive or
	 * negative on basis of selling (negative) conditions or buying conditions
	 * (positive).
	 * 
	 * @param amount It is the number of quantity by which instock - quantity should
	 *               be updated with either positively or negatively.
	 * @return It returns true if item is successful with a valid updation else
	 *         return false
	 */
	public boolean updateItem(int amount) {
		// If you are removing stock, then check that we have enough stock
		if (amount < 0 && itemQuantityInStock < (amount * -1)) {
			return false;
		}
		/*
		 * It is quantity + amount, but amount sign will change according the sign
		 * passed, can be positive or negative
		 */
		itemQuantityInStock += amount;
		return true;
	}

	/*
	 * Displays the all data members in the class
	 */
	/**
	 * Displays the all data members in the class
	 */
	public String toString() {
		return "Item: " + itemCode + " " + itemName + " " + itemQuantityInStock + " price: $"
				+ String.format("%.2f", itemPrice) + " cost: $" + String.format("%.2f", itemCost);
	}

}
