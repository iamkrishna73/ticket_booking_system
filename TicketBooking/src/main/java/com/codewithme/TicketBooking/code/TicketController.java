package com.codewithme.TicketBooking.code;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService ticketService;


    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/generate")
    public Map<String, String> generateTicket(@RequestParam String source, @RequestParam String destination) {
        Ticket ticket = ticketService.createTicket(source, destination);
        return Map.of(
                "ticketId", ticket.getTicketId(),
                "expiryTime", ticket.getExpiryTime().toString()
        );
    }

    @PostMapping("/use")
    public String useTicket(@RequestParam String ticketId, @RequestParam boolean isEntry) {
        ticketService.useTicket(ticketId, isEntry);
        return isEntry ? "Ticket validated for entry." : "Ticket validated for exit.";
    }

}