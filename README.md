# Project Management System for SoftwareHuset A/S

This is a project management system for the fictional company SoftwareHuset A/S. This project was created by group 9 as a part of the course "02161 - Software Engineering 1".

The Project can be found on GitHub with the link HTTPS: https://github.com/MaxTheTech/Project-Management-System---Group-9.git


## 1. Run the Project Management System

To run the system as intended, go to:

`src/main/java/dtu/projectManagementSystem/app/Application.java`

The app is run in the terminal using the scanner. Below is shown how to get to different features in the system. When running the application, the user will be prompted with numbers to write to go through the menus.

### Menu
- **Add Employee**

- **Login**
    - **Enter Employee `<id>` Management:**
        - View Employee Activities:
            - All
            - Project activities
            - Non-project activities
            - Back
        - Create Non-Project Activity
        - Edit Non-Project Activity:
            - Edit starting day
            - Edit duration
            - Back
        - Register Time:
            - Register time on project activity
            - Register time on non-project activity
            - Back
        - Back

    - **Enter Project Management:**
        - Create Project
        - List Projects
        - Enter Project:
            - Edit project
                - Edit starting date (year + week)
                - Edit duration (in weeks)
                - Edit expected workload (in half-hours)
                - Back
            - List project activities
            - Enter project activity:
                - Edit starting week
                - Edit duration
                - Edit expected workload
                - Back
            - Set project manager
            - Enter project manager privileges (has to be manager for the project):
                - List available employees for project activity
                - Assign employee to project activity
                - Generate project report
                - Back
            - Create project activity
            - Back

        - Back

  - **Logout**
- **Exit**

## 2. To Run the Cucumber Tests

Go to:

`src/test/java/dtu/projectManagementSystem/acceptance_tests/CucumberTest.java`

And run the file.

## 3. To Run the White Box Tests

Go to:

`src/test/java/dtu/projectManagementSystem/junit/WhiteBoxTests.java`

And run the file.
