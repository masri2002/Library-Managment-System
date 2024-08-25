package org.digitinary.traninng.librarymanagmentsystem.repository.specification;

import org.digitinary.traninng.librarymanagmentsystem.entity.Book;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecification {
    public static Specification<Book> getBookWithAuthorNameLike(String nameAuthor){
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("author"),"%"+nameAuthor);
    }

}
