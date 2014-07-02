package edu.gatech.cs2340.trip.model.places;

/**
 * Created by dheavern on 6/19/14.
 */
public interface ApiRequestBuilder {
    void findAttractions(boolean includeAttractions);
    void findFood(boolean includeFood);
    void setSearchQuery(String query);
    void setMinimumRating(int minimumRating);
    void setPriceLevels(int lowLevel, int highLevel);
    void setOrigin(String latitude, String longitude);
    void setRadius(int meters);
    ApiRequest build();
}
