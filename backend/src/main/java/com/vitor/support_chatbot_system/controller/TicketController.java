package com.vitor.support_chatbot_system.controller;

import org.springframework.web.bind.annotation.RestController;

import com.vitor.support_chatbot_system.dto.CreateTicketRequest;
import com.vitor.support_chatbot_system.dto.TicketResponse;
import com.vitor.support_chatbot_system.service.TicketService;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;


@RestController
@RequestMapping("/tickets")
@AllArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TicketResponse createTicket(@RequestBody CreateTicketRequest request) {
        return ticketService.createTicket(request);
    }

    @GetMapping("/{protocol}")
    @ResponseStatus(HttpStatus.OK)
    public TicketResponse getTicket(@PathVariable String protocol) {
        return ticketService.getTicketByProtocol(protocol);
    }
}
