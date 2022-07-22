package com.chat.chatapp.service;

import com.chat.chatapp.dto.UserDTO;
import com.chat.chatapp.dto.UserRegisterDTO;
import com.chat.chatapp.entities.Message;
import com.chat.chatapp.entities.ChatUser;
import com.chat.chatapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

//    public UserService(UserRepository userRepository){
//        this.userRepository = userRepository;
//    }
    // temporary use of collections until spring data binding
    public List<ChatUser> users = new LinkedList<>();

    public List<Message> messages = new LinkedList<>();

    @Autowired
    private UserRepository userRepository;


    public UserDTO addUser(UserRegisterDTO user){
        ChatUser newUser = new ChatUser();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        //users.add(newUser);
        userRepository.save(newUser);
        return new UserDTO(newUser.getId(), newUser.getUsername());
    }

    public List<UserDTO> getUsers(){
        return userRepository.findAll().stream()
                .map(userEntity -> new UserDTO(userEntity.getId(), userEntity.getUsername()))
                .collect(Collectors.toList());
    }

}
