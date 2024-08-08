package com.example.demoapp.controller;

import com.example.demoapp.model.Book;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/*
* Controller: Nơi tiếp nhận request từ client, xử lý và trả về response
* - @Controller: Các controller trả về Template (giao diện). Ngoài ra có thể trả về dữ liệu dạng JSON, XML, ...
* - @RestController: Các controller trả về dữ liệu dạng JSON, XML, ...
* */
@RestController
@RequestMapping("/books")
public class BookController {
    private List<Book> books = new ArrayList<>(List.of(
            new Book(1, "Java Programming", "Author 1", 2021),
            new Book(2, "Python Programming", "Author 2", 2020),
            new Book(3, "C Programming", "Author 3", 2019)
    ));

    // Lấy danh sách tất cả sách
    // @RequestMapping(method = RequestMethod.GET, value = "/all")
    @GetMapping // GET: http://localhost:8080/books
    public List<Book> getAllBooks() {
        return books;
    }

    // Lấy sách theo id
    // http://localhost:8080/books/1
    // http://localhost:8080/books/2
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable int id) {
        System.out.println("id = " + id);
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }
}
