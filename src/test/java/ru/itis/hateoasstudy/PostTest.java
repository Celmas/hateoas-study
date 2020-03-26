package ru.itis.hateoasstudy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.itis.hateoasstudy.model.Post;
import ru.itis.hateoasstudy.model.User;
import ru.itis.hateoasstudy.service.PostService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class PostTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;
    private Post post;

    @BeforeEach
    public void setUp() {
        this.post = postWithoutComments();
        when(postService.flushComments(1L)).thenReturn(post);
    }

    @Test
    public void testFlushComments() throws Exception {
        mockMvc.perform(delete("/posts/1/flush"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(post.getTitle()))
                .andExpect(jsonPath("$.description").value(post.getDescription()))
                .andDo(document("flushed_comment", responseFields(
                        fieldWithPath("title").description("Название поста"),
                        fieldWithPath("description").description("Описание поста"),
                        fieldWithPath("createdDate").description("Время создания")
                )));
    }

    private Post postWithoutComments() {
        return Post.builder()
                .author(User.builder()
                        .id(1L)
                        .lastName("Test")
                        .firstName("Tester")
                        .build())
                .title("Testing")
                .description("Test description")
                .createdDate(Timestamp.valueOf(LocalDateTime.now()))
                .comments(Collections.emptyList())
                .build();

    }
}
