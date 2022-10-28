package model;

import org.json.JSONObject;
import persistence.Writable;

// Represent a piece of excerpt from books, movies, and so on.
public class Excerpt implements Writable {
    private String from;
    private String content;

    // EFFECTS: record an expert and where it from
    public Excerpt(String from, String content) {
        this.from = from;
        this.content = content;
    }

    // EFFECTS: get where the excerpt from
    public String getFrom() {
        return from;
    }

    // EFFECTS: get the content of the excerpt
    public String getContent() {
        return content;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("from", from);
        json.put("Lines", content);
        return json;
    }
}
