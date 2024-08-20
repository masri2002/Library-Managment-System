package org.digitinary.traninng.librarymanagmentsystem.repository;

import org.digitinary.traninng.librarymanagmentsystem.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("select  b.inStock from  Book b where b.id=:id")
    boolean inStockById(@Param("id")Long id);
}
