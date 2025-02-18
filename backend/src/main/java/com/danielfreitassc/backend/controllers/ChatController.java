package com.danielfreitassc.backend.controllers;


import java.util.List;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.danielfreitassc.backend.dtos.ChatIdRequestDto;
import com.danielfreitassc.backend.dtos.ChatRequestDto;
import com.danielfreitassc.backend.dtos.ChatRequestUpdateDto;
import com.danielfreitassc.backend.dtos.ChatResponseDto;
import com.danielfreitassc.backend.dtos.MessageResponseDto;
import com.danielfreitassc.backend.services.ChatService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @MessageMapping("/new-message")
    @SendTo("/topics/messages")
    public ChatResponseDto sendMessage(@Valid ChatRequestDto chatRequestDto) {
        return chatService.sendMessage(chatRequestDto);
    }

    @MessageMapping("/update-message")
    @SendTo("/topics/messages")
    public ChatResponseDto updateMessage(@Valid ChatRequestUpdateDto chatRequestUpdateDto) {
        return chatService.updateMessage(chatRequestUpdateDto);
    }

    @MessageMapping("/delete-message")
    @SendTo("/topics/messages")
    public MessageResponseDto deleteMessage(@Valid ChatIdRequestDto chatIdRequestDto) {
        return chatService.deleteMessage(chatIdRequestDto);
    }

    @GetMapping("/history-chats")
    public List<ChatResponseDto> getMessages() {
        return chatService.getMessages();
    }

    @GetMapping("/history-chats/{id}")
    public ChatResponseDto getMessage(@PathVariable String id) {
        return chatService.getMessage(id);
    }
    
}
