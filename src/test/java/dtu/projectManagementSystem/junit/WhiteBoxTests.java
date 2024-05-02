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
    @Test
    public void createProject_TestSetB() throws Exception {
        //Setup
        EmployeeInfo admin = new EmployeeInfo("SebB");
        softwareHuset.registerEmployee(admin);
        softwareHuset.employeeLogin("SebB");

        //Make sure that projectRepository contains a project named "testProject" as specified in the white-box planning section
        softwareHuset.createProject("testProject");
        Assertions.assertTrue(softwareHuset.getProjectRepository().stream().
                anyMatch(i -> i.getProjectName().equals("testProject")));

        //Try to create another project with the same name: Test Case B, and see if the exception is thrown correctly
        Exception exception = Assertions.assertThrows(Exception.class, () ->
            softwareHuset.createProject("testProject"));

        Assertions.assertEquals("Project already exists", exception.getMessage());

    }

}
