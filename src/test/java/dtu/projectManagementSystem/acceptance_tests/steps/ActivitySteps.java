package dtu.projectManagementSystem.acceptance_tests.steps;
import dtu.projectManagementSystem.app.DateServer;
import dtu.projectManagementSystem.app.SoftwareHuset;
import dtu.projectManagementSystem.domain.ProjectActivity;
import dtu.projectManagementSystem.domain.RegistrationTicket;
import dtu.projectManagementSystem.info.EmployeeInfo;
import dtu.projectManagementSystem.domain.Project;
import org.junit.jupiter.api.Assertions;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.Objects;
import org.junit.Assert;

public class ActivitySteps {
    private SoftwareHuset softwareHuset = new SoftwareHuset();
    private EmployeeInfo employee;
    private Project project;
    private ProjectActivity activity;
    private int timeInHalfHours;
    private RegistrationTicket ticket;

    @Given("the employee {string} is logged in")
    public void theEmployeeIsLoggedIn(String username) throws Exception {
        employee = new EmployeeInfo(username);
        softwareHuset.registerEmployee(employee);
        softwareHuset.employeeLogin(username);
    }
    @Given("it is the current date")
    public void itIsTheCurrentDate() {
        // Write code here that turns the phrase above into concrete actions
        Assertions.assertTrue(true);
    }
    @Given("an activity with the name {string} exists in project {string}")
    public void anActivityWithTheNameExistsInProject(String activityName, String projectName) throws Exception {
        project = softwareHuset.createProject(projectName);
        activity = softwareHuset.createProjectActivity(project, activityName);
    }
    @When("the employee registers {int} half-hours spent on the activity")
    public void theEmployeeRegistersHalfHoursSpentOnTheActivity(int timeInHalfHours) throws Exception {
        // Write code here that turns the phrase above into concrete actions
        this.ticket = this.activity.createRegistrationTicket(employee.asEmployee(), 1, timeInHalfHours);
        this.timeInHalfHours = timeInHalfHours;
        Assertions.assertTrue(true );
    }
    @Then("the time spent by the employee is registered to the activity and the employee")
    public void theTimeSpentByTheEmployeeIsRegisteredToTheActivityAndTheEmployee() {
        // Write code here that turns the phrase above into concrete actions
        Assertions.assertEquals(this.ticket.getTimeInHalfHours(), this.timeInHalfHours);
    }


    // SECOND SCENARIO
    @Given("there is an activity with the name {string}")
    public void thereIsAnActivityWithTheName(String activityName) throws Exception {
        // Write code here that turns the phrase above into concrete actions
        activity = softwareHuset.createNonProjectActivity(activityName);
    }

    @When("the employee employee registers {string} days spent on the non-project activity")
    public void theEmployeeEmployeeRegistersDaysSpentOnTheNonProjectActivity(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the time spent by the employee is registered to the non-project activity and the employee")
    public void theTimeSpentByTheEmployeeIsRegisteredToTheNonProjectActivityAndTheEmployee() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }



}
