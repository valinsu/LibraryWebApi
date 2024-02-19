package com.example.library.exception;

public class BookNotFoundException extends RuntimeException{

    public BookNotFoundException(String isbn){
        super("The book with ISBN" + isbn + " was not fount");
    }
    public BookNotFoundException(Long id){
        super("The book with ID" + id + " was not fount");
    }

}
