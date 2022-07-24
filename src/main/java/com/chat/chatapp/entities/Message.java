package com.chat.chatapp.entities;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "message")
public class Message {

    @GeneratedValue
    @Id
    @NotNull
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "sender_user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private ChatUser sender;

    @ManyToOne
    @JoinColumn(name = "receiver_user_id")
    @NotNull
    private ChatUser receiver;

    @Column(name = "sentAt")
    @NotNull
    private Date sendAt;

    @Column(name = "content")
    @NotNull
    private String content;

}
