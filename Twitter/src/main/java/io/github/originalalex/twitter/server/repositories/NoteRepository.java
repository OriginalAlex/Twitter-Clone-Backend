package io.github.originalalex.twitter.server.repositories;

import io.github.originalalex.twitter.server.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
}
