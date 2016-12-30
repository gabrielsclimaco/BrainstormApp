package com.reforcointeligente.brainstormapp.Model;


import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

@ParseClassName("Student")
public class Student extends ParseObject {

    public String getStudentName() {
        return getString(getStudentNameTitle());
    }

    public void setStudentName(String studentName) {
        put(getStudentNameTitle(), studentName);
    }

    public String getStudentAge() {
        return getString(getStudentAgeTitle());
    }

    public void setStudentAge(String studentAge) {
        put(getStudentAgeTitle(), studentAge);
    }

    public String getStudentAddress() {
        return getString(getStudentAddressTitle());
    }

    public void setStudentAddress(String studentAddress) {
        put(getStudentAddressTitle(), studentAddress);
    }

    public String getStudentCity() {
        return getString(getStudentCityTitle());
    }

    public void setStudentCity(String studentCity) {
        put(getStudentCityTitle(), studentCity);
    }

    public String getStudentSchoolYear() {
        return getString(getStudentSchoolYearTitle());
    }

    public void setStudentSchoolYear(String studentSchoolYear) {
        put(getStudentSchoolYearTitle(), studentSchoolYear);
    }

    public String getStudentSchool() {
        return getString(getStudentSchoolTitle());
    }

    public void setStudentSchool(String studentSchool) {
        put(getStudentSchoolTitle(), studentSchool);
    }

    public String getStudentParentName() {
        return getString(getStudentParentNameTitle());
    }

    public void setStudentParentName(String studentParentName) {
        put(getStudentParentNameTitle(), studentParentName);
    }

    public String getStudentParentPhone() {
        return getString(getStudentParentPhoneTitle());
    }

    public void setStudentParentPhone(String studentParentPhone) {
        put(getStudentParentPhoneTitle(), studentParentPhone);
    }

    public String getStudentParentCellphone() {
        return getString(getStudentParentCellphoneTitle());
    }

    public void setStudentParentCellphone (String studentParentCellphone) {
        put(getStudentParentCellphoneTitle(), studentParentCellphone);
    }

    public String getStudentParentEmail() {
        return getString(getStudentParentEmailTitle());
    }

    public void setStudentParentEmail(String studentParentEmail) {
        put(getStudentParentEmailTitle(), studentParentEmail);
    }

    public static ParseQuery<Student> getQuery() {
        return ParseQuery.getQuery(Student.class);
    }

    public static String getStudentNameTitle() {
        return "nome_aluno";
    }

    private static String getStudentAgeTitle() {
        return "idade_aluno";
    }

    private static String getStudentAddressTitle() {
        return "endereco_aluno";
    }

    private static String getStudentCityTitle() {
        return "cidade_aluno";
    }

    private static String getStudentSchoolYearTitle() {
        return "serie_aluno";
    }

    private static String getStudentSchoolTitle() {
        return "colegio_aluno";
    }

    private static String getStudentParentNameTitle() {
        return "nome_pai_aluno";
    }

    private static String getStudentParentPhoneTitle() {
        return "telefone_pai_aluno";
    }

    private static String getStudentParentCellphoneTitle() {
        return "celular_pai_aluno";
    }

    private static String getStudentParentEmailTitle() {
        return "email_pai_aluno";
    }
}