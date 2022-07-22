package com.chat.chatapp.controller;
import com.chat.chatapp.dto.MessageDTO;
import com.chat.chatapp.dto.UserDTO;
import com.chat.chatapp.dto.UserRegisterDTO;
import com.chat.chatapp.service.MessageService;
import com.chat.chatapp.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    private final MessageService messageService;

    public UserController(UserService userService, MessageService messageService){

        this.userService = userService;
        this.messageService = messageService;
    }

    @PostMapping("/user")
    public UserDTO addUser(@RequestBody UserRegisterDTO user){

        return userService.addUser(user);
    }

    @GetMapping("/user")
    public List<UserDTO> getUsers(){
        return userService.getUsers();
    }

    @PostMapping("/user/message")
    public MessageDTO sendMessage(@RequestBody MessageDTO messageDTO){
        return messageService.sendMessage(messageDTO);
    }

}
