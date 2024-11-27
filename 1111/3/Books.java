package com.example.lms;

import javafx.scene.control.Alert;
import java.util.Objects;

public class Books {
    // Các trường dữ liệu được giữ nguyên để lưu thông tin sách
    private String title;
    private String authorName;
    private int numAvailableToBuy;
    private int numAvailableToBorrow;
    private double priceToBuy;
    private double priceToBorrow;

    // Constructor mặc định
    public Books() {
    }

    // Constructor có tham số để khởi tạo sách với đầy đủ thông tin
    public Books(String title, String authorName, int numAvailableToBuy,
                 int numAvailableToBorrow, double priceToBuy, double priceToBorrow) {
        this.title = title;
        this.authorName = authorName;
        this.numAvailableToBuy = numAvailableToBuy;
        this.numAvailableToBorrow = numAvailableToBorrow;
        this.priceToBuy = priceToBuy;
        this.priceToBorrow = priceToBorrow;
    }

    // Các phương thức getter và setter vẫn giữ nguyên như trước

    // Thêm phương thức toString() để dễ dàng in thông tin sách
    @Override
    public String toString() {
        return String.format("%d) Title: %s\nAuthor name: %s\nAvailable to buy: %d\n" +
                        "Available to borrow: %d\nPrice to buy: %.2f\n" +
                        "Price to borrow: %.2f",
                hashCode(), title, authorName,
                numAvailableToBuy, numAvailableToBorrow,
                priceToBuy, priceToBorrow);
    }

    // Phương thức in chi tiết sách sử dụng Alert được giữ nguyên
    public void printBookDetails(int bookCounter) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Book details");
        alert.setHeaderText(null);
        alert.setContentText(bookCounter + ")\nTitle: " + title +
                "\nAuthor name: " + authorName +
                "\nAvailable to buy: " + numAvailableToBuy +
                "\nAvailable to borrow: " + numAvailableToBorrow +
                "\nPrice to buy: " + priceToBuy +
                "\nPrice to borrow: " + priceToBorrow);
        alert.showAndWait();
    }

    // Phương thức kiểm tra sách có sẵn để mua không
    public boolean isAvailableToBuy() {
        return numAvailableToBuy > 0;
    }

    // Phương thức kiểm tra sách có sẵn để mượn không
    public boolean isAvailableToBorrow() {
        return numAvailableToBorrow > 0;
    }

    // Thêm phương thức equals() để so sánh hai đối tượng sách
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Books books = (Books) o;
        return numAvailableToBuy == books.numAvailableToBuy &&
                numAvailableToBorrow == books.numAvailableToBorrow &&
                Double.compare(priceToBuy, books.priceToBuy) == 0 &&
                Double.compare(priceToBorrow, books.priceToBorrow) == 0 &&
                Objects.equals(title, books.title) &&
                Objects.equals(authorName, books.authorName);
    }

    // Thêm phương thức hashCode() để hỗ trợ so sánh và sử dụng trong các collection
    @Override
    public int hashCode() {
        return Objects.hash(title, authorName, numAvailableToBuy,
                numAvailableToBorrow, priceToBuy, priceToBorrow);
    }

    // Getter methods
    public String getTitle() {
        return title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public int getNumAvailableToBuy() {
        return numAvailableToBuy;
    }

    public int getNumAvailableToBorrow() {
        return numAvailableToBorrow;
    }

    public double getPriceToBuy() {
        return priceToBuy;
    }

    public double getPriceToBorrow() {
        return priceToBorrow;
    }

    // Setter methods
    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setNumAvailableToBuy(int numAvailableToBuy) {
        this.numAvailableToBuy = numAvailableToBuy;
    }

    public void setNumAvailableToBorrow(int numAvailableToBorrow) {
        this.numAvailableToBorrow = numAvailableToBorrow;
    }

    public void setPriceToBuy(double priceToBuy) {
        this.priceToBuy = priceToBuy;
    }

    public void setPriceToBorrow(double priceToBorrow) {
        this.priceToBorrow = priceToBorrow;
    }
}