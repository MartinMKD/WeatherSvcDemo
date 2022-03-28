package com.banno.weatherservice;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class OpenWeatherServlet extends HttpServlet
{
    private OpenWeatherClient weatherCli;

    protected OpenWeatherServlet()
    {
    }

    public OpenWeatherServlet(String apiKey)
    {
        this.weatherCli = new OpenWeatherClient((apiKey));
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
            response.getWriter().println("<h1>Temp: " + currWeather.get("current").get("temp") + "</h1><br/>");
        } catch (Exception e) {
            response.getWriter().println(e.getMessage());
        }
    }
}