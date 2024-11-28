package com.example.lms;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GoogleBooksAPI {
    private static final String API_URL = "https://www.googleapis.com/books/v1/volumes?q=";
    private static final String API_KEY = "AIzaSyAmyeQwZEKJ5YrelDlsLsTD-Wm9vjmLUnk";  // Thay thế API Key của bạn nếu cần

    // Phương thức tìm kiếm sách
    public JsonObject searchBook(String query) throws Exception {
        // Tạo HttpClient
        HttpClient client = HttpClient.newHttpClient();

        // Xây dựng URL với API Key và từ khóa tìm kiếm
        String fullUrl = API_URL + query + "&key=" + API_KEY;

        // Xây dựng yêu cầu HTTP
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(fullUrl))
                .build();

        // Gửi yêu cầu và nhận phản hồi
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Parse phản hồi JSON sử dụng Gson
        String jsonResponse = response.body();
        JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();

        return jsonObject;
    }
}
