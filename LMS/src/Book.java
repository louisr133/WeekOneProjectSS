import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Book {
	private static ArrayList<HashMap<String, String>> booksArray = new ArrayList<HashMap<String, String>>();
	private static File file = new File("Resources/Books");
	private static int max = 0;
	private static int holder = 0;

	private static void read() {
		try {
			Scanner scan = new Scanner(file);
			scan.useDelimiter(",");

			while (scan.hasNext()) {
				HashMap<String, String> bookData = new HashMap<>();
				bookData.put("bookId", scan.next());
				bookData.put("title", scan.next());
				bookData.put("authId", scan.next());
				bookData.put("pubId", scan.next());
				booksArray.add(bookData);

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println(e);
		}

		booksArray.forEach((el) -> System.out.println(el));
		System.out.println();

	}

	private static void nextId() {
		booksArray.forEach(k ->{
			holder = Integer.parseInt(k.get("bookId"));
			if(holder > max) {
				max = holder;
			}
		});
		
	}

	private static void addBook() {
		Scanner scan = new Scanner(System.in);
		System.out.println("To exit type \"exit\"");
		System.out.print("Please provide the Book Title: ");
		String title = scan.nextLine();
		if (!"exit".equals(title)) {
			System.out.println("What is the name of the author");
			String authName = scan.nextLine();
			int authId = Author.create(authName);
			System.out.println("What is the name of the Publisher");
			String pubName = scan.nextLine();
			int pubId = Publisher.create(pubName);
			int bookId = max++;
			
			HashMap<String, String> newBook = new HashMap<>();
			newBook.put("bookId", Integer.toString(bookId));
			newBook.put("title", title);
			newBook.put("authId", Integer.toString(authId));
			newBook.put("pubId", Integer.toString(pubId));
			booksArray.add(newBook);
			
			
			System.out.println(booksArray);
		}

	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		boolean isRunning = true;
		while (isRunning) {
			try {
				System.out.println("[1]Add Book");
				System.out.println("[2]Read Books file");
				System.out.println("[3]Update Book");
				System.out.println("[4]Delete Book");
				System.out.println("[99]Go Back to Main Menu");
				System.out.print("Please Select one of the options above:");

				int selection = Integer.parseInt(scan.nextLine());

				switch (selection) {
				case 1:
					System.out.println("Add Book");
					Book.addBook();
					break;
				case 2:
					System.out.println("Reading Book File");
					Book.read();
					break;
				case 3:
					System.out.println("Updating Book");
					break;
				case 4:
					System.out.println("Deleting Book");
					break;
				case 99:
					System.out.println("Back to Main Menu");
					isRunning = false;
					break;
				default:
					System.out.println("Not an option");
					break;
				}
			} catch (Exception e) {
				System.out.println(e);
				scan.nextLine();
			}
		}

	}
}
