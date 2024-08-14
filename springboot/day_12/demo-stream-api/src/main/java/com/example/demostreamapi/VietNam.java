package com.example.demostreamapi;

public class VietNam implements Greeting {
    @Override
    public void sayHello(String name) {
        System.out.println("Xin chào " + name);
    }
}
