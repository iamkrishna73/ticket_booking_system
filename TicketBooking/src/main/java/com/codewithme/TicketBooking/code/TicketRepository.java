package com.codewithme.TicketBooking.code;

import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    // Find ticket by unique ticket ID
    Optional<Ticket> findByTicketId(String ticketId);

    // Find active tickets that haven't expired
    Optional<Ticket> findByTicketIdAndIsActiveTrueAndExpirationTimeAfter(String ticketId, LocalDateTime now);
}
