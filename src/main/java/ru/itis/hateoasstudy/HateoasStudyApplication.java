package ru.itis.hateoasstudy;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.itis.hateoasstudy.model.*;
import ru.itis.hateoasstudy.repository.CommentRepository;
import ru.itis.hateoasstudy.repository.NoteRepository;
import ru.itis.hateoasstudy.repository.PostRepository;
import ru.itis.hateoasstudy.repository.UserRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;

import static java.util.Arrays.asList;

@SpringBootApplication
public class HateoasStudyApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(HateoasStudyApplication.class, args);

        CommentRepository commentRepository = context.getBean(CommentRepository.class);
        NoteRepository noteRepository = context.getBean(NoteRepository.class);
        PostRepository postRepository = context.getBean(PostRepository.class);
        UserRepository userRepository = context.getBean(UserRepository.class);

        User marat = User.builder()
                .firstName("Марат")
                .lastName("Исангулов")
                .build();
        User ilnur = User.builder()
                .firstName("Ильнур")
                .lastName("Сагитов")
                .build();
        marat = userRepository.save(marat);
        ilnur = userRepository.save(ilnur);

        Note note1 = Note.builder()
                .author(marat)
                .title("Поигарть в доту")
                .notificationTime(Timestamp.valueOf(LocalDateTime.now()))
                .status(NoteStatus.Draft)
                .build();
        Note note2 = Note.builder()
                .author(ilnur)
                .title("Прийти на пару JavaLab")
                .notificationTime(Timestamp.valueOf(LocalDateTime.now()))
                .status(NoteStatus.Draft)
                .build();
        noteRepository.saveAll(asList(note1, note2));
        marat.setNotes(Collections.singletonList(note1));
        ilnur.setNotes(Collections.singletonList(note2));

        Post post1 = Post.builder()
                .author(ilnur)
                .tittle("JavaLab топ")
                .description("temp")
                .createdDate(Timestamp.valueOf(LocalDateTime.now()))
                .build();
        Post post2 = Post.builder()
                .author(marat)
                .tittle("DataLab топ")
                .description("Враньё")
                .createdDate(Timestamp.valueOf(LocalDateTime.now()))
                .build();
        post1 = postRepository.save(post1);
        post2 = postRepository.save(post2);

        marat.setPosts(Collections.singletonList(post2));
        ilnur.setPosts(Collections.singletonList(post1));

        Comment comment1 = Comment.builder()
                .author(ilnur)
                .body("Провокация!")
                .post(post2)
                .createdDate(Timestamp.valueOf(LocalDateTime.now()))
                .build();
        Comment comment2 = Comment.builder()
                .author(marat)
                .body("Извиняюсь, я был не прав")
                .post(post2)
                .createdDate(Timestamp.valueOf(LocalDateTime.now()))
                .build();
        Comment comment3 = Comment.builder()
                .author(marat)
                .body("Лайк репост")
                .post(post1)
                .createdDate(Timestamp.valueOf(LocalDateTime.now()))
                .build();
        commentRepository.saveAll(asList(comment1, comment2, comment3));
        post1.setComments(Collections.singletonList(comment3));
        post2.setComments(asList(comment1, comment2));
        postRepository.saveAll(asList(post1, post2));

        marat.setComments(asList(comment2, comment3));
        ilnur.setComments(Collections.singletonList(comment1));
        userRepository.saveAll(asList(marat, ilnur));
    }

}
