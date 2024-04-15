package dtu.projectManagementSystem.acceptance_tests.helper;

import dtu.projectManagementSystem.app.SoftwareHuset;
import dtu.projectManagementSystem.info.EmployeeInfo;

public class EmployeeHelper { //Max-Peter Schrøder (s214238)
    private EmployeeInfo employee;
    private SoftwareHuset softwareHuset;

    public EmployeeHelper(SoftwareHuset softwareHuset) {
        this.softwareHuset = softwareHuset;
    }

    public EmployeeInfo getEmployee() {
        if (employee == null) {
            employee = getExampleEmployee();
        }
        return employee;
    }

    public void setEmployee(String id) {
        employee = new EmployeeInfo(id);
    }

    public EmployeeInfo registerExampleEmployee() throws Exception {
        employee = getExampleEmployee();
        softwareHuset.registerEmployee(employee);
        return employee;
    }

    public EmployeeInfo getExampleEmployee() {
        EmployeeInfo empl = new EmployeeInfo("mps");
        return empl;
    }
}
