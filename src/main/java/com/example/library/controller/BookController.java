package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.model.Library;
import com.example.library.service.BookService;
import com.example.library.service.LibraryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;
    private final LibraryService libraryService;
    @GetMapping
    public List<Book> finfAllBook() {
        return bookService.findAllBook();
    }

    @PostMapping("save_book")
    public Book createBook(@Valid @RequestBody Book book){
        bookService.createBook(book);
        libraryService.createBookByLibrary(book.getId());
        return book;
    }

    @GetMapping("/{isbn}")
    public Book findBookByIsbn(@PathVariable String isbn){
        return bookService.findBookByIsbn(isbn);
    }

    public Book findBookById(Long id){
        return bookService.findBookById(id);//todo
    }
    @PutMapping("update_book")
    public Book updateBook(@Valid @RequestBody Book book){
        return bookService.updateBook(book);
    }

    @DeleteMapping("delete_book/{isbn}")
    public void deleteStudent(@PathVariable String isbn){
        bookService.deleteBook(isbn);
    }
}
