package dtu.projectManagementSystem.acceptance_tests.steps;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import dtu.projectManagementSystem.acceptance_tests.helper.EmployeeHelper;
import dtu.projectManagementSystem.acceptance_tests.helper.ErrorMessageHolder;
import dtu.projectManagementSystem.app.SoftwareHuset;
import dtu.projectManagementSystem.info.EmployeeInfo;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LoginSteps {

    private SoftwareHuset softwareHuset;
    private EmployeeInfo employee;
    public EmployeeHelper helper;
    private ErrorMessageHolder errorMessage;

    public LoginSteps(SoftwareHuset softwareHuset, EmployeeHelper helper, ErrorMessageHolder errorMessage) {
        this.softwareHuset = softwareHuset;
        this.helper = helper;
        this.errorMessage = errorMessage;
    }

    @Given("that no-one is logged in")
    public void thatNoOneIsLoggedIn() {
        Assert.assertFalse(softwareHuset.isLoggedIn());
    }
    @Given("an employee with an ID exists")
    public void anEmployeeWithAnIdExists() throws Exception {
        employee = helper.registerExampleEmployee();
    }
    @When("the employee logs in with their ID")
    public void anEmployeeLogsInWithTheirId() throws Exception {
        try {
            softwareHuset.employeeLogin(employee.getId());
        } catch (Exception e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
    }
    @Then("the employee is logged into the system")
    public void theEmployeeIsLoggedIntoTheSystem() {
        Assert.assertTrue(softwareHuset.isLoggedIn());
    }

    @Given("an employee has an ID {string} that is not registered in the system")
    public void anEmployeeHasAnIDThatIsNotRegisteredInTheSystem(String id) {
        employee = new EmployeeInfo(id);
    }

    @Then("the employee is not logged into the system, and an error message {string} appears")
    public void theEmployeeIsNotLoggedIntoTheSystemAndAnErrorMessageAppears(String error) throws Exception {
        Assert.assertEquals("Employee not found with ID: mps", errorMessage.getErrorMessage());
    }
}
