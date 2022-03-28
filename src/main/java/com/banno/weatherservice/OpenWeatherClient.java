package com.banno.weatherservice;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class OpenWeatherClient {

    private static final Logger LOG = LoggerFactory.getLogger(OpenWeatherClient.class);

    private final String apiKey;
    private final ObjectMapper mapper = new ObjectMapper();

    public OpenWeatherClient(String apiKey) {
        this.apiKey = apiKey;
    }

    public JsonNode oneCall(String lat, String lon) throws Exception {
        Validate.notEmpty(lat, "latitude cannot be empty");
        Validate.notEmpty(lon, "longitude cannot be empty");

        StringBuilder url = new StringBuilder("https://api.openweathermap.org/data/2.5/");
        url.append("onecall").append("?appid=")
                .append(apiKey)
                .append("&exclude=hourly,daily,minutely")
                .append("&units=metric")
                .append("&lat=")
                .append(lat)
                .append("&lon=")
                .append(lon);

        return mapper.readTree(get(url.toString()));
    }

    private String get(String uri) throws Exception {
        LOG.info("Weather URL: {}", uri);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
