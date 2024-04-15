Feature: Create a new project # Max 1
    Description: Create a new project with a name and generated ID
    Actor: Employee

Scenario: New project is created
    Given an employee is logged in
    And the current date is in the year 2024
    When the employee creates a new project with the name "TestProject"
    Then a project with the name "TestProject" and ID 24001 is created, where 001 is the number of the project

Scenario: Project name already exists
    Given an employee is logged in
    And the current date is in the year 2024
    And a project with the name "TestProject" exists
    When the employee creates a new project with the name "TestProject"
    Then the project is not created, and an error message "Project already exists" appears
