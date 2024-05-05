package dtu.projectManagementSystem.domain;

import dtu.projectManagementSystem.app.DateServer;

public class ProjectActivity extends Activity {

    private int expectedWorkloadHalfhours;

    private Project parentProject;
    public ProjectActivity(String name, Integer id) {
        super(name, id);
    }

    public Project getParentproject(){
        return parentProject;
    }

    public void setExpectedWorkloadHalfhours(int halfhours) {
        this.expectedWorkloadHalfhours = halfhours;
    }

    public int getExpectedWorkloadHalfhours() {
        return this.expectedWorkloadHalfhours;
    }

    public String getTypeName() {
        return "Project Activity";
    }

}
