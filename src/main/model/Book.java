package model;

import java.util.ArrayList;
import java.util.List;

// Represent a book its name, reading process, and notes from reader
public class Book {
    private String bookName;
    private int currentPage;
    private int lastPage;
    private double percentage;
    private Notes notes;

    // EFFECTS: create a new book object with given name and an empty list for notes
    public Book(String name) {
        bookName = name;
        notes = new Notes();
    }

    // MODIFIES: this
    // EFFECTS: rename the book
    public void rename(String name) {
        this.bookName = name;
    }

    // REQUIRE: page >= 0
    // MODIFIES: this
    // EFFECTS: set up the last page
    public void setLastPage(int page) {
        lastPage = page;
    }

    // REQUIRE: page >= 0 and page <= last page
    // MODIFIES: this
    // EFFECTS: set up the current page and calculate the percentage of reading
    public void setCurrentStage(int page) {
        currentPage = page;
        percentage = Math.round(((double)currentPage / (double)lastPage) * 100);
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

}
