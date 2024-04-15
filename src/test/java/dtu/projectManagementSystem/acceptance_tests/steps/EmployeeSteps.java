package dtu.projectManagementSystem.acceptance_tests.steps;

import dtu.projectManagementSystem.acceptance_tests.helper.ErrorMessageHolder;
import dtu.projectManagementSystem.app.SoftwareHuset;
import dtu.projectManagementSystem.domain.Employee;
import dtu.projectManagementSystem.domain.NonProjectActivity;
import dtu.projectManagementSystem.app.DateServer;
import dtu.projectManagementSystem.info.EmployeeInfo;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class EmployeeSteps { //Max-Peter Schrøder (s214238)
    private SoftwareHuset softwareHuset;
    private NonProjectActivity nonProjectActivity;
    private ErrorMessageHolder errorMessage;
    private DateServer startingDay;
    private int durationDays;
    private String activityName;
    private Employee employee;

    public EmployeeSteps(SoftwareHuset softwareHuset, ErrorMessageHolder errorMessage) {
        this.softwareHuset = softwareHuset;
        this.errorMessage = errorMessage;
    }

    @When("the employee creates a non-project activity with the name {string}")
    public void theEmployeeCreatesANonProjectActivityWithTheName(String name) {
        try {
            activityName = name;
            softwareHuset.createNonProjectActivity(activityName);
            employee = softwareHuset.getLoggedInEmployee();
            nonProjectActivity = employee.getNonProjectActivity(activityName);
        } catch (Exception e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

    @Then("the non-project activity is created")
    public void theNonProjectActivityIsCreated() {
        Assert.assertEquals(activityName, employee.getNonProjectActivity(activityName).getName());
    }

    @Given("there is a non-project activity with the name {string}")
    public void thereIsANonProjectActivityWithTheName(String name) throws Exception {
        activityName = name;
        softwareHuset.createNonProjectActivity(activityName);
        employee = softwareHuset.getLoggedInEmployee();
        nonProjectActivity = employee.getNonProjectActivity(activityName);
    }

    @Then("the error message {string} appears")
    public void theErrorMessageAppears(String error) throws Exception {
        Assert.assertEquals(error, errorMessage.getErrorMessage());
        System.out.println(errorMessage.getErrorMessage());
    }

    @When("the employee sets the starting day to a given day and the duration to {int} days")
    public void theEmployeeSetsTheStartingDayToAGivenDayAndTheDurationToDays(Integer duration) {
        durationDays = duration;
        nonProjectActivity.setStartingDay(startingDay);
        nonProjectActivity.setDurationDays(durationDays);
    }
    @Then("the starting day and duration are set")
    public void theStartingDayAndDurationAreSet() {
        Assert.assertEquals(nonProjectActivity.getStartingDay(), startingDay);
        Assert.assertEquals(nonProjectActivity.getDurationDays(), durationDays);
    }

}
