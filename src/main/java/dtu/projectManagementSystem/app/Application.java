package dtu.projectManagementSystem.app;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            boolean running = true;
    
            // Main loop for the program
            while (running) {
                System.out.println("\n=== Login Menu ===");
                System.out.println("1. Login");
                System.out.println("2. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline left-over
    
                switch (choice) {
                    case 1:
                        System.out.print("Enter your username: ");
                        String username = scanner.nextLine();
                        System.out.println("Hello, " + username + "!");
                        // Call the main menu function
                        showMainMenu(scanner);
                        break;
                    case 2:
                        System.out.println("Exiting...");
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter 1 or 2.");
                }
            }
            scanner.close();
        }
    
        private static void showMainMenu(Scanner scanner) {
            boolean mainMenuRunning = true;
            while (mainMenuRunning) {
                System.out.println("\n=== Main Menu ===");
                System.out.println("1. Option One");
                System.out.println("2. Option Two");
                System.out.println("3. Logout");
    
                System.out.print("Enter your choice: ");
                int selection = scanner.nextInt();
    
                switch (selection) {
                    case 1:
                        System.out.println("You selected Option One!");
                        break;
                    case 2:
                        System.out.println("You selected Option Two!");
                        break;
                    case 3:
                        System.out.println("Logging out...");
                        mainMenuRunning = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                }
            }
        }
}

