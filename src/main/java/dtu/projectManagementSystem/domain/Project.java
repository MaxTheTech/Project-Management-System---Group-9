package dtu.projectManagementSystem.domain;

import java.util.ArrayList;
import java.util.List;

public class Project {

    private int projectId;
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

    public void setManagerId(String managerId) {
        this.managerId = managerId;
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

    public int getExpectedWorkloadHours() {
        return this.expectedWorkloadHours;
    }

    public List<Activity> getProjectActivities() {
        return projectActivities;
    }
}
