Feature: Assign an employee to a project activity
    Description: The act of assigning an employee to a project activity
    Actor: project manager

Scenario: Assign employee to project activity
    Given an employee is logged in
    And the employee is the project manager of a project "TestProject"
    And an activity with the name "TestActivity" exists in the project
    When the project manager assigns an employee to the project activity
    And The employee is assigned to the project activity

Scenario: Employee is unavailable for activity
    Given an employee is logged in
    And the employee is the project manager of a project "TestProject"
    And an activity with the name "TestActivity" exists in the project
    And another employee exists, who cannot take on more activities currently
    When the project manager assigns an employee to the project activity
    Then the employee is not assigned to the project activity
    And an error message "Employee cannot currently take on more activities" appears

Scenario: project does not exist

Scenario: Activity does not exist
    Given an employee is logged in
    And the employee is the project manager of a project "TestProject"
    And no activity with the name "TestActivity" exists in the project
    When the project manager tries to assign an employee to the activity
    Then an error message "Activity does not exist in the given project"

Scenario: Employee is already assigned to the activity
    Given an employee is logged in
    And the employee is the project manager of a project "TestProject" 
    And an activity with the name "TestActivity" exists in the project
    And another employee is already assigned to the activity
    When the project manager assigns the other employee to "TestActivity"
    Then the other employee is still assigned to "TestActivity" and an error message "This employee is already assigned to this activity" appears

Scenario: Not project manager for given project
    Given an employee is logged in
    And the employee is not the project manager of a project "TestProject" 
    And an activity with the name "TestActivity" exists in the project
    When the project manager assigns the an employee to "TestActivity"
    Then an error message "You are not the project manager for this project" appears
