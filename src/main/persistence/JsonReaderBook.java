package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.*;
import org.json.*;

// Methods were taken from JsonReader in:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

// Represents a reader that reads bookLists from JSON data stored in file
public class JsonReaderBook {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReaderBook(String source) {
        this.source = source;
    }

    // EFFECTS: reads bls from file and returns it;
    // throws IOException if an error occurs reading data from file
    public BookLists read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        EventLog.getInstance().logEvent(new Event("Books Data loaded: "));
        return parseBookLists(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses bookLists from JSON object and returns it
    private BookLists parseBookLists(JSONObject jsonObject) {
        BookLists bls = new BookLists("DoneBooks", "ToReadBooks", "RecommendBooks");

        JSONObject finish = jsonObject.getJSONObject("finish");
        addBook(bls.getFinishedBooks(), finish);

        JSONObject toRead = jsonObject.getJSONObject("toRead");
        addBook(bls.getToRead(), toRead);

        JSONObject recom = jsonObject.getJSONObject("recom");
        addBook(bls.getRecommendBooks(), recom);

        addBookList(bls, jsonObject);
        return bls;
    }

    // MODIFIES: bls
    // EFFECTS: parses bookList from JSON object and adds them to bls
    private void addBookList(BookLists bls, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("special");
        for (Object json : jsonArray) {
            JSONObject bl = (JSONObject) json;
            String blName = bl.getString("blName");
            BookList specialBL = new BookList(blName);
            addBook(specialBL, bl);
            bls.addBooks(specialBL);
        }
    }

    // MODIFIES: bookList inside bookLists
    // EFFECTS: parses book from JSON object and adds it to bookList
    private void addBook(BookList bl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("books");
        for (Object json : jsonArray) {
            JSONObject b = (JSONObject) json;
            String bookName = b.getString("bName");
            int lastPage = b.getInt("lPage");
            int currentPage = b.getInt("cPage");
            //double percentage = b.getDouble("%");
            Book book = new Book(bookName);
            book.setLastPage(lastPage);
            book.setCurrentStage(currentPage);
            addNote(book, b);
            bl.addBook(book);
        }
    }

    // MODIFIES: book inside bookLists
    // EFFECTS: parses note from JSON object and adds it to book
    private void addNote(Book b, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("notes");
        for (Object json : jsonArray) {
            JSONObject n = (JSONObject) json;
            int page = n.getInt("page");
            String content = n.getString("content");
            Note note = new Note(page, content);
            b.getNotes().addNote(note);
        }
    }
}
