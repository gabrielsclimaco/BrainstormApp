package com.reforcointeligente.brainstormapp.Model;

import java.sql.Struct;

public class Lesson {
    private String lessonDate;
    private String lessonTime;
    private Teacher lessonTeacher;
    private Student lessonStudent;
    private String lessonSubject;
    private String lessonPlace;
    private Double lessonPricePerHour;
    private Double lessonDuration;
    private Double lessonTotalValue;
    private Double lessonProfit;

    public Lesson(String lessonDate, String lessonTime, Teacher lessonTeacher, Student lessonStudent,
                  String lessonSubject, String lessonPlace, Double lessonPricePerHour,
                  Double lessonDuration, Double lessonTotalValue, Double lessonProfit) {
        this.lessonDate = lessonDate;
        this.lessonTime = lessonTime;
        this.lessonTeacher = lessonTeacher;
        this.lessonStudent = lessonStudent;
        this.lessonSubject = lessonSubject;
        this.lessonPlace = lessonPlace;
        this.lessonPricePerHour = lessonPricePerHour;
        this.lessonDuration = lessonDuration;
        this.lessonTotalValue = lessonTotalValue;
        this.lessonProfit = lessonProfit;
    }

    public String getLessonDate() {
        return lessonDate;
    }

    public void setLessonDate(String lessonDate) {
        this.lessonDate = lessonDate;
    }

    public String getLessonTime() {
        return lessonTime;
    }

    public void setLessonTime(String lessonTime) {
        this.lessonTime = lessonTime;
    }

    public Teacher getLessonTeacher() {
        return lessonTeacher;
    }

    public void setLessonTeacher(Teacher lessonTeacher) {
        this.lessonTeacher = lessonTeacher;
    }

    public Student getLessonStudent() {
        return lessonStudent;
    }

    public void setLessonStudent(Student lessonStudent) {
        this.lessonStudent = lessonStudent;
    }

    public String getLessonSubject() {
        return lessonSubject;
    }

    public void setLessonSubject(String lessonSubject) {
        this.lessonSubject = lessonSubject;
    }

    public String getLessonPlace() {
        return lessonPlace;
    }

    public void setLessonPlace(String lessonPlace) {
        this.lessonPlace = lessonPlace;
    }

    public Double getLessonPricePerHour() {
        return lessonPricePerHour;
    }

    public void setLessonPricePerHour(Double lessonPricePerHour) {
        this.lessonPricePerHour = lessonPricePerHour;
    }

    public Double getLessonDuration() {
        return lessonDuration;
    }

    public void setLessonDuration(Double lessonDuration) {
        this.lessonDuration = lessonDuration;
    }

    public Double getLessonTotalValue() {
        setLessonTotalValue();
        return lessonTotalValue;
    }

    private void setLessonTotalValue() {
        lessonTotalValue = lessonPricePerHour * lessonDuration;
    }

    public Double getLessonProfit() {
        setLessonProfit();

        return lessonProfit;
    }

    private void setLessonProfit() {
        setLessonTotalValue();

        lessonProfit = lessonTotalValue - lessonTeacher.getPricePerHour();
    }
}
