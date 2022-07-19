package com.chat.chatapp.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MessageEntity {

    private Integer id;

    private Integer senderId;

    private Integer receiverId;

    private String content;
}
