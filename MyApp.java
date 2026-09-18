import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class MyApp {

    public static void main(String[] args) throws IOException {

        int port = 8080;

        HttpServer server = HttpServer.create(
                new InetSocketAddress("0.0.0.0", port), 0);

        server.createContext("/", (HttpExchange exchange) -> {

            String response =
                    "<html>" +
                    "<head>" +
                    "<title>Docker Java App</title>" +
                    "</head>" +
                    "<body>" +
                    "<h1>Hello from Docker!</h1>" +
                    "<h2>Multi-Stage Docker Build</h2>" +
                    "<p>Java application is running successfully.</p>" +
                    "<p>Environment: production</p>" +
                    "<p>Port: 8080</p>" +
                    "</body>" +
                    "</html>";

            exchange.getResponseHeaders()
                    .set("Content-Type", "text/html");

            exchange.sendResponseHeaders(
                    200, response.getBytes().length);

            OutputStream output =
                    exchange.getResponseBody();

            output.write(response.getBytes());
            output.close();
        });

        server.start();

        System.out.println(
                "Java application started on port " + port);
    }
}