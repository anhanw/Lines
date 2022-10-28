package persistence;

import model.Excerpts;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Methods were taken from JsonReaderTest in:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

public class JsonReaderExcerptTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReaderExcerpt reader = new JsonReaderExcerpt("./data/noSuchFile.json");
        try {
            Excerpts ex = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyExcerpts() {
        JsonReaderExcerpt reader = new JsonReaderExcerpt("./data/testExcerptReaderEmptyExcerpts.json");
        try {
            Excerpts es = reader.read();
            assertEquals("TuanTuan", es.getExcerptsName());
            assertEquals(0, es.getExcerpts().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralExcerpts() {
        JsonReaderExcerpt reader = new JsonReaderExcerpt("./data/testExcerptReaderGeneralExcerpts.json");
        try {
            Excerpts es = reader.read();
            assertEquals("TuanTuan", es.getExcerptsName());
            assertEquals(2, es.getExcerpts().size());
            assertEquals("book", es.getExcerpts().get(0).getFrom());
            assertEquals("biubiu", es.getExcerpts().get(0).getContent());
            assertEquals("dadida", es.getExcerpts().get(1).getFrom());
            assertEquals("life aaaa", es.getExcerpts().get(1).getContent());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
