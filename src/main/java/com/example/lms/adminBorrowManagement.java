package com.example.lms;

import java.sql.Struct;
import java.util.Date;
import java.util.PrimitiveIterator;

public class adminBorrowManagement {
    private String studentNumber;
    private String firstName;
    private String lastName;
    private String gender;
    private String bookTitle;
    private String author;
    private String bookType;
    private String image;
    private Date date;
    private String checkReturn;

    public String getCheckReturn() {
        return checkReturn;
    }

    public void setCheckReturn(String checkReturn) {
        this.checkReturn = checkReturn;
    }

    public adminBorrowManagement(String studentNumber, String firstName, String lastName, String gender, String bookTitle, String author, String bookType, String image, Date date, String checkReturn) {
        this.studentNumber = studentNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.bookTitle = bookTitle;
        this.author = author;
        this.bookType = bookType;
        this.image = image;
        this.date = date;
        this.checkReturn = checkReturn;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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
}
