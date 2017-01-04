package com.reforcointeligente.brainstormapp.Model;

import java.util.List;

public class Teacher {

    private String teacherName;
    private String teacherAddress;
    private String teacherCity;
    private String teacherPhone;
    private String teacherCellphone;
    private String teacherCourse;
    private Boolean teacherCar;
    private Double pricePerHour;
    private String teacherEmail;
    private List<String> teacherStudents;

    public Teacher(String teacherName, String teacherAddress, String teacherCity,
                   String teacherPhone, String teacherCellphone, String teacherCourse,
                   Boolean teacherCar, Double pricePerHour, String teacherEmail,
                   List<String> teacherStudents) {
        this.teacherName = teacherName;
        this.teacherAddress = teacherAddress;
        this.teacherCity = teacherCity;
        this.teacherPhone = teacherPhone;
        this.teacherCellphone = teacherCellphone;
        this.teacherCourse = teacherCourse;
        this.teacherCar = teacherCar;
        this.pricePerHour = pricePerHour;
        this.teacherEmail = teacherEmail;
        this.teacherStudents = teacherStudents;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherAddress() {
        return teacherAddress;
    }

    public void setTeacherAddress(String teacherAddress) {
        this.teacherAddress = teacherAddress;
    }

    public String getTeacherCity() {
        return teacherCity;
    }

    public void setTeacherCity(String teacherCity) {
        this.teacherCity = teacherCity;
    }

    public String getTeacherPhone() {
        return teacherPhone;
    }

    public void setTeacherPhone(String teacherPhone) {
        this.teacherPhone = teacherPhone;
    }

    public String getTeacherCellphone() {
        return teacherCellphone;
    }

    public void setTeacherCellphone(String teacherCellphone) {
        this.teacherCellphone = teacherCellphone;
    }

    public String getTeacherCourse() {
        return teacherCourse;
    }

    public void setTeacherCourse(String teacherCourse) {
        this.teacherCourse = teacherCourse;
    }

    public Boolean getTeacherCar() {
        return teacherCar;
    }

    public void setTeacherCar(Boolean teacherCar) {
        this.teacherCar = teacherCar;
    }

    public Double getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(Double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    public List<String> getTeacherStudents() {
        return teacherStudents;
    }

    public void setTeacherStudents(List<String> teacherStudents) {
        this.teacherStudents = teacherStudents;
    }
}


