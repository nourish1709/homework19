package com.cursor.library.controllers;

import static org.junit.jupiter.api.Assertions.*;

import com.cursor.library.daos.BookDao;
import com.cursor.library.models.Book;
import com.cursor.library.models.CreateBookDto;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

class BookControllerTest extends BaseControllerTest {

    private BookDao bookDao;

    @BeforeAll
    void setUp() {
        bookDao = new BookDao();
    }

    @Test
        void createBookTest() throws Exception {
        CreateBookDto createBookDto = new CreateBookDto();
        createBookDto.setName("Cool createBookDto");
        createBookDto.setDescription("Cool description");
        createBookDto.setNumberOfWords(100500);
        createBookDto.setRating(10);
        createBookDto.setYearOfPublication(2020);
        createBookDto.setAuthors(Arrays.asList("author1", "author2"));

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/books")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(OBJECT_MAPPER.writeValueAsString(createBookDto));

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        Book book = OBJECT_MAPPER.readValue(
                result.getResponse().getContentAsString(),
                Book.class
        );

        bookDao.addBook(book);

        mockMvc.perform(MockMvcRequestBuilders.get("/books/" + book.getBookId()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getAllTest() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/books");

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        List<Book> books = OBJECT_MAPPER.readValue(
                result.getResponse().getContentAsString(),
                new TypeReference<>() {
                }
        );

        List<Book> booksFromDB = bookDao.getAll();

        assertEquals(booksFromDB, books);
    }

    @Test
    void getByIdSuccessTest() throws Exception {
        Book bookFromDao = bookDao.getById("random_id_value_1");

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/books/" + bookFromDao.getBookId());

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Book book = OBJECT_MAPPER.readValue(
                result.getResponse().getContentAsString(),
                Book.class
        );

        assertEquals(bookFromDao, book);
    }

    @Test
    void getByIdExpectNotFoundStatus() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/books/random_id");

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void deleteByIdSuccessTest() throws Exception {
        Book bookFromDao = bookDao.getById("random_id_value_1");

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/books/" + bookFromDao.getBookId());

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Book book = OBJECT_MAPPER.readValue(
                result.getResponse().getContentAsString(),
                Book.class
        );

        assertEquals(bookFromDao, book);
    }

    @Test
    void deleteByIdExpectNotFoundStatusTest() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/books/random_id");

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
