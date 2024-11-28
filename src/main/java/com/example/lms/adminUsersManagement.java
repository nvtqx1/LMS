package com.example.lms;

public class adminUsersManagement {
    private String studentNumber;
    private String password;
    private String image;

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public adminUsersManagement(String studentNumber, String password, String image) {
        this.studentNumber = studentNumber;
        this.password = password;
        this.image = image;
    }
}
