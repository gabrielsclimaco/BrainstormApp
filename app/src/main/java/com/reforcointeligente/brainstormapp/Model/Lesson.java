package com.reforcointeligente.brainstormapp.Model;

import java.sql.Time;
import java.util.Date;
import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

@ParseClassName("Lesson")
public class Lesson extends ParseObject {
    //data, horário, professor, aluno, disciplina, local, valor da hora/aula, horas de aula,
    //valor pago ao professor (valor da hora/aula * horas de aula),
    //lucro da aula (valor total da aula - valor pago ao professor)

    public String getLessonDate() {
        return getString(getLessonDateTitle());
    }

    public void setLessonDate(String lessonDate) {
        put(getLessonDateTitle(), lessonDate);
    }

    public String getLessonTime() {
        return getString(getLessonTimeTitle());
    }

    public void setLessonTime(String lessonTime) {
        put(getLessonTimeTitle(), lessonTime);
    }

    public String getLessonTeacher() {
        return getString(getLessonTeacherTitle());
    }

    public void setLessonTeacher(String teacherName) {
        put(getLessonTeacherTitle(), teacherName);
    }

    public String getLessonStudent() {
        return getString(getLessonStudentTitle());
    }

    public void setLessonStudent(String studentName) {
        put(getLessonStudentTitle(), studentName);
    }

    public String getLessonSubject() {
        return getString(getLessonSubjectTitle());
    }

    public void setLessonSubject(String subject){
        put(getLessonSubjectTitle(), subject);
    }

    public String getLessonPlace() {
        return getString(getLessonPlaceTitle());
    }

    public void setLessonPlace(String place) {
        put(getLessonPlaceTitle(), place);
    }

    public Double getLessonPricePerHour() {
        return getDouble(getLessonPricePerHourTitle());
    }

    public void setLessonPricePerHour(String pricePerHour) {
        put(getLessonPricePerHourTitle(), pricePerHour);
    }

    public Double getLessonDuration() {
        return getDouble(getLessonDurationTitle());
    }

    public void setLessonDuration(Double Duration){
        put(getLessonDurationTitle(), Duration);
    }

    public Double getLessonTotalValue() {
        return getDouble(getLessonTotalValueTitle());
    }

    public void getLessonTotalValues(Double totalValue){
        put(getLessonTotalValueTitle(), totalValue);
    }

    public Double getLessonTeacherValue() {
        return getDouble(getLessonTeacherValueTitle());
    }

    public void setLessonTeacherValue(Double teacherValue) {
        put(getLessonTeacherValueTitle(), teacherValue);
    }

    public Double getLessonProfit() {
        return getDouble(getLessonProfitTitle());
    }

    public void setLessonProfit(Double profit) {
        put(getLessonProfitTitle(), profit);
    }

    public static ParseQuery<Lesson> getQuery() {
        return ParseQuery.getQuery(Lesson.class);
    }

    private static String getLessonDateTitle () {
        return "data_aula";
    }

    private static String getLessonTimeTitle () {
        return "horario_aula";
    }

    private static String getLessonTeacherTitle () {
        return "professor_aula";
    }

    private static String getLessonStudentTitle () {
        return "aluno_aula";
    }

    private static String getLessonSubjectTitle () {
        return "disciplina_aula";
    }

    private static String getLessonPlaceTitle () {
        return "local_aula";
    }

    private static String getLessonPricePerHourTitle () {
        return "valor_hora_aula";
    }

    private static String getLessonDurationTitle () {
        return "duracao_aula";
    }

    private static String getLessonTotalValueTitle () {
        return "valor_total_aula";
    }

    private static String getLessonTeacherValueTitle () {
        return "valor_professor_aula";
    }

    private static String getLessonProfitTitle () {
        return "lucro_aula";
    }

}
