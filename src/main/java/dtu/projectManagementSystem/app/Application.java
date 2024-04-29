package dtu.projectManagementSystem.app;
import dtu.projectManagementSystem.domain.*;
import dtu.projectManagementSystem.info.EmployeeInfo;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Application {

    private static SoftwareHuset softwareHuset = new SoftwareHuset();
    private static ErrorMessageHolder errorMessage = new ErrorMessageHolder();

    public static void main(String[] args) throws Exception {
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
        private static void showMainMenu(Scanner scanner) throws Exception {
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

        private static void showEmployeeManagementMenu(Scanner scanner) throws Exception {
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
                        showEmployeeActivitiesMenu(scanner);
                        break;
                    case 2:
                        System.out.println("Enter name for non-project activity");
                        String nonProjectName = scanner.nextLine();
                        try {
                            softwareHuset.createNonProjectActivity(nonProjectName);
                        } catch (Exception e){
                            errorMessage.setErrorMessage(e.getMessage());
                            System.out.println(errorMessage.getErrorMessage());
                        }
                        break;
                    case 3:
                        editNonProjectActivityMenu(scanner);
                        break;
                    case 4:
                        registerTimeMenu(scanner);
                        break;
                    case 5:
                        //System.out.println("Back to Main menu...");
                        employeeManagementMenuRunning = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter 1, 2, 3, 4 or 5.");
                }
            }
        }
        private static void showEmployeeActivitiesMenu(Scanner scanner) throws Exception {
            boolean showEmployeeActivitiesMenuRunning = true;
            while (showEmployeeActivitiesMenuRunning) {
                System.out.println("\n=== Show activities for employee:" + softwareHuset.getLoggedInId() + " ===");
                System.out.println("1. Show all for "+ softwareHuset.getLoggedInId());
                System.out.println("2. Show activities for a given project for" +softwareHuset.getLoggedInId());
                System.out.println("3. Show non-project activities for "+softwareHuset.getLoggedInId());
                System.out.println("4. Back");
                System.out.print("Enter your choice: ");
                int selection = scanner.nextInt();

                switch (selection) {
                    case 1:
                        for (Activity activity :softwareHuset.getLoggedInEmployee().getEmployeeActivities())
                            System.out.println(activity.getName());
                        break;
                    case 2:
                        System.out.println("Enter project to show activities for");
                            for (Project project : softwareHuset.getProjectRepository()){

                            }

                        break;
                    case 3:
                        break;
                    case 4:
                        //System.out.println("Going Back");
                        showEmployeeActivitiesMenuRunning = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter 1, 2, 3 or 4.");
                }
            }
        }

        private static void editNonProjectActivityMenu(Scanner scanner){

        }

        private static void registerTimeMenu(Scanner scanner){

        }


        private static void showProjectManagementMenu(Scanner scanner){

        }


}

