Feature: Assign an employee to a project activity # Charlotte Ena Manikutdlak Grimstrup - s204382
    Description: The act of assigning an employee to a project activity
    Actor: project manager

Scenario: Assign employee to project activity
    Given an employee is logged in
    And the employee is the project manager of the project "TestProject"
    And an activity with the name "TestActivity" exists in a project
    When the project manager assigns an employee "test" to the project activity "TestActivity"
    And The employee is assigned to the project activity

Scenario: Employee is unavailable for activity
    Given an employee is logged in
    And the employee is the project manager of the project "TestProject"
    And an activity with the name "TestActivity" exists in a project
    And another employee exists, who cannot take on more activities currently
    When the project manager assigns the employee to the project activity "TestActivity"
    Then the employee is not assigned to the project activity
    And an error message "Employee cannot currently take on more activities" is given

Scenario: project does not exist

Scenario: Activity does not exist
    Given an employee is logged in
    And the employee is the project manager of the project "TestProject"
    And no activity with the name "TestActivity1" exists in the project "TestProject"
    When the project manager tries to assign an employee to the activity "TestActivity1"
    Then an error message "Activity does not exist in the given project" is given

Scenario: Employee is already assigned to the activity
    Given an employee is logged in
    And the employee is the project manager of the project "TestProject"
    And an activity with the name "TestActivity" exists in a project
    And another employee is already assigned to the activity "TestActivity"
    When the project manager assigns the other employee to "TestActivity"
    Then the other employee is still assigned to "TestActivity" and an error message "Another employee is already assigned to this activity" appears

Scenario: Not project manager for given project
    Given an employee is logged in
    And the employee is not the project manager of a project "TestProject" 
    And an activity with the name "TestActivity" exists in a project
    When the project manager assigns the an employee to "TestActivity"
    Then an error message "You are not the project manager for this project" is given
