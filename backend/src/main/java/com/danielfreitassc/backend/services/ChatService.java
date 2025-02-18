package com.danielfreitassc.backend.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.danielfreitassc.backend.dtos.ChatIdRequestDto;
import com.danielfreitassc.backend.dtos.ChatRequestDto;
import com.danielfreitassc.backend.dtos.ChatRequestUpdateDto;
import com.danielfreitassc.backend.dtos.ChatResponseDto;
import com.danielfreitassc.backend.dtos.MessageResponseDto;
import com.danielfreitassc.backend.mappers.ChatMapper;
import com.danielfreitassc.backend.models.ChatEntity;
import com.danielfreitassc.backend.models.MessageStatus;
import com.danielfreitassc.backend.repositories.ChatRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;
    private final ChatMapper chatMapper;

    public ChatResponseDto sendMessage(ChatRequestDto chatRequestDto) {
        ChatEntity chatEntity = chatMapper.toEntity(chatRequestDto);
        chatEntity.setStatus(MessageStatus.SENT);
        return chatMapper.toDto(chatRepository.save(chatEntity));
    }

    public ChatResponseDto updateMessage(ChatRequestUpdateDto chatRequestUpdateDto) {
        ChatEntity chatEntity = checkId(chatRequestUpdateDto.id());
        chatMapper.toUpdateDto(chatRequestUpdateDto, chatEntity);
        return chatMapper.toDto(chatRepository.save(chatEntity));
    }   

    public MessageResponseDto deleteMessage(ChatIdRequestDto chatIdRequestDto) {
       ChatEntity chatEntity = checkId(chatIdRequestDto.id());
       chatEntity.setStatus(MessageStatus.DELETED);
       chatRepository.save(chatEntity);
       return new MessageResponseDto("Mensagem apagada");
    }

    public List<ChatResponseDto> getMessages() {
        return chatRepository.findAll().stream()
        .map(chatMapper::toDto).collect(Collectors.toList());
    }

    public ChatResponseDto getMessage(String id) {
        return chatMapper.toDto(checkId(id));
    }    

    private ChatEntity checkId(String id) {
        Optional<ChatEntity> chat = chatRepository.findById(id);
        if(chat.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Mensagem não encontrado");
        return chat.get();
    }

}
