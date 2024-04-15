package dtu.projectManagementSystem.acceptance_tests.steps;

import dtu.projectManagementSystem.acceptance_tests.helper.ErrorMessageHolder;
import dtu.projectManagementSystem.app.SoftwareHuset;
import dtu.projectManagementSystem.domain.Project;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;

public class ProjectSteps {
    private SoftwareHuset softwareHuset = new SoftwareHuset();
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

    }

    @Then("the project is not created, and an error message {string} appears")
    public void the_project_is_not_created_and_an_error_message_appears(String string) throws Exception {
        Assertions.assertEquals(string,errorMessage.getErrorMessage());
    }
}
