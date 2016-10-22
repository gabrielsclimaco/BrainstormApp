package com.reforcointeligente.brainstormapp.Model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

@ParseClassName("Teacher")
public class Teacher extends ParseObject {
    //nome, endereço (end, bairro, cidade), telefone (residencial e celular), email,
    //curso de graduação, matérias para lecionar, possui carro, preço da hora/aula

    //Return teacher's name
    private String getTeacherName() {
        return getString(getTeacherNameTitle());
    }

    //Set teacher's name
    private void setTeacherName(String teacherName) {
        put(getTeacherNameTitle(), teacherName);
    }

    //Return teacher's address
    private String getTeacherAddress() {
        return getString(getTeacherAddressTitle());
    }

    //Set teacher's address
    private void setTeacherAddress(String teacherAddress) {
        put(getTeacherAddressTitle(), teacherAddress);
    }

    //Return city
    private String getTeacherCity() {
        return getString(getTeacherCityTitle());
    }

    //Set city
    private void setTeacherCity(String teacherCity) {
        put(getTeacherCityTitle(), teacherCity);
    }

    //Return teacher's residencial phone
    public String getTeacherPhone(){
        return getString(getTeacherPhoneTitle());
    }

    //Set teacher's residencial phone
    public void setTeacherPhone(String teacherPhone){
        put(getTeacherPhoneTitle(), teacherPhone);
    }

    //Return teacher's cellphone
    public String getTeacherCellphone(){
        return getString(getTeacherCellphoneTitle());
    }

    //Set teacher's cellphone
    public void setTeacherCellphone(String teacherCellphone){
        put(getTeacherCellphoneTitle(), teacherCellphone);
    }

    public String getTeacherCourse() {
        return getString(getTeacherCourseTitle());
    }

    public void setTeacherCourse(String course) {
        put(getTeacherCourseTitle(), course);
    }

    public Boolean getTeacherCar() {
        return getBoolean(getTeacherCarTitle());
    }

    public void setTeacherCar(Boolean car){
        put(getTeacherCarTitle(), car);
    }

    public Double getTeacherPricePerHour() {
        return getDouble(getTeacherPricePerHourTitle());
    }

    public void setTeacherPricePerHour(Double pricePerHour) {
        put(getTeacherPricePerHourTitle(), pricePerHour);
    }

    // NOT FINISHED
    public String getTeacherSubject() {
        return getString(getTeacherSubjectTitle());
    }

    public void setTeacherSubject(String subject) {
        put(getTeacherSubjectTitle(), subject);
    }

    public static ParseQuery<Teacher> getQuery() {
        return ParseQuery.getQuery(Teacher.class);
    }


    private static String getTeacherNameTitle() {
        return "nome_professor";
    }

    private static String getTeacherAddressTitle() {
        return "endereco_professor";
    }

    private static String getTeacherCityTitle() {
        return "cidade_professor";
    }

    private static String getTeacherPhoneTitle() {
        return "telefone_professor";
    }

    private static String getTeacherCellphoneTitle(){
        return "celular_professor";
    }

    private static String getTeacherCourseTitle() {
        return "curso_professor";
    }

    private static String getTeacherSubjectTitle() {
        return "materias_professor";
    }

    private static String getTeacherCarTitle() {
        return "possui_carro_professor";
    }

    private static String getTeacherPricePerHourTitle() {
        return "preco_hora_professor";
    }
}


