package com.banno.weatherservice;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Iterator;

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
            return "freezing";
        } else if (temp < 5.0) {
            return "cold";
        } else if (temp < 10.0) {
            return "chilly";
        } else if (temp < 15.0) {
            return "cool";
        } else if (temp < 28.0) {
            return "warm";
        } else {
            return "hot";
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

            response.getWriter().println("Current conditions: <p>"
                    + currTemp + " <span>&#8451;</span> (feels " + tempLabel(currTemp) + "), ");

            if (currWeather.get("current").get("weather").isArray()) {
                Iterator<JsonNode> iter = currWeather.get("current").get("weather").iterator();
                while (iter.hasNext()) {
                    final JsonNode objNode = iter.next();
                    String desc = objNode.get("description").asText();
                    response.getWriter().print(desc);
                    if (iter.hasNext()) {
                        response.getWriter().print(",");
                    }
                }
            }

            response.getWriter().println("</p>Alerts:");

            if (currWeather.get("alerts").isArray()) {
                for (final JsonNode objNode : currWeather.get("alerts")) {
                    String desc = objNode.get("description").asText();
                    desc = desc.replaceAll("\\n", "<br>");
                    response.getWriter().println("<br><pre>" + desc+ "</pre></br>");
                }
            }

        } catch (Exception e) {
            response.getWriter().println(e.getMessage());
        }
    }
}