Feature: Create non-project activity
    Description: Creating a non-project activity
    Actor: Employee

Scenario: Create a non-project activity
    Given an employee is logged in
    When the employee creates a non-project activity with the name "Vacation"
    Then the non-project activity is created

Scenario: Create a non-project activity that already exists
    Given an employee is logged in
    And there is a non-project activity with the name "Vacation"
    When the employee creates a non-project activity with the name "Vacation"
    Then the error message "Employee mps is already working on Non-Project Activity: Vacation" appears

Scenario: Set starting day and duration for non-project activity
    Given an employee is logged in
    And there is a non-project activity with the name "Vacation"
    When the employee sets the starting day to a given day and the duration to 10 days
    Then the starting day and duration are set