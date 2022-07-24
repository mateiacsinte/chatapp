package com.chat.chatapp.controller;
import com.chat.chatapp.dto.message.MessageDTO;
import com.chat.chatapp.dto.message.SendMessageDTO;
import com.chat.chatapp.dto.user.UserDTO;
import com.chat.chatapp.dto.user.UserRegisterDTO;
import com.chat.chatapp.exceptions.UserNotFoundException;
import com.chat.chatapp.service.MessageService;
import com.chat.chatapp.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    private final MessageService messageService;

    public UserController(UserService userService, MessageService messageService){

        this.userService = userService;
        this.messageService = messageService;
    }

    @GetMapping("/user")
    public List<UserDTO> getUsers(){
        return userService.getUsers();
    }

    @PostMapping("/user")
    public UserDTO addUser(@RequestBody UserRegisterDTO user){
        return userService.addUser(user);
    }

    @PutMapping("/user")
    public UserDTO updateUser(@RequestBody UserDTO user) throws UserNotFoundException {
        return userService.updateUser(user);
    }

    @DeleteMapping("/user")
    public UserDTO deleteUser(@RequestBody UserDTO user) throws UserNotFoundException {
        return userService.deleteUser(user);
    }

    @PostMapping("/user/message")
    public MessageDTO sendMessage(@RequestBody SendMessageDTO messageDTO){
        return messageService.sendMessage(messageDTO);
    }

    @GetMapping("/user/message")
    public List<MessageDTO> getMessages(){
        return messageService.getMessages();
    }

    @GetMapping("/user/message/{userId}")
    public List<MessageDTO> getMessagesOfUser(@PathVariable Integer userId){
        return messageService.getMessages(userId);
    }
}
