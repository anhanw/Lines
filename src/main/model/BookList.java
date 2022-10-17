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

    // EFFECTS: view booklist in form of String
    public String booksToString() {
        String result = ("\n" + name + ":");;
        int i = 0;
        for (Book b:bookList) {
            result = result + "\n\t" + "(" + i + ")" + b.getBookName();
            i++;
        }
        return result;
    }

    //EFFECTS: get the list of book names in this list
    public List<String> getBookNames() {
        List<String> bookNames = new ArrayList<String>();
        for (Book b: bookList) {
            bookNames.add(b.getBookName());
        }
        return bookNames;
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
