package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static jdk.nashorn.internal.objects.NativeSet.size;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
    }

    @Test
    void testSetCurrentStage() {
        tBook.setLastPage(389);
        tBook.setCurrentStage(210);
        assertEquals(210, tBook.getCurrentPage());
        assertEquals((double)54.0, tBook.getPercentage());
    }

}