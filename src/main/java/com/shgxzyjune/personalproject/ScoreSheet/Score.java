package com.shgxzyjune.personalproject.ScoreSheet;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shgxzyjune.personalproject.Courses.Course;
import com.shgxzyjune.personalproject.student.Student;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int scoreId = 1; //just for persistence purpose
    
    
    @NotNull
    
    @JsonIgnore
    private  int Value = 0;
    private String grade;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Student student;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Course course;

    public Score() {
        setValue(0);
        setGrade(0);
    }

    public Score(int value) {
        setValue(value);
        setGrade(value);
    }


    public int getScoreId() {
        return scoreId;
    }

    public void setScoreId(int scoreId) {
        this.scoreId = scoreId;
    }

    public int getValue() {
        return Value;
    }

    public void setValue(int value) {
        Value = value;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(int value) {
        //this.grade = grade;
        if(value <= 40)
            this.grade = "F";
        else if(value <= 50)
                this.grade = "D";
        else if (value <= 60)
            this.grade = "C";
        else if(value <= 69)
            this.grade = "B";
        else this.grade = "A";

    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
