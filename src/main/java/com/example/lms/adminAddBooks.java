package com.example.lms;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class adminAddBooks {
    private String bookTitle;
    private String author;
    private String bookType;
    private String image;
    private Date date;

    // Constructor
    public adminAddBooks(String bookTitle, String author, String bookType, String image, Date date) {
        this.bookTitle = bookTitle;
        this.author = author;
        this.bookType = bookType;
        this.image = image;
        this.date = date;
    }

    // Getters and Setters
    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    // API key
    private static final String API_KEY = "AIzaSyDCtNN2okTUP2dUSXBKw4bUqBaeTDNXM0E";

    // Phương thức tĩnh để tìm kiếm sách theo tiêu đề
    public static List<adminAddBooks> searchBooksByTitle(String bookTitle) {
        // API URL với bộ lọc intitle
        String apiUrl = "https://www.googleapis.com/books/v1/volumes?q=intitle:"
                + bookTitle.replace(" ", "+") + "&key=" + API_KEY;
        List<adminAddBooks> books = new ArrayList<>();
        try {
            // Gửi yêu cầu HTTP GET
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("HTTP error code : " + conn.getResponseCode());
            }

            // Đọc phản hồi JSON
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            StringBuilder response = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) {
                response.append(output);
            }
            conn.disconnect();

            // Phân tích JSON và tạo danh sách sách
            JsonObject jsonResponse = JsonParser.parseString(response.toString()).getAsJsonObject();
            JsonArray items = jsonResponse.getAsJsonArray("items");
            if (items != null) {
                for (JsonElement item : items) {
                    JsonObject volumeInfo = item.getAsJsonObject().getAsJsonObject("volumeInfo");
                    String title = volumeInfo.has("title") ? volumeInfo.get("title").getAsString() : "Unknown Title";
                    String authors = volumeInfo.has("authors")
                            ? volumeInfo.getAsJsonArray("authors").get(0).getAsString()
                            : "Unknown Author";
                    String bookType = "N/A"; // Không có thông tin loại sách trong API
                    String image = volumeInfo.has("imageLinks")
                            ? volumeInfo.getAsJsonObject("imageLinks").get("thumbnail").getAsString()
                            : "No Image";
                    Date date = null; // Ngày xuất bản không xử lý trong ví dụ này

                    // Thêm sách vào danh sách kết quả
                    books.add(new adminAddBooks(title, authors, bookType, image, date));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }
}