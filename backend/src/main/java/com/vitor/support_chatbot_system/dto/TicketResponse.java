package com.vitor.support_chatbot_system.dto;

import java.time.LocalDateTime;

import com.vitor.support_chatbot_system.enums.TicketStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TicketResponse {
    private Long id;
    private String protocol;
    private String sector;
    private String title;
    private String description;
    private TicketStatus status;
    private LocalDateTime createdAt;
}
