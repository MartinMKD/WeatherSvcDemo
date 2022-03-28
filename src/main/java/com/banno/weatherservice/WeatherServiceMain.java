package com.banno.weatherservice;

import org.eclipse.jetty.server.Server;

public class WeatherServiceMain {

    public static void main(String[] args) throws Exception {
        WeatherServerFactory serverFactory = new WeatherServerFactory();
        Server server = serverFactory.createServer();
        server.start();
        server.join();
    }
}
