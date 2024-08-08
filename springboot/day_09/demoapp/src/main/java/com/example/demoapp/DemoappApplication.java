package com.example.demoapp;

import com.example.demoapp.model.Book;
import com.github.javafaker.Faker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoappApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoappApplication.class, args);

		Faker faker = new Faker();
		System.out.println(faker.name().fullName());

		// Khoi tao doi tuong Book binh thuong
		Book book = new Book();
		book.setId(1);
		book.setTitle("The Lord of the Rings");
		book.setAuthor("J.R.R. Tolkien");
		book.setYear(1954);

		System.out.println(book);

		// Khoi tao doi tuong Book su dung Builder
		Book book2 = Book.builder()
				.year(1937)
				.title("The Hobbit")
				.build();
		System.out.println(book2);
	}

}
