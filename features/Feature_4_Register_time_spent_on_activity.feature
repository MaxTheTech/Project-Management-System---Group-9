Feature: Register time spent on activity
    Description: Register the time spent on an activity on the given day
    Actor: Employee

Scenario: Register time for a project activity
    Given the employee "S" is logged in
    And it is the current date
    And an activity with the name "TestActivity" exists in project "TestProject"
    When the employee registers 9 half-hours spent on the activity
    Then the time spent by the employee is registered to the activity and the employee

Scenario: Register time for non-project activity
    Given the employee "S" is logged in
    And there is an activity with the name "Vacation"
    When the employee "S" registers "5" days spent on the non-project activity
    Then the time spent by the employee is registered to the non-project activity and the employee

Scenario: Edit project activity time registration
    Given the employee is logged in
    And an activity with the name "TestActivity" exists in project "TestProject"
    And the employee has previously registered time on this activity
    When the employee edits the time spent on the activity for this registration
    Then the time registration ticket is updated for the employee and the activity

Scenario: Edit time for non-project activity
    Given the employee is logged in
    And there is an activity with the name "Vacation"
    When the employee edits the duration in days spent on the non-project activity
    Then the time spent by the employee is registered to the non-project activity and the employee
