package com.poc.expectations.response;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;

import java.io.FileReader;
import java.util.Map;
import java.util.Optional;

public class JsonResponseReader {

    private static final String RESPONSE_PATH = "/config/responses/";
    private static final String EXTENSION = ".json";

    private final JSONParser parser;

    public JsonResponseReader() {
        parser = new JSONParser(JSONParser.MODE_JSON_SIMPLE);
    }

    public Optional<JSONObject> getFiltered(String file, Map<String, Object> filter) {
        //TODO
        return Optional.empty();
    }

    public Optional<JSONObject> getDefault(String file) {

        JSONArray array = (JSONArray) readFile(file);

        return array.stream()
                .filter(obj -> {
                    JSONObject json = (JSONObject) obj;
                    return (boolean) json.get("default");
                })
                .findFirst()
                .map(obj -> (JSONObject) obj);
    }

    private Object readFile(String file) {
        try (FileReader reader = new FileReader(RESPONSE_PATH + file + EXTENSION)) {
            return parser.parse(reader);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}