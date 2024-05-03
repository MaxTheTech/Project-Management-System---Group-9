package dtu.projectManagementSystem.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


import dtu.projectManagementSystem.app.DateServer;

public class Employee {

    private static final int MAX_NUMBER_OF_PROJECTS = 20;
    private String id;
    private List<Activity> employeeActivities = new ArrayList<>();

    public Employee(String id) throws Exception {
        if (id.length()>4 || !id.matches("^[a-zA-Z]+$")){
            throw new Exception(id+" invalid: \nID has to be only letters and no more than 4");
        }else {
            this.id = id;
        }

    }

    public String getId() {
        return id;
    }

    public List<Activity> getEmployeeActivities() {
        return Collections.unmodifiableList(employeeActivities);
    }


    //Max-Peter Schrøder (s214238)
    public List<NonProjectActivity> getEmployeeNonProjectActivities() {
        return employeeActivities.stream()
                .filter(NonProjectActivity.class::isInstance)
                .map(NonProjectActivity.class::cast)
                .collect(Collectors.toList());
    }

    //Max-Peter Schrøder (s214238)
    public List<ProjectActivity> getEmployeeProjectActivities() {
        return employeeActivities.stream()
                .filter(a -> a instanceof ProjectActivity)
                .map(a -> (ProjectActivity) a)
                .collect(Collectors.toList());
    }

    //Max-Peter Schrøder (s214238)
    public NonProjectActivity getNonProjectActivity(String name) {
        return getEmployeeNonProjectActivities().stream().filter(a -> a.getName().equals(name))
                .findAny()
                .orElse(null);
    }

    public ProjectActivity getProjectActivity(String name) { //Simon Bom (s214751) (mostly copied)
        return getEmployeeProjectActivities().stream().filter(a -> a.getName().equals(name))
                .findAny()
                .orElse(null);
    }

    //Max-Peter Schrøder (s214238)
    public Activity findActivity(Activity activity) {
        return employeeActivities.stream()
                .filter(a -> Objects.equals(a.getId(), activity.getId()))  // Use == for primitive int comparison
                .findAny()
                .orElse(null);
    }

    //Max-Peter Schrøder (s214238)
    public boolean activityExists(Activity activity) {
        return employeeActivities.stream()
                .anyMatch(a -> Objects.equals(a.getId(), activity.getId()));
    }

    //Max-Peter Schrøder (s214238)
    public boolean activityExists(String name) {
        return employeeActivities.stream()
                .anyMatch(a -> a.getName().equals(name));
    }

    //Max-Peter Schrøder (s214238)
    public void addActivity(Activity activity) throws Exception {
        if (activity instanceof NonProjectActivity && activityExists(activity.getName())) {
            throw new Exception("Employee " + this.id + " is already working on " + activity.getTypeName() + ": " + activity.getName());
        } else if (activityExists(activity)) {
            throw new Exception("Employee " + this.id + " is already working on " + activity.getTypeName() + ": " + activity.getName());
        } else if (employeeActivities.size() >= MAX_NUMBER_OF_PROJECTS) {
            throw new Exception("Employee " + this.id + " cannot take on more activities, maximum is " + MAX_NUMBER_OF_PROJECTS + " activities.");
        } else {
            employeeActivities.add(activity);
        }
    }

    //Max-Peter Schrøder (s214238)
    public void removeActivity(Activity activity) throws Exception {
        boolean found = false;
        Activity toRemove = null;
        for (Activity currentActivity : employeeActivities) {
            if (Objects.equals(currentActivity.getId(), activity.getId())) {
                found = true;
                toRemove = currentActivity;
                break;
            }
        }
        if (!found) {
            throw new Exception("Employee " + this.id + " is not currently working on this activity");
        }
        employeeActivities.remove(toRemove);
    }
}
