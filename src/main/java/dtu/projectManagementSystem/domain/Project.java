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

    private int startingWeek;
    private int startingYear;
    private List<Activity> projectActivities = new ArrayList<>();

    public Project(String projectName, int projectId,SoftwareHuset softwareHuset){
        this.projectName = projectName;
        this.projectId = projectId;
        this.softwareHuset = softwareHuset;
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

    public void setManagerId(String managerId) throws Exception {
        if (hasManager){
            throw new Exception("Project already has an assigned project manager");
        }
        this.managerId = managerId;
        this.hasManager = true;
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

    public void setProjectName(String name) throws Exception{
        if (softwareHuset.projectExist(name)){
            throw new Exception("This project name already exists as a separate project");
        }
        this.projectName=name;

    }
    public void setSoftwareHuset(SoftwareHuset softwareHuset){this.softwareHuset=softwareHuset;}

    public void setHasManager(Boolean bool){
        this.hasManager=bool;
    }

    public int getExpectedWorkloadHours() {
        return this.expectedWorkloadHours;
    }

    public List<Activity> getProjectActivities() {
        return projectActivities;
    }
}
