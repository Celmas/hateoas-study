package ru.itis.hateoasstudy.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.hateoasstudy.service.NoteServiceImpl;

@RepositoryRestController
@AllArgsConstructor
public class NoteController {
    private NoteServiceImpl noteService;

    @RequestMapping(value = "/notes/{note-id}/publish", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<?> post(@PathVariable("note-id") Long noteId) {
        return ResponseEntity.ok(
                new EntityModel<>(
                        noteService.postNote(noteId)));
    }

    @RequestMapping(value = "/notes/{note-id}/delete", method = RequestMethod.DELETE)
    public @ResponseBody
    ResponseEntity<?> delete(@PathVariable("note-id") Long noteId) {
        return ResponseEntity.ok(
                new EntityModel<>(
                        noteService.deleteNote(noteId)));
    }
}
