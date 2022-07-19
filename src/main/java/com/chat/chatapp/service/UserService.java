package com.chat.chatapp.service;

import com.chat.chatapp.dto.UserDTO;
import com.chat.chatapp.dto.UserRegisterDTO;
import com.chat.chatapp.repository.MessageEntity;
import com.chat.chatapp.repository.UserEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    // temporary use of collections until spring data binding
    public List<UserEntity> users = new LinkedList<>();

    public List<MessageEntity> messages = new LinkedList<>();


    public UserDTO addUser(UserRegisterDTO user){
        UserEntity newUser = new UserEntity(users.size(), user.getUsername(), user.getPassword(), null);
        users.add(newUser);
        return new UserDTO(newUser.getId(), newUser.getUsername());
    }

    public List<UserDTO> getUsers(){
        return users.stream()
                .map(userEntity -> new UserDTO(userEntity.getId(), userEntity.getUsername()))
                .collect(Collectors.toList());
    }

}
