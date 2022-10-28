package persistence;

import org.json.JSONObject;

// Method was taken from JsonWriter in:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

// Represent things that needed to change into JSON object
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
