package dtu.projectManagementSystem.domain;

import dtu.projectManagementSystem.domain.Activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class Employee {

    private static final int MAX_NUMBER_OF_PROJECTS = 20;
    private String id;
    private List<Activity> currentActivities = new ArrayList<>();

    public Employee(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public List<Activity> getCurrentActivities() {
        return Collections.unmodifiableList(currentActivities);
    }

    public void addActivity(Activity activity) throws Exception {
        if (currentActivities.size() < 20) {
            currentActivities.add(activity);
        } else {
            throw new Exception("Employee "+this.id+" cannot take on more activities, maximum is "+MAX_NUMBER_OF_PROJECTS+" activities.");
        }
    }

    public void removeActivity(Activity activity) throws Exception {
    }
}
