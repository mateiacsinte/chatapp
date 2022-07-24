package com.chat.chatapp.entities;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class ChatUser {

    @GeneratedValue
    @Id
    @NotNull
    private Integer id;

    @NotNull
    @Column(name = "username")
    private String username;

    @NotNull
    @Column(name = "password")
    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sender", cascade = CascadeType.REMOVE)
    private Set<Message> sentMessages = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "receiver", cascade = CascadeType.REMOVE)
    private Set<Message> receivedMessages = new HashSet<>();

}
