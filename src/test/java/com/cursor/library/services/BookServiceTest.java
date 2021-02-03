package com.cursor.library.services;

import static org.junit.jupiter.api.Assertions.*;

import com.cursor.library.daos.BookDao;
import com.cursor.library.exceptions.BadIdException;
import com.cursor.library.exceptions.BookNameIsNullException;
import com.cursor.library.exceptions.BookNameIsTooLongException;
import com.cursor.library.models.Book;
import com.cursor.library.models.CreateBookDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    private BookDao bookDao;
    private BookService bookService;

    @BeforeAll
    void setUp() {
        bookDao = Mockito.mock(BookDao.class);
        bookService = new BookService(bookDao);
    }

    @Test
    void getBookByIdSuccessTest() {
        String bookId = "book-id";

        Mockito.when(bookDao.getById(bookId)).thenReturn(new Book(bookId));

        Book bookFromDB = bookService.getById(bookId);

        assertEquals(
                bookId,
                bookFromDB.getBookId()
        );
    }

    @Test
    void getBookByIdBadIdExceptionTest() {
        assertThrows(
                BadIdException.class,
                () -> bookService.getById("       ")
        );
    }

    @Test
    void getValidatedBookNameExpectBookNameIsNullExceptionTest() {
        assertThrows(
                BookNameIsNullException.class,
                () -> bookService.getValidatedBookName(null)
        );
    }

    @Test
    void getValidatedBookNameExpectNameIsTooLongExceptionTest() {
        String name = " ";
        for (int i = 0; i < 10; i++) {
            name += name;
        }
        String finalName = name;

        assertThrows(
                BookNameIsTooLongException.class,
                () -> bookService.getValidatedBookName(finalName)
        );
    }

    @Test
    void getValidatedBookNameSuccessTest() {
        String name = "The best book ever";

        assertEquals(
                name,
                bookService.getValidatedBookName(" \t \n " + name + "   \t\t\n")
        );
    }

    @Test
    void createBookTest() {
        CreateBookDto bookDto = new CreateBookDto();
        bookDto.setName("Java in a Nutshell");
        bookDto.setDescription("A desktop quick reference");
        bookDto.setNumberOfWords(100500);
        bookDto.setRating(10);
        bookDto.setYearOfPublication(2014);
        bookDto.setAuthors(Arrays.asList("Benjamin J. Evans", "David Flanagan"));

        Mockito.when(bookService.createBook(bookDto)).thenReturn(null);

        assertNull(bookService.createBook(bookDto));
    }
}
