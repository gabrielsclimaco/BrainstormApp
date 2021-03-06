package com.reforcointeligente.brainstormapp.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Lesson implements Serializable {
    private String lessonDate;
    private String lessonTime;
    private String lessonTeacher;
    private String lessonStudent;
    private ArrayList<String> lessonSubjects;
    private String lessonPlace;
    private Double lessonDisplacement;
    private Double lessonValuePerHour;
    private Double lessonDuration;
    private Double lessonTotalValue;
    private Double lessonProfit;

    public Lesson () {}

    public Lesson(String lessonDate, String lessonTime, String lessonTeacher, String lessonStudent,
                  ArrayList<String> lessonSubject, String lessonPlace, Double lessonDisplacement,
                  Double lessonValuePerHour, Double lessonDuration, Double lessonProfit) {
        this.lessonDate = lessonDate;
        this.lessonTime = lessonTime;
        this.lessonTeacher = lessonTeacher;
        this.lessonStudent = lessonStudent;
        this.lessonSubjects = lessonSubject;
        this.lessonPlace = lessonPlace;
        this.lessonDisplacement = lessonDisplacement;
        this.lessonValuePerHour = lessonValuePerHour;
        this.lessonDuration = lessonDuration;
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

    public String getLessonTeacher() {
        return lessonTeacher;
    }

    public void setLessonTeacher(String lessonTeacher) {
        this.lessonTeacher = lessonTeacher;
    }

    public String getLessonStudent() {
        return lessonStudent;
    }

    public void setLessonStudent(String lessonStudent) {
        this.lessonStudent = lessonStudent;
    }

    public ArrayList<String> getLessonSubject() {
        return lessonSubjects;
    }

    public void setLessonSubject(ArrayList<String> lessonSubjects) {
        this.lessonSubjects = lessonSubjects;
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
        lessonTotalValue = lessonValuePerHour * lessonDuration + lessonDisplacement;
    }

    public Double getLessonProfit() {
        return lessonProfit;
    }

    public void setLessonProfit(Double profit) {
        this.lessonProfit = profit;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();

        result.put("lessonDate", lessonDate);
        result.put("lessonTime", lessonTime);
        result.put("lessonTeacher", lessonTeacher);
        result.put("lessonStudent", lessonStudent);
        result.put("lessonSubjects", lessonSubjects);
        result.put("lessonPlace", lessonPlace);
        result.put("lessonDisplacement", lessonDisplacement);
        result.put("lessonValuePerHour", lessonValuePerHour);
        result.put("lessonDuration", lessonDuration);
        result.put("lessonTotalValue", lessonTotalValue);
        result.put("lessonProfit", lessonProfit);

        return result;
    }
}
