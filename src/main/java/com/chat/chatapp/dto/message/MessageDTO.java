package com.chat.chatapp.dto.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class MessageDTO {

    private Integer senderId;

    private Integer receiverId;

    private String content;

    private Date sentAt;
}
