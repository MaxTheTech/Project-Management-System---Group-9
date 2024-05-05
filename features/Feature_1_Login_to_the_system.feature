Feature: Login to the system  # Max-Peter Schrøder - s214238
    Description: Login into the system
    Actor: Employee

Scenario: Employee logs in
    Given that no-one is logged in
    And an employee with an ID exists
    When the employee logs in with their ID
    Then the employee is logged into the system

Scenario: Employee does not exists
    Given that no-one is logged in
    And an employee has an ID "mps" that is not registered in the system
    When the employee logs in with their ID
    Then the employee is not logged into the system, and an error message "Employee not found with ID: mps" appears
