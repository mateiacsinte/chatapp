package com.chat.chatapp.repository;

import com.chat.chatapp.entities.ChatUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<ChatUser, Long> {
}
