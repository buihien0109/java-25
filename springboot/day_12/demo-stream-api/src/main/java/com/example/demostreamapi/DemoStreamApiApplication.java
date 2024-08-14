package com.example.demostreamapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

/*
 * Function interface: là interface chỉ chứa 1 phương thức abstract.
 * Lambda expression: Được su dung de trien khai function interface.
 * */
@SpringBootApplication
public class DemoStreamApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoStreamApiApplication.class, args);

        // C1: Sử dung class implement Greeting
        Greeting vietNam = new VietNam();
        vietNam.sayHello("Huy");

        // C2: Su dung Anonymous class
        Greeting english = new Greeting() {
            @Override
            public void sayHello(String name) {
                System.out.println("Hello " + name);
            }
        };
        english.sayHello("An");

        // C3: Su dung Lambda expression
//        const sum = (a, b) => {
//            return a + b;
//        };
        Greeting japan = (name) -> {
            System.out.println("こんにちは " + name);
        };
        japan.sayHello("Tuan");

        // Calculate sum of 2 numbers
        Calculator sum = (a, b) -> {
            return a + b;
        };
        System.out.println("Sum = " + sum.calculate(10, 20));

        // Calculate subtraction of 2 numbers
        Calculator subtraction = (a, b) -> {
            return a - b;
        };
        System.out.println("Subtraction = " + subtraction.calculate(20, 10));

        // Thao tac voi List
//        List<Integer> numbers = new ArrayList<>(List.of(3, 5, 20, 12, 48));
//        numbers.forEach((number) -> System.out.println(number));
//
//        System.out.println("Xoa so le");
//        numbers.removeIf((number) -> number % 2 == 1);
//        numbers.forEach((number) -> System.out.println(number));
//
//        System.out.println("Sap xep");
//        numbers.sort((a, b) -> a - b);
//        numbers.forEach((number) -> System.out.println(number));

        // Stream API
        List<Integer> numbers = new ArrayList<>(List.of(3, 5, 20, 12, 48));
        int total = numbers.stream()
                .map(number -> number * 2)
                .reduce(0, (a, b) -> a + b);
        System.out.println("Tong = " + total);

        int max = numbers.stream()
                .filter(number -> number % 2 == 0)
                .max((a, b) -> a - b)
                .orElse(0);
        System.out.println("Max = " + max);

        int max1 = numbers.stream()
                .filter(number -> number % 2 == 0)
                .mapToInt(number -> number)
                .max()
                .orElse(0);
        System.out.println("Max1 = " + max1);

//        7. Lấy danh sách các phần tử không trùng nhau (distinct)
//        8. Lấy 5 phần tử đầu tiên trong mảng (limit)
//        9. Lấy phần tử từ thứ 3 -> thứ 5 (limit + skip)
//        10. Lấy phần tử đầu tiên > 5 (findFirst)
//        11. Kiểm tra xem list có phải là list chẵn hay không (allMatch)
//        12. Kiểm tra xem list có phần tử > 10 hay không (anyMatch)
//        13. Có bao nhiêu phần tử > 5 (count)
//        14. Nhân đôi các phần tủ trong list và trả về list mới (map)
//        15. Kiểm tra xem list không chứa giá trị nào = 8 hay không (noneMatch)
    }
}
