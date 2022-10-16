package model;

import java.util.ArrayList;
import java.util.List;

// Represent a collection of booklists with finished books, to read books and recommend books
public class BookLists {
    private List<BookList> specialLists;
    private BookList finishedBooks;
    private BookList toRead;
    private BookList recommendBooks;

    // EFFECTS: create three new booklists
    public BookLists(String finish, String toRead, String recommend) {
        specialLists = new ArrayList<BookList>();
        this.finishedBooks = new BookList(finish);
        this.toRead = new BookList(toRead);
        this.recommendBooks = new BookList(recommend);
    }

    // MODIFIES: this
    // EFFECTS: Move a book from toRead to finishedBooks or just add the book to finishedBooks
    public void doneABook(Book b) {
        toRead.removeBook(b);
        finishedBooks.addBook(b);
    }

    // MODIFIES: this
    // EFFECTS: Move a book from recommendBooks to toRead or just add the book to toRead
    public void planABook(Book b) {
        recommendBooks.removeBook(b);
        toRead.addBook(b);
    }

    // MODIFIES: this
    // EFFECTS: Add a bookList to specialLists
    public void addBooks(BookList bl) {
        specialLists.add(bl);
    }

    // MODIFIES: this
    // EFFECTS: remove the given bookList from specialLists
    public void removeBooks(BookList bl) {
        specialLists.remove(bl);
    }

    // EFFECTS: check whether the given lists is in specialLists
    public boolean containsBooks(BookList bl) {
        return specialLists.contains(bl);
    }

    // REQUIRE: bookList with given name exists in specialLists
    // EFFECTS: get the bookList in specialLists
    public BookList getBooks(String name) {
        if (name == finishedBooks.getName()) {
            return finishedBooks;
        } else if (name == toRead.getName()) {
            return toRead;
        } else if (name == recommendBooks.getName()) {
            return recommendBooks;
        } else {
            for (BookList bl : specialLists) {
                if (name == bl.getName()) {
                    return bl;
                }
            }
        }
        return null;
    }

    // EFFECTS: get the finishedBooks
    public BookList getFinishedBooks() {
        return finishedBooks;
    }

    // EFFECTS: get the toRead
    public BookList getToRead() {
        return toRead;
    }

    // EFFECTS: get the recommendBooks
    public BookList getRecommendBooks() {
        return recommendBooks;
    }

    // EFFECTS: get the specialLists
    public List<BookList> getSpecialLists() {
        return specialLists;
    }


    // REQUIRE:
    // MODIFIES:
    // EFFECTS:

    // REQUIRE:
    // MODIFIES:
    // EFFECTS:
}
