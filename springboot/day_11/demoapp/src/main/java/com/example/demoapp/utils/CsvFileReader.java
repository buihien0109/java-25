package com.example.demoapp.utils;

import com.example.demoapp.model.Book;
import org.springframework.stereotype.Component;

import java.util.List;

@Component // Su dung thu vien OpenCSV
public class CsvFileReader implements IFileReader {
    @Override
    public List<Book> readFile(String path) {
        return List.of();
    }
}
