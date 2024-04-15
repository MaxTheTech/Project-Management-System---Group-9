package dtu.projectManagementSystem.app;

public class DateServer {

    enum day {
        Monday,
        Tuesday,
        Wednesday,
        Thursday,
        Friday
    }
    private int weekDay = day.Monday.ordinal();
    private int week = 1;
    public final int year = 2024;


    public DateServer(){};
    public int getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(int weekDay) {
        this.weekDay = weekDay;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }
}
