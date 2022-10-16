package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NoteTest {
    private Note note;

    @BeforeEach
    void setUp() {
        note = new Note(22, "Daisy, Kyle, Look! Triple bond!");
    }

    @Test
    void testConstructor() {
        assertEquals(22, note.getPage());
        assertEquals("Daisy, Kyle, Look! Triple bond!", note.getContent());
    }
}
