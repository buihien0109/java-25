package com.example.demoapp.service;

import com.example.demoapp.model.Book;

import java.util.List;

public interface BookService {
    Book findBookById(int id);

    List<Book> findBooksByTitle(String keyword);
}
