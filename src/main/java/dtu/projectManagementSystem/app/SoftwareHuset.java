package dtu.projectManagementSystem.app;

import java.util.ArrayList;
import dtu.projectManagementSystem.domain.Project;
import dtu.projectManagementSystem.domain.ErrorMessageHolder;

import dtu.projectManagementSystem.domain.*;

import java.util.List;

import dtu.projectManagementSystem.domain.Employee;
import dtu.projectManagementSystem.info.EmployeeInfo;

public class SoftwareHuset {

    private boolean isLoggedIn = false;
    private String loggedInId;
    private List<Employee> employeeRepository = new ArrayList<>();
    private ArrayList<Project> projectRepository = new ArrayList<>();
    private static String currentlyLoggedIn;
    private DateServer date = new DateServer();
    private int projectId = 0;

    private ErrorMessageHolder errorMessage;

    public SoftwareHuset() {
        this.projectId = (date.year - 2000) * 1000;
    }

    //Logs in the employee, if they exist
    public void employeeLogin(String id) throws Exception { //Max-Peter Schrøder (s214238)
        EmployeeInfo ei = new EmployeeInfo(id);
        Employee employee = findEmployee(ei);

        if (employee == null) {
            throw new Exception("Employee not found with ID: " + id);
        }
        isLoggedIn = true;
        loggedInId = employee.getId();
        System.out.println("Employee has successfully logged in with ID: " + employee.getId());
    }

    public void employeeLogout() throws Exception { //Max-Peter Schrøder (s214238)
        checkEmployeeLoggedIn();
        Employee employee = getLoggedInEmployee();
        System.out.println("Employee "+employee.getId()+" has logged out.");
        isLoggedIn = false;
        loggedInId = null;
    }

    public void setLoggedIn(Boolean l) {
        isLoggedIn = l;
    }

    public boolean isLoggedIn() {
        return this.isLoggedIn;
    }

    public String getLoggedInId() {
        return this.loggedInId;
    }

    //Finds employee in employee repository, returns null if they don't exist
    public Employee findEmployee(EmployeeInfo ei) { //Max-Peter Schrøder (s214238)
        return employeeRepository.stream()
                .filter(e -> e.getId().equals(ei.getId()))
                .findAny()
                .orElse(null);
    }

    public List<Employee> getEmployeeRepository(){
        return employeeRepository;
    }

    public static void setCurrentlyLoggedIn(String login) {
        currentlyLoggedIn = login;
    }

    public static String getCurrentlyLoggedIn() {
        return currentlyLoggedIn;
    }

    public static List<String> employees;


    public void createProject(String projectName) throws Exception {
        if (projectExist(projectName)) {
            throw new Exception("Project already exists");
        }
        int id = generateProjectId();
        Project project = new Project(projectName, id,this);
        projectRepository.add(project);
    }

    private int generateProjectId() {
        projectId++;
        return projectId;
    }

    public void createNonProjectActivity(String name) throws Exception { //Max-Peter Schrøder (s214238)
        Employee employee = getLoggedInEmployee(); // 1

        if (employee.getNonProjectActivity(name) != null) { // 2
            throw new Exception("Non-project activity "+name+" for employee "+employee.getId()+" already exists"); // 3
        }

        int id = generateNonProjectActivityId(); // 4
        NonProjectActivity activity = new NonProjectActivity(name, id); // 5
        employee.addActivity(activity); // 6
        System.out.println("Employee "+employee.getId()+" has successfully created "+activity.getTypeName()+": "+activity.getName()); // 7
    }

    public int generateNonProjectActivityId() throws Exception { //Max-Peter Schrøder (s214238)
        checkEmployeeLoggedIn();
        Employee employee = getLoggedInEmployee();
        List< NonProjectActivity> activities = employee.getEmployeeNonProjectActivities();

        boolean[] idTaken = new boolean[100];

        for (NonProjectActivity activity : activities) {
            int id = activity.getId();
            if (id >= 1 && id <= 99) {
                idTaken[id] = true;
            }
        }

        for (int i = 1; i < idTaken.length; i++) {
            if (!idTaken[i]) {
                return i;
            }
        }
        throw new Exception("All ID's from 1-99 are taken.");
    }

    //Registers employee, is system-level command so does not require login
    public void registerEmployee(EmployeeInfo employeeInfo) throws Exception { //Max-Peter Schrøder (s214238)
        Employee employee = findEmployee(employeeInfo);
        if (employee != null) {
            throw new Exception("Employee is already registered");
        }
        employeeRepository.add(employeeInfo.asEmployee());
        System.out.println("Employee with ID "+employeeInfo.getId()+" has been registered");
    }

    public Employee getLoggedInEmployee() throws Exception { //Max-Peter Schrøder (s214238)
        checkEmployeeLoggedIn();
        EmployeeInfo ei = new EmployeeInfo(loggedInId);
        return findEmployee(ei);
    }

    private void checkEmployeeLoggedIn() throws Exception { //Max-Peter Schrøder (s214238)
        if (!isLoggedIn()) {
            throw new Exception("Employee is not logged in. Log-in with employee ID to access command.");
        }
    }

    public int getYear() {
        return date.year;
    }

    public List<Project> getProjectRepository(){
        return projectRepository;
    }

    public String getProjectName(int id) {
        String name = "";
        for (int i = 0; i < projectRepository.size(); i++) {
            if (projectRepository.get(i).getProjectId() == id) {
                return name = projectRepository.get(i).getProjectName();
            }

        }
        return name;
    }

    public boolean projectExist(String name) {
        for (int i = 0; i < projectRepository.size(); i++) {
            if (projectRepository.get(i).getProjectName().equals(name)) {
                return true;
            }
        }
        return false;
    }




    public Project getProject(String name){
        for (Project project : projectRepository){
            if (project.getProjectName().equals(name)){
                return project;
            }
        } return null;
        // Måske throw exception i stedet for return null?
    }

}


