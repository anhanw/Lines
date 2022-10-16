package model;

import java.util.ArrayList;
import java.util.List;

// Represent a booklist with its name and books contained
public class BookList {
    private String name;
    private List<Book> bookList;

    // EFFECTS: Create a new booklist with given name
    public BookList(String name) {
        this.name = name;
        bookList = new ArrayList<Book>();
    }

    // MODIFIES: this
    // EFFECTS: Add a book to bookList
    public void addBook(Book b) {
        bookList.add(b);
    }

    // MODIFIES: this
    // EFFECTS: remove the given book from bookList
    public void removeBook(Book b) {
        bookList.remove(b);
    }

    // EFFECTS: check whether the given book is in the bookList
    public boolean containsBook(Book b) {
        return bookList.contains(b);
    }

    // REQUIRE: Book with given name exists in bookList
    // EFFECTS: get the book in bookList
    public Book getBook(String name) {
        for (Book b: bookList) {
            if (name == b.getBookName()) {
                return b;
            }
        }
        return null;
    }

    // EFFECTS: get the name of the list
    public String getName() {
        return name;
    }

    // EFFECTS: get the bookList
    public List getBookList() {
        return bookList;
    }
}
