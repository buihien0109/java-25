package com.example.demoapp.controller;

import com.example.demoapp.model.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*
 * Controller: Nơi tiếp nhận request từ client, xử lý và trả về response
 * - @Controller: Các controller trả về Template (giao diện). Ngoài ra có thể trả về dữ liệu dạng JSON, XML, ...
 * - @RestController: Các controller trả về dữ liệu dạng JSON, XML, ...
 * - @Rescontroller = @Controller + @ResponseBody
 * - @ResponseBody: Chỉ trả về dữ liệu, không trả về Template. Dữ liệu trả về có thể là JSON, XML, ...
 * - ResponseEntity<?>: Class dại diện cho 1 dối tượng response , có thể set status code, header, ...
 * */
//@RestController
@Controller
@RequestMapping("/books")
public class BookController {
    private List<Book> books = new ArrayList<>(List.of(
            new Book(1, "Java Programming", "Author 1", 2019),
            new Book(2, "Python Programming", "Author 2", 2021),
            new Book(3, "C Programming", "Author 3", 2020)
    ));

    // Lấy danh sách tất cả sách
    // @RequestMapping(method = RequestMethod.GET, value = "/all")
//    @GetMapping // GET: http://localhost:8080/books
//    @ResponseBody
//    @ResponseStatus(HttpStatus.CREATED) // 201
//    public List<Book> getAllBooks() {
//        return books;
//    }

    // Lấy sách theo id
    // http://localhost:8080/books/1
    // http://localhost:8080/books/2
//    @GetMapping("/{id}")
//    @ResponseBody
//    public Book getBookById(@PathVariable int id) {
//        System.out.println("id = " + id);
//        for (Book book : books) {
//            if (book.getId() == id) {
//                return book;
//            }
//        }
//        return null;
//    }

    @GetMapping // GET: http://localhost:8080/books
    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(books, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return new ResponseEntity<>(book, HttpStatus.OK); // 200
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404
    }

    // 1. Viết API sắp xếp book theo năm giảm dần
    // http://localhost:8080/books/sortByYear
    @GetMapping("/sortByYear")
    public ResponseEntity<List<Book>> sortByYear() {
        books.sort(new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o2.getYear() - o1.getYear();
            }
        });
        return ResponseEntity.ok(books);
    }

    // 2. Tìm kiem sach theo ten (trong ten chua tu khoa bat ky)
    // http://localhost:8080/books/searchByTitle/{keyword}
    @GetMapping("/searchByTitle/{keyword}")
    public ResponseEntity<List<Book>> searchByTitle(@PathVariable String keyword) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(book);
            }
        }
        return ResponseEntity.ok(result);
    }

    // 3. Tim kiem sach duoc xuat ban trong khoang thoi gian (fromYear, toYear)
    // http://localhost:8080/books/searchByYear/fromYear/{fromYear}/toYear/{toYear}
    @GetMapping("/searchByYear/fromYear/{fromYear}/toYear/{toYear}")
    public ResponseEntity<List<Book>> findBookInRangeYear(@PathVariable int fromYear, @PathVariable int toYear) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getYear() >= fromYear && book.getYear() <= toYear) {
                result.add(book);
            }
        }
        return ResponseEntity.ok(result);
    }
}
