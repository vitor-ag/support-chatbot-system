package com.vitor.support_chatbot_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import com.vitor.support_chatbot_system.enums.TicketStatus;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String protocol;
    private String sector;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private TicketStatus status;
    private LocalDateTime createdAt;
}
