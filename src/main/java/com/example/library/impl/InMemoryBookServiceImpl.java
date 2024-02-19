package com.example.library.impl;

import com.example.library.model.Book;
import com.example.library.repository.InMemoryBookDao;
import com.example.library.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InMemoryBookServiceImpl implements BookService {
    private final InMemoryBookDao bookRepository;

    @Override
    public List<Book> findAllBook() {
        return bookRepository.findAllBook();
    }

    @Override
    public Book createBook(Book book) {
        return bookRepository.createBook(book);
    }

    @Override
    public Book findBookById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book findBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    @Override
    public Book updateBook(Book book) {
        return bookRepository.updateBook(book);
    }

    @Override
    public void deleteBook(String isbn) {
        bookRepository.deleteBook(isbn);
    }
}
