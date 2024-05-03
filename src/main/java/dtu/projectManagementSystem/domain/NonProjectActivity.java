package dtu.projectManagementSystem.domain;

import dtu.projectManagementSystem.app.DateServer;

//Max-Peter Schrøder (s214238)
public class NonProjectActivity extends Activity {

    private DateServer startingDay = null;
    private int durationDays = 0;
    public NonProjectActivity(String name, Integer id) {
        super(name, id);
    }

    public void setStartingDay(DateServer startingDay) {
        this.startingDay = startingDay;
    }

    public DateServer getStartingDay() {
        return this.startingDay;
    }

    public void setDurationDays(int durationDays) {
        this.durationDays = durationDays;
    }

    public int getDurationDays() {
        return this.durationDays;
    }

    public String getTypeName() {
        return "Non-Project Activity";
    }

}
