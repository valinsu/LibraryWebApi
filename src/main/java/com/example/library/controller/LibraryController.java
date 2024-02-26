package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.model.Library;
import com.example.library.service.LibraryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/library_books")
@AllArgsConstructor
public class LibraryController {

    private final LibraryService libraryService;
    @GetMapping("free_books")
    public List<Book> findFreeBook() {
        return libraryService.findFreeBook();
    }



    @PostMapping("save_library_book")
    public Library createLibraryBook(@RequestBody Library library) {
        libraryService.createLibraryEntryAsync(library.getBookId());
        return library;
    }

    @DeleteMapping("delete_library_book/{bookId}")
    public void deleteLibraryBook(@PathVariable Long bookId){
        libraryService.deleteLibraryBook(bookId);
    }
}
