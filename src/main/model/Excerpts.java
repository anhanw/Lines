package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represent a collection of excerpts.
public class Excerpts implements Writable {
    private String name;
    private List<Excerpt> excerpts;

    // EFFECTS: instantiate excerpts a new list
    public Excerpts(String name) {
        this.name = name;
        excerpts = new ArrayList<Excerpt>();
    }

    // MODIFIES: this
    // EFFECTS: Change the name of bookList
    public void renameExcerpts(String name) {
        this.name = name;
    }

    // MODIFIES: this
    // EFFECTS: add a new excerpt to excerpts collection
    public void addExcerpt(Excerpt line) {
        EventLog.getInstance().logEvent(new Event("Added excerpt."));
        excerpts.add(line);
    }

    // EFFECTS: Change the excerpts to String
    public String excerptsToString() {
        String excerpt = name + ":";
        for (Excerpt e : excerpts) {
            excerpt = excerpt + "<br> <br>    From: " + e.getFrom() + "<br>        " + e.getContent();
        }
        if (excerpts.size() != 0) {
            return excerpt;
        } else {
            return (name + ":<br>No excerpts for now!");
        }
    }

    // EFFECTS: get excerpts
    public String getExcerptsName() {
        return name;
    }

    // EFFECTS: get excerpts
    public List<Excerpt> getExcerpts() {
        return excerpts;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("excerptsName", name);
        json.put("excerpts", excerptsToJson());
        return json;
    }

    // EFFECTS: returns things in the excerpts as a JSON array
    private JSONArray excerptsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Excerpt e : excerpts) {
            jsonArray.put(e.toJson());
        }

        return jsonArray;
    }
}
