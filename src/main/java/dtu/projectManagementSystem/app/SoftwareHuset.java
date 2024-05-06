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
    private String loggedInId = null;
    private final List<Employee> employeeRepository = new ArrayList<>();
    private final ArrayList<Project> projectRepository = new ArrayList<>();
    private static String currentlyLoggedIn;
    private final DateServer date = new DateServer();
    private int projectId = 0;
    private ErrorMessageHolder errorMessage;

    public SoftwareHuset() {
        this.projectId = (date.year - 2000) * 1000;
    }

    //Max-Peter Schrøder (s214238)
    public void employeeLogin(String id) throws Exception {
        EmployeeInfo ei = new EmployeeInfo(id);
        Employee employee = findEmployee(ei);

        if (employee == null) {
            throw new Exception("Employee not found with ID: " + id);
        }
        isLoggedIn = true;
        loggedInId = employee.getId();
        System.out.println("Employee has successfully logged in with ID: " + employee.getId());
    }

    //Max-Peter Schrøder (s214238)
    public void employeeLogout() throws Exception {
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

    //Max-Peter Schrøder (s214238)
    public Employee findEmployee(EmployeeInfo ei) {
        return employeeRepository.stream()
                .filter(e -> e.getId().equals(ei.getId()))
                .findAny()
                .orElse(null);
    }

    public List<Employee> getEmployeeRepository(){
        return employeeRepository;
    }


    //Sebastian A. Ladegaard - s215530
    public void createProject(String projectName) throws Exception {
        assert true : "Precondition violated"; //precondition

        if (projectExist(projectName)) {                                    //1
            //assert postcondition in case A
            assert projectRepository.stream().
                    anyMatch(i -> i.getProjectName().equals(projectName))
                    : "Postcondition A";
            throw new Exception("Project already exists");                  //2
        }
        int id = generateProjectId();                                       //3
        Project project = new Project(projectName, id,this);    //4
        projectRepository.add(project);                                    //5

        //assert postcondition in case B
        assert projectRepository.stream().
                anyMatch(i -> i.getProjectName().equals(projectName))
                : "Postcondition B";
    }

    //Sebastian A. Ladegaard - s215530
    private int generateProjectId() {
        projectId++;
        return projectId;
    }

    //Max-Peter Schrøder (s214238)
    public NonProjectActivity createNonProjectActivity(String name) throws Exception {
        Employee employee = getLoggedInEmployee(); // 1

        assert employeeRepository.stream().
                anyMatch(i -> i.getId().equals(employee.getId()))
                : "Precondition";
        assert isLoggedIn && loggedInId != null : "Precondition";

        if (employee.getNonProjectActivity(name) != null) { // 2

            assert employee.getEmployeeNonProjectActivities().stream().
                    anyMatch(i -> i.getName().equals(name))
                    : "Postcondition";

            throw new Exception("Non-project activity "+name+" for employee "+employee.getId()+" already exists"); // 3
        }

        int id = generateNonProjectActivityId(); // 4
        NonProjectActivity activity = new NonProjectActivity(name, id); // 5
        employee.addActivity(activity); // 6

        assert employee.getEmployeeNonProjectActivities().stream().
                anyMatch(i -> i.getName().equals(name))
                : "Postcondition";
        assert activity.getStartingDay() == null && activity.getDurationDays() == 0;

        System.out.println("Employee "+employee.getId()+" has successfully created "+activity.getTypeName()+": "+activity.getName()); // 7
        return activity;
    }

    // Simon Bom (s214751)
    public ProjectActivity createProjectActivity(Project project, String name) throws Exception {
        Employee employee = getLoggedInEmployee();

        if (project.hasActivity(name)) {
            throw new Exception("Project activity "+name+" for project "+project+" already exists");
        }

        int id = generateProjectActivityId();
        ProjectActivity activity = new ProjectActivity(name, id);
        project.addActivity(activity);
        System.out.println("Employee "+employee.getId()+" has successfully created "+activity.getTypeName()+": "+activity.getName());
        return activity;
    }

    public int generateProjectActivityId() throws Exception { //Simon Bom
        return 11;
    }

    //Max-Peter Schrøder (s214238)
    public int generateNonProjectActivityId() throws Exception {
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

    //Max-Peter Schrøder (s214238)
    //Assert statements added by Emil Wille Andersen (s194501)
    public void registerEmployee(EmployeeInfo employeeInfo) throws Exception {
        // Pre-condition: Check that no employee with the same ID exists in the repository
        assert employeeRepository.stream()
                .noneMatch(e -> e.getId().equals(employeeInfo.getId()))
                : "Precondition failed: Employee is already registered";

        Employee employee = findEmployee(employeeInfo);
        if (employee != null) {
            throw new Exception("Employee is already registered");
        }
        employeeRepository.add(employeeInfo.asEmployee());
        System.out.println("Employee with ID "+employeeInfo.getId()+" has been registered");

        // Post-condition: Verify that the employee has been added to the repository
        assert employeeRepository.stream()
                .anyMatch(e -> e.getId().equals(employeeInfo.getId()))
                : "Postcondition failed: Employee was not added to the repository";
    }

    //Max-Peter Schrøder (s214238)
    public Employee getLoggedInEmployee() throws Exception {
        checkEmployeeLoggedIn();
        EmployeeInfo ei = new EmployeeInfo(loggedInId);
        return findEmployee(ei);
    }

    //Max-Peter Schrøder (s214238)
    private void checkEmployeeLoggedIn() throws Exception {
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

    //Sebastian A. Ladegaard - s215530
    public String getProjectName(int id) {
        String name = "";
        for (int i = 0; i < projectRepository.size(); i++) {
            if (projectRepository.get(i).getProjectId() == id) {
                return name = projectRepository.get(i).getProjectName();
            }

        }
        return name;
    }

    //Sebastian A. Ladegaard - s215530
    public boolean projectExist(String name) {
        for (int i = 0; i < projectRepository.size(); i++) {
            if (projectRepository.get(i).getProjectName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    //Emil Wille Andersen (s194501)
    public Project getProject(String name) throws Exception {
        for (Project project : projectRepository){
            if (project.getProjectName().equals(name)){
                return project;
            }
        } throw new Exception("No project with name "+name+" exists.");
    }

    // Simon Bom (s214751)
    public List<Employee> getAvailableEmployees(DateServer startingDate, int duration) {
        List<Employee> availableEmployees = new ArrayList<>();
        for (Employee employee : getEmployeeRepository()){
            if (employee.isAvailableDuring(startingDate, duration)) {
                availableEmployees.add(employee);
            }
        }
        return availableEmployees;
    }

    // Simon Bom (s214751)
    public boolean anEmployeeIsAvailable(DateServer startingDate, int duration){
        return !getAvailableEmployees(startingDate, duration).isEmpty();
    }

    //Charlotte Grimstrup (s204382)
    public int generateProjectActivityID(List<ProjectActivity> projectActivities){
        int id = 0;
        id=projectActivities.size();
        for (ProjectActivity projectActivity : projectActivities){
            if (projectActivity.getId().equals(id) && id <=98){
                id++;
            }
        }
        return id;
    }
    //Charlotte Grimstrup (s204382)
    public ProjectActivity createProjectActivity2(String project, String name) throws Exception {
        if (!projectExist(project)){ //1
            throw new Exception("This project doesn't exist"); //2
        }

        Project projectName = getProject(project); //3
        List<ProjectActivity> projectActivities =projectName.getProjectActivities(); //4

        if (projectActivities.isEmpty()){ //5
            ProjectActivity projectActivity = new ProjectActivity(name, generateProjectActivityID(projectActivities)); //6
            projectName.addActivity(projectActivity); //7
            return projectActivity;
        }else {
            if (projectName.hasActivity(name)){//8
                throw new Exception("This project activity name already exists as a activity"); //9
            }
            ProjectActivity projectActivity = new ProjectActivity(name, generateProjectActivityID(projectActivities)); //10
            projectName.addActivity(projectActivity); //11
            return projectActivity;
        }
    }

    public boolean assignEmployeeToActivity(Employee empl, String projectActivity, String project) throws Exception {
        Project projectName = getProject(project);
        if(!projectName.hasActivity(projectActivity)){
            throw new Exception("Activity does not exist in the given project");
        }
        if (!getLoggedInId().matches(projectName.getManagerId())){
            throw new Exception("You are not the project manager for this project");
        }
        if (empl.activityExists(projectActivity)){
            throw new Exception("Already assigned to project");
        }
        //empl.equals(projectName.getActivity(projectActivity).getAssignedEmployee())
        if (projectName.getActivity(projectActivity).getAssignedEmployee() != null ){
            throw new Exception("Another employee is already assigned to this activity");
        }
        if(empl.getEmployeeProjectActivities().size() >= 20){
            throw new Exception("Employee cannot currently take on more activities");
        }
        empl.addActivity(projectName.getActivity(projectActivity));
        projectName.getActivity(projectActivity).setAssignedEmployee(empl);

        return true;
    }
}


