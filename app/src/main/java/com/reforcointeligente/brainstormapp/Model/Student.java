package com.reforcointeligente.brainstormapp.Model;

import java.io.Serializable;

public class Student implements Serializable {

    private String studentName;
    private Integer studentAge;
    private String studentAddress;
    private String studentCity;
    private String studentSchoolYear;
    private String studentSchool;
    private String studentParentName;
    private String studentParentPhone;
    private String studentParentCellphone;
    private String studentParentEmail;
    private Double studentDebt;

    public Student() {
    }

    public Student(String studentName, Integer studentAge, String studentAddress,
                   String studentCity, String studentSchoolYear, String studentSchool,
                   String studentParentName, String studentParentPhone,
                   String studentParentCellphone, String studentParentEmail, Double studentDebt) {
        this.studentName = studentName;
        this.studentAge = studentAge;
        this.studentAddress = studentAddress;
        this.studentCity = studentCity;
        this.studentSchoolYear = studentSchoolYear;
        this.studentSchool = studentSchool;
        this.studentParentName = studentParentName;
        this.studentParentPhone = studentParentPhone;
        this.studentParentCellphone = studentParentCellphone;
        this.studentParentEmail = studentParentEmail;
        this.studentDebt = studentDebt;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getStudentAge() {
        return studentAge;
    }

    public void setStudentAge(Integer studentAge) {
        this.studentAge = studentAge;
    }

    public String getStudentAddress() {
        return studentAddress;
    }

    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }

    public String getStudentCity() {
        return studentCity;
    }

    public void setStudentCity(String studentCity) {
        this.studentCity = studentCity;
    }

    public String getStudentSchoolYear() {
        return studentSchoolYear;
    }

    public void setStudentSchoolYear(String studentSchoolYear) {
        this.studentSchoolYear = studentSchoolYear;
    }

    public String getStudentSchool() {
        return studentSchool;
    }

    public void setStudentSchool(String studentSchool) {
        this.studentSchool = studentSchool;
    }

    public String getStudentParentName() {
        return studentParentName;
    }

    public void setStudentParentName(String studentParentName) {
        this.studentParentName = studentParentName;
    }

    public String getStudentParentPhone() {
        return studentParentPhone;
    }

    public void setStudentParentPhone(String studentParentPhone) {
        this.studentParentPhone = studentParentPhone;
    }

    public String getStudentParentCellphone() {
        return studentParentCellphone;
    }

    public void setStudentParentCellphone(String studentParentCellphone) {
        this.studentParentCellphone = studentParentCellphone;
    }

    public String getStudentParentEmail() {
        return studentParentEmail;
    }

    public void setStudentParentEmail(String studentParentEmail) {
        this.studentParentEmail = studentParentEmail;
    }

    public Double getStudentDebt() {
        return studentDebt;
    }

    public void setStudentDebt(Double studentDebt) {
        this.studentDebt = studentDebt;
    }
}