package com.example.demoapp.dao;

import com.example.demoapp.model.Book;

import java.util.List;

public interface BookDAO {
    List<Book> findAll();

    Book findById(int id);

    List<Book> findByTitleContainsIgnoreCase(String keyword);

    List<Book> findByYearBetween(int startYear, int endYear);

    List<Book> sortBooksByYearDesc();
}
