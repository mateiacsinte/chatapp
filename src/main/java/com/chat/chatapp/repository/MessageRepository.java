package com.chat.chatapp.repository;

import com.chat.chatapp.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

    @Query("SELECT m FROM Message m WHERE m.sender.id = ?1 or m.receiver.id = ?1")
    List<Message> getMessageOfUser(Integer id);
}
