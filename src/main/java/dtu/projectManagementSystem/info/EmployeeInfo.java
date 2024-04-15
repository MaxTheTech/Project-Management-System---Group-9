package dtu.projectManagementSystem.info;
import dtu.projectManagementSystem.domain.Employee;


public class EmployeeInfo {

    private String id;
    public EmployeeInfo(String id) {
        this.id = id;
    }

    public EmployeeInfo(Employee employee) {
        this.id = employee.getId();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Employee asEmployee() {
        return new Employee(id);
    }
}

