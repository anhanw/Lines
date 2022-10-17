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
        tLines = new Excerpts("Taolu");
    }

    @Test
    void testConstructor() {
        assertEquals("Taolu", tLines.getExcerptsName());
        assertEquals(0, tLines.getExcerpts().size());
    }

    @Test
    void testRename() {
        tLines.renameExcerpts("ShiFang");
        assertEquals("ShiFang", tLines.getExcerptsName());
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
    void testEmptyExcerptsToString() {
        assertEquals("Taolu:\nNo excerpts for now!", tLines.excerptsToString());
    }

    @Test
    void testExcerptsToString() {
        tLines.addExcerpt(l1);
        tLines.addExcerpt(l2);
        String s = "Taolu:\nFrom: Lam\n\tThis is life.\nFrom: KOB\n\tWhat's up!";
        assertEquals(s,tLines.excerptsToString());
    }
}
