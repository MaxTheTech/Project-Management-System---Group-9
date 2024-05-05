Feature: Create project activity # Charlotte Ena Manikutdlak Grimstrup - s204382
    Description: Creating an activty and setting the parameters
    Actor: Project manager

Scenario: Create a project activity
    Given an employee is logged in
    And he is the project manager of the project "TestProject"
    When he creates a project activity "TestActivity" in the project
    Then the project activity is created

Scenario: Change project activity name but already exists
    Given an employee is logged in
    And the employee is the project manager of the project "TestProject"
    And an activity with the name "TestActivity" exists in a project
    When the employee creates an activity in the project with the name "TestActivity"
    Then the project activity is not created, and an error message "This project activity name already exists as a activity" appears