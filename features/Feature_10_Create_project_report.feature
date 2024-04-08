Feature: Create project report # Seb 3
    Description: The act of getting a project report
    Actor: Project Manager

Scenario: Get project report
    Given an employee is logged in
    And the employee is the project manager of a project "TestProject"
    When the project manager requests a project report for the project
    Then the project report is returned

Scenario: An employee, who isn't the project manager of the project, wants to generate report
    Given an employee is logged in
    And a project with the name "TestProject" exists
    When the employee requests a project report for the project
    Then the report is not shown, and an error message "Only the project manager can generate reports" appears
