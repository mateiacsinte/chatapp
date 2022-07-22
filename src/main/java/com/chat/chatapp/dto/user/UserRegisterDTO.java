package com.chat.chatapp.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserRegisterDTO {

    private String username;

    private String password;
}
