package com.vitor.support_chatbot_system.service;

import org.springframework.stereotype.Service;

import com.vitor.support_chatbot_system.chat.ChatSession;
import com.vitor.support_chatbot_system.dto.CreateTicketRequest;
import com.vitor.support_chatbot_system.dto.TicketResponse;
import com.vitor.support_chatbot_system.enums.ChatState;

import lombok.*;

@Service
@AllArgsConstructor
public class ChatService {
    private ChatSession session = new ChatSession();

    private final TicketService ticketService;

    public String processMessage(String message) {
        switch (session.getState()) {
            case START:
                session.setState(ChatState.MENU);
                return """
                    Bem-vindo ao suporte!
                    
                    1 - Criar chamado
                    2 - Consultar status
                    """;

            case MENU:
                if (message.equals("1")) {
                    session.setState(ChatState.WAITING_SECTOR);
                    return "Informe o setor:";
                } else if (message.equals("2")) {
                    session.setState(ChatState.WAITING_PROTOCOL);
                    return "Informe o protocolo:";
                } else {
                    return "Opção inválida";
                }

            case WAITING_SECTOR:
                session.setSector(message);
                session.setState(ChatState.WAITING_TITLE);
                return "Dê um nome ao chamado:";

            case WAITING_TITLE:
                session.setTitle(message);
                session.setState(ChatState.WAITING_DESCRIPTION);
                return "Escreva seu problema detalhadamente:";

            case WAITING_DESCRIPTION:
                session.setDescription(message);
                session.setState(ChatState.CONFIRMATION);
                return """
                        Resumo do chamado:

                        Setor: %s
                        Título: %s
                        Descrição: %s

                        1 - Confirmar
                        2 - Cancelar
                        """.formatted(
                            session.getSector(), 
                            session.getTitle(), 
                            session.getDescription()
                        );

            case CONFIRMATION:
                if (message.equals("1")) {
                    CreateTicketRequest request = new CreateTicketRequest(
                        session.getSector(), 
                        session.getTitle(), 
                        session.getDescription());
                    
                    TicketResponse response = ticketService.createTicket(request);
                    session = new ChatSession();

                    return """
                            Chamado criado com sucesso!

                            Protocolo: %s
                            Status: %s
                            """.formatted(
                                response.getProtocol(),
                                response.getStatus()
                            );
                } else if (message.equals("2")) {
                    session = new ChatSession();

                    return "Chamado cancelado com sucesso.";
                } else {
                    return "Opção inválida";
                }
            case WAITING_PROTOCOL:
                return "Fluxo ainda não implementado.";
            case FINISHED:
                return "Fluxo ainda não implementado.";
            default:
                return "Opção inválida.";
        }
    }
}
