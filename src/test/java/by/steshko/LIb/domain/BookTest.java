package by.steshko.LIb.domain;

import by.steshko.LIb.domain.Book;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {
    @Test
    void getName() {
        Book book = new Book("book",1000,"1234567",
                "Fantasy","Author","Some Description");
        assertEquals("book",book.getName());
    }

    @Test
    void setName() {
        Book book = new Book("book",1000,"1234567",
                "Fantasy","Author","Some Description");
        String expectedName = "newBook";
        book.setName(expectedName);
        assertEquals(expectedName,book.getName());
    }

    @Test
    void getYear() {
        Book book = new Book("book",1000,"1234567",
                "Fantasy","Author","Some Description");
        assertEquals(1000,book.getYear());
    }

    @Test
    void setYear() {
        Book book = new Book("book",1000,"1234567",
                "Fantasy","Author","Some Description");
        Integer expectedYear = 1001;
        book.setYear(expectedYear);
        assertEquals(expectedYear,book.getYear());
    }

    @Test
    void getDescription() {
        Book book = new Book("book",1000,"1234567",
                "Fantasy","Author","Some Description");
        assertEquals("Some Description",book.getDescription());
    }

    @Test
    void setDescription() {
        Book book = new Book("book",1000,"1234567",
                "Fantasy","Author","Some Description");
        String expectedDescription = "Another description";
        book.setDescription(expectedDescription);
        assertEquals(expectedDescription,book.getDescription());
    }

    @Test
    void getIsbn() {
        Book book = new Book("book",1000,"1234567",
                "Fantasy","Author","Some Description");
        assertEquals("1234567",book.getIsbn());
    }

    @Test
    void setIsbn() {
        Book book = new Book("book",1000,"1234567",
                "Fantasy","Author","Some Description");
        String expectedIsbn = "7654321";
        book.setIsbn(expectedIsbn);
        assertEquals(expectedIsbn,book.getIsbn());
    }

    @Test
    void getGenre() {
        Book book = new Book("book",1000,"1234567",
                "Fantasy","Author","Some Description");
        assertEquals("Fantasy",book.getGenre());
    }

    @Test
    void setGenre() {
        Book book = new Book("book",1000,"1234567",
                "Fantasy","Author","Some Description");
        String expectedIsbn = "7654321";
        book.setIsbn(expectedIsbn);
        assertEquals(expectedIsbn,book.getIsbn());
    }

    @Test
    void getAuthor() {
        Book book = new Book("book",1000,"1234567",
                "Fantasy","Author","Some Description");
        assertEquals("Author",book.getAuthor());
    }

    @Test
    void setAuthor() {
        Book book = new Book("book",1000,"1234567",
                "Fantasy","Author","Some Description");
        String expectedAuthor = "Artur";
        book.setAuthor(expectedAuthor);
        assertEquals(expectedAuthor, book.getAuthor());
    }

    @Test
    void getFilename() {
        Book book = new Book("book",1000,"1234567",
                "Fantasy","Author","Some Description");
        book.setFilename("filename");
        assertEquals("filename", book.getFilename());
    }

    @Test
    void setFilename() {
        Book book = new Book("book",1000,"1234567",
                "Fantasy","Author","Some Description");
        book.setFilename("filename");
        assertEquals("filename", book.getFilename());
    }
}