package com.example.demoapp.utils;

import com.example.demoapp.model.Book;
import org.springframework.stereotype.Component;

import java.util.List;

@Component // Su dung thu vien Apache POI
public class ExcelFileReader implements IFileReader {
    @Override
    public List<Book> readFile(String path) {
        // Implement the method here
        return List.of();
    }
}
