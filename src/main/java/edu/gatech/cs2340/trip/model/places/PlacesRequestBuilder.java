package edu.gatech.cs2340.trip.model.places;

import com.google.gson.JsonObject;


/**
 * Created by dheavern on 6/19/14.
 */
public class PlacesRequestBuilder implements ApiRequestBuilder {
    private String apiKey = "AIzaSyBPYvn0hWrHIz-OEKXLcmQfGzqMK_Tk0wU";
    private String[] coordinates;
    private boolean food;
    private boolean attractions;
    private String query;
    private int minRating;
    private int[] priceLevels;
    private int[] openHours;
    private int radius;


    public PlacesRequestBuilder() {
        coordinates = new String[2];
        priceLevels = new int[2];
        openHours = new int[2];
        //Setting default Coordinates in Atlanta
        setOrigin("33.862100", "-84.687900");
        setRadius(50000);
        setSearchQuery("");
        setMinimumRating(0);
        setPriceLevels(0, 4);
    }

    @Override
    public void findAttractions(boolean includeAttractions) {
        attractions = includeAttractions;
    }

    @Override
    public void findFood(boolean includeFood) {
        food = includeFood;
    }

    @Override
    public void setSearchQuery(String query) {
        this.query = query;
    }

    @Override
    public void setMinimumRating(int minimumRating) {
        minRating = minimumRating;
    }

    @Override
    public void setPriceLevels(int lowLevel, int highLevel) {
        priceLevels[0] = lowLevel;
        priceLevels[1] = highLevel;
    }

    @Override
    public void setOrigin(String latitude, String longitude) {
        coordinates[0] = latitude;
        coordinates[1] = longitude;
    }

    @Override
    public void setRadius(int meters) {
        radius = meters;
    }

    @Override
    public ApiRequest build() {
        PlacesRequest request = new PlacesRequest();
        return request;
    }

    private class PlacesRequest extends ApiRequest {
        public PlacesRequest() {
            super("https://maps.googleapis.com/maps/api/place/nearbysearch/json");
            addParameter("key", apiKey);
            addParameter("location", coordinates[0]+","+coordinates[1]);
            addParameter("radius", Integer.toString(radius));
            addParameter("sensor", "false");
            addParameter("keyword", query);
            addParameter("minprice", Integer.toString(priceLevels[0]));
            addParameter("maxprice", Integer.toString(priceLevels[1]));
            String types = "";
            if (food) {
                types += "food|cafe|bakery|restaurant|meal_delivery|\n" +
                        "meal_takeaway|";
            }
            if (attractions) {
                types += "zoo|shopping_mall|park|museum|night_club|movie_theater|casino|bowling_alley|bar|art_gallery|" +
                        "aquarium|amusement_park";
            }
            addParameter("types", types);
        }
    }
}
