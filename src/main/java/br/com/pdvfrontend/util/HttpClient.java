package br.com.pdvfrontend.util;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient {

    private static final String BASE_URL = "http://localhost:8080/pessoas";

    public static String get(String endpoint) throws IOException {
        URL url = new URL(BASE_URL + endpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        return readResponse(conn);
    }

    public static String post(String endpoint, String json) throws IOException {
        return sendWithBody("POST", endpoint, json);
    }

    public static String put(String endpoint, String json) throws IOException {
        return sendWithBody("PUT", endpoint, json);
    }

    public static String delete(String endpoint) throws IOException {
        URL url = new URL(BASE_URL + endpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("DELETE");
        return readResponse(conn);
    }

    private static String sendWithBody(String method, String endpoint, String json) throws IOException {
        URL url = new URL(BASE_URL + endpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(method);
        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {
            os.write(json.getBytes("UTF-8"));
        }
        return readResponse(conn);
    }

    private static String readResponse(HttpURLConnection conn) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) sb.append(line);
            return sb.toString();
        }
    }
}

