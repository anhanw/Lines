package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BookListsTest {
    private BookLists tLists;
    private BookList bl1 = new BookList("Pure");
    private BookList bl2 = new BookList("Love is Love");
    private Book b1 = new Book("QunShangHuiXiang");
    private Book b2 = new Book("JiaYou!!!");

    @BeforeEach
    void setUp() {
        tLists = new BookLists("Done", "Plan", "LookAhead");
    }

    @Test
    void testConstructor() {
        assertEquals(0, tLists.getSpecialLists().size());
        assertEquals("Done", tLists.getFinishedBooks().getName());
        assertEquals("Plan", tLists.getToRead().getName());
        assertEquals("LookAhead", tLists.getRecommendBooks().getName());
        assertEquals(0, tLists.getFinishedBooks().getBookList().size());
        assertEquals(0, tLists.getToRead().getBookList().size());
        assertEquals(0, tLists.getRecommendBooks().getBookList().size());
    }

    @Test
    void testAddBooks() {
        tLists.addBooks(bl1);
        assertEquals(1, tLists.getSpecialLists().size());
        assertEquals(bl1, tLists.getSpecialLists().get(0));
    }

    @Test
    void testAddManyBooks() {
        tLists.addBooks(bl1);
        tLists.addBooks(bl2);
        assertEquals(2, tLists.getSpecialLists().size());
        assertEquals(bl1, tLists.getSpecialLists().get(0));
        assertEquals(bl2, tLists.getSpecialLists().get(1));
    }

    @Test
    void testRemoveBooks() {
        tLists.addBooks(bl1);
        tLists.addBooks(bl2);
        tLists.removeBooks(bl1);
        assertEquals(1, tLists.getSpecialLists().size());
        assertEquals(bl2, tLists.getSpecialLists().get(0));
    }

    @Test
    void testRemoveManyBooks() {
        tLists.addBooks(bl1);
        tLists.addBooks(bl2);
        tLists.removeBooks(bl1);
        tLists.removeBooks(bl2);
        assertEquals(0, tLists.getSpecialLists().size());
    }

    @Test
    void testContainsBooks() {
        tLists.addBooks(bl1);
        assertTrue(tLists.containsBooks(bl1));
        assertFalse(tLists.containsBooks(bl2));
    }

    @Test
    void testGetBook() {
        tLists.addBooks(bl1);
        tLists.addBooks(bl2);
        tLists.addBooks(bl2);
        assertEquals(bl1, tLists.getBooks("Pure"));
        assertEquals(bl2, tLists.getBooks("Love is Love"));
        assertEquals(null, tLists.getBooks("null"));
    }
}
