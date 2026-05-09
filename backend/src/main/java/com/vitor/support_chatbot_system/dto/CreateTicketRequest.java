package com.vitor.support_chatbot_system.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTicketRequest {
    private String sector;
    private String title;
    private String description;
}
