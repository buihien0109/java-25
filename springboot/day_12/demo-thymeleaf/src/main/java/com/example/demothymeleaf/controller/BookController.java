package com.example.demothymeleaf.controller;

import com.example.demothymeleaf.model.Book;
import com.example.demothymeleaf.model.PageResponse;
import com.example.demothymeleaf.model.PageResponseImpl;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {
    private List<Book> books = new ArrayList<>();

    public BookController() {
        Faker faker = new Faker();
        for (int i = 0; i < 30; i++) {
            Book book = Book.builder()
                    .id(i + 1)
                    .title(faker.book().title())
                    .author(faker.book().author())
                    .year(faker.number().numberBetween(1950, 2021))
                    .build();
            books.add(book);
        }
    }

    // http://localhost:8080 -> Hiển thị template "index.html"
    @GetMapping
    public String getHomePage(Model model, @RequestParam(required = false) String title) {
        Book book = books.get(0);

        List<Book> newBooks = new ArrayList<>();
        if(title != null) {
            newBooks = books.stream()
                    .filter(b -> b.getTitle().toLowerCase().contains(title.toLowerCase()))
                    .toList();
        } else {
            newBooks = books;
        }

        model.addAttribute("book", book);
        model.addAttribute("books", newBooks);
        return "index";
    }

    // http://localhost:8080/books
    // http://localhost:8080/books?page=1&size=10
    // currentPage: Trang hien tai
    // pageSize: So luong phan tu tren 1 trang
    // totalElements: Tong so phan tu
    // totalPages: Tong so trang
    // content: Du lieu tren trang hien tai
    @GetMapping("/books")
    public String getBooksPage(Model model,
                               @RequestParam(required = false, defaultValue = "1") int page,
                               @RequestParam(required = false, defaultValue = "10") int size) {

        PageResponse<Book> pageResponse = PageResponseImpl.<Book>builder()
                .currentPage(page)
                .pageSize(size)
                .data(books)
                .build();

        model.addAttribute("pageResponse", pageResponse);
        return "books";
    }

    @GetMapping("/books/{id}")
    public String getBookDetail(@PathVariable int id, Model model) {
        Book book = books.stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .orElse(null);

        model.addAttribute("book", book);
        return "book-detail";
    }

    // http://localhost:8080/blog
    @GetMapping("/blog")
    public String getBlogPage() {
        return "admin/blog";
    }
}
