package model;

import java.util.ArrayList;
import java.util.List;

// Represent a collection of excerpts.
public class Excerpts {
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
        excerpts.add(line);
    }

    // EFFECTS: Change the excerpts to String
    public String excerptsToString() {
        String excerpt = name + ":";
        for (Excerpt e : excerpts) {
            excerpt = excerpt + "\nFrom: " + e.getFrom() + "\n\t" + e.getContent();
        }
        if (excerpts.size() != 0) {
            return excerpt;
        } else {
            return (name + ":\nNo excerpts for now!");
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
}
