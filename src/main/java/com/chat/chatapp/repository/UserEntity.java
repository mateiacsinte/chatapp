package com.chat.chatapp.repository;

import java.util.List;

public class UserEntity {

    private Integer id;

    private String username;

    private String password;

    private List<MessageEntity> messages;
}
