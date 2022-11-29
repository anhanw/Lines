package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookListTest {
    private BookList tList;
    private Book b1 = new Book("QunShangHuiXiang");
    private Book b2 = new Book("JiaYou!!!");

    @BeforeEach
    void setUp() {
        tList = new BookList("test");
    }

    @Test
    void testConstructor() {
        assertEquals("test", tList.getName());
        assertEquals(0, tList.getBookList().size());
    }

    @Test
    void testAddOneBook() {
        tList.addBook(b1);
        assertEquals(1, tList.getBookList().size());
        assertEquals(b1, tList.getBookList().get(0));
    }

    @Test
    void testAddManyBook() {
        tList.addBook(b1);
        tList.addBook(b2);
        tList.addBook(b2);
        assertEquals(3, tList.getBookList().size());
        assertEquals(b1, tList.getBookList().get(0));
        assertEquals(b2, tList.getBookList().get(1));
        assertEquals(b2, tList.getBookList().get(2));
    }

    @Test
    void testRemoveOneBook() {
        tList.addBook(b1);
        tList.addBook(b2);
        tList.addBook(b2);
        tList.removeBook(b2);
        assertEquals(2, tList.getBookList().size());
        assertEquals(b1, tList.getBookList().get(0));
        assertEquals(b2, tList.getBookList().get(1));
    }

    @Test
    void testRemoveAllBook() {
        tList.addBook(b1);
        tList.addBook(b2);
        tList.addBook(b2);
        tList.removeBook(b2);
        tList.removeBook(b2);
        tList.removeBook(b1);
        assertEquals(0, tList.getBookList().size());
    }

    @Test
    void testContainsBook() {
        tList.addBook(b1);
        tList.addBook(b2);
        tList.addBook(b2);
        assertTrue(tList.containsBook(b1));
        assertTrue(tList.containsBook(b2));
    }

    @Test
    void testBooksToString() {
        tList.addBook(b1);
        tList.addBook(b2);
        String s = tList.booksToString();
        assertEquals("<html><body>test:<br>    (0)QunShangHuiXiang<br>    (1)JiaYou!!!<body></html>", s);
    }

    @Test
    void testGetBookNames() {
        tList.addBook(b1);
        tList.addBook(b1);
        List<String> bn = new ArrayList<>();
        bn.add("QunShangHuiXiang");
        bn.add("QunShangHuiXiang");
        assertEquals(bn, tList.getBookNames());
    }
}
