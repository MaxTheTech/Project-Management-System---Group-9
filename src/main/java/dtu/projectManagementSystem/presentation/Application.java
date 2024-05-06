package dtu.projectManagementSystem.presentation;
import dtu.projectManagementSystem.app.DateServer;
import dtu.projectManagementSystem.app.SoftwareHuset;
import dtu.projectManagementSystem.domain.*;
import dtu.projectManagementSystem.info.EmployeeInfo;
import java.sql.SQLOutput;
import java.util.InputMismatchException;
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
                int choice = 0;
                try {
                    choice = scanner.nextInt();
                } catch (InputMismatchException ignored) {
                }
                scanner.nextLine();  // Consume newline left-over
    
                switch (choice) {
                    case 1:
                        System.out.println("Enter employee ID");
                        String newEmployeeID = scanner.next();
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
                        String username = scanner.next();
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
                int choice = 0;
                try {
                    choice = scanner.nextInt();
                } catch (InputMismatchException ignored) {
                }
                scanner.nextLine();
    
                switch (choice) {
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
                System.out.println("\n=== Management for employee: " + softwareHuset.getLoggedInId() + " ===");
                System.out.println("1. View employee activities");
                System.out.println("2. Create non-project activity");
                System.out.println("3. Edit a non-project activity");
                System.out.println("4. Register time");
                System.out.println("5. Back");
                System.out.print("Enter your choice: ");
                int choice = 0;
                try {
                    choice = scanner.nextInt();
                } catch (InputMismatchException ignored) {
                }
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        showEmployeeActivitiesMenu(scanner);
                        break;
                    case 2:
                        System.out.println("Enter name for non-project activity");
                        String nonProjectName = scanner.next();
                        try {
                            softwareHuset.createNonProjectActivity(nonProjectName);
                        } catch (Exception e){
                            errorMessage.setErrorMessage(e.getMessage());
                            System.out.println(errorMessage.getErrorMessage());
                        }
                        break;
                    case 3:
                        System.out.println("Enter name of the non-project activity to edit");
                        String nonProjectActivityToEdit = scanner.next();
                        NonProjectActivity nonProjectActivity=softwareHuset.getLoggedInEmployee().getNonProjectActivity(nonProjectActivityToEdit);
                        if (nonProjectActivity != null){
                            editNonProjectActivityMenu(scanner,nonProjectActivity);
                        } else{
                            System.out.println("Non-project activity with name "+nonProjectActivityToEdit+ " does not exist.");
                        }

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
                System.out.println("2. Show activities for a given project for " +softwareHuset.getLoggedInId());
                System.out.println("3. Show non-project activities for "+softwareHuset.getLoggedInId());
                System.out.println("4. Back");
                System.out.print("Enter your choice: ");
                int choice = 0;
                try {
                    choice = scanner.nextInt();
                } catch (InputMismatchException ignored) {
                }
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        int activityCount = 0;
                        for (Activity activity :softwareHuset.getLoggedInEmployee().getEmployeeActivities()){
                            System.out.println(activity.getName());
                            activityCount++;
                        }
                        if (activityCount==0){
                            System.out.println(softwareHuset.getLoggedInId() + " has no activities.");
                        }


                        break;
                    case 2:
                        System.out.println("Enter project to show activities for");
                        String projectName = scanner.next();
                        if(softwareHuset.projectExist(projectName)){
                            for (ProjectActivity projectActivity : softwareHuset.getLoggedInEmployee().getEmployeeProjectActivities()){
                                if (projectActivity.getParentproject().getProjectName().equals(projectName)){
                                    System.out.println(projectActivity.getName());
                                }
                            }
                        }else{
                            System.out.println("Project with name "+projectName+" does not exist");
                        }

                        break;
                    case 3:
                        for (Activity activity :softwareHuset.getLoggedInEmployee().getEmployeeNonProjectActivities())
                            System.out.println(activity.getName());
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

        private static void editNonProjectActivityMenu(Scanner scanner, NonProjectActivity nonProjectActivity){
            boolean editNonProjectActivityMenuRunning = true;
            while (editNonProjectActivityMenuRunning) {
                System.out.println("\n=== Edit non-project activity " + nonProjectActivity.getName() +" ===");
                System.out.println("1. Edit starting date ");
                System.out.println("2. Edit duration (in days) ");
                System.out.println("3. Back");
                System.out.print("Enter your choice: ");
                int choice = 0;
                try {
                    choice = scanner.nextInt();
                } catch (InputMismatchException ignored) {
                }
                scanner.nextLine();

                switch (choice){
                    case 1:
                        if(nonProjectActivity.getStartingDay()==null){
                            System.out.println("A starting date has not yet been set for this activity");
                        }else {
                            System.out.println("The starting date for this activity is currently:");
                            System.out.println(nonProjectActivity.getStartingDay().toString());
                        }
                        System.out.println("Enter the new starting date for "+nonProjectActivity.getName());
                        try {
                            System.out.println("Enter starting year");
                            int startingYear = scanner.nextInt();  // Attempts to read an int from user
                            try {
                                System.out.println("Enter starting week");
                                int startingWeek = scanner.nextInt();
                                try {
                                    System.out.println("Enter starting day");
                                    int startingDay = scanner.nextInt();
                                    nonProjectActivity.setStartingDay(new DateServer(startingYear,startingWeek,startingDay));
                                    System.out.println("Starting date for "+ nonProjectActivity.getName() + " set to:");
                                    System.out.println("Starting year: "+startingYear +", starting week: "+startingWeek+", starting day: "+startingDay);

                                } catch (InputMismatchException e) {
                                    System.out.println("Input must be an integer");
                                }

                            } catch (InputMismatchException e) {
                                System.out.println("Input must be an integer");
                            }

                        } catch (InputMismatchException e) {
                            System.out.println("Input must be an integer");
                        }
                        break;

                    case 2:
                        System.out.println("The duration for "+nonProjectActivity.getName()+" is currently "+nonProjectActivity.getDurationDays());


                        System.out.println("Enter duration (in days) for "+nonProjectActivity.getName());
                        try {
                            int duration = scanner.nextInt();  // Attempts to read an int from user
                            nonProjectActivity.setDurationDays(duration);
                            System.out.println("Duration for " + nonProjectActivity.getName()+" set to "+duration+ " days");
                        } catch (InputMismatchException e) {
                            System.out.println("Input must be an integer");
                        }
                        break;
                    case 3:
                        //System.out.println("Going Back");
                        editNonProjectActivityMenuRunning = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter 1, 2 or 3.");
                }


            }
        }

        private static void registerTimeMenu(Scanner scanner) throws Exception {
            boolean registerTimeMenuRunning = true;
            while (registerTimeMenuRunning){
                System.out.println("\n=== Register time ===");
                System.out.println("1. Register time on project activity ");
                System.out.println("2. Register time on non-project activity ");
                System.out.println("3. Back");
                System.out.print("Enter your choice: ");
                int choice = 0;
                try {
                    choice = scanner.nextInt();
                } catch (InputMismatchException ignored) {
                }
                scanner.nextLine();

                switch (choice){
                    case 1:
                        System.out.println("Enter name of the project:");
                        String projectName = scanner.next();
                        if(softwareHuset.projectExist(projectName)){
                            Project project = softwareHuset.getProject(projectName);
                            System.out.println("Enter project activity name:");
                            String activityName = scanner.next();
                            if(project.hasActivity(activityName)){
                                System.out.println("Enter time spent");
                                System.out.println("Feature not yet implemented");
                                ////////////////////////////////////////////////////////////////
                                //////           Register time is not implemented       ////////
                                ////////////////////////////////////////////////////////////////
                            }else {
                                System.out.println("Project "+projectName+" does not have an activity called "+activityName);
                            }
                        }else{
                            System.out.println("Project with name "+projectName+" does not exist");
                        }
                        break;
                    case 2:
                        System.out.println("Enter non-project activity name:");
                        String nonActivityName = scanner.next();
                        NonProjectActivity nonProjectActivity = softwareHuset.getLoggedInEmployee().getNonProjectActivity(nonActivityName);
                        if(nonProjectActivity != null){
                            System.out.println("Enter time spent");
                            System.out.println("Feature not yet implemented");
                            ////////////////////////////////////////////////////////////////
                            //////           Register time is not implemented       ////////
                            ////////////////////////////////////////////////////////////////
                        }else {
                            System.out.println("Non-project activity with name "+nonActivityName+" does not exist");
                        }
                        break;
                    case 3:
                        //System.out.println("Going Back");
                        registerTimeMenuRunning = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter 1, 2 or 3.");
                }
            }

        }


        private static void showProjectManagementMenu(Scanner scanner) throws Exception {
            boolean projectManagementMenuRunning = true;
            while (projectManagementMenuRunning) {
                System.out.println("\n=== Project management ===");
                System.out.println("1. Create project");
                System.out.println("2. List projects");
                System.out.println("3. Enter project");
                System.out.println("4. Back");
                System.out.print("Enter your choice: ");
                int choice = 0;
                try {
                    choice = scanner.nextInt();
                } catch (InputMismatchException ignored) {
                }
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println("Enter name of the new project: ");
                        String projectName = scanner.next();
                        try{
                            softwareHuset.createProject(projectName);
                            System.out.println("New project "+ projectName+" created");
                        }catch (Exception e){
                            errorMessage.setErrorMessage(e.getMessage());
                            System.out.println(errorMessage.getErrorMessage());
                        }
                        break;
                    case 2:
                        int projectCount = 0;
                        for(Project project : softwareHuset.getProjectRepository()){
                            System.out.println(project.getProjectName());
                            projectCount++;
                        }
                        if(projectCount==0){
                            System.out.println("There are no projects");
                        }
                        break;
                    case 3:
                        System.out.println("Enter name of project: ");
                        String projectToEnter = scanner.next();
                        if(softwareHuset.projectExist(projectToEnter)){
                            showSpecificProjectManagementMenu(scanner,softwareHuset.getProject(projectToEnter));
                        }else{
                            System.out.println("Project with name "+projectToEnter+" does not exist.");
                        }
                        break;
                    case 4:
                        projectManagementMenuRunning=false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter 1, 2, 3 or 4.");
                }
            }
        }

    private static void showSpecificProjectManagementMenu(Scanner scanner, Project project) throws Exception {
        boolean specificProjectManagementMenu = true;
        while (specificProjectManagementMenu) {
            System.out.println("\n=== Management for project: "+project.getProjectName()+" ===");
            System.out.println("1. Edit project");
            System.out.println("2. List project activities");
            System.out.println("3. Enter project activity");
            System.out.println("4. Set project manager");
            System.out.println("5. Enter project manager privileges");
            System.out.println("6. Create project activity");
            System.out.println("7. Back");
            System.out.print("Enter your choice: ");
            int choice = 0;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException ignored){
            }
            scanner.nextLine();

            switch (choice) {
                case 1:
                    showEditProjectMenu(scanner, project);
                    break;
                case 2:
                    int activityCount = 0;
                    for(Activity activity : project.getProjectActivities()){
                        System.out.println(activity.getName());
                        activityCount++;
                    }
                    if (activityCount==0){
                        System.out.println("There are no activities in this project");
                    }
                    break;
                case 3:
                    System.out.println("Enter project activity to edit:");
                    String projectActivity = scanner.next();
                    if(project.hasActivity(projectActivity)){
                        showEditProjectActivityMenu(scanner, project.getActivity(projectActivity));
                    }

                    break;
                case 4:
                    if(project.isHasManager()){
                        System.out.println("Project already has a manager");
                    }else{
                        project.setManagerId(softwareHuset.getLoggedInId());
                        System.out.println("Manager for project "+project.getProjectName()+ " set to "+ softwareHuset.getLoggedInId());
                    }
                    break;
                case 5:
                    if(project.getManagerId().equals(softwareHuset.getLoggedInId())){
                        showProjectManagerPriviligesMenu(scanner,project);
                    }else{
                        System.out.println("You are not the project manager for this project");
                    }

                    break;
                case 6:
                    System.out.println("Enter name for new project activity");
                    String newProjectActivityName = scanner.next();
                    System.out.println("Feature not yet implemented");
                    ////////////////////////////////////////////////////////////////
                    //////    Create project activity is not implemented    ////////
                    ////////////////////////////////////////////////////////////////
                    break;
                case 7:
                    specificProjectManagementMenu=false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, 3, 4, 5 or 6.");
            }
        }
    }

    private static void showProjectManagerPriviligesMenu(Scanner scanner, Project project) throws Exception {
        boolean projectManagerPriviligesMenu = true;
        while (projectManagerPriviligesMenu) {
            System.out.println("\n=== Management for employee: ===");
            System.out.println("1. List available employees for project activity");
            System.out.println("2. Assign employee to project activity");
            System.out.println("3. Generate project report");
            System.out.println("4. Back");
            System.out.print("Enter your choice: ");
            int choice = 0;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException ignored) {
            }
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter project activity");
                    String projectActivityName = scanner.next();
                    System.out.println("Feature not yet implemented");
                    ////////////////////////////////////////////////////////////////
                    //////   List available employees is not implemented    ////////
                    ////////////////////////////////////////////////////////////////
                    break;
                case 2:
                    System.out.println("Enter project activity");
                    String projectActivityName1 = scanner.next();
                    System.out.println("Feature not yet implemented");
                    ////////////////////////////////////////////////////////////////
                    //////      Assign employee is not implemented          ////////
                    ////////////////////////////////////////////////////////////////
                    break;
                case 3:
                    System.out.println(project.projectReport());
                    break;
                case 4:
                    projectManagerPriviligesMenu = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, 3, 4 or 5.");
            }
        }
    }

    private static void showEditProjectActivityMenu(Scanner scanner, ProjectActivity projectActivity) {
        boolean editProjectActivityMenuRunning = true;
        while (editProjectActivityMenuRunning) {
            System.out.println("\n=== Edit project activity "+projectActivity.getName()+" ===");
            System.out.println("1. Edit starting week");
            System.out.println("2. Edit duration");
            System.out.println("3. Edit expected workload (in half hours)");
            System.out.println("4. Back");
            System.out.print("Enter your choice: ");
            int choice = 0;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException ignored) {
            }
            scanner.nextLine();

            switch (choice) {
                case 1:
                    if(projectActivity.getStartingDate()==null){
                        System.out.println("The starting week for this activity has not yet been set");
                    }else{
                        System.out.println("The starting week for this activity is currently set to "+projectActivity.getStartingDate().getWeek());
                    }
                    try {
                        System.out.println("Enter starting week:");
                        int startingWeek = scanner.nextInt();
                        DateServer startingDate = new DateServer(0,startingWeek,0);
                        projectActivity.setStartingWeek(startingDate);
                        System.out.println("Starting week for "+projectActivity.getName()+" set to "+startingWeek);

                    } catch (InputMismatchException e) {
                        System.out.println("Input must be an integer");
                    }
                    break;
                case 2:
                    System.out.println("The duration for this activity is currently set to "+projectActivity.getDurationWeeks()+" weeks.");
                    try {
                        System.out.println("Enter duration:");
                        int duration = scanner.nextInt();
                        projectActivity.setDurationWeeks(duration);
                        System.out.println("Duration for "+projectActivity.getName()+" set to "+duration);

                    } catch (InputMismatchException e) {
                        System.out.println("Input must be an integer");
                    }
                    break;
                case 3:
                    System.out.println("The expected workload for this activity is currently set to "+projectActivity.getExpectedWorkloadHalfhours()+" half hours");
                    try {
                        System.out.println("Enter expected workload:");
                        int expectedWorkload = scanner.nextInt();
                        projectActivity.setDurationWeeks(expectedWorkload);
                        System.out.println("Expected workload for "+projectActivity.getName()+" set to "+expectedWorkload+ " half hours");

                    } catch (InputMismatchException e) {
                        System.out.println("Input must be an integer");
                    }
                    break;
                case 4:
                    editProjectActivityMenuRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, 3, or 4.");
            }
        }
    }

    private static void showEditProjectMenu(Scanner scanner, Project project) {
        boolean editProjectMenuRunning = true;
        while (editProjectMenuRunning) {
            System.out.println("\n=== Edit project "+project.getProjectName()+" ===");
            System.out.println("1. Edit starting date (year + week)");
            System.out.println("2. Edit duration (in weeks)");
            System.out.println("3. Edit expected workload (in half hours)");
            System.out.println("4. Back");
            System.out.print("Enter your choice: ");
            int choice = 0;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException ignored) {
            }
            scanner.nextLine();

            switch (choice) {
                case 1:
                    if(project.isStartingDateHasBeenSet()){
                        System.out.println("The starting date for the project is currently year "+project.getStartingYear()+" week "+project.getStartingWeek());
                    }else{
                        System.out.println("The starting date for this project has not been set yet");
                    }
                    try {
                        System.out.println("Enter starting year:");
                        int startingYear = scanner.nextInt();
                        try {
                            System.out.println("Enter starting week:");
                            int startingWeek = scanner.nextInt();
                            project.setStartingYear(startingYear);
                            project.setStartingWeek(startingWeek);
                            project.setStartingDateHasBeenSet();
                            System.out.println("Starting date for project "+project.getProjectName()+" set to year "+startingYear+" week "+startingWeek);

                        } catch (InputMismatchException e) {
                            System.out.println("Input must be an integer");
                        }

                    } catch (InputMismatchException e) {
                        System.out.println("Input must be an integer");
                    }
                    break;
                case 2:
                    System.out.println("The duration for this project is currently set to "+ project.getDurationInWeeks()+" weeks.");
                    try {
                        System.out.println("Enter duration:");
                        int duration = scanner.nextInt();
                        project.setDurationInWeeks(duration);
                        System.out.println("Duration for project "+project.getProjectName()+" set to "+duration+" weeks.");

                    } catch (InputMismatchException e) {
                        System.out.println("Input must be an integer");
                    }
                    break;
                case 3:
                    System.out.println("The expected workload for this project is currently set to "+project.getExpectedWorkloadHours()+" half hours");
                    try {
                        System.out.println("Enter expected workload (in half hours)");
                        int expectedWorkload = scanner.nextInt();
                        project.setExpectedWorkloadHours(expectedWorkload);
                        System.out.println("Expected workload for project "+project.getProjectName()+ " set to " + expectedWorkload + " half hours.");

                    } catch (InputMismatchException e) {
                        System.out.println("Input must be an integer");
                    }
                    break;
                case 4:
                    editProjectMenuRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, 3 or 4.");
            }
        }
    }


}


