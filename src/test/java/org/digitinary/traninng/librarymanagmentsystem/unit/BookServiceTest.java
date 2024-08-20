package org.digitinary.traninng.librarymanagmentsystem.unit;


import org.digitinary.traninng.librarymanagmentsystem.entity.Book;
import org.digitinary.traninng.librarymanagmentsystem.enums.BookType;
import org.digitinary.traninng.librarymanagmentsystem.mapper.BookMapper;
import org.digitinary.traninng.librarymanagmentsystem.model.BookModel;
import org.digitinary.traninng.librarymanagmentsystem.repository.BookRepository;
import org.digitinary.traninng.librarymanagmentsystem.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private BookService bookService;

    public BookServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllBooks() {

        Book book = new Book("Title", "Author", "Publisher", "ISBN", BookType.FICTION, true);
        BookModel bookModel = new BookModel(1L, "Title", "Author", "Publisher", "ISBN", BookType.FICTION, true);
        when(bookRepository.findAll()).thenReturn(Stream.of(book).collect(Collectors.toList()));
        when(bookMapper.bookToBookModel(book)).thenReturn(bookModel);


        List<BookModel> result = bookService.getAllBooks();


        assertEquals(1, result.size());
        assertEquals(bookModel, result.get(0));
        verify(bookRepository, times(1)).findAll();
        verify(bookMapper, times(1)).bookToBookModel(book);
    }

    @Test
    void testAddBook() {

        BookModel bookModel = new BookModel(1L, "Title", "Author", "Publisher", "ISBN", BookType.FICTION, true);
        Book book = new Book("Title", "Author", "Publisher", "ISBN", BookType.FICTION, true);
        when(bookMapper.bookModelToBook(bookModel)).thenReturn(book);


        bookService.addBook(bookModel);


        verify(bookMapper, times(1)).bookModelToBook(bookModel);
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void testGetBookById() {

        Long id = 1L;
        Book book = new Book("Title", "Author", "Publisher", "ISBN", BookType.FICTION, true);
        BookModel bookModel = new BookModel(id, "Title", "Author", "Publisher", "ISBN", BookType.FICTION, true);
        when(bookRepository.findById(id)).thenReturn(Optional.of(book));
        when(bookMapper.bookToBookModel(book)).thenReturn(bookModel);


        BookModel result = bookService.getBookById(id);


        assertEquals(bookModel, result);
        verify(bookRepository, times(1)).findById(id);
        verify(bookMapper, times(1)).bookToBookModel(book);
    }

    @Test
    void testGetBookByIdNotFound() {

        Long id = 1L;
        when(bookRepository.findById(id)).thenReturn(Optional.empty());


        RuntimeException thrown = assertThrows(RuntimeException.class, () -> bookService.getBookById(id));
        assertEquals("Book not found with id: " + id, thrown.getMessage());
        verify(bookRepository, times(1)).findById(id);
        verify(bookMapper, never()).bookToBookModel(any());
    }

    @Test
    void testDeleteBookById() {
        Long id = 1L;

        bookService.deleteBookById(id);

        verify(bookRepository, times(1)).deleteById(id);
    }
}
