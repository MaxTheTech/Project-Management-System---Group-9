package dtu.projectManagementSystem.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<NonProjectActivity> getEmployeeNonProjectActivities() { //Max-Peter Schrøder (s214238)
        return employeeActivities.stream()
                .filter(NonProjectActivity.class::isInstance)
                .map(NonProjectActivity.class::cast)
                .collect(Collectors.toList());
    }

    public List<ProjectActivity> getEmployeeProjectActivities() { //Max-Peter Schrøder (s214238)
        return employeeActivities.stream()
                .filter(a -> a instanceof ProjectActivity)
                .map(a -> (ProjectActivity) a)
                .collect(Collectors.toList());
    }

    public NonProjectActivity getNonProjectActivity(String name) { //Max-Peter Schrøder (s214238)
        return getEmployeeNonProjectActivities().stream().filter(a -> a.getName().equals(name))
                .findAny()
                .orElse(null);
    }

    public Activity findActivity(Activity activity) { //Max-Peter Schrøder (s214238)
        return employeeActivities.stream()
                .filter(a -> a.getId() == activity.getId())  // Use == for primitive int comparison
                .findAny()
                .orElse(null);
    }

    public boolean activityExists(Activity activity) { //Max-Peter Schrøder (s214238)
        return employeeActivities.stream()
                .anyMatch(a -> a.getId() == activity.getId());
    }

    public boolean activityExists(String name) { //Max-Peter Schrøder (s214238)
        return employeeActivities.stream()
                .anyMatch(a -> a.getName().equals(name));
    }

    public void addActivity(Activity activity) throws Exception { //Max-Peter Schrøder (s214238)
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

    public void removeActivity(Activity activity) throws Exception { //Max-Peter Schrøder (s214238)
        boolean found = false;
        Activity toRemove = null;
        for (Activity currentActivity : employeeActivities) {
            if (currentActivity.getId() == activity.getId()) {
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
