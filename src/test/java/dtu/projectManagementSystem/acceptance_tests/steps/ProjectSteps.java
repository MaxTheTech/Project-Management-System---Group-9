package dtu.projectManagementSystem.acceptance_tests.steps;

import dtu.projectManagementSystem.acceptance_tests.helper.ErrorMessageHolder;
import dtu.projectManagementSystem.acceptance_tests.helper.ProjectHelper;
import dtu.projectManagementSystem.app.SoftwareHuset;
import dtu.projectManagementSystem.domain.Project;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;

import java.util.HashMap;

public class ProjectSteps {
    private SoftwareHuset softwareHuset = new SoftwareHuset();

    private Project project;

    //project parameters:
    private int durationInWeeks;
    private int startingWeek;
    private int startingYear;
    private int expectedWorkload;
    private ErrorMessageHolder errorMessage;

    public ProjectSteps(SoftwareHuset softwareHuset, ErrorMessageHolder errorMessage) {
        this.softwareHuset = softwareHuset;
        this.errorMessage = errorMessage;
    }

    @Given("the current date is in the year {int}")
    public void the_current_date_is_in_the_year(int int1) {
        Assertions.assertEquals(int1, softwareHuset.getYear());
    }

    @When("the employee creates a new project with the name {string}")
    public void the_employee_creates_a_new_project_with_the_name(String string) {
        try {
            softwareHuset.createProject(string);
        } catch (Exception e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

    @Then("a project with the name {string} and ID {int} is created, where 001 is the number of the project")
    public void a_project_with_the_name_and_id_is_created_where_xyz_is_the_number_of_the_project(String string, int int1) {
        Assertions.assertEquals(string,softwareHuset.getProjectName(int1));
    }

    @Given("a project with the name {string} exists")
    public void a_project_with_the_name_exists(String string) throws Exception {
        softwareHuset.createProject(string);
        Assertions.assertTrue(softwareHuset.projectExist(string));
        this.project=softwareHuset.getProject(string);

    }

    @Then("the project is not created, and an error message {string} appears")
    public void the_project_is_not_created_and_an_error_message_appears(String string) throws Exception {
        Assertions.assertEquals(string,errorMessage.getErrorMessage());
    }

    @When("the employee self-assigns themselves as project manager of the project")
    public void theEmployeeSelfAssignsThemselvesAsProjectManagerOfTheProject() {
        try{
            project.setManagerId(softwareHuset.getLoggedInId());
        } catch (Exception e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

    @And("the project has an existing project manager")
    public void theProjectHasAnExistingProjectManager() {
        //Måske tilføj hvem den specifikke manager er?
        this.project.setHasManager(true);
    }

    @Then("they become the project manager of the project")
    public void theyBecomeTheProjectManagerOfTheProject() {
        Assertions.assertEquals(softwareHuset.getLoggedInId(),this.project.getManagerId());
    }

    @Then("they do not become the project manager, and an error message {string} appears")
    public void theyDoNotBecomeTheProjectManagerAndAnErrorMessageAppears(String string) {
        Assertions.assertEquals(string,errorMessage.getErrorMessage());
    }


    @And("he is the project manager of a project {string}")
    public void heIsTheProjectManagerOfAProject(String string) throws Exception {
        softwareHuset.createProject(string);
        this.project=softwareHuset.getProject(string);
        project.setManagerId(softwareHuset.getLoggedInId());
        Assertions.assertEquals(project.getManagerId(),softwareHuset.getLoggedInId());
    }

    @When("the project manager sets the starting week as {int}, the duration as {int} weeks, the year as {int}, and expected workload as {int} hours")
    public void theProjectManagerSetsTheStartingWeekAsTheDurationAsWeeksTheYearAsAndExpectedWorkloadAsHours(int startingWeek, int duration, int startingYear, int expectedWorkload) {
        this.startingWeek=startingWeek;
        this.project.setStartingWeek(startingWeek);
        this.durationInWeeks=duration;
        this.project.setDurationInWeeks(duration);
        this.startingYear=startingYear;
        this.project.setStartingYear(startingYear);
        this.expectedWorkload=expectedWorkload;
        this.project.setExpectedWorkloadHours(expectedWorkload);
    }

    @Then("the project has these parameters set")
    public void theProjectHasTheseParametersSet() {
        Assertions.assertEquals(this.startingWeek,this.project.getStartingWeek());
        Assertions.assertEquals(this.durationInWeeks,this.project.getDurationInWeeks());
        Assertions.assertEquals(this.startingYear,this.project.getStartingYear());
        Assertions.assertEquals(this.expectedWorkload,this.project.getExpectedWorkloadHours());
    }

    @And("the project has a set starting week, duration and expected workload")
    public void theProjectHasASetStartingWeekDurationAndExpectedWorkload() {
        this.project.setStartingWeek(ProjectHelper.getProjectParameterExamples().get("startingWeek1"));
        this.project.setDurationInWeeks(ProjectHelper.getProjectParameterExamples().get("duration1"));
        this.project.setExpectedWorkloadHours(ProjectHelper.getProjectParameterExamples().get("expectedWorkload1"));
    }

    @When("the project manager updates the parameters")
    public void theProjectManagerUpdatesTheParameters() {
        this.project.setStartingWeek(ProjectHelper.getProjectParameterExamples().get("startingWeek2"));
        this.project.setDurationInWeeks(ProjectHelper.getProjectParameterExamples().get("duration2"));
        this.project.setExpectedWorkloadHours(ProjectHelper.getProjectParameterExamples().get("expectedWorkload2"));
    }

    @Then("the project parameters are changed")
    public void theProjectParametersAreChanged() {
        Assertions.assertNotEquals(this.project.getStartingWeek(), (int) ProjectHelper.getProjectParameterExamples().get("startingWeek1"));
        Assertions.assertNotEquals(this.project.getDurationInWeeks(), (int) ProjectHelper.getProjectParameterExamples().get("duration1"));
        Assertions.assertNotEquals(this.project.getExpectedWorkloadHours(), (int) ProjectHelper.getProjectParameterExamples().get("expectedWorkload1"));
    }

    @When("he changes the name of the project to {string}")
    public void heChangesTheNameOfTheProjectTo(String string) {
        this.project.setSoftwareHuset(this.softwareHuset);
        try{
            project.setProjectName(string);
        } catch (Exception e) {
            errorMessage.setErrorMessage(e.getMessage());
        }

    }

    @Then("the name of the project is changed to {string}")
    public void theNameOfTheProjectIsChangedTo(String string) {
        Assertions.assertEquals(project.getProjectName(),string);
    }

    @And("another project exists with the name {string}")
    public void anotherProjectExistsWithTheName(String string) throws Exception {
        softwareHuset.createProject(string);
    }

    @Then("the project name is not changed and an error message {string} appears")
    public void theProjectNameIsNotChangedAndAnErrorMessageAppears(String string) {
        Assert.assertEquals(string, errorMessage.getErrorMessage());
    }
}
