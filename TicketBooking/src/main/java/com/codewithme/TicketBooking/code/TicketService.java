package com.codewithme.TicketBooking.code;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    // Generate a new ticket
    public Ticket generateTicket() {
        String ticketId = UUID.randomUUID().toString(); // Generate a unique ticket ID
        LocalDateTime expirationTime = LocalDateTime.now().plusHours(18); // Set expiration time

        Ticket ticket = new Ticket(ticketId, expirationTime);
        return ticketRepository.save(ticket); // Save and return the ticket
    }

    // Validate entry
    public String validateEntry(String ticketId, String entryStation) {
        Optional<Ticket> optionalTicket = ticketRepository.findByTicketIdAndIsActiveTrueAndExpirationTimeAfter(ticketId, LocalDateTime.now());

        if (optionalTicket.isEmpty()) {
            return "Ticket is invalid or expired.";
        }

        Ticket ticket = optionalTicket.get();
        if (ticket.getEntryStation() != null) {
            return "Ticket has already been used for entry.";
        }

        ticket.setEntryStation(entryStation);
        ticket.setUsageCount(ticket.getUsageCount() + 1);
        ticketRepository.save(ticket);
        return "Entry validated successfully.";
    }

    // Validate exit
    public String validateExit(String ticketId, String exitStation) {
        Optional<Ticket> optionalTicket = ticketRepository.findByTicketIdAndIsActiveTrueAndExpirationTimeAfter(ticketId, LocalDateTime.now());

        if (optionalTicket.isEmpty()) {
            return "Ticket is invalid or expired.";
        }

        Ticket ticket = optionalTicket.get();
        if (ticket.getEntryStation() == null) {
            return "Ticket cannot be used for exit without entry.";
        }
        if (ticket.getExitStation() != null) {
            return "Ticket has already been used for exit.";
        }

        ticket.setExitStation(exitStation);
        ticket.setUsageCount(ticket.getUsageCount() + 1);

        // Deactivate ticket if used twice
        if (ticket.getUsageCount() >= 2) {
            ticket.setActive(false);
        }

        ticketRepository.save(ticket);
        return "Exit validated successfully.";
    }
}

