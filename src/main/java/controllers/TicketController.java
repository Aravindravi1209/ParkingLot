package controllers;

import dtos.GenerateTicketRequestDto;
import dtos.GenerateTicketResponseDto;
import services.TicketService;

public class TicketController {
    private TicketService ticketService;
    public TicketController(TicketService ticketService)
    {
        this.ticketService=ticketService;
    }

    public GenerateTicketResponseDto generateTicket(GenerateTicketRequestDto request)
    {
        return null;
    }
}
