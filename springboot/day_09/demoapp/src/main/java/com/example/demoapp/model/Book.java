package com.example.demoapp.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Book {
    int id;
    String title;
    String author;
    int year;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }
}
