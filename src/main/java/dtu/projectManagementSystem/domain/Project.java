package dtu.projectManagementSystem.domain;

import dtu.projectManagementSystem.app.SoftwareHuset;
import io.cucumber.java.an.E;

import java.util.ArrayList;
import java.util.List;

public class Project{

    private int projectId;
    private boolean hasManager = false;
    private String managerId;
    private String projectName;

    private SoftwareHuset softwareHuset;
    //Project time variables
    private int durationInWeeks;
    private int expectedWorkloadHours;

    private boolean startingDateHasBeenSet=false;
    private int startingWeek;
    private int startingYear;
    private List<ProjectActivity> projectActivities = new ArrayList<>();

    //Sebastian A. Ladegaard - s215530
    public Project(String projectName, int projectId,SoftwareHuset softwareHuset){
        this.projectName = projectName;
        this.projectId = projectId;
        this.softwareHuset = softwareHuset;
    }
    public void setStartingDateHasBeenSet(){
        this.startingDateHasBeenSet = true;
    }
    public boolean isStartingDateHasBeenSet(){
        return startingDateHasBeenSet;
    }
    public String getProjectName(){
        return this.projectName;
    }
    public void setStartingWeek(int startingWeek){this.startingWeek=startingWeek;}
    public int getStartingWeek(){return startingWeek;}
    public void setStartingYear(int startingYear){this.startingYear=startingYear;}
    public int getStartingYear(){return startingYear;}
    public int getProjectId(){
        return this.projectId;
    }

    // Emil Wille Andersen (s194501)
    public void setManagerId(String managerId) throws Exception {
        if (hasManager){
            throw new Exception("Project already has an assigned project manager");
        }
        this.managerId = managerId;
        this.hasManager = true;
    }

    // Emil Wille Andersen (s194501)
    public ProjectActivity getActivity(String activityName){
        for (ProjectActivity activity : projectActivities){
            if (activity.getName().equals(activityName)){
                return activity;
            }
        } return null;
    }
    public String getManagerId() {
        return this.managerId;
    }

    public void setDurationInWeeks(int durationInWeeks) {
        this.durationInWeeks = durationInWeeks;
    }

    public int getDurationInWeeks() {
        return this.durationInWeeks;
    }

    public void setExpectedWorkloadHours(int expectedWorkloadHours) {
        this.expectedWorkloadHours = expectedWorkloadHours;
    }

    // Emil Wille Andersen (s194501)
    public void setProjectName(String name) throws Exception{
        if (softwareHuset.projectExist(name)){
            throw new Exception("This project name already exists as a separate project");
        }
        this.projectName=name;

    }

    // Emil Wille Andersen (s194501)
    public boolean hasActivity(String activityName){
        for (Activity activity:projectActivities){
            if (activity.getName().equals(activityName)){
                return true;
            }
        }return false;
    }
    public boolean isHasManager(){
        return hasManager;
    }
    public void setSoftwareHuset(SoftwareHuset softwareHuset){this.softwareHuset=softwareHuset;}

    public void setHasManager(Boolean bool){
        this.hasManager=bool;
    }

    public int getExpectedWorkloadHours() {
        return this.expectedWorkloadHours;
    }

    public List<ProjectActivity> getProjectActivities() {
        return projectActivities;
    }

    //Sebastian A. Ladegaard - s215530
    public String projectReport() throws Exception {
        if (!softwareHuset.getLoggedInEmployee().getId().equals(managerId)){
            throw new Exception("Only the project manager can generate reports");
        }
        /* This code snippet was deleted during refactoring by Sebastian L. - s215530
         * as described in the report, this code doesn't follow the KISS principle.
         *
        String managerIdToReport;
        if(hasManager){
            managerIdToReport = managerId;
        } else {
            managerIdToReport = "None";
        }
           */

        StringBuilder builder = new StringBuilder().
                append("ID: ").append(projectId).append("\n").
                append("Name: ").append(projectName).append("\n").
                append("Manager: ").append(managerId).append("\n").
                append("Starting week: ").append(startingWeek).append("\n").
                append("Duration in weeks: ").append(durationInWeeks).append("\n").
                append("Expected workload in hours: ").append(expectedWorkloadHours).append("\n").
                append("List of activities:\n");

        for(int i = 0; i < projectActivities.size(); i++) {
            builder.append(projectActivities.get(i).getName()).append("\n");

        }
        return builder.toString();
    }

    public void addActivity(ProjectActivity activity){
        projectActivities.add(activity);
    }
}
