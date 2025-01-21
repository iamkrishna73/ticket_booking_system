package com.codewithme.TicketBooking.code;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Ticket createTicket(String source, String destination, LocalDateTime expiryTime) {
        Ticket ticket = new Ticket();
        ticket.setTicketId(UUID.randomUUID().toString());
        ticket.setSource(source);
        ticket.setDestination(destination);
        ticket.setExpiryTime(expiryTime);
        ticketRepository.save(ticket);
        return ticket;
    }

    public Ticket useTicketForEntry(String ticketId) {
        Ticket ticket = ticketRepository.findByTicketId(ticketId);
        if (ticket == null || ticket.isUsedForEntry() || ticket.getExpiryTime().isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Invalid or expired ticket");
        }
        ticket.setUsedForEntry(true);
        ticketRepository.save(ticket);
        return ticket;
    }

    public Ticket useTicketForExit(String ticketId) {
        Ticket ticket = ticketRepository.findByTicketId(ticketId);
        if (ticket == null || !ticket.isUsedForEntry() || ticket.isUsedForExit()) {
            throw new IllegalStateException("Invalid or improperly used ticket");
        }
        ticket.setUsedForExit(true);
        ticketRepository.save(ticket);
        return ticket;
    }

}
