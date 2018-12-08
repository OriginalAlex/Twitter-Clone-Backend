package io.github.originalalex.twitter.server.controller;

import io.github.originalalex.twitter.server.models.Note;
import io.github.originalalex.twitter.server.repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class NoteController {

    @Autowired
    private NoteRepository noteRepository;

    @RequestMapping("/")
    public String index() {
        return "Hey";
    }

    @GetMapping("/notes")
    public List<Note> getAllNotes() {
        System.out.println("received!");
        return noteRepository.findAll();
    }

    @PostMapping(path = "/notes", consumes = "application/json")
    public Note createNote(@RequestBody Note note) {
        System.out.println("received!");
        System.out.println(note);
        return noteRepository.save(note);
    }

    @GetMapping("/notes/{id}")
    public Note getNoteById(@PathVariable(value="id") Long noteId) {
        System.out.println("received!");
        return noteRepository.findById(noteId).orElse(null);
    }

    @PutMapping("/notes/{id}")
    public Note updateNote(@PathVariable(value = "id") Long noteId,
                           @Valid @RequestBody Note noteDetails) {
        System.out.println("received!");
        Note note = noteRepository.findById(noteId)
                .orElseThrow(null);

        note.setTitle(noteDetails.getTitle());
        note.setContent(noteDetails.getContent());

        Note updatedNote = noteRepository.save(note);
        return updatedNote;
    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long noteId) {
        System.out.println("received!");
        Note note = noteRepository.findById(noteId)
                .orElseThrow(null);

        noteRepository.delete(note);

        return ResponseEntity.ok().build();
    }

}
