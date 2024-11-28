package com.example.lms;

import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GoogleBooksAPI {
    private static final String GOOGLE_BOOKS_API_KEY = "AIzaSyB8e66shFWxTuqifrwsxxFd9f2TxygGE54"; // Thay bằng API key của bạn

    // Tìm kiếm sách qua Google Books API
    public JsonObject searchBook(String bookTitle) {
        String urlString = "https://www.googleapis.com/books/v1/volumes?q=" + bookTitle + "&key=" + GOOGLE_BOOKS_API_KEY;
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                // Chuyển đổi chuỗi JSON thành JsonObject
                return new com.google.gson.JsonParser().parse(response.toString()).getAsJsonObject();
            } else {
                System.out.println("Error: Unable to fetch data from Google Books API. Response code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}