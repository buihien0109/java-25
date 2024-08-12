package com.example.demoapp.dao;

import com.example.demoapp.model.Book;

import java.util.List;

public interface BookDAO {
    Book findById(int id);

    List<Book> findByTitleContainsIgnoreCase(String keyword);
}
