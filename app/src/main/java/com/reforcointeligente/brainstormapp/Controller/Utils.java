package com.reforcointeligente.brainstormapp.Controller;

import com.parse.ParseException;
import com.parse.ParseQuery;
import com.reforcointeligente.brainstormapp.Model.Student;

import java.util.List;

public class Utils {
    public static List<Student> getListOfStudents(){
        /* Query UBS data from parse */
        ParseQuery<Student> queryStudents = Student.getQuery();
        queryStudents.orderByAscending(Student.getStudentNameTitle());
        List<Student> studentsList = null;

        try {
            studentsList = queryStudents.find();

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return studentsList;
    }
}