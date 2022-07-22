package com.chat.chatapp.dto.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SendMessageDTO {

    private Integer senderId;

    private Integer receiverId;

    private String content;
}
