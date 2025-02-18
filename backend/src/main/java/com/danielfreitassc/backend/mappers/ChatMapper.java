package com.danielfreitassc.backend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.danielfreitassc.backend.dtos.ChatRequestDto;
import com.danielfreitassc.backend.dtos.ChatRequestUpdateDto;
import com.danielfreitassc.backend.dtos.ChatResponseDto;
import com.danielfreitassc.backend.models.ChatEntity;
import com.danielfreitassc.backend.models.MessageStatus;

@Mapper(componentModel = "spring")
public interface ChatMapper {
    @Mapping(target = "message", expression = "java(mapContent(chatEntity))")
    ChatResponseDto toDto(ChatEntity chatEntity);

    @Mapping(target = "createdAt", ignore = true) 
    @Mapping(target = "status", ignore = true)   
    void toUpdateDto(ChatRequestUpdateDto chatRequestUpdateDto, @MappingTarget ChatEntity chatEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    ChatEntity toEntity(ChatRequestDto chatRequestDto);

    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    ChatEntity toUpdateEntity(ChatRequestUpdateDto chatRequestUpdateDto);

    default String mapContent(ChatEntity chatEntity) {
        return chatEntity.getStatus().equals(MessageStatus.DELETED) ? "Mensagem apagada" : chatEntity.getMessage();
    }
}   
