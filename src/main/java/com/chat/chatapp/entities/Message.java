package com.chat.chatapp.entities;

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
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "sender_user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ChatUser sender;

    @ManyToOne
    @JoinColumn(name = "receiver_user_id")
    private ChatUser receiver;

    @Column(name = "sentAt")
    private Date sendAt;

    @Column(name = "content")
    private String content;

}
