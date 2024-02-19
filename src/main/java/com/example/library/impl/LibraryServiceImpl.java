package com.example.library.impl;

import com.example.library.model.Book;
import com.example.library.model.Library;
import com.example.library.repository.BookRepository;
import com.example.library.repository.LibraryRepository;
import com.example.library.service.BookService;
import com.example.library.service.LibraryService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Primary
@Service
@AllArgsConstructor
public class LibraryServiceImpl implements LibraryService {

    private final LibraryRepository libraryRepository;
    private final BookRepository bookRepository;

    @Override
    public List<Book> findFreeBook() {
        List<Book> freeBooks = new ArrayList<>();
        List<Library> libraries = libraryRepository.findAll();
        for (Library libraryEntry : libraries) {
            if (libraryEntry.getReturnTime() != null && libraryEntry.getReturnTime().isBefore(LocalDateTime.now())) {
                Book freeBook = bookRepository.findBookById(libraryEntry.getBookId());
                if (freeBook != null) {
                    freeBooks.add(freeBook);
                }
            }
        }
        return freeBooks;
    }

    @Async
    @Override
    public void createBookByLibrary(Long bookId) {
        Library library = new Library();
        library.setBookId(bookId);
        libraryRepository.save(library);
    }
}
