package dtu.projectManagementSystem.domain;

import dtu.projectManagementSystem.app.DateServer;

// Simon Bom (s214751)
public class Ticket {
    private DateServer startingDate;
    private DateServer registrationDate;
    private DateServer lastChangeDate;
    private int timeInHalfHours;
    private Employee employee;
    private static int count = 0;
    private int id;

    public Ticket(DateServer startingWeek, int timeInHalfHours, Employee employee){
        this.startingDate = startingWeek;
        // these should both be the current time when created, but we don't have that functional now:
        this.registrationDate = new DateServer(2024,5,5);
        this.lastChangeDate = new DateServer(2024,5,5); // should be updated if ticket is edited
        this.timeInHalfHours = timeInHalfHours;
        this.employee = employee;
        this.id = count++ ;
    }

    public DateServer getLastChangeDate() {return lastChangeDate;}
    public DateServer getStartingWeek() {return startingDate;}
    public DateServer getRegistrationWeek() {return registrationDate;}
    public int getTimeInHalfHours() {return timeInHalfHours;}
    public Employee getEmployee() {return employee;}
    public int getId() {return id;}
    public void setLastChangeDate(DateServer lastChangeDate) {this.lastChangeDate = lastChangeDate;}
    public void setStartingWeek(DateServer startingWeek) {this.startingDate = startingWeek;}
    public void setRegistrationWeek(DateServer registrationDate) {this.registrationDate = registrationDate;}
    public void setTimeInHalfHours(int timeInHalfHours) {this.timeInHalfHours = timeInHalfHours;}
    public void setEmployee(Employee employee) {this.employee = employee;}
    public void setId(int id) {this.id = id;}


}
