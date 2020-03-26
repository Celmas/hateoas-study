package ru.itis.hateoasstudy.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.hateoasstudy.service.PostService;

@RepositoryRestController
@AllArgsConstructor
public class PostController {
    private PostService postService;

    @RequestMapping(value = "/posts/{post-id}/flush", method = RequestMethod.DELETE)
    public @ResponseBody
    ResponseEntity<?> flushComments(@PathVariable("post-id") Long postId) {
        return ResponseEntity.ok(
                new EntityModel<>(
                        postService.flushComments(postId)));
    }
}
