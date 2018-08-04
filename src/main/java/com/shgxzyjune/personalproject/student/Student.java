package com.shgxzyjune.personalproject.student;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shgxzyjune.personalproject.Courses.Course;
import com.shgxzyjune.personalproject.classroom.Classroom;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Student {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotEmpty
    @Column(unique = true, nullable = false) //added this removed @NotNull
    private String name;
    private String  sex;

    @DateTimeFormat //added so as to persist as date time format
    private LocalDate birthDay;
    private Integer age;


    @ManyToOne(cascade = CascadeType.ALL) //(fetch = FetchType.LAZY) //todo why cant i use lazy initializer
    @JoinColumn(name = "classroom", nullable = false)
    private Classroom classroom;


    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL) //(fetch = FetchType.LAZY) //(mappedBy = "students")
    private  Set<Course> courses = new HashSet<>();


    public Student() {
    }
//
    public Student(int id, String name, String sex, int classroomId) {
        setId(id);
        setName(name);
        setSex(sex);
        //setId(id);
        setClassroom(new Classroom(classroomId));
    }

    public Student(int studentId) {
        setId(studentId);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourse(Set<Course> course) {
        this.courses = course;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Integer YY, Integer MM, Integer DD) {
        this.birthDay = LocalDate.of(YY,MM,DD);
    }

    public Integer getAge() {
        return age;
    }

    public void setAge() {
        LocalDate today = LocalDate.now(ZoneId.systemDefault());
        Long theAge = getBirthDay().until(today, ChronoUnit.YEARS);
        this.age = Math.toIntExact(theAge);

    }
}
