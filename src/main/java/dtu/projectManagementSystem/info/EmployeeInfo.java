package dtu.projectManagementSystem.info;
import dtu.projectManagementSystem.domain.Employee;


public class EmployeeInfo { //Max-Peter Schrøder (s214238)

    private String id;
    public EmployeeInfo(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Employee asEmployee() throws Exception {
        return new Employee(id);
    }
}

