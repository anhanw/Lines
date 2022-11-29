package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NotesTest {
    private Notes tNotes;
    private Note n1 = new Note(32, "That's too much!");
    private Note n2 = new Note(45, "That's too too much!");
    private Note n3 = new Note(66, "Hope can be better~");

    @BeforeEach
    void setUp() {
        this.tNotes = new Notes();
    }

    @Test
    void testAddOneNote() {
        tNotes.addNote(n1);
        assertEquals(1, tNotes.size());
        assertEquals(32, tNotes.get(0).getPage());
        assertEquals("That's too much!", tNotes.get(0).getContent());
    }

    @Test
    void testAddManyNote() {
        tNotes.addNote(n1);
        tNotes.addNote(n2);
        assertEquals(2, tNotes.size());
        assertEquals(32, tNotes.get(0).getPage());
        assertEquals("That's too much!", tNotes.get(0).getContent());
        assertEquals(45, tNotes.get(1).getPage());
        assertEquals("That's too too much!", tNotes.get(1).getContent());
    }

    @Test
    void testRemoveOneNote() {
        tNotes.addNote(n1);
        tNotes.addNote(n2);
        tNotes.removeNote(n1);
        assertEquals(1, tNotes.size());
        assertEquals(45, tNotes.get(0).getPage());
        assertEquals("That's too too much!", tNotes.get(0).getContent());
    }

    @Test
    void testRemoveAllNote() {
        tNotes.addNote(n1);
        tNotes.addNote(n2);
        tNotes.removeNote(n1);
        tNotes.removeNote(n2);
        assertEquals(0, tNotes.size());
    }

    @Test
    void testRemoveManyNote() {
        tNotes.addNote(n1);
        tNotes.addNote(n2);
        tNotes.addNote(n3);
        tNotes.removeNote(n1);
        tNotes.removeNote(n2);
        assertEquals(1, tNotes.size());
        assertEquals(66, tNotes.get(0).getPage());
        assertEquals("Hope can be better~", tNotes.get(0).getContent());
    }

    @Test
    void testEmptyNotesToString() {
        assertEquals("No notes for now!", tNotes.notesToString());
    }

    @Test
    void testOneNotesToString() {
        tNotes.addNote(n1);
        String s = "Notes:<br>    (0) That's too much! (P32).";
        assertEquals(s, tNotes.notesToString());
    }

    @Test
    void testSameNotesToString() {
        tNotes.addNote(n1);
        tNotes.addNote(n1);
        String s = "Notes:<br>    (0) That's too much! (P32).<br>    (1) That's too much! (P32).";
        assertEquals(s, tNotes.notesToString());
    }

    @Test
    void testManyNotesToString() {
        tNotes.addNote(n1);
        tNotes.addNote(n2);
        String s = "Notes:<br>    (0) That's too much! (P32).<br>    (1) That's too too much! (P45).";
        assertEquals(s, tNotes.notesToString());
    }
}
