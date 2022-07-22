package com.chat.chatapp.service;

import com.chat.chatapp.dto.message.MessageDTO;
import com.chat.chatapp.dto.message.SendMessageDTO;
import com.chat.chatapp.entities.ChatUser;
import com.chat.chatapp.entities.Message;
import com.chat.chatapp.repository.MessageRepository;
import com.chat.chatapp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    private final UserRepository userRepository;

    public MessageService(MessageRepository messageRepository, UserRepository userRepository){
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    public MessageDTO sendMessage(SendMessageDTO messageDTO){
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

        return new MessageDTO(message.getSender().getId(), message.getReceiver().getId(),
                message.getContent(), message.getSendAt());
    }

    public List<MessageDTO> getMessages() {
        return messageRepository
                .findAll()
                .stream()
                .map(message -> new MessageDTO(message.getSender().getId(),
                        message.getReceiver().getId(), message.getContent(), message.getSendAt()))
                .collect(Collectors.toList());
    }
    public List<MessageDTO> getMessages(Integer userId){
        return messageRepository
                .getMessageOfUser(userId)
                .stream()
                .map(message -> new MessageDTO(message.getSender().getId(),
                        message.getReceiver().getId(), message.getContent(), message.getSendAt()))
                .collect(Collectors.toList());
        }
}
