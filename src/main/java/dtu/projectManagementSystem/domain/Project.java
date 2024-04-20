package dtu.projectManagementSystem.domain;

import io.cucumber.java.an.E;

import java.util.ArrayList;
import java.util.List;

public class Project {

    private int projectId;

    private boolean hasManager = false;
    private String managerId;
    private String projectName;
    private int durationInWeeks;
    private int expectedWorkloadHours;
    private List<Activity> projectActivities = new ArrayList<>();

    public Project(String projectName, int projectId){
        this.projectName = projectName;
        this.projectId = projectId;
    }

    public String getProjectName(){
        return this.projectName;
    }

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
