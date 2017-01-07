package com.reforcointeligente.brainstormapp.Model;

public class Lesson {
    private String lessonDate;
    private String lessonTime;
    private Teacher lessonTeacher;
    private Student lessonStudent;
    private String lessonSubject;
    private String lessonPlace;
    private Double lessonDisplacement;
    private Double lessonValuePerHour;
    private Double lessonDuration;
    private Double lessonTotalValue;
    private Double lessonProfit;

    public Lesson(String lessonDate, String lessonTime, Teacher lessonTeacher, Student lessonStudent,
                  String lessonSubject, String lessonPlace, Double lessonDisplacement,
                  Double lessonValuePerHour, Double lessonDuration) {
        this.lessonDate = lessonDate;
        this.lessonTime = lessonTime;
        this.lessonTeacher = lessonTeacher;
        this.lessonStudent = lessonStudent;
        this.lessonSubject = lessonSubject;
        this.lessonPlace = lessonPlace;
        this.lessonDisplacement = lessonDisplacement;
        this.lessonValuePerHour = lessonValuePerHour;
        this.lessonDuration = lessonDuration;
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

    public Double getLessonDisplacement() {
        return lessonDisplacement;
    }

    public void setLessonDisplacement(Double lessonDisplacement) {
        this.lessonDisplacement = lessonDisplacement;
    }

    public Double getLessonValuePerHour() {
        return lessonValuePerHour;
    }

    public void setLessonValuePerHour(Double lessonValuePerHour) {
        this.lessonValuePerHour = lessonValuePerHour;
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
        lessonTotalValue = lessonValuePerHour * lessonDuration;
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
