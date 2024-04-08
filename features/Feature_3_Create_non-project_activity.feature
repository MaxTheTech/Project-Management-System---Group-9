Feature: Create non-project activity # Seb 2
    Description: Creating a non-project activity
    Actor: Employee

Scenario: Create a non-project activity
    Given an employee is logged in
    And there is an activity with the name "Vacation" 
    And the activity starts on a given day with a duration of "14" days
    When the employee creates the non-project activity
    Then the non-project activity is created

Scenario: Create a non-project activity that already exists
    Given an employee is logged in
    And there is an activity with the name "Vacation"
    When the employee creates a non-project activity with the name "Vacation"
    Then the error message "Non-project activity already exists" appears
