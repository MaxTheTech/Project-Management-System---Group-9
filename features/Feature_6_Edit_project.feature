Feature: Edit project # Simon 2
    Description: Set and edit the parameters of a project
    Actor: Project manager

Scenario: Set project parameters
    Given an employee is logged in
    And he is the project manager of a project "TestProject"
    When the project manager sets the starting week as "5", the duration as "3" weeks, the year as "2024", and expected workload as "200" hours
    Then the project has these parameters set

Scenario: Change project parameters
    Given an employee is logged in
    And he is the project manager of a project "TestProject"
    And the project has a set starting week, duration and expected workload
    When the project manager updates the parameters
    Then the project parameters are changed

Scenario: Change name of project
    Given an employee is logged in
    And he is the project manager of a project "TestProject"
    When he changes the name of the project to "TestProject2"
    Then the name of the project is changed to "TestProject2"

Scenario: Change project name but already exists
    Given an employee is logged in
    And he is the project manager of a project "TestProject"
    And another project exists with the name "TestProject2"
    When the project manager changes the name to "TestProject2"
    Then the project name is not changed and an error message "This project name already exists as a separate project" appears
