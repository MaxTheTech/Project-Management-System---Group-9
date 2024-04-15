package dtu.projectManagementSystem.app;

import java.util.ArrayList;
import dtu.projectManagementSystem.domain.Project;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.stream.Stream;

import dtu.projectManagementSystem.domain.Employee;
import dtu.projectManagementSystem.domain.Activity;
import dtu.projectManagementSystem.info.EmployeeInfo;
import dtu.projectManagementSystem.domain.Project;

public class SoftwareHuset {

    private boolean isLoggedIn = false;
    private String loggedInId;
    private List<Employee> employeeRepository = new ArrayList<>();
    private List<Project> projectRepository = new ArrayList<>();
    private static String currentlyLoggedIn;
    private ArrayList<Project> projectList = new ArrayList<>();
    private DateServer date = new DateServer();

    public void employeeLogin(String id) throws Exception {
        EmployeeInfo ei = new EmployeeInfo(id);
        Employee employee = findEmployee(ei);

        if (employee == null) {
            throw new Exception("Employee not found with ID: " + id);
        }
        isLoggedIn = true;
        loggedInId = employee.getId();
        System.out.print("Employee has successfully logged in with ID: " + employee.getId());
    }

    public void setLoggedIn(Boolean l) {
        isLoggedIn = l;
    }


    public Employee findEmployee(EmployeeInfo ei) {
        Employee employeeStream = employeeRepository.stream()
                .filter(e -> e.getId().equals(ei.getId()))
                .findAny()
                .orElse(null);
        return employeeStream;
    }

    public SoftwareHuset() {
    }

    public static void setCurrentlyLoggedIn(String login) {
        currentlyLoggedIn = login;
    }

    public static String getCurrentlyLoggedIn() {
        return currentlyLoggedIn;
    }

    public boolean isLoggedIn() {
        return this.isLoggedIn;
    }

    public static List<String> employees;


    public void createProject(String projectName) {
        int id = generateProjectId();
        Project project = new Project(projectName, id);
        projectList.add(project);
    }

    private int generateProjectId() {
        return (date.year - 2000) * 1000 + projectList.size() + 1;
    }

    public void registerEmployee(EmployeeInfo e) throws Exception {
        Employee employee = findEmployee(e);
        if (employee != null) {
            throw new Exception("Employee is already registered");
        }
        employeeRepository.add(e.asEmployee());
    }

    private void checkEmployeeLoggedIn() throws Exception {
        if (!isLoggedIn()) {
            throw new Exception("Employee with ID: " + loggedInId + " is not logged in");
        }
    }

    public int getYear() {
        return date.year;
    }


    public String getProjectName(int id) {
        String name = "";
        for (int i = 0; i < projectList.size(); i++) {
            if (projectList.get(i).getProjectId() == id) {
                return name = projectList.get(i).getProjectName();
            }

        }
        return name;
    }

    public boolean projectExist(String name) {
        for (int i = 0; i < projectList.size(); i++) {
            if (projectList.get(i).getProjectName().equals(name)) {
                return true;
            }
        }
        return false;
    }

}


