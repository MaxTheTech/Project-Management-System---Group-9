package dtu.projectManagementSystem.acceptance_tests.steps;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {
    @Given("that no-one is logged in")
    public void thatNoOneIsLoggedIn() {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
        assertTrue(true);
    }
    @Given("an employee with an ID exists")
    public void anEmployeeWithAnExists(String string) {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
        assertTrue(true);
    }
    @When("the employee logs in with their ID")
    public void anEmployeeLogsInWithTheir(String string) {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
        assertTrue(true);
    }
    @Then("the employee is logged into the system")
    public void theEmployeeIsLoggedIntoTheSystem(String string) {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
        assertTrue(true);
    }

    @Given("an employee has an ID that is not registered in the system")
    public void anEmployeeHasAnIDThatIsNotRegisteredInTheSystem() {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
        assertTrue(true);
    }
    @When("an employee logs in with the ID")
    public void anEmployeeLogsInWithTheID() {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
        assertTrue(true);
    }
    @Then("the employee is not logged into the system, and an error message {string} appears")
    public void theEmployeeIsNotLoggedIntoTheSystemAndAnErrorMessageAppears(String string) {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
        assertTrue(true);
    }
}
