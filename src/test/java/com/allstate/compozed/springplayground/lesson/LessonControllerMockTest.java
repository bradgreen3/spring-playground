package com.allstate.compozed.springplayground.lesson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.Collections;
import java.util.Random;

import static org.hamcrest.Matchers.is;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



/**
 * Created by localadmin on 7/18/17.
 */

@RunWith(SpringRunner.class)
@WebMvcTest(LessonController.class)

public class LessonControllerMockTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    LessonRepository repository;

    @Test
    public void testCreate() throws Exception {

        when(repository.save(any(LessonModel.class))).then(returnsFirstArg());

        MockHttpServletRequestBuilder request = post("/lessons")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"Mock me another one!\"}");

        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Mock me another one!")));

        verify(repository).save(any(LessonModel.class));
    }

    @Test
    public void testList() throws Exception {

        Long id = new Random().nextLong();
        LessonModel lesson = new LessonModel();
        lesson.setTitle("test");
        lesson.setId(id);

        when(repository.findAll()).thenReturn(Collections.singletonList(lesson));

        MockHttpServletRequestBuilder request = get("/lessons").
                contentType(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(lesson.getId())))
                .andExpect(jsonPath("$[0].title", is(lesson.getTitle())));

        verify(repository).findAll();

    }


}
