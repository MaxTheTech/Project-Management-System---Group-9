package dtu.projectManagementSystem.domain;

public class Project {

    private int projectId;
    private String managerId = "";
    private String projectName = "";
    private int durationInWeeks;
    private int expectedWorkloadInHours;


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

}
