package com.reforcointeligente.brainstormapp.Model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HandshakeCompletedEvent;

public class Teacher implements Serializable{

    private String teacherName;
    private String teacherAddress;
    private String teacherCity;
    private String teacherPhone;
    private String teacherCellphone;
    private String teacherCourse;
    private Boolean teacherCar;
    private Double pricePerHour;
    private String teacherEmail;
    private List<String> teacherSubjects;
    private Double teacherValueToPay;

    public Teacher(String teacherName, String teacherAddress, String teacherCity,
                   String teacherPhone, String teacherCellphone, String teacherCourse,
                   Boolean teacherCar, Double pricePerHour, String teacherEmail,
                   List<String> teacherSubjects, Double teacherValueToPay) {
        this.teacherName = teacherName;
        this.teacherAddress = teacherAddress;
        this.teacherCity = teacherCity;
        this.teacherPhone = teacherPhone;
        this.teacherCellphone = teacherCellphone;
        this.teacherCourse = teacherCourse;
        this.teacherCar = teacherCar;
        this.pricePerHour = pricePerHour;
        this.teacherEmail = teacherEmail;
        this.teacherSubjects = teacherSubjects;
        this.teacherValueToPay = teacherValueToPay;
    }

    public Teacher() {

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

    public Double getTeacherPricePerHour() {
        return pricePerHour;
    }

    public void setTeacherPricePerHour(Double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    public List<String> getTeacherSubjects() {
        return teacherSubjects;
    }

    public void setTeacherSubjects(List<String> teacherSubjects) {
        this.teacherSubjects = teacherSubjects;
    }

    public Double getTeacherValueToPay() {
        return teacherValueToPay;
    }

    public void setTeacherValueToPay(Double teacherValueToPay) {
        this.teacherValueToPay = teacherValueToPay;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();

        result.put("teacherName", teacherName);
        result.put("teacherAddress", teacherAddress);
        result.put("teacherCity", teacherCity);
        result.put("teacherPhone", teacherPhone);
        result.put("teacherCellphone", teacherCellphone);
        result.put("teacherCourse", teacherCourse);
        result.put("teacherCar", teacherCar);
        result.put("pricePerHour", pricePerHour);
        result.put("teacherEmail", teacherEmail);
        result.put("teacherSubjects", teacherSubjects);
        result.put("teacherValueToPay", teacherValueToPay);

        return result;
    }
}


