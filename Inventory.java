/*
 * CET-CS Academic Level 3 
 * Declaration : I declare that this is my own original work and is free from Plagiarism.
 * Student Name : Krushang Patel 
 * Student Id : 041-021-848 
 * Section# : 302 
 * Course : CST8130 - Data Structures 
 * Professor : James Mwangi Phd.
 */

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * This is a domain class where all the major functionalities are defined and
 * implemented. All the adding, updating, buying and selling functions are
 * defined here.
 * 
 * @author Krushang Patel
 * @version Java 11
 *
 */
public class Inventory {

	/**
	 * List of FoodItems that represents our inventory
	 */
	private ArrayList<FoodItem> inventory; /* List of FoodItems that represents our inventory */

	/*
	 * This is a default constructor used for instantiation and inheritence in
	 * further class.
	 */
	/**
	 * This is a default constructor used for instantiation and inheritence in
	 * further class.
	 */
	public Inventory() {
		inventory = new ArrayList<FoodItem>(20);
	}

	/*
	 * This method adds an item to the inventory array. It uses polymorphism to call
	 * addItem method in the correct derived FoodItem class for input of the fields
	 * of the FoodItem.
	 */
	/**
	 * This method reads from the Scanner object passed in and fills the data member
	 * fields of the class with valid data.
	 * 
	 * @param scanner  - Scanner to use for input
	 * @param fromFile - When code is true, the console output is not performed as
	 *                 existed in file.
	 * @return <code>true</code> if all data members were successfully populated,
	 *         <code>false</code> otherwise
	 */
	public boolean addItem(Scanner scanner, boolean fromFile) {
		boolean valid = false;
		FoodItem item = null;
		while (!valid) {
			if (!fromFile)
				System.out.print("Do you wish to add a fruit(f), vegetable(v) or a preserve(p)? ");
			if (scanner.hasNext(Pattern.compile("[fFvVpP]"))) {
				String choice = scanner.next();
				switch (choice.toLowerCase()) {
				case "f":
					item = new Fruit();
					break;
				case "v":
					item = new Vegetable();
					break;
				case "p":
					item = new Preserve();
					break;
				default: // Should not get here.
					item = new FoodItem();
					break;
				}
				valid = true;
			} else {
				System.out.println("Invalid entry");
				scanner.next();
				valid = false;
			}
		}
		if (item.inputCode(scanner, fromFile)) {
			if (alreadyExists(item) < 0) {
				if (item.addItem(scanner, fromFile)) {
					insertItem(item);
					return true;
				}
				return false;
			} else {
				System.out.println("Item code already exists");
				return false;
			}
		}
		return true;
	}

	/*
	 * Returns the index of a FoodItem in the inventory array with the same itemCode
	 * as the FoodItem object in the parameter list, else returns -1
	 */
	/**
	 * This method search for a food item and see if it is already stored in the
	 * inventory
	 * 
	 * @param item - FoodItem to look for
	 * @return - It returns an index of item if it is found, or else it returns -1.
	 */
	public int alreadyExists(FoodItem item) {
		return binarySearch(item.getItemCode(), 0, inventory.size() - 1);
	}

	/*
	 * This method is used to perform binary search in the inventory to search if
	 * the food item exists or not.
	 */
	/**
	 * This method is used to perform binary search in the inventory to search if
	 * the food item exists or not. The entire search performs upon the item code
	 * existing in the array list.
	 * 
	 * @param itemCode It represents the item code which is to be searched.
	 * @param start    It is the starting index for the binary search (i.e. the
	 *                 first index)
	 * @param end      It is the ending index for the binary search (i.e.
	 * @return It returns index of the found item or -1 if it is not found
	 */
	private int binarySearch(int itemCode, int start, int end) {
		int middle = (start + end) / 2;
		if (start > end)
			return -1;
		if (inventory.isEmpty())
			return -1;
		if (inventory.get(middle).getItemCode() == itemCode)
			return middle;
		if (inventory.get(middle).getItemCode() > itemCode)
			return binarySearch(itemCode, start, middle - 1);
		if (inventory.get(middle).getItemCode() < itemCode)
			return binarySearch(itemCode, middle + 1, end);
		return -1;
	}

	/*
	 * This method is used to insert an item into the inventory and maintains sort
	 * order
	 */
	/**
	 * This method is used to insert an item into the inventory and maintains sort
	 * order
	 * 
	 * @param item - Item to add to the inventory
	 */
	private void insertItem(FoodItem item) {
		// Used to compare FoodItems
		FoodItemComparator comp = new FoodItemComparator();
		for (int i = 0; i < inventory.size(); i++) {
			/*
			 * If the item is greater than the one in inventory, insert, insert here and
			 * push everything else out
			 */
			if (comp.compare(inventory.get(i), item) >= 0) {
				inventory.add(i, item);
				return;
			}
		}
		inventory.add(item);
	}

	/*
	 * This method is used to add food items (fruit, vegetables or preserves) to
	 * inventory from a file
	 */
	/**
	 * This method is used to add food items (fruit, vegetables or preserves) to
	 * inventory from a file
	 * 
	 * @param scanner - Scanner to use for input
	 */
	public void readFromFile(Scanner scanner) {
		try {
			System.out.print("Enter the filename to read from: ");
			String filename = scanner.next();

			File file = new File(filename);
			if (file.exists()) {
				Scanner fileReader = new Scanner(file);
				fileReader.useDelimiter("[\\r\\n]+");
				while (fileReader.hasNext()) {
					if (!addItem(fileReader, true)) {
						System.out.println("Error Encountered while reading the file, aborting...");
						break;
					}
				}
			} else {
				System.out.println("File Not Found, ignoring...");
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	/*
	 * This method is used to add food items (fruit, vegetables or preserves) to
	 * inventory from a file
	 */
	/**
	 * This method is used to saves the content of inventory to a file.
	 * 
	 * @param scanner - Scanner to use for input
	 */
	public void saveToFile(Scanner scanner) {
		try {
			System.out.print("Enter the filename to save to: ");
			String filename = scanner.next();

			File file = new File(filename);
			file.createNewFile();
			file.setWritable(true);
			Formatter writer = new Formatter(file);
			ListIterator<FoodItem> iter = inventory.listIterator();
			while (iter.hasNext()) {
				iter.next().outputItem(writer);
			}
			writer.flush();
			writer.close();
		} catch (IOException e) {
			System.out.println("Could not create file, " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	/*
	 * Asks a user for an item code to search for and then displays that item if
	 * found
	 */
	/**
	 * 
	 * Asks a user for an item code to search for and then displays that item if
	 * found
	 * 
	 * @param scanner - Scanner to use for input
	 */
	public void searchForItem(Scanner scanner) {
		FoodItem itemToSearchFor = new FoodItem();
		itemToSearchFor.inputCode(scanner, false);
		int index = binarySearch(itemToSearchFor.getItemCode(), 0, inventory.size() - 1);
		if (index == -1)
			System.out.println("Code not found in inventory...");
		else
			System.out.println(inventory.get(index).toString());
	}

	/*
	 * Displays the all data members in the class
	 */
	/**
	 * Displays the all data members in the class
	 */
	@Override
	public String toString() {
		String returnString = "Inventory:\n";
		ListIterator<FoodItem> items = inventory.listIterator();
		while (items.hasNext())
			returnString += items.next().toString() + "\n";
		return returnString;
	}

	/*
	 * Reads in an itemCode to update and quantity to update by and updates that
	 * item by the input quantity in the inventory array. The boolean parameter is
	 * used to denote whether buying operation (true) or selling operation (false)
	 * is occurring. Method returns true/false on whether update was successful or
	 * not.
	 * 
	 */
	/**
	 * Update the quantity stored in the food item
	 * 
	 * @param scanner   - It is used to input device to use
	 * @param buyOrSell - If we are to add to quantity returns (<code>true</code>)
	 *                  or If we are to remove to quantity returns
	 *                  (<code>false</code>)
	 * @return - It returns true if method update was successful and false if update
	 *         was not successful.
	 */
	public boolean updateQuantity(Scanner scanner, boolean buyOrSell) {
		// If there are no items then we can't update, return
		if (inventory.isEmpty())
			return false;

		FoodItem temp = new FoodItem();
		temp.inputCode(scanner, false);
		int index = alreadyExists(temp);
		if (index != -1) {
			String buySell = buyOrSell ? "buy" : "sell";
			System.out.print("Enter valid quantity to " + buySell + ": ");
			if (scanner.hasNextInt()) {
				int amount = scanner.nextInt();
				if (amount > 0) {
					return inventory.get(index).updateItem(buyOrSell ? amount : amount * -1);
				} else {
					System.out.println("Invalid quantity...");
				}
			} else {
				System.out.println("Invalid quantity...");
			}
		}
		return false;
	}

}
