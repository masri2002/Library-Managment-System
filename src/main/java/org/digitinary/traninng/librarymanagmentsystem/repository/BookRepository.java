package org.digitinary.traninng.librarymanagmentsystem.repository;


import org.digitinary.traninng.librarymanagmentsystem.entity.Book;
import org.digitinary.traninng.librarymanagmentsystem.enums.BookType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("select  b.inStock from  Book b where b.id=:id")
    boolean inStockById(@Param("id")Long id);
    Page<Book> findByBookType(BookType type, Pageable pageable);
}
