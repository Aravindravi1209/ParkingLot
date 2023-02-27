package services;

import Strategies.spotAssignmentStrategies.SpotAssignmentStrategy;
import models.*;
import repositories.ParkingLotRepository;
import repositories.TicketRepository;

import java.util.Date;

public class TicketService {
    private TicketRepository ticketRepository;
    private SpotAssignmentStrategy spotAssignmentStrategy;

    private ParkingLotRepository parkingLotRepository;
    public TicketService(TicketRepository ticketRepository, SpotAssignmentStrategy spotAssignmentStrategy,
                         ParkingLotRepository parkingLotRepository)
    {
        this.ticketRepository=ticketRepository;
        this.spotAssignmentStrategy=spotAssignmentStrategy;
        this.parkingLotRepository=parkingLotRepository;
    }
    public Ticket generateTicket(Long parkingLotId, Vehicle vehicle, SpotType spotType, EntryGate entryGate)
    {
        ParkingLot parkingLot = parkingLotRepository.getParkingLotById(parkingLotId);
        ParkingSpot parkingSpot = spotAssignmentStrategy.assignSpot(parkingLot,spotType,entryGate);

        if(parkingSpot == null) return null;

        Ticket ticket = new Ticket();
        ticket.setEntryGate(entryGate);
        ticket.setEntryTime(new Date());
        ticket.setGeneratedBy(entryGate.getOperator());
        ticket.setParkingSpot(parkingSpot);
        ticket.setVehicle(vehicle);
        ticket.setParkingLot(parkingLot);
        return ticket;
    }

    public Ticket updateTickets(Ticket ticket, Vehicle vehicle)
    {
        Ticket updatedTicket = ticketRepository.addTicket(ticket,vehicle);
        return updatedTicket;
    }

    public Ticket deleteTickets(Ticket ticket)
    {
        Ticket deletedTicket = ticketRepository.deleteTicket(ticket);
        return deletedTicket;
    }
}
