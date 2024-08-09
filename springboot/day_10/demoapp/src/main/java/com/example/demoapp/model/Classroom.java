package com.example.demoapp.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Classroom {
//    @Autowired
//    Student student;
//
//    @Autowired
//    Teacher teacher;

    Student student;
    Teacher teacher;

    public Classroom(Student student, @Qualifier("teacher1") Teacher teacher) {
        this.student = student;
        this.teacher = teacher;
    }
}