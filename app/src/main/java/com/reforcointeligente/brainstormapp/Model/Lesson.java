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

    private Date getLessonDate() {
        return getLessonDate();
    }

    private void setLessonDate(Date lessonDate) {
        put(getLessonDate(), lessonDate);
    }

    private Time getLessonTime() {
        return getLessonTime();
    }

    private void setLessonTime(Time lessonTime) {
        put(getLessonTime(), lessonTime);
    }

    public static ParseQuery<Lesson> getQuery() {
        return ParseQuery.getQuery(Lesson.class);
    }


}
