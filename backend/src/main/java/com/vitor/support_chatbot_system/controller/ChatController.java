package com.vitor.support_chatbot_system.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vitor.support_chatbot_system.dto.ChatRequest;
import com.vitor.support_chatbot_system.dto.ChatResponse;
import com.vitor.support_chatbot_system.service.ChatService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/chat")
@AllArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ChatResponse processMessage(@RequestBody ChatRequest request) {
        String response = chatService.processMessage(
                request.getMessage()
            );
        
        return new ChatResponse(response);
    }
}