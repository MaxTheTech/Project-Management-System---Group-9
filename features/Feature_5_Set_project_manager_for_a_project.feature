Feature: Set project manager for a project # Max 2
    Description: An employee assigns themselves as project manager for an unassigned project
    Actor: Employee / Project manager

Scenario: Self-assign as project manager
    Given an employee is logged in
    And a project with the name "TestProject" exists
    When the employee self-assigns themselves as project manager of the project
    Then they become the project manager of the project
    
Scenario: Project manager is already assigned
    Given an employee is logged in
    And a project with the name "TestProject" exists
    And the project has an existing project manager
    When the employee self-assigns themselves as project manager of the project
    Then they do not become the project manager, and an error message "Project already has an assigned project manager" appears
