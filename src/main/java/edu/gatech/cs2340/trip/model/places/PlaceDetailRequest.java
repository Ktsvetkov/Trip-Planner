package edu.gatech.cs2340.trip.model.places;

/**
 * Created by dheavern on 6/26/14.
 */
public class PlaceDetailRequest extends ApiRequest {
    private String apiKey = "AIzaSyBPYvn0hWrHIz-OEKXLcmQfGzqMK_Tk0wU";

    public PlaceDetailRequest(String placeId) {
        super("https://maps.googleapis.com/maps/api/place/details/json");
        addParameter("key", apiKey);
        addParameter("placeid", placeId);
    }
}
