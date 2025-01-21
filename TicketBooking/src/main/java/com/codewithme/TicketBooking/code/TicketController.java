package com.codewithme.TicketBooking.code;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    // Endpoint to generate a new ticket
    @PostMapping("/generate")
    public ResponseEntity<Ticket> generateTicket() {
        Ticket ticket = ticketService.generateTicket();
        return ResponseEntity.ok(ticket);
    }

    // Endpoint to validate entry
    @PostMapping("/validate-entry")
    public ResponseEntity<String> validateEntry(@RequestParam String ticketId, @RequestParam String entryStation) {
        String response = ticketService.validateEntry(ticketId, entryStation);
        return ResponseEntity.ok(response);
    }

    // Endpoint to validate exit
    @PostMapping("/validate-exit")
    public ResponseEntity<String> validateExit(@RequestParam String ticketId, @RequestParam String exitStation) {
        String response = ticketService.validateExit(ticketId, exitStation);
        return ResponseEntity.ok(response);
    }
}
