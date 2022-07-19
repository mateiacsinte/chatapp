package com.chat.chatapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MessageDTO {

    private Integer senderId;

    private Integer receiverId;

    private String content;
}
