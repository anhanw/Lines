package model;

import java.util.ArrayList;
import java.util.List;

// Represent a collection of excerpts.
public class Excerpts {
    private List<Excerpt> excerpts;

    // EFFECTS: instantiate excerpts a new list
    public Excerpts() {
        excerpts = new ArrayList<Excerpt>();
    }

    // MODIFIES: this
    // EFFECTS: add a new excerpt to excerpts collection
    public void addExcerpt(Excerpt line) {
        excerpts.add(line);
    }

    // MODIFIES: this
    // EFFECTS: remove a new excerpt to excerpts collection
    public void removeExcerpt(Excerpt line) {
        excerpts.remove(line);
    }

    // EFFECTS: Change the excerpts to String
    public String excerptsToString() {
        String excerpt = "Excerpts:";
        for (Excerpt e : excerpts) {
            excerpt = excerpt + "\nFrom: " + e.getFrom() + "\n\t" + e.getContent();
        }
        if (excerpt != "Excerpts:") {
            return excerpt;
        } else {
            return "No excerpts for now!";
        }
    }

    // EFFECTS: get excerpts
    public List<Excerpt> getExcerpts() {
        return  excerpts;
    }
}
