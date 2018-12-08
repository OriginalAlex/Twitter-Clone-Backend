package io.github.originalalex.twitter.server.repositories;

import io.github.originalalex.twitter.server.models.post.Conversation;
import io.github.originalalex.twitter.server.models.post.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    default Set<Message> getByConversation(Conversation conversation) {
        List<Message> allMessages = this.findAll();
        Set<Message> messagesInConvo = new HashSet<>();
        allMessages.forEach(message -> {
            if (message.getConversationBelongingTo().getId() == conversation.getId()) {
                messagesInConvo.add(message);
            }
        });
        return messagesInConvo;
    }

}
