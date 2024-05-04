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
        //Make sure that projectRepository contains a project named "testProject"
        softwareHuset.createProject("testProject");
        Assertions.assertTrue(softwareHuset.getProjectRepository().stream().
                anyMatch(i -> i.getProjectName().equals("testProject")));

        //Try to create another project with the same name: Test Case B,
        //and see if the exception is thrown correctly
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
        AssertionError assertionError = Assertions.assertThrows(AssertionError.class, () ->
                softwareHuset.registerEmployee(new EmployeeInfo("EmiB")));

        //Assert that the system gives the correct error message
        Assertions.assertEquals("Precondition failed: Employee is already registered", assertionError.getMessage());
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


    //Test by Max-Peter Schrøder (s214238)
    @Test
    public void createNonProjectActivity_TestSetA() throws Exception {
        //Set the name of the non-project activity
        String name = "Vacation";

        //Assert that the employee is not logged into the system.
        Assertions.assertFalse(softwareHuset.isLoggedIn());

        //Catch exception when trying to create a non-project activity without being logged in
        Exception exception = Assertions.assertThrows(Exception.class, () ->
                softwareHuset.createNonProjectActivity(name));

        //Assert that the system gives the correct error message
        Assertions.assertEquals("Employee is not logged in. Log-in with employee ID to access command.", exception.getMessage());

        //Print the error message
        System.out.println(exception.getMessage());
    }



    //Test by Max-Peter Schrøder (s214238)
    @Test
    public void createNonProjectActivity_TestSetB() throws Exception {
        //Set the id of the logged in employee and the name of the non-project activity
        String id = "mps";
        String name = "Vacation";

        //Register the employee and login to the system
        softwareHuset.registerEmployee(new EmployeeInfo(id));
        softwareHuset.employeeLogin(id);

        //Assert that the employee is logged in
        Assertions.assertTrue(softwareHuset.isLoggedIn());
        Assertions.assertSame(id, softwareHuset.getLoggedInId());

        //Create first non-project activity with the given name
        softwareHuset.createNonProjectActivity(name);

        //Catch exception when trying to create another non-project activity with the same name
        Exception exception = Assertions.assertThrows(Exception.class, () ->
                softwareHuset.createNonProjectActivity(name));

        //Assert that the system gives the correct error message
        Assertions.assertEquals("Non-project activity "+name+" for employee "+softwareHuset.getLoggedInEmployee().getId()+" already exists", exception.getMessage());

        //Print the error message
        System.out.println(exception.getMessage());
    }


    //Test by Max-Peter Schrøder (s214238)
    @Test
    public void createNonProjectActivity_TestSetC() throws Exception {
        //Set the id of the logged in employee and the name of the non-project activity
        String id = "mps";
        String name = "Vacation";

        //Register the employee and login to the system
        softwareHuset.registerEmployee(new EmployeeInfo(id));
        softwareHuset.employeeLogin(id);

        //Assert that the employee is logged in
        Assertions.assertTrue(softwareHuset.isLoggedIn());
        Assertions.assertSame(id, softwareHuset.getLoggedInId());

        //Create the non-project activity with the given name
        softwareHuset.createNonProjectActivity(name);

        //Assert that the non-project activity with the given name has been created for the employee
        Assertions.assertTrue(softwareHuset.getLoggedInEmployee().getEmployeeNonProjectActivities().stream().
                anyMatch(i -> i.getName().equals(name)));
    }


}
