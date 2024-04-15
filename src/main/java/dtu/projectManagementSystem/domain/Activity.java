package dtu.projectManagementSystem.domain;

import dtu.projectManagementSystem.app.DateServer;

import java.util.Calendar;

public abstract class Activity {
    private String name;
    private int id;

    public Activity(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public abstract String getTypeName() ;

    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }


}
