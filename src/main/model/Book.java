package model;

import org.json.JSONObject;
import persistence.Writable;

// Represent a book its name, reading process, and notes from reader
public class Book implements Writable {
    private String bookName;
    private int currentPage;
    private int lastPage;
    private double percentage;
    private Notes notes;

    // EFFECTS: create a new book object with given name and an empty list for notes
    public Book(String name) {
        bookName = name;
        notes = new Notes();
        this.currentPage = 0;
    }

    // MODIFIES: this
    // EFFECTS: rename the book
    public void rename(String name) {
        EventLog.getInstance().logEvent(new Event("Rename book to " + name));
        this.bookName = name;
    }

    // REQUIRE: page >= 0
    // MODIFIES: this
    // EFFECTS: set up the last page
    public void setLastPage(int page) {
        lastPage = page;
        percentage = Math.round(((double)currentPage / (double)lastPage) * 100);
    }

    // REQUIRE: page >= 0 and page <= last page
    // MODIFIES: this
    // EFFECTS: set up the current page and calculate the percentage of reading
    public void setCurrentStage(int page) {
        currentPage = page;
        EventLog.getInstance().logEvent(new Event("Update stage: " + bookName));
        percentage = Math.round(((double)currentPage / (double)lastPage) * 100);
    }

    // MODIFIES: this
    // EFFECTS: set up the current stage of reading to 100
    public void doneBook() {
        this.currentPage = lastPage;
        EventLog.getInstance().logEvent(new Event("Done: " + bookName));
        percentage = 100;
    }

    // EFFECTS: get name of book
    public String getBookName() {
        return bookName;
    }

    // EFFECTS: get the current page of book
    public int getCurrentPage() {
        return currentPage;
    }

    // EFFECTS: get the last page of book
    public int getLastPage() {
        return lastPage;
    }

    // EFFECTS: get reading percentage of book
    public double getPercentage() {
        return percentage;
    }

    // EFFECTS: get notes of book
    public Notes getNotes() {
        return notes;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("bName", bookName);
        json.put("cPage", currentPage);
        json.put("lPage", lastPage);
        json.put("%", percentage);
        json.put("notes", notes.toJson());
        return json;
    }
}
