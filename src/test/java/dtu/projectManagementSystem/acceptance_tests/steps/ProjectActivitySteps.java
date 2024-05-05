package dtu.projectManagementSystem.acceptance_tests.steps;

import dtu.projectManagementSystem.acceptance_tests.helper.EmployeeHelper;
import dtu.projectManagementSystem.acceptance_tests.helper.ErrorMessageHolder;
import dtu.projectManagementSystem.app.SoftwareHuset;
import dtu.projectManagementSystem.domain.Project;
import dtu.projectManagementSystem.domain.ProjectActivity;
import dtu.projectManagementSystem.info.EmployeeInfo;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;

public class ProjectActivitySteps {
    private SoftwareHuset softwareHuset = new SoftwareHuset();
    public EmployeeHelper helper;
    private ErrorMessageHolder errorMessage;

    private Project project;
    private String projectActivityName;
    private ProjectActivity projectActivity;


    public ProjectActivitySteps(SoftwareHuset softwareHuset, ErrorMessageHolder errorMessage, EmployeeHelper helper) {
        this.softwareHuset = softwareHuset;
        this.errorMessage = errorMessage;
        this.helper = helper;
    }

    //Charlotte Grimstrup (s204382)
    //Feature 7 scenario 1
    @Given("he is the project manager of the project {string}")
    public void heIsTheProjectManagerOfAProject(String string) {
        try {
            softwareHuset.createProject(string);
            this.project=softwareHuset.getProject(string);
            project.setManagerId(softwareHuset.getLoggedInId());
            Assertions.assertEquals(this.project.getManagerId(),softwareHuset.getLoggedInId());

        } catch (Exception e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
    }
    @When("he creates a project activity {string} in the project")
    public void heCreatesAProjectActivityInAProject(String string) throws Exception {

        //try {
            projectActivityName=string;
            this.projectActivity=softwareHuset.createProjectActivity(project.getProjectName(), projectActivityName);
            project.addActivity(projectActivity);
        /*} catch (Exception e) {
            errorMessage.setErrorMessage(e.getMessage());
        }*/

    }
    @Then("the project activity is created")
    public void theProjectActivityIsCreated() {
        Assertions.assertEquals(this.projectActivity, project.getActivity(projectActivityName));

    }


    //Charlotte Grimstrup (s204382)
    //Feature 7 scenario 2

    @And("the employee is the project manager of the project {string}")
    public void theEmployeeIsTheProjectManagerOfTheProject(String string) throws Exception {
        softwareHuset.createProject(string);
        this.project=softwareHuset.getProject(string);
        project.setManagerId(softwareHuset.getLoggedInId());
        Assert.assertEquals(this.project.getManagerId(), softwareHuset.getLoggedInId());

    }

    @And("an activity with the name {string} exists in a project")
    public void anActivityWithTheNameExistsInAProject(String string) throws Exception {
        try {
            projectActivityName=string;
            this.projectActivity=softwareHuset.createProjectActivity(project.getProjectName(), projectActivityName);
            project.addActivity(projectActivity);
        } catch (Exception e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

    @When("the employee creates an activity in the project with the name {string}")
    public void theEmployeeCreatesAnActivityInTheProjectWithTheName(String string) {

        try {
            this.projectActivity=softwareHuset.createProjectActivity(project.getProjectName(), string);
            Assertions.assertNull(projectActivity);

        } catch (Exception e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
    }
    @Then("the project activity is not created, and an error message {string} appears")
    public void theProjectActivityIsNotCreatedAndAnErrorMessageAppears(String string) {
        Assertions.assertEquals(string, errorMessage.getErrorMessage());

    }

    //Charlotte Grimstrup (s204382)
    //Feature 8 scenario 1
    @When("the project manager assigns an employee to the project activity")
    public void theProjectManagerAssignsAnEmployeeToTheProjectActivity() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @And("The employee is assigned to the project activity")
    public void theEmployeeIsAssignedToTheProjectActivity() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    //Charlotte Grimstrup (s204382)
    //Feature 8 scenario 2

    @Given("another employee exists, who cannot take on more activities currently")
    public void anotherEmployeeExistsWhoCannotTakeOnMoreActivitiesCurrently() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("the employee is not assigned to the project activity")
    public void theEmployeeIsNotAssignedToTheProjectActivity() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("an error message {string} appears")
    public void anErrorMessageAppears(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    //Charlotte Grimstrup (s204382)
    //Feature 8 scenario 4
    @Given("no activity with the name {string} exists in the project")
    public void noActivityWithTheNameExistsInTheProject(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("the project manager tries to assign an employee to the activity")
    public void theProjectManagerTriesToAssignAnEmployeeToTheActivity() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("an error message {string}")
    public void anErrorMessage(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    //Charlotte Grimstrup (s204382)
    //Feature 8 scenario 5

    @Given("another employee is already assigned to the activity")
    public void anotherEmployeeIsAlreadyAssignedToTheActivity() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("the project manager assigns the other employee to {string}")
    public void theProjectManagerAssignsTheOtherEmployeeTo(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("the other employee is still assigned to {string} and an error message {string} appears")
    public void theOtherEmployeeIsStillAssignedToAndAnErrorMessageAppears(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    //Charlotte Grimstrup (s204382)
    //Feature 8 scenario 6
    @Given("the employee is not the project manager of a project {string}")
    public void theEmployeeIsNotTheProjectManagerOfAProject(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("the project manager assigns the an employee to {string}")
    public void theProjectManagerAssignsTheAnEmployeeTo(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }



}
