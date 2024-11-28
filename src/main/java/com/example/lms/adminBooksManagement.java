package com.example.lms;

import java.sql.Date;

public class adminBooksManagement {
    private String  title;
    private String  author;
    private String  image;
    private String bookType;
    private Date date;

    public adminBooksManagement(String title, String author, String bookType, String image, Date date) {
        this.title = title;
        this.author = author;
        this.bookType = bookType;
        this.image = image;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }
}
