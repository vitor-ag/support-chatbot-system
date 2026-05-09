package com.vitor.support_chatbot_system.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.vitor.support_chatbot_system.dto.CreateTicketRequest;
import com.vitor.support_chatbot_system.dto.TicketResponse;
import com.vitor.support_chatbot_system.entity.Ticket;
import com.vitor.support_chatbot_system.enums.TicketStatus;
import com.vitor.support_chatbot_system.exception.TicketNotFoundException;
import com.vitor.support_chatbot_system.repository.TicketRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;

    public TicketResponse createTicket(CreateTicketRequest request) {
        Ticket ticket = new Ticket();

        ticket.setSector(request.getSector());
        ticket.setTitle(request.getTitle());
        ticket.setDescription(request.getDescription());
        ticket.setStatus(TicketStatus.OPEN);
        ticket.setCreatedAt(LocalDateTime.now());
        ticket.setProtocol(UUID.randomUUID().toString());

        Ticket savedTicket = ticketRepository.save(ticket);

        return mapToResponse(savedTicket);
    }

    public TicketResponse getTicketByProtocol(String protocol) {
        
        Ticket ticket = ticketRepository.findByProtocol(protocol)
            .orElseThrow(() -> new TicketNotFoundException("Ticket not found: " + protocol));

        return mapToResponse(ticket);
    }

    public TicketResponse mapToResponse(Ticket ticket) {
        return new TicketResponse(
            ticket.getId(), 
            ticket.getProtocol(), 
            ticket.getSector(),
            ticket.getTitle(),
            ticket.getDescription(),
            ticket.getStatus(),
            ticket.getCreatedAt()
        );
    }
}
