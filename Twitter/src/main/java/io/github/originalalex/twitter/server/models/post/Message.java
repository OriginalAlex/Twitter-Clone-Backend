package io.github.originalalex.twitter.server.models.post;

import io.github.originalalex.twitter.server.models.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String content;

    @ManyToOne
    private Conversation conversationBelongingTo;

    @ManyToOne
    private User sender;

    private Date dateSent;

    @PrePersist
    public void createdAt() {
        this.dateSent = new Date();
    }

    public Date getDateSent() {
        return dateSent;
    }

    public void setDateSent(Date dateSent) {
        this.dateSent = dateSent;
    }

    public User getSender() {

        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public Conversation getConversationBelongingTo() {

        return conversationBelongingTo;
    }

    public void setConversationBelongingTo(Conversation conversationBelongingTo) {
        this.conversationBelongingTo = conversationBelongingTo;
    }

    public String getContent() {

        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;

    }

    public void setId(Long id) {
        this.id = id;
    }

}
