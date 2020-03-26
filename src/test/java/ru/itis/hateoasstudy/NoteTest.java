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
import ru.itis.hateoasstudy.model.Note;
import ru.itis.hateoasstudy.model.NoteStatus;
import ru.itis.hateoasstudy.model.User;
import ru.itis.hateoasstudy.service.NoteService;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class NoteTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoteService noteService;
    private Note note;

    @BeforeEach
    public void setUp() {
        this.note = postedNote();
        when(noteService.postNote(1L)).thenReturn(note);
    }

    @Test
    public void notePublishTest() throws Exception {
        mockMvc.perform(put("/notes/1/publish"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(note.getTitle()))
                .andExpect(jsonPath("$.status").value(note.getStatus().toString()))
                .andDo(document("posted_note", /*links(
                        linkWithRel("delete").description("Удаление заметки") // Два дня пытался заставить линк нормально документироваться, но НЕ ВЫШЛО
                        ),*/ responseFields(
                        fieldWithPath("title").description("Название заметки"),
                        fieldWithPath("status").description("Статус заметки"),
                        fieldWithPath("notificationTime").description("Время для напоминания")
                )));
    }

    private Note postedNote() {
        return Note.builder()
                .id(1L)
                .title("test")
                .author(User.builder()
                        .firstName("test name")
                        .lastName("test surname")
                        .build())
                .status(NoteStatus.Posted)
                .notificationTime(Timestamp.valueOf(LocalDateTime.now()))
                .build();
    }

}
