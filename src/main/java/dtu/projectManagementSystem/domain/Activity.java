package dtu.projectManagementSystem.domain;


import dtu.projectManagementSystem.app.DateServer;

import java.util.ArrayList;
import java.util.List;

public abstract class Activity {
    // All these fields are public because other need to inherit this
    public DateServer startingDate;
    public int durationWeeks;
    public String name;
    public Integer id;
    public List<Ticket> tickets = new ArrayList<>();
    private static int ticketId = 0;



    public Activity(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    public DateServer getStartingDate() {return startingDate;}
    public int getDurationWeeks() {return durationWeeks;}
    public abstract String getTypeName() ;
    public String getName() {
        return this.name;
    }
    public Integer getId() {
        return this.id;
    }

    public void setStartingWeek(DateServer startingDate) {this.startingDate = startingDate;}
    public void setDurationWeeks(int durationWeeks) {this.durationWeeks = durationWeeks;}
    public void setName(String name) {this.name = name;}
    public void setId(Integer id) {this.id = id;}

    // Simon Bom (s214751)
    public Ticket createTicket(Employee employee, DateServer start, int timeInHalfHours) throws Exception {
        // Pre-condition: Check that employee is available
        // assert employee.hasAlreadyRegistedTimeDuring(start, timeInHalfHours) : "Precondition failed: Employee is not available";

        boolean available = employee.hasAlreadyRegistedTimeDuring(start, timeInHalfHours);
        if (!available) {
            throw new Exception("Employee has already registered time for this time slot");
        }
        Ticket ticket = new Ticket(start, timeInHalfHours, employee);
        this.tickets.add(ticket);

        // Post-condition: Check that the ticket is created.
        // int id = ticket.getId();
        // assert this.getTicketById(id) == ticket : "Postcondition failed: ticket was not added to the activity";

        return ticket;
    }

    // Simon Bom (s214751)
    public Ticket getTicketById(int id) {
        for (Ticket t : tickets) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }


}
