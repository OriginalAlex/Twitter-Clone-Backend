package io.github.originalalex.twitter.server.repositories;

import io.github.originalalex.twitter.server.models.post.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {
}
