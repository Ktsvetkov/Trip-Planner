package edu.gatech.cs2340.trip.model.places;

import com.google.api.client.json.Json;
import com.google.gson.JsonObject;

/**
 * Created by dheavern on 6/19/14.
 */
public class PlaceFinder {
    private ApiRequestBuilder placesRequestBuilder = new PlacesRequestBuilder();
    private ApiRequest placeDetails;
    private String[] coordinates;
    private boolean food;
    private boolean attractions;
    private String query;
    private int minRating;
    private int[] priceLevels;
    private int[] openHours;
    private int radius;

    public PlaceFinder() {
        coordinates = new String[2];
        priceLevels = new int[2];
        openHours = new int[2];
    }

    public void findAttractions(boolean includeAttractions) {
        attractions = includeAttractions;
    }

    public void findFood(boolean includeFood) {
        food = includeFood;
    }

    public void setSearchQuery(String query) {
        this.query = query;
    }

    public void setMinimumRating(int minimumRating) {
        minRating = minimumRating;
    }

    public void setPriceLevels(int lowLevel, int highLevel) {
        priceLevels[0] = lowLevel;
        priceLevels[1] = highLevel;
    }

    public void setOpenHours(int startTime, int endTime) {
        openHours[0] = startTime;
        openHours[1] = endTime;
    }

    public void setOrigin(String latitude, String longitude) {
        coordinates[0] = latitude;
        coordinates[1] = longitude;
    }

    public void setRadius(int meters) {
        radius = meters;
    }

    public JsonObject execute() {
        placesRequestBuilder.setPriceLevels(priceLevels[0], priceLevels[1]);
        placesRequestBuilder.setMinimumRating(minRating);
        placesRequestBuilder.setSearchQuery(query);
        placesRequestBuilder.findAttractions(attractions);
        placesRequestBuilder.findFood(food);
        placesRequestBuilder.setOrigin(coordinates[0], coordinates[1]);
        placesRequestBuilder.setRadius(radius);
        return placesRequestBuilder.build().execute();
    }
}
