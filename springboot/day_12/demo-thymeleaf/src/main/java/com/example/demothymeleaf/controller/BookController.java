package com.example.demothymeleaf.controller;

import com.example.demothymeleaf.model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {
    private List<Book> books = new ArrayList<>(List.of(
            new Book(1, "Java Programming", "Author 1", 2021),
            new Book(2, "Python Programming", "Author 2", 2020),
            new Book(3, "C Programming", "Author 3", 2019)
    ));

    // http://localhost:8080 -> Hiển thị template "index.html"
    @GetMapping
    public String getHomePage(Model model) {
        Book book = books.get(0);

        model.addAttribute("book", book);
        return "index";
    }

    // http://localhost:8080/blog
    @GetMapping("/blog")
    public String getBlogPage() {
        return "admin/blog";
    }
}
