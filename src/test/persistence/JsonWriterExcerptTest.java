package persistence;

import model.Excerpt;
import model.Excerpts;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Methods were taken from JsonWriterTest in:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

public class JsonWriterExcerptTest {
    @Test
    void testWriterInvalidFile() {
        try {
            Excerpts es = new Excerpts("TuanTuan");
            JsonWriterExcerpt writer = new JsonWriterExcerpt("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyExcerpts() {
        try {
            Excerpts es = new Excerpts("TuanTuan");
            JsonWriterExcerpt writer = new JsonWriterExcerpt("./data/testExcerptWriterEmptyExcerpts.json");
            writer.open();
            writer.write(es);
            writer.close();

            JsonReaderExcerpt reader = new JsonReaderExcerpt("./data/testExcerptWriterEmptyExcerpts.json");
            es = reader.read();
            assertEquals("TuanTuan", es.getExcerptsName());
            assertEquals(0, es.getExcerpts().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralExcerpts() {
        try {
            Excerpts es = new Excerpts("TuanTuan");
            Excerpt piece1 = new Excerpt("book", "biubiu");
            Excerpt piece2 = new Excerpt("dadida", "life aaaa");
            es.addExcerpt(piece1);
            es.addExcerpt(piece2);
            JsonWriterExcerpt writer = new JsonWriterExcerpt("./data/testExcerptWriterGeneralExcerpts.json");
            writer.open();
            writer.write(es);
            writer.close();

            JsonReaderExcerpt reader = new JsonReaderExcerpt("./data/testExcerptWriterGeneralExcerpts.json");
            es = reader.read();
            assertEquals("TuanTuan", es.getExcerptsName());
            assertEquals(2, es.getExcerpts().size());
            assertEquals("book", es.getExcerpts().get(0).getFrom());
            assertEquals("biubiu", es.getExcerpts().get(0).getContent());
            assertEquals("dadida", es.getExcerpts().get(1).getFrom());
            assertEquals("life aaaa", es.getExcerpts().get(1).getContent());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
