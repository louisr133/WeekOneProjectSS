import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

//1,Greg Warren,2,Samantha Sanders,3,Albert Biden,4,Einstein Yang,5,George Lopez,6,Harry Buffet,7,George Washington,8,Lady Gaga,9,Mark Twain,
public class Author {

	private static HashMap<Integer, String> map = new HashMap<>();
	private static File file = new File("Resources/Author");
	private static FileWriter wout = null;
	private static int maxId = 0;
	private static String authId;

	private static void updateHashMap() {
		Scanner scan;
		try {
			scan = new Scanner(file);
			scan.useDelimiter(",");
			while (scan.hasNext()) {
				map.put((Integer) Integer.parseInt(scan.next()), scan.next());
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	// Add author
	public static void create() {
		
		System.out.println("What is the name of the new author? (write \"exit\" to exit)");
		Scanner scan = new Scanner(System.in);
		String name = scan.nextLine();
		if (!"exit".equalsIgnoreCase(name)) {
			try {
				BufferedWriter out = new BufferedWriter(new FileWriter(file, true));
				maxId++;
				out.write(maxId + "," + name + ",");
				out.close();
			} catch (IOException e) {
				System.out.println("Error occured: " + e);
			}
		}
		updateHashMap();
	}

	public static int create(String name) {
		Scanner scanA = new Scanner(System.in);
		Scanner scan = null;

		try {
			scan = new Scanner(file);
		} catch (FileNotFoundException e1) {
			System.out.println("Exception occured: " + e1);
		}

		// scan.next() separated by ,
		scan.useDelimiter(",");

		try {
			while (scan.hasNext()) {
				map.put(Integer.parseInt(scan.next()), scan.next());
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}

		for (Integer key : map.keySet()) {
			maxId = findLargestVal(maxId, key);
		};
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(file, true));
			maxId++;
			out.write(maxId + "," + name + ",");
			out.close();
		} catch (IOException e) {
			System.out.println("Error occured: " + e);
		}

		updateHashMap();
		
		return maxId;
	}

	// read file
	private static void read() {
		Scanner scan = null;
		try {
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("Exception: " + e);
		}
		scan.useDelimiter(",");
		while (scan.hasNext()) {
			System.out.println("Id:" + scan.next() + " : " + scan.next());
		}
	}

	private static void rewrite() {
		try {
			wout = new FileWriter(file, false);
			map.forEach((k, v) -> {
				try {
					System.out.print(k + "," + v + ",");
					wout.write(k.toString() + "," + v + ",");

				} catch (IOException e) {
					System.out.println(e);
				}
			});
		} catch (IOException e) {
			System.out.println("Exception: " + e);
		}
		try {
			wout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		updateHashMap();
	}

	// update authors
	private static void update() {
		System.out.println("Which author would you like to update? \"exit\" to exit");
		System.out.println(map);
		System.out.println("Enter author id:");
		Scanner scan = new Scanner(System.in);
		boolean isPassable = true;

		while (isPassable) {
			try {
				authId = scan.nextLine();
				if (!"exit".equalsIgnoreCase(authId)) {
					System.out.println("What would you like to change the name to?");
					String update = scan.nextLine();
					map.replace(Integer.parseInt(authId), update);
					Author.rewrite();
					isPassable = false;
				} else {
					isPassable = false;
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		System.out.println();
	}

	// delete author
	public static void delete() {
		System.out.println("Which author would you like to delete? \"exit\" to exit");
		System.out.println(map);
		System.out.println("Enter author id:");
		Scanner scan = new Scanner(System.in);
		boolean isPassable = true;

		while (isPassable) {
			try {
				authId = scan.nextLine();
				if (!"exit".equalsIgnoreCase(authId)) {
					map.remove(Integer.parseInt(authId));
					Author.rewrite();
					isPassable = false;
				} else {
					isPassable = false;
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		System.out.println();

	}

	// find bigger value
	public static int findLargestVal(int firstVal, int secondVal) {
		if (firstVal > secondVal) {
			return firstVal;
		} else {
			return secondVal;
		}
	}

	// Main
	public static void main(String[] args) {
		Scanner scanA = new Scanner(System.in);
		Scanner scan = null;

		try {
			scan = new Scanner(file);
		} catch (FileNotFoundException e1) {
			System.out.println("Exception occured: " + e1);
		}

		// scan.next() separated by ,
		scan.useDelimiter(",");

		try {
			while (scan.hasNext()) {
				map.put(Integer.parseInt(scan.next()), scan.next());
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}

		for (Integer key : map.keySet()) {
			maxId = findLargestVal(maxId, key);
		}

		boolean isRun = true;

		while (isRun) {
			System.out.println("[1]Add author");
			System.out.println("[2]Read authors");
			System.out.println("[3]Update author");
			System.out.println("[4]Delete author");
			System.out.println("[99]Go Back to Main Menu");
			System.out.print("Please Select one of the following options:");
			int selection = 0;
			try {
				selection = Integer.parseInt(scanA.nextLine());
				// options
				switch (selection) {
				case 1:
					System.out.println("Add Author");
					Author.create();
					break;
				case 2:
					Author.read();
					break;
				case 3:
					System.out.println("Updating Author");
					Author.update();
					break;
				case 4:
					System.out.println("Deleting Author");
					Author.delete();
					break;
				case 99:
					System.out.println("Back");
					isRun = false;
					break;
				default:
					System.out.println("Not an option");
					break;
				}
			} catch (Exception e) {
				System.out.println("Incorrect input.");
				scan.nextLine();
			}
		}

	}

}
