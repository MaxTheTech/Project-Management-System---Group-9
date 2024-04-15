package dtu.projectManagementSystem.app;

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

    public boolean isLoggedIn() { return isLoggedIn; }

    public void setLoggedIn(Boolean l) {
        isLoggedIn = l;
    }

    public String getLoggedInId() { return loggedInId; }

    public Employee findEmployee(EmployeeInfo ei) {
        Employee employeeStream = employeeRepository.stream()
                .filter(e -> e.getId().equals(ei.getId()))
                .findAny()
                .orElse(null);
        return employeeStream;
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
            throw new Exception("Employee with ID: "+ loggedInId +" is not logged in");
        }
    }
}


    //public SoftwareHuset(){
    //}



