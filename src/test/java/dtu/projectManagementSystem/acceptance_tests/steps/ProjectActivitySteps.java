package dtu.projectManagementSystem.acceptance_tests.steps;

import dtu.projectManagementSystem.acceptance_tests.helper.EmployeeHelper;
import dtu.projectManagementSystem.acceptance_tests.helper.ErrorMessageHolder;
import dtu.projectManagementSystem.app.SoftwareHuset;
import dtu.projectManagementSystem.domain.Employee;
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
    private ErrorMessageHolder errorMessage;

    private Project project;
    private String projectActivityName;
    private ProjectActivity projectActivity;
    private Employee employee;
    private Employee employee2;

    public ProjectActivitySteps(SoftwareHuset softwareHuset, ErrorMessageHolder errorMessage, EmployeeHelper helper) {
        this.softwareHuset = softwareHuset;
        this.errorMessage = errorMessage;
    }

    //Charlotte Grimstrup (s204382)
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
    //Charlotte Grimstrup (s204382)
    @When("he creates a project activity {string} in the project")
    public void heCreatesAProjectActivityInAProject(String string) throws Exception {
            projectActivityName=string;
            this.projectActivity=softwareHuset.createProjectActivity2(project.getProjectName(), projectActivityName);
            project.addActivity(projectActivity);
    }
    //Charlotte Grimstrup (s204382)
    @Then("the project activity is created")
    public void theProjectActivityIsCreated() {
        Assertions.assertEquals(this.projectActivity, project.getActivity(projectActivityName));
    }


    //Charlotte Grimstrup (s204382)
    @And("the employee is the project manager of the project {string}")
    public void theEmployeeIsTheProjectManagerOfTheProject(String string) throws Exception {
        softwareHuset.createProject(string);
        this.project=softwareHuset.getProject(string);
        project.setManagerId(softwareHuset.getLoggedInId());
        Assert.assertEquals(project.getManagerId(), softwareHuset.getLoggedInId());
    }
    //Charlotte Grimstrup (s204382)
    @And("an activity with the name {string} exists in a project")
    public void anActivityWithTheNameExistsInAProject(String string) throws Exception {
        try {
            projectActivityName=string;
            this.projectActivity=softwareHuset.createProjectActivity2(project.getProjectName(), projectActivityName);
            project.addActivity(projectActivity);
        } catch (Exception e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
    }
    //Charlotte Grimstrup (s204382)
    @When("the employee creates an activity in the project with the name {string}")
    public void theEmployeeCreatesAnActivityInTheProjectWithTheName(String string) {
        try {
            this.projectActivity=softwareHuset.createProjectActivity2(project.getProjectName(), string);
            Assertions.assertNull(projectActivity);
        } catch (Exception e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
    }
    //Charlotte Grimstrup (s204382)
    @Then("the project activity is not created, and an error message {string} appears")
    public void theProjectActivityIsNotCreatedAndAnErrorMessageAppears(String string) {
        Assertions.assertEquals(string, errorMessage.getErrorMessage());
    }
    //Charlotte Grimstrup (s204382)
    @When("the project manager assigns an employee {string} to the project activity {string}")
    public void theProjectManagerAssignsAnEmployeeToTheProjectActivity(String empString, String Activity) throws Exception {
        EmployeeInfo testEmp = new EmployeeInfo(empString);
        softwareHuset.registerEmployee(testEmp);
        this.employee=softwareHuset.findEmployee(testEmp);
        this.projectActivity=project.getActivity(Activity);
        Assert.assertTrue(softwareHuset.assignEmployeeToActivity(employee, Activity, project.getProjectName()));
    }
    //Charlotte Grimstrup (s204382)
    @And("The employee is assigned to the project activity")
    public void theEmployeeIsAssignedToTheProjectActivity() {
        Assert.assertNotNull(employee.getProjectActivity(projectActivity.getName()));
    }

    //Charlotte Grimstrup (s204382)
    @Given("another employee exists, who cannot take on more activities currently")
    public void anotherEmployeeExistsWhoCannotTakeOnMoreActivitiesCurrently() throws Exception {
        EmployeeInfo testEmp = new EmployeeInfo("empl");
        softwareHuset.registerEmployee(testEmp);
        this.employee=softwareHuset.findEmployee(testEmp);
        //System.out.println("ID of employee "+employee.getEmployeeProjectActivities().size());

        for (int i = 0; i <= 19; i++) {
            softwareHuset.createProjectActivity2(project.getProjectName(), String.valueOf(i));
            softwareHuset.assignEmployeeToActivity(employee, String.valueOf(i), project.getProjectName());
            //System.out.println("ID of employee "+employee.getEmployeeProjectActivities().size());
        }
    }
    //Charlotte Grimstrup (s204382)
    @When("the project manager assigns the employee to the project activity {string}")
    public void the_project_manager_assigns_the_employee_to_the_project_activity(String string) {

        try {
            projectActivity=softwareHuset.createProjectActivity2(project.getProjectName(), "extraActivity");
            softwareHuset.assignEmployeeToActivity(employee, projectActivity.getName(), project.getProjectName());
        } catch (Exception e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
    }
    //Charlotte Grimstrup (s204382)
    @Then("the employee is not assigned to the project activity")
    public void theEmployeeIsNotAssignedToTheProjectActivity(){
        Assert.assertFalse(employee.activityExists(projectActivity));
    }

    //Charlotte Grimstrup (s204382)
    @Given("no activity with the name {string} exists in the project {string}")
    public void noActivityWithTheNameExistsInTheProject(String activityName, String projectName) throws Exception {
        this.project=softwareHuset.getProject(projectName);
        Assert.assertFalse(project.hasActivity(activityName));
    }

    //Charlotte Grimstrup (s204382)
    @When("the project manager tries to assign an employee to the activity {string}")
    public void theProjectManagerTriesToAssignAnEmployeeToTheActivity(String activityName) throws Exception {
        try {
            Assert.assertFalse(softwareHuset.assignEmployeeToActivity(employee, activityName, project.getProjectName()));
        }catch (Exception e){
            errorMessage.setErrorMessage(e.getMessage());
        }
    }
    //Charlotte Grimstrup (s204382)
    @Then("an error message {string} is given")
    public void anErrorMessage(String string) {
        Assertions.assertEquals(string, errorMessage.getErrorMessage());
    }

    //Charlotte Grimstrup (s204382)
    //Feature 8 scenario 5

    @Given("another employee is already assigned to the activity {string}")
    public void anotherEmployeeIsAlreadyAssignedToTheActivity(String Activity) throws Exception {
        EmployeeInfo secondTestEmp = new EmployeeInfo("tess");
        softwareHuset.registerEmployee(secondTestEmp);
        this.employee2 = softwareHuset.findEmployee(secondTestEmp);
        Assert.assertTrue(softwareHuset.assignEmployeeToActivity(employee2, projectActivity.getName(), project.getProjectName()));

    }
    @When("the project manager assigns the other employee to {string}")
    public void theProjectManagerAssignsTheOtherEmployeeTo(String string) {
        try {
            EmployeeInfo TestEmp = new EmployeeInfo("test");
            softwareHuset.registerEmployee(TestEmp);
            this.employee = softwareHuset.findEmployee(TestEmp);
            Assert.assertFalse(softwareHuset.assignEmployeeToActivity(employee, projectActivity.getName(), project.getProjectName()));
        }catch (Exception e){
            errorMessage.setErrorMessage(e.getMessage());
        }
    }
    @Then("the other employee is still assigned to {string} and an error message {string} appears")
    public void theOtherEmployeeIsStillAssignedToAndAnErrorMessageAppears(String string, String string2) {
        Assertions.assertFalse(employee.activityExists(string));
        Assertions.assertTrue(employee2.activityExists(string));
        Assertions.assertEquals(string2, errorMessage.getErrorMessage());

    }

    //Charlotte Grimstrup (s204382)
    //Feature 8 scenario 6
    @Given("the employee is not the project manager of a project {string}")
    public void theEmployeeIsNotTheProjectManagerOfAProject(String string) throws Exception {

        softwareHuset.createProject(string);
        this.project=softwareHuset.getProject(string);
        project.setManagerId("PMID");
        Assertions.assertFalse(project.getManagerId().equals(softwareHuset.getLoggedInId()));
    }
    @When("the project manager assigns the an employee to {string}")
    public void theProjectManagerAssignsTheAnEmployeeTo(String string) {
        try {
            Assert.assertFalse(softwareHuset.assignEmployeeToActivity(employee, projectActivity.getName(), project.getProjectName()));
        }catch (Exception e){
            errorMessage.setErrorMessage(e.getMessage());
        }
    }



}
