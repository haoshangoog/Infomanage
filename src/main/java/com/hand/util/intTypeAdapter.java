package com.hand.util;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class intTypeAdapter extends TypeAdapter<Integer> {

    @Override
    public void write(JsonWriter out, Integer value) throws IOException {
        out.value(String.valueOf(value));
    }
    @Override
    public Integer read(JsonReader in) throws IOException {
        try {
            return Integer.parseInt(in.nextString());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}