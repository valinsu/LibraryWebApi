package com.example.library.repository;


import com.example.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    void deleteBookByIsbn(String isbn);
    Book findBookByIsbn(String isbn);
    Book findBookById(Long id);
    boolean existsBooksByIsbn(String isbn);
}
