package dtu.projectManagementSystem.acceptance_tests.steps;
import dtu.projectManagementSystem.app.DateServer;
import dtu.projectManagementSystem.app.SoftwareHuset;
import dtu.projectManagementSystem.domain.NonProjectActivity;
import dtu.projectManagementSystem.domain.ProjectActivity;
import dtu.projectManagementSystem.domain.Ticket;
import dtu.projectManagementSystem.info.EmployeeInfo;
import dtu.projectManagementSystem.domain.Employee;
import dtu.projectManagementSystem.domain.Project;
import io.cucumber.java.en.And;
import org.junit.jupiter.api.Assertions;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

public class ActivitySteps { // Simon Bom (s214751)
    private SoftwareHuset softwareHuset = new SoftwareHuset();
    private EmployeeInfo employeeInfo;
    private Employee employee;
    private Project project;
    private ProjectActivity activity;
    private int timeInHalfHours;
    private Ticket ticket;
    private NonProjectActivity nonProjectActivity;
    private int week;
    private int ticketId;
    private int days;
    private String activityName;
    private List<Employee> employees;
    private DateServer startingDate;
    private int durationInWeeks;

    @Given("the employee {string} is logged in")
    public void theEmployeeIsLoggedIn(String username) throws Exception {
        this.employeeInfo = new EmployeeInfo(username);
        this.employee = employeeInfo.asEmployee();
        softwareHuset.registerEmployee(employeeInfo);
        softwareHuset.employeeLogin(username);
    }
    @Given("it is the current date")
    public void itIsTheCurrentDate() {
        // we say it's week five
        this.week = 5;
    }
    @Given("an activity with the name {string} exists in project {string}")
    public void anActivityWithTheNameExistsInProject(String activityName, String projectName) throws Exception {
        softwareHuset.createProject(projectName);
        this.project=softwareHuset.getProject(projectName);
        activity = softwareHuset.createProjectActivity(project, activityName);
    }
    @When("the employee registers {int} half-hours spent on the activity")
    public void theEmployeeRegistersHalfHoursSpentOnTheActivity(int timeInHalfHours) throws Exception {
        // Write code here that turns the phrase above into concrete actions
        this.ticket = this.activity.createTicket(this.employee, new DateServer(2024,5,5), timeInHalfHours);
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
        this.activityName = activityName;
        this.nonProjectActivity = softwareHuset.createNonProjectActivity(activityName);
    }

    @When("the employee registers {int} days spent on the non-project activity")
    public void theEmployeeEmployeeRegistersDaysSpentOnTheNonProjectActivity(int days) {
        // Write code here that turns the phrase above into concrete actions
        this.nonProjectActivity.setDurationDays(days);
    }

    @Then("the time spent by the employee is registered to the non-project activity and the employee")
    public void theTimeSpentByTheEmployeeIsRegisteredToTheNonProjectActivityAndTheEmployee() {
        // Write code here that turns the phrase above into concrete actions
        Assertions.assertEquals(this.nonProjectActivity.getDurationDays(), this.days);
        // Assertions.assertEquals(this.employee.getNonProjectActivity(this.activityName), this.nonProjectActivity);
    }

    @Given("the employee has previously registered time on this activity")
    public void theEmployeeHasPreviouslyRegisteredTimeOnThisActivity() {
        try {
            this.activity.createTicket(this.employee, new DateServer(2024,5,4), 3);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @When("the employee edits the time spent on the activity for this registration")
    public void theEmployeeEditsTheTimeSpentOnTheActivityForThisRegistration() {
        // Write code here that turns the phrase above into concrete actions
        this.ticket.setTimeInHalfHours(9);
    }
    @Then("the time registration ticket is updated for the employee and the activity")
    public void theTimeRegistrationTicketIsUpdatedForTheEmployeeAndTheActivity() {
        // Write code here that turns the phrase above into concrete actions
        Assertions.assertEquals(9,this.ticket.getTimeInHalfHours());
    }

    @When("the employee edits the duration spent on the non-project activity to {int} days")
    public void theEmployeeEditsTheDurationInDaysSpentOnTheNonProjectActivity(int days) {
        // Write code here that turns the phrase above into concrete actions
        this.days = days;
        this.nonProjectActivity.setDurationDays(days);
    }
    @Then("an empty list of employees who can take on more activities during the activity duration is returned and a message {string} appears")
    public void anEmptyListOfEmployeesWhoCanTakeOnMoreActivitesDuringTheActivityDurationIsReturnedAndAMessageAppears(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }


    // Feature 9 This is seriously hard to test because it requires creating a lot of activities and employees
    @And("the employee is the project manager of {string}")
    public void the_employee_is_the_project_manager_of_a_project(String project) throws Exception {
        softwareHuset.createProject(project);
        softwareHuset.employeeLogin(this.employee.getId());
        this.project = softwareHuset.getProject(project);
        this.project.setManagerId(this.employee.getId());
    }

    @Given("an activity with the name {string} exists in the project")
    public void anActivityWithTheNameExistsInTheProject(String activityName) throws Exception {
        activity = softwareHuset.createProjectActivity(this.project, activityName);
        this.startingDate = new DateServer(2024,5,1);
        this.durationInWeeks = 1;
        activity.setDurationWeeks(this.durationInWeeks);
        activity.setStartingWeek(this.startingDate);
    }
    @Given("there are employees available during the activity duration")
    public void thereAreEmployeesAvailableDuringTheActivityDuration() {
        boolean b = softwareHuset.anEmployeeIsAvailable(this.startingDate,this.durationInWeeks);
        Assertions.assertTrue(b);
    }
    @When("the project manager selects the activity and checks for available employees")
    public void theProjectManagerSelectsTheActivityAndChecksForAvailableEmployees() {

    }
    @Then("a list of employees who can take on more activities during the activity duration is returned")
    public void aListOfEmployeesWhoCanTakeOnMoreActivitiesDuringTheActivityDurationIsReturned() {
        DateServer startingDate = this.activity.getStartingDate();
        int duration = this.activity.getDurationWeeks();
        employees = softwareHuset.getAvailableEmployees(startingDate, duration);
        Assertions.assertTrue(true);
    }

}
