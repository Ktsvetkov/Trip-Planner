package edu.gatech.cs2340.trip.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.api.client.json.Json;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import edu.gatech.cs2340.trip.model.Itinerary;
import edu.gatech.cs2340.trip.model.accounts.Account;
import edu.gatech.cs2340.trip.model.accounts.UpdateAccountManger;
import edu.gatech.cs2340.trip.model.places.PlaceDetailRequest;
import edu.gatech.cs2340.trip.model.places.PlaceFinder;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by dheavern on 6/23/14.
 */
@WebServlet(urlPatterns = {
        "/places"
})
public class PlacesServlet extends HttpServlet {
    private PlaceFinder placeFinder = new PlaceFinder();


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {
        response.sendRedirect("main.jsp");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException{
        PrintWriter out = response.getWriter();
        String action = request.getParameter("action");
        if(action != null && action.equals("places")) {
            JsonObject places = findPlaces(request);
            out.print(places);
            return;
        } else if (action != null && action.equals("details")) {
            JsonObject results = new JsonObject();
            if (request.getParameter("placeId") != null) {
                results = findDetails(request.getParameter("placeId"));
            }
            out.print(results);
            return;
        } else if (action != null && action.equals("centralLocation")) {
            String location = request.getParameter("location");
            if (location == null) {
                out.print("Bad request");
                return;
            }
            setCentralLocation(location, request);
            out.print("Ok");
            return;
        } else if (action != null && action.equals("itinerary")) {
            out.print(getItinerary(request));
            return;
        }
    }

    private JsonObject findPlaces(HttpServletRequest request) {
        String[] coordinates = {request.getParameter("latitude"),
                request.getParameter("longitude")};
        boolean food = false;
        boolean attractions = false;
        if (request.getParameter("search_type") != null && request.getParameter("search_type").equals("food")) {
            food = true;
        } else if(request.getParameter("search_type") != null && request.getParameter("search_type").equals("attractions"))  {
            attractions = true;
        }
        String query = request.getParameter("query");
        int minRating;
        try {
            minRating = Integer.parseInt(request.getParameter("rating"));
        } catch (Exception e) {
            minRating = 0;
        }
        int[] priceLevels = new int[2];
        try {
            priceLevels[0] = 0;
            priceLevels[1] = Integer.parseInt(request.getParameter("maxprice"));
        } catch (Exception e) {
            priceLevels[0] = 0;
            priceLevels[1] = 4;
        }
        int radius;
        try {
            radius = Integer.parseInt(request.getParameter("radius"));
        } catch (Exception e) {
            radius = 50000;
        }
        placeFinder.setRadius(radius);
        placeFinder.setOrigin(coordinates[0], coordinates[1]);
        placeFinder.findAttractions(attractions);
        placeFinder.findFood(food);
        placeFinder.setSearchQuery(query);
        placeFinder.setMinimumRating(minRating);
        placeFinder.setPriceLevels(priceLevels[0], priceLevels[1]);
        return placeFinder.execute();
    }

    private JsonObject findDetails(String place_id) {
        return new PlaceDetailRequest(place_id).execute();
    }

    private void setCentralLocation(String location, HttpServletRequest request) {
        JsonParser parser = new JsonParser();
        JsonObject locationJson = parser.parse(location).getAsJsonObject();
        if (request.getSession().getAttribute("account") != null) {
            Account userAccount = (Account) request.getSession().getAttribute("account");
            userAccount.getTripData().setCentralLocation(locationJson);
            UpdateAccountManger.saveTripChanges(userAccount);
        } else if (request.getSession().getAttribute("itinerary") != null) {
            Itinerary itinerary = (Itinerary) request.getSession().getAttribute("itinerary");
            itinerary.setCentralLocation(locationJson);
        } else {
            Itinerary newItinerary = new Itinerary();
            newItinerary.setCentralLocation(locationJson);
            request.getSession().setAttribute("itinerary",locationJson);
        }
    }

    private String getItinerary(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("account") != null) {
            Account userAccount = (Account) session.getAttribute("account");
            return userAccount.getTripData().toString();
        } else if (session.getAttribute("itinerary") != null) {
            Itinerary itinerary = (Itinerary) session.getAttribute("itinerary");
            return itinerary.toString();
        } else {
            Itinerary itinerary = new Itinerary();
            session.setAttribute("itinerary", itinerary);
            return itinerary.toString();
        }
    }
}
