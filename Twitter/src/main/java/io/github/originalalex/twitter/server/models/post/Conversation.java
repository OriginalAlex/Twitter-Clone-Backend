package io.github.originalalex.twitter.server.models.post;

import io.github.originalalex.twitter.server.models.user.User;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "conversations")
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToMany
    private Set<User> involvedParties;

    @OneToMany
    private Set<Message> messages;

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    private void addMessage(Message message) { this.messages.add(message); }

    public Set<User> getInvolvedParties() {

        return involvedParties;
    }

    public void setInvolvedParties(Set<User> involvedParties) {
        this.involvedParties = involvedParties;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
