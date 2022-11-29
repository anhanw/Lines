package persistence;

import model.Event;
import model.EventLog;
import model.Excerpt;
import model.Excerpts;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Methods were taken from JsonReader in:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

// Represents a reader that reads Excerpts from JSON data stored in file
public class JsonReaderExcerpt {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReaderExcerpt(String source) {
        this.source = source;
    }

    // EFFECTS: reads excerpts from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Excerpts read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        EventLog.getInstance().logEvent(new Event("Excerpts Data loaded: "));
        return parseExcerpts(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses excerpts from JSON object and returns it
    private Excerpts parseExcerpts(JSONObject jsonObject) {
        String name = jsonObject.getString("excerptsName");
        Excerpts excerpts = new Excerpts(name);
        addExcerpt(excerpts, jsonObject);
        return excerpts;
    }

    // MODIFIES: excerpts
    // EFFECTS: parses excerpt from JSON object and adds them to bls
    private void addExcerpt(Excerpts lines, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("excerpts");
        for (Object json : jsonArray) {
            JSONObject excerpt = (JSONObject) json;
            String from = excerpt.getString("from");
            String content = excerpt.getString("Lines");
            Excerpt line = new Excerpt(from,content);
            lines.addExcerpt(line);
        }
    }
}
