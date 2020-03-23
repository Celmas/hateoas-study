package ru.itis.hateoasstudy.config;

import lombok.AllArgsConstructor;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import ru.itis.hateoasstudy.controller.NoteController;
import ru.itis.hateoasstudy.model.Note;
import ru.itis.hateoasstudy.model.NoteStatus;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@AllArgsConstructor
public class NoteConfiguration implements RepresentationModelProcessor<EntityModel<Note>> {
    private RepositoryEntityLinks links;

    @Override
    public EntityModel<Note> process(EntityModel<Note> model) {
        Note content = model.getContent();
        if (content != null && content.getStatus() != NoteStatus.Deleted && content.getStatus() != NoteStatus.Posted) {
            model.add(WebMvcLinkBuilder.linkTo(methodOn(NoteController.class).post(content.getId())).withRel("publish"));
        }
        if (content != null && content.getStatus() != NoteStatus.Deleted) {
            model.add(WebMvcLinkBuilder.linkTo(methodOn(NoteController.class).delete(content.getId())).withRel("delete"));
        }
        return model;
    }
}
