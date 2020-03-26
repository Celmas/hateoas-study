package ru.itis.hateoasstudy.service;

import ru.itis.hateoasstudy.model.Post;

public interface PostService {
    Post flushComments(Long id);
}
