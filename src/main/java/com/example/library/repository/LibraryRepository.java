package com.example.library.repository;

import com.example.library.model.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface LibraryRepository extends JpaRepository<Library, Long> {

    @Modifying
    @Transactional
    @Query("delete from Library where bookId = :bookId")
    void deleteLibraryBook(Long bookId);

    boolean existsByBookId(Long bookId);
}
