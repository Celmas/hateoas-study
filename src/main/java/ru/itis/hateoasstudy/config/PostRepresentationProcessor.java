package ru.itis.hateoasstudy.config;

import lombok.AllArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import ru.itis.hateoasstudy.controller.PostController;
import ru.itis.hateoasstudy.model.Post;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
@AllArgsConstructor
public class PostRepresentationProcessor implements RepresentationModelProcessor<EntityModel<Post>> {

    @Override
    public EntityModel<Post> process(EntityModel<Post> model) {
        Post content = model.getContent();
        if (content != null && !content.getComments().isEmpty()) {
            model.add(WebMvcLinkBuilder.linkTo(methodOn(PostController.class).flushComments(content.getId())).withRel("flush comments"));
        }
        return model;
    }
}
