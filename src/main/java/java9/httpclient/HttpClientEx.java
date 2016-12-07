package java9.httpclient;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import static java.net.http.HttpResponse.asString;

/**
 * Created by kopernik on 20/11/16.
 */
public class HttpClientEx {

    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
        useApacheHttpClient();
    }

    public static void useApacheHttpClient() throws IOException {
        HttpGet get = new HttpGet("http://google.com");
        get.addHeader("User-Agent", "Mozilla");

        HttpResponse response = HttpClients.createDefault().execute(get);

        System.out.println("Code is "+response.getStatusLine().getStatusCode());
        System.out.println("Body is "+new BufferedReader(
                new InputStreamReader(
                        response.getEntity().getContent(), StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining()));
    }

    public static void useJavaClient() throws URISyntaxException, IOException, InterruptedException {
        java.net.http.HttpResponse response =
                HttpClient.getDefault()
                .request(new URI("http://google.com"))
                .header("User-Agent", "Mozilla")
                .GET().response();

        System.out.println("Code is "+response.statusCode());
        System.out.println("Body is "+response.body(asString()));
    }
}
