package com.example.demoapp.utils;

import com.example.demoapp.model.Book;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component // jackson || gson -> them vao pom.xml
public class JsonFileReader implements IFileReader {
    @Override
    public List<Book> readFile(String path) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Book> books = new ArrayList<>();
        try {
            books = objectMapper.readValue(
                    new File(path),
                    new TypeReference<List<Book>>(){}
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return books;
    }
}
