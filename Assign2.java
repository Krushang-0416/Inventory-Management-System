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
import java.util.regex.Pattern;
/**
 * This is a driver class where all the major functionalities are called and
 * executed. All the adding, updating, buying and selling functions are
 * functioned.
 * 
 * @author Krushang Patel
 * @version Java 11
 *
 */
public class Assign2 {
	/**
	 * Helper method to display menu
	 */
	public static void displayMenu() {
		System.out.println("Please select one of the following:");
		System.out.println("1: Add Item to Inventory");
		System.out.println("2: Display Current Inventory");
		System.out.println("3: Buy Item(s)");
		System.out.println("4: Sell Item(s)");
		System.out.println("5: Search for Item");
		System.out.println("6: Save Inventory to File");
		System.out.println("7: Read Inventory from File");
		System.out.println("8: To Exit");
		System.out.print("> ");
	}

	/**
	 * It is a method main where program execution starts
	 * 
	 * @param args - Parameters passed into the application
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		scanner.useDelimiter(Pattern.compile("[\\r\\n]+"));
		Inventory inventory = new Inventory();
		int choice = 0;
		while (choice != 8) {
			try {
				displayMenu();
				if (scanner.hasNext(Pattern.compile("[1-8]"))) {
					choice = scanner.nextInt();
					switch (choice) {
					case 1: // Add Item
						if (!inventory.addItem(scanner, false))
							System.out.println("Error...could not add item");
						break;
					case 2: // Display Current Inventory
						System.out.println(inventory);
						break;
					case 3: // Buy Item(s)
						if (!inventory.updateQuantity(scanner, true))
							System.out.println("Error...could not buy item");
						break;
					case 4: // Sell Item(s)
						if (!inventory.updateQuantity(scanner, false))
							System.out.println("Error...could not sell item");
						break;
					case 5: // Search for Item
						inventory.searchForItem(scanner);
						break;
					case 6: // Save Inventory to File
						inventory.saveToFile(scanner);
						break;
					case 7: // Read Inventory from File
						inventory.readFromFile(scanner);
						break;
					case 8: // To Exit
						System.out.println("Exiting...");
						break;
					default: // Should never get here
						System.out.println("Something went wrong");
						break;
					}
				} else {
					System.out.println("Incorrect value entered");
					scanner.next();
				}
			} catch (Exception e) {
				System.out.println("Error Occurred: " + e.getMessage());
			}
		}
		scanner.close();
	}
}
