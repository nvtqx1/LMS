package com.example.lms;


import java.util.Date;

public class adminAddBooks {
    private String bookTitle;
    private String author;
    private String bookType;
    private String image;
    private Date date;

    public adminAddBooks(String bookTitle, String author, String bookType, String image, Date date) {
        this.bookTitle = bookTitle;
        this.author = author;
        this.bookType = bookType;
        this.image = image;
        this.date = date;
    }

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
