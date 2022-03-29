package com.banno.weatherservice;

import org.eclipse.jetty.server.Server;

import java.io.InputStream;
import java.util.Properties;

public class WeatherServiceMain {
    public static void main(String[] args) throws Exception {
        Properties props = new Properties();
        InputStream is = WeatherServiceMain.class.getResourceAsStream("/WeatherService.properties");
        props.load(is);

        WeatherServerFactory serverFactory =
                new WeatherServerFactory(props.getProperty("httpPort"),
                        props.getProperty("apiKey")
                );

        Server server = serverFactory.createServer();
        server.start();
        server.join();
    }
}
