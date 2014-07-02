package edu.gatech.cs2340.trip.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Created by dheavern on 6/26/14.
 */
public class    Itinerary {
    private JsonObject centralLocation;
    private JsonArray stops;

    public Itinerary() {
        centralLocation = new JsonObject();
        stops = new JsonArray();
    }

    public Itinerary(String fromString) {
        JsonParser parser = new JsonParser();
        JsonElement json = parser.parse(fromString);
        JsonObject jsonObject = json.getAsJsonObject();
        centralLocation = jsonObject.getAsJsonObject("centralLocation");
        stops = jsonObject.getAsJsonArray("stops");
    }

    public JsonArray getStops() {
        return stops;
    }

    public void addStop(JsonObject stop) {
        stops.add(stop);
    }

    public JsonObject getCentralLocation() {
        return centralLocation;
    }

    public void setCentralLocation(JsonObject centralLocation) {
        this.centralLocation = centralLocation;
    }

    @Override
    public String toString() {
        JsonObject completeObject = new JsonObject();
        completeObject.add("centralLocation", centralLocation);
        completeObject.add("stops", stops);
        return completeObject.toString();
    }
}
