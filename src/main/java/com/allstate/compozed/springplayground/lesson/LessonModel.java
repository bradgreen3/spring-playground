package com.allstate.compozed.springplayground.lesson;
import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by localadmin on 7/18/17.
 */

@Entity
@Table(name = "lessons")
public class LessonModel {

    @Column(columnDefinition = "date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date deliveredOn;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    public Date getDeliveredOn() {
        return deliveredOn;
    }

    public void setDeliveredOn(Date deliveredOn) {
        this.deliveredOn = deliveredOn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
