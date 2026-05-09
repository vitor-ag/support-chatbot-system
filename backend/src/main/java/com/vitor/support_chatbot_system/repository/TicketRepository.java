package com.vitor.support_chatbot_system.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vitor.support_chatbot_system.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long>{
    Optional<Ticket> findByProtocol(String protocol);
}
