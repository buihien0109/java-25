package com.example.demoapp.service.impl;

import com.example.demoapp.dao.BookDAO;
import com.example.demoapp.model.Book;
import com.example.demoapp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDAO bookDAO;

    @Override
    public Book findBookById(int id) {
        return bookDAO.findById(id);
    }

    @Override
    public List<Book> findBooksByTitle(String keyword) {
        return bookDAO.findByTitleContainsIgnoreCase(keyword);
    }
}
