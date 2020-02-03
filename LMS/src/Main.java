import java.util.Scanner;

/**
 * @author LouisRoman
 *
 **/
public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		boolean isRunning = true;
		while(isRunning) {
			System.out.println("[1]Author Services");
			System.out.println("[2]Publisher Services");
			System.out.println("[3]Book Services");
			System.out.println("[-1]Exit Program");
			System.out.print("Please Select one of the following options:");
			
			try {
				int selection = 0;
				selection = scan.nextInt();
			
				switch(selection) {
					case 1:
						System.out.println("You chose the Author");
						Author.main(null);
						break;
					case 2:
						System.out.println("You chose the Publisher");
						Publisher.main(null);
						break;
					case 3:
						System.out.println("You chose the Book Services");
						Book.main(null);
						break;
					case -1:
						System.out.println("Goodbye!");
						isRunning = false;
						break;
					default:
						System.out.println("Not an option");
						break;
				}
			} catch(Exception e) {
				System.out.println("Incorrect input.");
			}
		}
	}
}