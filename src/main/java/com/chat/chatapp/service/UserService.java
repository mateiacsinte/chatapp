package com.chat.chatapp.service;

import com.chat.chatapp.dto.user.UserDTO;
import com.chat.chatapp.dto.user.UserRegisterDTO;
import com.chat.chatapp.entities.ChatUser;
import com.chat.chatapp.exceptions.UserNotFoundException;
import com.chat.chatapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    private UserRepository userRepository;


    public UserDTO addUser(UserRegisterDTO user){
        ChatUser newUser = new ChatUser();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        userRepository.save(newUser);
        return new UserDTO(newUser.getId(), newUser.getUsername());
    }

    public List<UserDTO> getUsers(){
        return userRepository.findAll().stream()
                .map(userEntity -> new UserDTO(userEntity.getId(), userEntity.getUsername()))
                .collect(Collectors.toList());
    }

    public UserDTO updateUser(UserDTO user) throws UserNotFoundException {
         Optional<ChatUser> currentUserOpt = userRepository.findById(user.getId());
         if(currentUserOpt.isPresent()){
             currentUserOpt.get().setUsername(user.getUsername());
             userRepository.save(currentUserOpt.get());
             return new UserDTO(currentUserOpt.get().getId(), currentUserOpt.get().getUsername());
         }else throw new UserNotFoundException("User Not Found");
    }

    public UserDTO deleteUser(UserDTO user) throws UserNotFoundException {
         Optional<ChatUser> currentUserOpt = userRepository.findById(user.getId());
         if(currentUserOpt.isPresent()){
             userRepository.delete(currentUserOpt.get());
             return new UserDTO(currentUserOpt.get().getId(), currentUserOpt.get().getUsername());
         }else throw new UserNotFoundException("User Not Found");
    }
}
