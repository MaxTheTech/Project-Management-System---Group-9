package dtu.projectManagementSystem.junit;

import dtu.projectManagementSystem.app.SoftwareHuset;
import dtu.projectManagementSystem.info.EmployeeInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WhiteBoxTests {

    private final SoftwareHuset softwareHuset = new SoftwareHuset();

    @Test
    public void createProject_TestSetA() throws Exception {
        //Setup
        EmployeeInfo admin = new EmployeeInfo("SebA");
        softwareHuset.registerEmployee(admin);
        softwareHuset.employeeLogin("SebA");

        //Make sure that projectRepository is empty as specified in the white-box planning section
        Assertions.assertEquals(0, softwareHuset.getProjectRepository().size());

        //Create project and test that it was successfully added
        softwareHuset.createProject("testProject");
        Assertions.assertTrue(softwareHuset.getProjectRepository().stream().
                anyMatch(i -> i.getProjectName().equals("testProject")));

    }

    public void createProject_TestSetB(){

    }

}
