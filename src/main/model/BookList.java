package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represent a booklist with its name and books contained
public class BookList implements Writable {
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
        EventLog.getInstance().logEvent(new Event("Added book: " + b.getBookName()));
        bookList.add(b);
    }

    // MODIFIES: this
    // EFFECTS: remove the given book from bookList
    public void removeBook(Book b) {
        EventLog.getInstance().logEvent(new Event("Removed book: " + b.getBookName()));
        bookList.remove(b);
    }

    // EFFECTS: check whether the given book is in the bookList
    public boolean containsBook(Book b) {
        EventLog.getInstance().logEvent(new Event("Check book: " + b.getBookName()));
        return bookList.contains(b);
    }

    // EFFECTS: view booklist in form of String
    public String booksToString() {
        String result = ("<html><body>" + name + ":");;
        int i = 0;
        for (Book b:bookList) {
            result = result + "<br>    " + "(" + i + ")" + b.getBookName();
            i++;
        }
        result = result + "<body></html>";
        return result;
    }

//    // EFFECTS: view booklist in form of String
//    public String booksToString() {
//        String result = ("\n" + name + ":");;
//        int i = 0;
//        for (Book b:bookList) {
//            result = result + "\n\t" + "(" + i + ")" + b.getBookName();
//            i++;
//        }
//        return result;
//    }

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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("blName", name);
        json.put("books", booksToJson());
        return json;
    }

    // EFFECTS: returns things in this bookList as a JSON array
    private JSONArray booksToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Book b : bookList) {
            jsonArray.put(b.toJson());
        }

        return jsonArray;
    }
}
