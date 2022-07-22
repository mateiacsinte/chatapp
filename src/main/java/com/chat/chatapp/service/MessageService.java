package com.chat.chatapp.service;

import com.chat.chatapp.dto.MessageDTO;
import com.chat.chatapp.entities.ChatUser;
import com.chat.chatapp.entities.Message;
import com.chat.chatapp.repository.MessageRepository;
import com.chat.chatapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    private final UserRepository userRepository;

    public MessageService(MessageRepository messageRepository, UserRepository userRepository){
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    public MessageDTO sendMessage(MessageDTO messageDTO){
        Message message = new Message();
        ChatUser sender = userRepository
                .findAll()
                .stream()
                .filter(user -> Objects.equals(user.getId(), messageDTO.getSenderId()))
                        .findFirst().orElse(null);

        ChatUser receiver = userRepository
                .findAll()
                .stream()
                .filter(user -> Objects.equals(user.getId(), messageDTO.getReceiverId()))
                        .findFirst().orElse(null);

        message.setContent(messageDTO.getContent());
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setSendAt(new Date(System.currentTimeMillis()));
        messageRepository.save(message);
        return messageDTO;
    }
}
