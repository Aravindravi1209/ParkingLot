package repositories;

import models.Ticket;
import models.Vehicle;

import java.util.HashMap;

public class TicketRepository {
    private HashMap<Ticket, Vehicle> tickets = new HashMap<>();

    public Ticket addTicket(Ticket ticket, Vehicle vehicle)
    {
        tickets.put(ticket,vehicle);
        return ticket;
    }

    public Ticket deleteTicket(Ticket ticket)
    {
        tickets.remove(ticket);
        return ticket;
    }
}

