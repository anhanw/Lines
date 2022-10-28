package persistence;

import model.BookLists;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

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
    void testReaderEmptyWorkRoom() {
        JsonReaderBook reader = new JsonReaderBook("./data/testReaderEmptyBookLists.json");
        try {
            BookLists bls = reader.read();
            assertEquals("DoneBooks", bls.getFinishedBooks().getName());
            assertEquals("ToReadBooks", bls.getToRead().getName());
            assertEquals("RecommendBooks", bls.getRecommendBooks().getName());
            assertEquals(0, bls.getSpecialLists().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReaderBook reader = new JsonReaderBook("./data/testReaderGeneralBookLists.json");
        try {
            WorkRoom wr = reader.read();
            assertEquals("My work room", wr.getName());
            List<Thingy> thingies = wr.getThingies();
            assertEquals(2, thingies.size());
            checkThingy("needle", Category.STITCHING, thingies.get(0));
            checkThingy("saw", Category.WOODWORK, thingies.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
