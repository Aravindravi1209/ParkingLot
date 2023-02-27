package controllers;

import dtos.GenerateTicketRequestDto;
import dtos.GenerateTicketResponseDto;
import dtos.ResponseStatusDto;
import models.Ticket;
import services.TicketService;

public class TicketController {
    private TicketService ticketService;
    public TicketController(TicketService ticketService)
    {
        this.ticketService=ticketService;
    }

    public GenerateTicketResponseDto generateTicket(GenerateTicketRequestDto request)
    {
        Ticket ticket = ticketService.generateTicket(request.getParkingLotId(),
                request.getVehicle(),request.getSpotType(),request.getEntryGate());

        Ticket updatedTicket = ticketService.updateTickets(ticket, request.getVehicle());

        GenerateTicketResponseDto response = new GenerateTicketResponseDto();
        response.setTicket(updatedTicket);
        response.setResponseStatusDto(ResponseStatusDto.SUCCESS);
        return response;
    }
}
