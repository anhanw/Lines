package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExcerptsTest {
    private Excerpts tLines;
    private Excerpt l1 = new Excerpt("Lam", "This is life.");
    private Excerpt l2 = new Excerpt("KOB", "What's up!");

    @BeforeEach
    void setUp() {
        tLines = new Excerpts();
    }

    @Test
    void testConstructor() {
        assertEquals(0, tLines.getExcerpts().size());
    }

    @Test
    void testAddOneExcerpt() {
        tLines.addExcerpt(l1);
        assertEquals(1, tLines.getExcerpts().size());
        assertEquals(l1, tLines.getExcerpts().get(0));
    }

    @Test
    void testAddSameExcerpt() {
        tLines.addExcerpt(l1);
        tLines.addExcerpt(l1);
        assertEquals(2, tLines.getExcerpts().size());
        assertEquals(l1, tLines.getExcerpts().get(0));
        assertEquals(l1, tLines.getExcerpts().get(1));
    }

    @Test
    void testAddManyExcerpt() {
        tLines.addExcerpt(l1);
        tLines.addExcerpt(l2);
        assertEquals(2, tLines.getExcerpts().size());
        assertEquals(l1, tLines.getExcerpts().get(0));
        assertEquals(l2, tLines.getExcerpts().get(1));
    }

    @Test
    void testRemoveOneExcerpt() {
        tLines.addExcerpt(l1);
        tLines.addExcerpt(l2);
        tLines.removeExcerpt(l1);
        assertEquals(1, tLines.getExcerpts().size());
        assertEquals(l2, tLines.getExcerpts().get(0));
    }

    @Test
    void testRemoveSameExcerpt() {
        tLines.addExcerpt(l1);
        tLines.addExcerpt(l2);
        tLines.addExcerpt(l2);
        tLines.removeExcerpt(l2);
        tLines.removeExcerpt(l2);
        assertEquals(1, tLines.getExcerpts().size());
        assertEquals(l1, tLines.getExcerpts().get(0));
    }

    @Test
    void testRemoveAllExcerpt() {
        tLines.addExcerpt(l1);
        tLines.addExcerpt(l2);
        tLines.removeExcerpt(l1);
        tLines.removeExcerpt(l2);
        assertEquals(0, tLines.getExcerpts().size());
    }

    @Test
    void testEmptyExcerptsToString() {
        assertEquals("No excerpts for now!", tLines.excerptsToString());
    }

    @Test
    void testExcerptsToString() {
        tLines.addExcerpt(l1);
        tLines.addExcerpt(l2);
        String s = "Excerpts:\nFrom: Lam\n\tThis is life.\nFrom: KOB\n\tWhat's up!";
        assertEquals(s,tLines.excerptsToString());
    }
}
