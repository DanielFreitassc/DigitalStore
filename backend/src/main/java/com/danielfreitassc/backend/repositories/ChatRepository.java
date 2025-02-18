package com.danielfreitassc.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danielfreitassc.backend.models.ChatEntity;

public interface ChatRepository extends JpaRepository<ChatEntity,String> {
    
}
