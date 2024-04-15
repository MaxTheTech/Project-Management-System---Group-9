package dtu.projectManagementSystem.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class Employee {

    private static final int MAX_NUMBER_OF_PROJECTS = 20;

    private String id;

    private List<Activity> currentProjects = new ArrayList<>();

    public Employee(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public List<Activity> getCurrentProjects() {
        return Collections.unmodifiableList(currentProjects);
    }

}

