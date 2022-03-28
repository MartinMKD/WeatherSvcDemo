package com.banno.weatherservice;

import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class WeatherServiceMain {

    public static void main(String[] args) throws Exception {
        final int port = 8080;
        final int idleTimeOut = 5000; // 5 seconds
        final int outputBufferSize = 32768;
        final String apiKey = "5cf87e4d27fa3db53bbb23b354fdb5a9";

        HttpConfiguration config = new HttpConfiguration();
        config.setOutputBufferSize(outputBufferSize);
        config.setSendServerVersion(true);
        config.setSendDateHeader(false);

        Server server = new Server();

        ServerConnector connector = new ServerConnector(server, new HttpConnectionFactory(config));
        connector.setPort(port);
        connector.setIdleTimeout(idleTimeOut); // millis

        server.addConnector(connector);

        ServletContextHandler context = new ServletContextHandler();
        context.setContextPath("/");
        context.addServlet(
                new ServletHolder(
                        new OpenWeatherServlet(apiKey)), "/hello");

        server.setHandler(new HandlerList(context, new DefaultHandler()));
        server.start();
        server.join();
    }
}
