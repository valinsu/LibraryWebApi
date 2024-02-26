package com.example.library.impl;

import com.example.library.model.Book;
import com.example.library.model.Library;
import com.example.library.repository.BookRepository;
import com.example.library.repository.LibraryRepository;
import com.example.library.service.LibraryService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class LibraryServiceImpl implements LibraryService {


    private static final Logger logger = LoggerFactory.getLogger(LibraryServiceImpl.class);

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
    public void createLibraryEntryAsync(Long bookId) {
        if (libraryRepository.existsByBookId(bookId)) {
            logger.info("Exists library for book with ID: {}", bookId);
        } else {
            Library libraryEntry = new Library();
            libraryEntry.setBookId(bookId);
            logger.info("Creating library entry for book with ID: {}", bookId);
            libraryRepository.save(libraryEntry);
        }
    }





    @Override
    public void deleteLibraryBook(Long bookId) {
        libraryRepository.deleteLibraryBook(bookId);
    }
}
