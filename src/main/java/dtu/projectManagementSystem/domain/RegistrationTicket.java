package dtu.projectManagementSystem.domain;

import dtu.projectManagementSystem.app.DateServer;

public class RegistrationTicket {
    private int startingWeek;
    private int registrationWeek;
    private int lastChangeDate;
    private int timeInHalfHours;
    private Employee employee;
    private int id;

    public RegistrationTicket(int startingWeek, int registrationWeek, int lastChangeDate, int timeInHalfHours, Employee employee, int id){
        this.startingWeek = startingWeek;
        this.registrationWeek = registrationWeek;
        this.lastChangeDate = lastChangeDate;
        this.timeInHalfHours = timeInHalfHours;
        this.employee = employee;
        this.id = id;
    }

    public int getLastChangeDate() {return lastChangeDate;}
    public int getStartingWeek() {return startingWeek;}
    public int getRegistrationWeek() {return registrationWeek;}
    public int getTimeInHalfHours() {return timeInHalfHours;}
    public Employee getEmployee() {return employee;}
    public int getId() {return id;}
    public void setLastChangeDate(int lastChangeDate) {this.lastChangeDate = lastChangeDate;}
    public void setStartingWeek(int startingWeek) {this.startingWeek = startingWeek;}
    public void setRegistrationWeek(int registrationWeek) {this.registrationWeek = registrationWeek;}
    public void setTimeInHalfHours(int timeInHalfHours) {this.timeInHalfHours = timeInHalfHours;}
    public void setEmployee(Employee employee) {this.employee = employee;}
    public void setId(int id) {this.id = id;}


}
