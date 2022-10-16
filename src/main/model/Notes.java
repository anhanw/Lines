package model;

import java.util.ArrayList;
import java.util.List;

// Represent a collection of notes
public class Notes {
    private List<Note> notes;

    public Notes() {
        notes = new ArrayList<Note>();
    }

    // REQUIRE: page <= lastPage
    // MODIFIES: this
    // EFFECTS: add a new note to notes
    public void addNote(Note n) {
        notes.add(n);
    }

    // MODIFIES: this
    // EFFECTS: remove a new excerpt to excerpts collection
    public void removeNote(Note n) {
        notes.remove(n);
    }

    // EFFECTS: change notes in form of string
    public String notesToString() {
        String note = "Notes:";
        for (Note n : notes) {
            note = note + "\n" + n.getContent() + " (P" + n.getPage() + ").";
        }
        if (note != "Notes:") {
            return note;
        } else {
            return "No notes for now!";
        }
    }

    // EFFECTS: get note from given index
    public Note get(int index) {
        return notes.get(index);
    }

    // EFFECTS: get size of notes
    public int size() {
        return notes.size();
    }
}
