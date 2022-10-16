package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExcerptTest {
    private Excerpt tLine;

    @BeforeEach
    void setUp() {
        tLine = new Excerpt("Ann", "Do it!");
    }

    @Test
    void testConstructor() {
        assertEquals("Ann", tLine.getFrom());
        assertEquals("Do it!", tLine.getContent());
    }
}
