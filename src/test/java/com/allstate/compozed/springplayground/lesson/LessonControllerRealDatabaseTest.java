/* See the file "LICENSE" for the full license governing this code. */
package com.allstate.compozed.springplayground.lesson;

/**
 * @author Not Dale
 * @since 7/19/17.
 */


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LessonControllerRealDatabaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LessonRepository repository;


    @Transactional
    @Rollback
    @Test
    public void listReturnsExistingLessons() throws Exception {

        // Setup
        final LessonModel lessonOne = new LessonModel();
        lessonOne.setTitle("Spelling 001 with Dale oLtts");

        final LessonModel lessonTwo = new LessonModel();
        lessonTwo.setTitle("ACID for CRUDL");

        repository.save(Arrays.asList(lessonOne, lessonTwo));

        // Exercise
        mockMvc.perform(get("/lessons"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(2)))
                .andExpect(jsonPath("$[0].id", is(lessonOne.getId().intValue())))
                .andExpect(jsonPath("$[0].title", is("Spelling 001 with Dale oLtts")));
    }

    @Transactional
    @Rollback
    @Test
    public void readReturnsExistingLesson() throws Exception {

        // Setup
        final LessonModel lesson = new LessonModel();
        lesson.setTitle("Algebra 1");

        repository.save(lesson);

        // Exercise
        mockMvc.perform(get("/lessons/{id}", lesson.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(lesson.getId().intValue())))
                .andExpect(jsonPath("$.title", is("Algebra 1")));
    }
}