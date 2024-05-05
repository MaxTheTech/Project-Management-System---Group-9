Feature: Check available employees # Simon Bom - s214751
    Description: The act of checking the employees schedule for a specific project
    Actor: Project manager

Scenario: Check available employees for activity
    Given the employee "SB" is logged in
    And the employee is the project manager of "TestProject"
    And an activity with the name "TestActivity" exists in the project
    And there are employees available during the activity duration
    When the project manager selects the activity and checks for available employees
    Then a list of employees who can take on more activities during the activity duration is returned

Scenario: No employees are available
    Given an employee is logged in
    And the employee is the project manager of "TestProject"
    And an activity with the name "TestActivity" exists in the project
    When the project manager selects the activity and checks for available employees
    Then an empty list of employees who can take on more activities during the activity duration is returned and a message "No employees are available during this activity duration" appears
