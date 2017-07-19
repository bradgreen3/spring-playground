package com.allstate.compozed.springplayground.lesson;

import org.springframework.web.bind.annotation.*;

/**
 * Created by localadmin on 7/18/17.
 */

@RestController
@RequestMapping("/lessons")
public class LessonController {

    private final LessonRepository repository;

    LessonController(final LessonRepository repository) {

        this.repository = repository;
    }

    @PostMapping
    LessonModel create(@RequestBody final LessonModel lesson) {
        return repository.save(lesson);
    }

    @GetMapping
    Iterable<LessonModel> list() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    LessonModel read(@PathVariable final long id) { return repository.findOne(id); }

    @DeleteMapping("/{id}")
    void delete(@PathVariable final long id) { repository.delete(id); }

    @PutMapping("/{id}")
    LessonModel update(@RequestBody final LessonModel lesson, @PathVariable final long id) {
        LessonModel foundLesson = repository.findOne(id);
            foundLesson.setTitle(lesson.getTitle());

            return repository.save(foundLesson);
    }
}
