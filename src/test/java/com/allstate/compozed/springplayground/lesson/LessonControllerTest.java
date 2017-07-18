package com.allstate.compozed.springplayground.lesson;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Created by localadmin on 7/18/17.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(LessonController.class)

public class LessonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LessonRepository repository;

    @Test
    public void createDelegatesToRepository() throws Exception{
    }
}