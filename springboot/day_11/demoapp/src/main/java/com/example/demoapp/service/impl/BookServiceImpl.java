package com.example.demoapp.service.impl;

import com.example.demoapp.dao.BookDAO;
import com.example.demoapp.model.Book;
import com.example.demoapp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDAO bookDAO;

    @Override
    public List<Book> getAllBooks() {
        return bookDAO.findAll();
    }

    @Override
    public Book findBookById(int id) {
        return bookDAO.findById(id);
    }

    @Override
    public List<Book> findBooksByTitle(String keyword) {
        return bookDAO.findByTitleContainsIgnoreCase(keyword);
    }

    @Override
    public List<Book> findBooksBeetweenYears(int startYear, int endYear) {
        List<Book> books = bookDAO.findAll();
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getYear() >= startYear && book.getYear() <= endYear) {
                result.add(book);
            }
        }
        return result;
    }

    @Override
    public List<Book> sortBooksByYearDesc() {
        List<Book> books = bookDAO.findAll();
        books.sort(new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o2.getYear() - o1.getYear();
            }
        });
        return books;
    }
}
