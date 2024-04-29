package dtu.projectManagementSystem.app;
import dtu.projectManagementSystem.domain.Employee;
import dtu.projectManagementSystem.domain.ErrorMessageHolder;
import dtu.projectManagementSystem.info.EmployeeInfo;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Application {

    private static SoftwareHuset softwareHuset = new SoftwareHuset();
    private static ErrorMessageHolder errorMessage = new ErrorMessageHolder();

    public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            boolean running = true;




            // Main loop for the program
            while (running) {
                System.out.println("\n=== Login Menu ===");
                System.out.println("1. Add employee");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline left-over
    
                switch (choice) {
                    case 1:
                        System.out.println("Enter employee ID");
                        String newEmployeeID = scanner.nextLine();
                        try {
                            EmployeeInfo newEmployee = new EmployeeInfo(newEmployeeID);
                            softwareHuset.registerEmployee(newEmployee);

                        } catch (Exception e){
                            errorMessage.setErrorMessage(e.getMessage());
                            System.out.println(errorMessage.getErrorMessage());

                        }
                        break;

                    case 2:
                        System.out.print("Enter your username: ");
                        String username = scanner.nextLine();
                        try {
                            softwareHuset.employeeLogin(username);
                            showMainMenu(scanner);
                        } catch (Exception e){
                            errorMessage.setErrorMessage(e.getMessage());
                            System.out.println(errorMessage.getErrorMessage());
                        }

                        break;

                    case 3:
                        System.out.println("Exiting...");
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter 1, 2 or 3.");
                }
            }
            scanner.close();
        }
        private static void showMainMenu(Scanner scanner) {
            boolean mainMenuRunning = true;
            while (mainMenuRunning) {
                System.out.println("\n=== Main Menu ===");
                System.out.println("1. Enter employee management");
                System.out.println("2. Enter project management");
                System.out.println("3. Logout");
    
                System.out.print("Enter your choice: ");
                int selection = scanner.nextInt();
    
                switch (selection) {
                    case 1:
                        showEmployeeManagementMenu(scanner);
                        break;
                    case 2:
                        showProjectManagementMenu(scanner);


                        break;
                    case 3:
                        System.out.println("Logging out...");
                        // Skal måske ændre isLoggedIn - Men i princippet bør det ikk ændre noget
                        mainMenuRunning = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                }
            }
        }

        private static void showEmployeeManagementMenu(Scanner scanner){
            boolean employeeManagementMenuRunning = true;
            while (employeeManagementMenuRunning) {
                System.out.println("\n=== Management for employee:" + softwareHuset.getLoggedInId() + " ===");
                System.out.println("1. View employee activities");
                System.out.println("2. Create non-project activity");
                System.out.println("3. Edit non-project activity");
                System.out.println("4. Register time");
                System.out.println("5. Back");
                System.out.print("Enter your choice: ");
                int selection = scanner.nextInt();

                switch (selection) {
                    case 1:
                        break;
                    case 2:


                        break;
                    case 5:
                        System.out.println("Back to Main menu...");
                         employeeManagementMenuRunning = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter 1, 2, 3, 4 or 5.");
                }



            }

        }




        private static void showProjectManagementMenu(Scanner scanner){

        }


}

