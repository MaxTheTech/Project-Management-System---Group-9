package dtu.projectManagementSystem.acceptance_tests.steps;

import dtu.projectManagementSystem.app.SoftwareHuset;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;

public class ProjectSteps {
    private SoftwareHuset softwareHuset = new SoftwareHuset();

    public ProjectSteps(SoftwareHuset softwareHuset) {
        this.softwareHuset = softwareHuset;
    }
    @Given("an employee is logged in")
    public void an_employee_is_logged_in() {
        int i = 1;

    }
    @Given("the current date is in the year {int}")
    public void the_current_date_is_in_the_year(int int1) {
        Assertions.assertEquals(int1, softwareHuset.getYear());
    }
    @When("the employee creates a new project with the name {string}")
    public void the_employee_creates_a_new_project_with_the_name(String string) {
        softwareHuset.createProject(string);
    }
    @Then("a project with the name {string} and ID {int} is created, where 001 is the number of the project")
    public void a_project_with_the_name_and_id_is_created_where_xyz_is_the_number_of_the_project(String string, int int1) {
        Assertions.assertEquals(string,softwareHuset.getProjectName(int1));
    }

    @Given("a project with the name {string} exists")
    public void a_project_with_the_name_exists(String string) {
        Assertions.assertTrue(softwareHuset.projectExist(string));
    }

    @Then("the project is not created, and an error message {string} appears")
    public void the_project_is_not_created_and_an_error_message_appears(String string) {
        // Write code here that turns the phrase above into concrete actions

    }
}
