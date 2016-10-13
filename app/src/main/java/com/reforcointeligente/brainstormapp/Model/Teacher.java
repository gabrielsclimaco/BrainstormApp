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
        return getString(getTeacherName());
    }

    //Set teacher's name
    private void setTeacherName(String teacherName) {
        put(getTeacherName(), teacherName);
    }

    //Return teacher's address
    private String getTeacherAddress() {
        return getString(getTeacherAddress());
    }

    //Set teacher's address
    private void setTeacherAddress(String teacherAddress) {
        put(getTeacherAddress(), teacherAddress);
    }

    //Return neighborhood
    private String getTeacherNeighborhood() {
        return getString(getTeacherNeighborhood());
    }

    //Set neighborhood
    private void setTeacherNeighborhood(String teacherNeighborhood) {
        put(getTeacherNeighborhood(), teacherNeighborhood);
    }

    //Return city
    private String getTeacherCity() {
        return getString(getTeacherCity());
    }

    //Set city
    private void setTeacherCity(String teacherCity) {
        put(getTeacherCity(), teacherCity);
    }

    //Return teacher's residencial phone
    public String getTeacherPhone(){
        return getString(getTeacherPhone());
    }

    //Set teacher's residencial phone
    public void setTeacherPhone(String teacherPhone){
        put(getTeacherPhone(), teacherPhone);
    }

    //Return teacher's cellphone
    public String getTeacherCellphone(){
        return getString(getTeacherCellphone());
    }

    //Set teacher's cellphone
    public void setTeacherCellphone(String teacherCellphone){
        put(getTeacherCellphone(), teacherCellphone);
    }

    public static ParseQuery<Teacher> getQuery() {
        return ParseQuery.getQuery(Teacher.class);
    }

}


