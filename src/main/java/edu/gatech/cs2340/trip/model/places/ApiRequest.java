package edu.gatech.cs2340.trip.model.places;

import com.google.api.client.googleapis.apache.GoogleApacheHttpTransport;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.apache.ApacheHttpTransport;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by dheavern on 6/19/14.
 */
public abstract class ApiRequest {
    private ApacheHttpTransport transport;
    private HttpRequestFactory requestFactory;
    private String baseApiUrl;
    private Map<String, String> parameters;

    protected ApiRequest(String baseApiUrl) {
        this.baseApiUrl = baseApiUrl;
        try {
            this.transport = GoogleApacheHttpTransport.newTrustedTransport();
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        requestFactory = transport.createRequestFactory();
        parameters = new HashMap<String, String>();
    }

    protected void addParameter(String key, String value) {
        parameters.put(key, value);
    }


    public JsonObject execute() {
        JsonObject responseData = null;
        String response = "";
        String requestUrl = baseApiUrl + "?";
        for (String key : parameters.keySet()) {
            requestUrl += key + "=" + parameters.get(key) + "&";
        }
        System.out.println("REQUEST: " + requestUrl);
        try {
            HttpRequest apiRequest = requestFactory.buildGetRequest(
                    new GenericUrl(requestUrl));
            response = apiRequest.execute().parseAsString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        responseData = new JsonParser().parse(response).getAsJsonObject();
        return responseData;
    }
}
