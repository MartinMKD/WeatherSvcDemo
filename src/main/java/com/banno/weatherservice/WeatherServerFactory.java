package com.banno.weatherservice;

import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class WeatherServerFactory {
    final int idleTimeOut;
    final int outputBufferSize;
    final int port;
    final String apiKey;

    public WeatherServerFactory(String port, String apiKey) {
        this.idleTimeOut = 5000;
        this.outputBufferSize = 32768;
        this.port = Integer.parseInt(port);
        this.apiKey = apiKey;
    }

    public Server createServer() {
        Server server = new Server();

        HttpConfiguration config = new HttpConfiguration();
        config.setOutputBufferSize(outputBufferSize);
        config.setSendServerVersion(true);
        config.setSendDateHeader(false);

        ServerConnector connector = new ServerConnector(server, new HttpConnectionFactory(config));
        connector.setPort(port);
        connector.setIdleTimeout(idleTimeOut); // millis

        server.addConnector(connector);

        ServletContextHandler context = new ServletContextHandler();
        context.setContextPath("/");

        context.addServlet(
                new ServletHolder(
                        new OpenWeatherServlet(apiKey)), "/weathersvc");

        server.setHandler(new HandlerList(context, new DefaultHandler()));

        return server;
    }
}
