package com.codewithme.TicketBooking.code;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Ticket findByTicketId(String ticketId);
}
