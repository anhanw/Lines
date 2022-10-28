package model;

import org.json.JSONObject;
import persistence.Writable;

// Represent a note within a page number
public class Note implements Writable {
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("page", page);
        json.put("content", content);
        return json;
    }
}
