package persistence;

import model.Book;
import model.BookList;
import model.BookLists;
import model.Note;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Methods were taken from JsonWriterTest in:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

public class JsonWriterBookTest {
    @Test
    void testWriterInvalidFile() {
        try {
            BookLists bls = new BookLists("f", "t", "r");
            JsonWriterBook writer = new JsonWriterBook("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyBookLists() {
        try {
            BookLists bls = new BookLists("f", "t", "r");
            JsonWriterBook writer = new JsonWriterBook("./data/testBookWriterEmptyBookLists.json");
            writer.open();
            writer.write(bls);
            writer.close();

            JsonReaderBook reader = new JsonReaderBook("./data/testBookWriterEmptyBookLists.json");
            bls = reader.read();
            assertEquals("DoneBooks", bls.getFinishedBooks().getName());
            assertEquals("ToReadBooks", bls.getToRead().getName());
            assertEquals("RecommendBooks", bls.getRecommendBooks().getName());
            assertEquals(0, bls.getSpecialLists().size());
            assertEquals(0, bls.getFinishedBooks().getBookList().size());
            assertEquals(0, bls.getToRead().getBookList().size());
            assertEquals(0, bls.getRecommendBooks().getBookList().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralBookLists() {
        try {
            BookLists bls = new BookLists("f", "t", "r");
            bls.addBooks(new BookList("test"));
            Book b = new Book("Harry");
            b.setLastPage(100);
            b.setCurrentStage(46);
            Note n = new Note(41, "haha");
            b.getNotes().addNote(n);
            bls.getToRead().addBook(b);

            JsonWriterBook writer = new JsonWriterBook("./data/testBookWriterGeneralBookLists.json");
            writer.open();
            writer.write(bls);
            writer.close();

            JsonReaderBook reader = new JsonReaderBook("./data/testBookWriterGeneralBookLists.json");
            bls = reader.read();
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
            fail("Exception should not have been thrown");
        }
    }
}
