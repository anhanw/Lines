package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BookTest {
    private Book tBook;

    @BeforeEach
    void setUp() {
        tBook = new Book("testBook");
    }

    @Test
    void testConstructor() {
        assertEquals("testBook", tBook.getBookName());
        assertEquals(0, tBook.getNotes().size());
        assertEquals(0, tBook.getCurrentPage());
    }

    @Test
    void testRename() {
        tBook.rename("Haha");
        assertEquals("Haha", tBook.getBookName());
    }

    @Test
    void testSetLastPage() {
        tBook.setLastPage(20);
        assertEquals(20, tBook.getLastPage());
        assertEquals(0, tBook.getPercentage());
    }

    @Test
    void testSetCurrentStage() {
        tBook.setLastPage(389);
        tBook.setCurrentStage(210);
        assertEquals(210, tBook.getCurrentPage());
        assertEquals((double)54.0, tBook.getPercentage());
    }

    @Test
    void testDoneBook() {
        tBook.doneBook();
        assertTrue(tBook.getCurrentPage() == tBook.getLastPage());
        assertEquals((double)100, tBook.getPercentage());
    }

}