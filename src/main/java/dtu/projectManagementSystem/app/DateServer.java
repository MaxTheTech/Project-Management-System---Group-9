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
    public int year = 2024;

    public DateServer(int year,int week,int day){
        this.year=year;
        this.week=week;
        this.weekDay=day;
    }
    public DateServer(){};
    public int getWeekDay() {
        return weekDay;
    }

    public void setYear(int year){
        this.year=year;
    }
    public int getYear(){return year;}
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
