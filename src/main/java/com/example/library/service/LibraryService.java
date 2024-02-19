package com.example.library.service;

import com.example.library.model.Book;

import java.util.List;

public interface LibraryService {
    List<Book> findFreeBook();

    void createBookByLibrary(Long bookId);
}
