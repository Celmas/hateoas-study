package ru.itis.hateoasstudy.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.hateoasstudy.model.Post;
import ru.itis.hateoasstudy.repository.PostRepository;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;
    @Override
    public Post flushComments(Long id) {
        Post post = postRepository.getOne(id);
        post.flushComments();
        return postRepository.save(post);
    }
}
