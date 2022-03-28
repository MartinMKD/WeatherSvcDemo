package com.banno.weatherservice;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.Validate;

import java.io.IOException;

public class OpenWeatherServlet extends HttpServlet
{
    final String apiKey;
    final private OpenWeatherClient weatherCli;

    public OpenWeatherServlet() {
        this.apiKey = "5cf87e4d27fa3db53bbb23b354fdb5a9";
        this.weatherCli = new OpenWeatherClient((apiKey));
    }

    private String tempLabel(double temp)
    {
        if (temp < 0) {
            return "Freezing";
        } else if (temp < 5) {
            return "Cold";
        } else if (temp < 10) {
            return "Chilly";
        } else if (temp < 15) {
            return "Cool";
        } else if (temp < 28) {
            return "Warm";
        } else {
            return "Hot";
        }
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException
    {
        String lat = request.getParameter("lat");
        String lon = request.getParameter("lon");

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);

        try {
            JsonNode currWeather = weatherCli.oneCall(lat, lon);
            double currTemp = currWeather.get("current").get("temp").asDouble();

            response.getWriter().println("<h3>Temp: "
                    + currTemp + " C (Feels: " + tempLabel(currTemp) + ")</h3>");

            if (currWeather.get("alerts").isArray()) {
                for (final JsonNode objNode : currWeather.get("alerts")) {
                    String desc = objNode.get("description").asText();
                    desc = desc.replaceAll("\\n", "<br>");
                    response.getWriter().println("<h3>Alert: <pre>" + desc+ "</pre>");
                }
            }

        } catch (Exception e) {
            response.getWriter().println(e.getMessage());
        }
    }
}