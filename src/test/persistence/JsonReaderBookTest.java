package persistence;

import model.Book;
import model.BookLists;
import model.Note;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Methods were taken from JsonReaderTest in:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

public class JsonReaderBookTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReaderBook reader = new JsonReaderBook("./data/noSuchFile.json");
        try {
            BookLists bls = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyBookLists() {
        JsonReaderBook reader = new JsonReaderBook("./data/testBookReaderEmptyBookLists.json");
        try {
            BookLists bls = reader.read();
            assertEquals("DoneBooks", bls.getFinishedBooks().getName());
            assertEquals("ToReadBooks", bls.getToRead().getName());
            assertEquals("RecommendBooks", bls.getRecommendBooks().getName());
            assertEquals(0, bls.getSpecialLists().size());
            assertEquals(0, bls.getFinishedBooks().getBookList().size());
            assertEquals(0, bls.getToRead().getBookList().size());
            assertEquals(0, bls.getRecommendBooks().getBookList().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralBookLists() {
        JsonReaderBook reader = new JsonReaderBook("./data/testBookReaderGeneralBookLists.json");
        try {
            BookLists bls = reader.read();
            Book b = new Book("Harry");
            b.setLastPage(100);
            b.setCurrentStage(46);
            Note n = new Note(41, "haha");
            b.getNotes().addNote(n);

            assertEquals("DoneBooks", bls.getFinishedBooks().getName());
            assertEquals("ToReadBooks", bls.getToRead().getName());
            assertEquals("RecommendBooks", bls.getRecommendBooks().getName());
            assertEquals(1, bls.getSpecialLists().size());
            assertEquals("test", bls.getSpecialLists().get(0).getName());
            assertEquals(0, bls.getFinishedBooks().getBookList().size());
            assertEquals(1, bls.getToRead().getBookList().size());
            Book book = (Book) bls.getToRead().getBookList().get(0);
            assertEquals("Harry", book.getBookName());
            assertEquals(41, book.getNotes().get(0).getPage());
            assertEquals("haha", book.getNotes().get(0).getContent());
            assertEquals(0, bls.getRecommendBooks().getBookList().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
