package dtu.projectManagementSystem.domain;


import dtu.projectManagementSystem.app.DateServer;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public abstract class Activity {
    // All these fields are public because other need to inheret this
    public int startingWeek;
    public int durationWeeks;
    public String name;
    public Integer id;
    public List<RegistrationTicket> tickets = new ArrayList<>();
    private int ticketId = 0;


    public Activity(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    public abstract String getTypeName() ;

    public String getName() {
        return this.name;
    }

    public Integer getId() {
        return this.id;
    }

    public RegistrationTicket createRegistrationTicket(Employee employee, int start, int timeInHalfHours) {
        id = generateRegistrationTicketId();
        int registrationWeek = 1;
        RegistrationTicket ticket = new RegistrationTicket(start, registrationWeek, registrationWeek, timeInHalfHours, employee, id);
        this.tickets.add(ticket);
        return ticket;
    }

    public int generateRegistrationTicketId(){
        id = ticketId++;
        return id;
    }
    public RegistrationTicket getTicketById(int id) {
        for (RegistrationTicket t : tickets) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }
}
