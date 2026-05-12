package com.vitor.support_chatbot_system.chat;

import com.vitor.support_chatbot_system.enums.ChatState;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChatSession {
    private ChatState state = ChatState.START;
    private String sector;
    private String title;
    private String description;
}
