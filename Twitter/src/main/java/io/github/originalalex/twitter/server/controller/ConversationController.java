package io.github.originalalex.twitter.server.controller;

import io.github.originalalex.twitter.server.models.post.Conversation;
import io.github.originalalex.twitter.server.repositories.ConversationRepository;
import io.github.originalalex.twitter.server.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/conversation")
public class ConversationController {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ConversationRepository conversationRepository;

    @RequestMapping(value = "/fetchById/{id}", method = RequestMethod.GET)
    public Conversation fetchConversationById(@PathVariable Long id) {
        /* verification */
        Conversation conversation = conversationRepository.findById(id).orElse(null);
        return conversation;
    }

    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
    public ResponseEntity sendMessage()


}
