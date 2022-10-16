package model;

// Represent a note within a page number
public class Note {
    private int page;
    private String content;

    // Effects: create notes with given page number and content;
    public Note(int page, String content) {
        this.page = page;
        this.content = content;
    }

    // EFFECTS: get the page number of the note
    public int getPage() {
        return  page;
    }

    // EFFECTS: get the note content
    public String getContent() {
        return content;
    }
}
