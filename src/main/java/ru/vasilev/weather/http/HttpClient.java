package ru.vasilev.weather.http;

import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
public class HttpClient {
    public String get(String targetURL, Map<String, String> params, Map<String, String> header) {
        return execute(targetURL, params, "GET", header);
    }

    public String post(String targetURL, Map<String, String> params, Map<String, String> header) {
        return execute(targetURL, params, "POST", header);
    }

    private String execute(String targetURL, Map<String, String> params, String method, Map<String, String> header) {
        HttpURLConnection connection = null;

        try {
            final String paramsString = getParamsString(params);
            URL url = new URL(targetURL + paramsString);
            connection = createConnection(url, method, header);
            
            StringBuilder response = getResponse(connection.getInputStream());
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    private StringBuilder getResponse(InputStream is) throws IOException {
        StringBuilder response;
        try (BufferedReader rd = new BufferedReader(new InputStreamReader(is))) {
            response = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
        }
        return response;
    }

    private HttpURLConnection createConnection(URL url, String method, Map<String, String> header) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(method);
        for (Map.Entry<String, String> entry :
                header.entrySet()) {
            connection.setRequestProperty(entry.getKey(), entry.getValue());
        }

        connection.setUseCaches(false);
        connection.setDoOutput(true);
        return connection;
    }

    private String getParamsString(Map<String, String> params) {
        StringBuilder result = new StringBuilder("?");

        for (Map.Entry<String, String> entry : params.entrySet()) {
            result.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
            result.append("&");
        }

        String resultString = result.toString();
        return resultString.length() > 0
                ? resultString.substring(0, resultString.length() - 1)
                : resultString;
    }
}
