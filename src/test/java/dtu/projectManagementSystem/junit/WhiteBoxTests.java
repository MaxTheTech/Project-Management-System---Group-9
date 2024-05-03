package dtu.projectManagementSystem.junit;

import dtu.projectManagementSystem.app.SoftwareHuset;
import dtu.projectManagementSystem.info.EmployeeInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class WhiteBoxTests {

    private final SoftwareHuset softwareHuset = new SoftwareHuset();

    @Test
    public void createProject_TestSetA() throws Exception {
        //Make sure that projectRepository is empty as specified in the white-box planning section
        Assertions.assertEquals(0, softwareHuset.getProjectRepository().size());

        //Create project and test that it was successfully added
        softwareHuset.createProject("testProject");
        Assertions.assertTrue(softwareHuset.getProjectRepository().stream().
                anyMatch(i -> i.getProjectName().equals("testProject")));

    }
    @Test
    public void createProject_TestSetB() throws Exception {
        //Make sure that projectRepository contains a project named "testProject" as specified in the white-box planning section
        softwareHuset.createProject("testProject");
        Assertions.assertTrue(softwareHuset.getProjectRepository().stream().
                anyMatch(i -> i.getProjectName().equals("testProject")));

        //Try to create another project with the same name: Test Case B, and see if the exception is thrown correctly
        Exception exception = Assertions.assertThrows(Exception.class, () ->
            softwareHuset.createProject("testProject"));

        Assertions.assertEquals("Project already exists", exception.getMessage());

    }

    //Test by Emil Wille Andersen (s194501)
    @Test
    public void registerEmployee_TestSetA() throws Exception {
        //Make sure that there is no employee registered with ID "EmiA"
        Assertions.assertNull(softwareHuset.findEmployee(new EmployeeInfo("EmiA")));

        //Try to register the ID "EmiA" in the system
        softwareHuset.registerEmployee(new EmployeeInfo("EmiA"));

        //Assert that the ID "EmiA" is now in the system
        Assertions.assertEquals(softwareHuset.findEmployee(new EmployeeInfo("EmiA")).getId(),"EmiA");
    }

    //Test by Emil Wille Andersen (s194501)
    @Test
    public void registerEmployee_TestSetB() throws Exception {
        //Make sure that there is an employee registered with ID "EmiB"
        softwareHuset.registerEmployee(new EmployeeInfo("EmiB"));

        //Try to register the ID "EmiB" in the system
        Exception exception = Assertions.assertThrows(Exception.class, () ->
                softwareHuset.registerEmployee(new EmployeeInfo("EmiB")));

        //Assert that the system gives the correct error message
        Assertions.assertEquals("Employee is already registered", exception.getMessage());
    }

    //Test by Emil Wille Andersen (s194501)
    @Test
    public void registerEmployee_TestSetC() throws Exception {

        //Try to register the ID "EmiLC" in the system
        Exception exception = Assertions.assertThrows(Exception.class, () ->
                softwareHuset.registerEmployee(new EmployeeInfo("EmilC")));

        //Assert that the system gives the correct error message
        Assertions.assertEquals("EmilC invalid: \nID has to be only letters and no more than 4", exception.getMessage());
    }




}
