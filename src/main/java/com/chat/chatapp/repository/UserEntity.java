package com.chat.chatapp.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserEntity {

    private Integer id;

    private String username;

    private String password;

    private List<MessageEntity> messages = new LinkedList<>();

    public boolean addMessage(MessageEntity message){
        return messages.add(message);
    }
}
