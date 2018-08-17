package com.shgxzyjune.personalproject.Courses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shgxzyjune.personalproject.ScoreSheet.Score;
import com.shgxzyjune.personalproject.Utilities.Faculty;
import com.shgxzyjune.personalproject.student.Student;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Course_table")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    @Size(min = 2, max = 25)
    private String name;

    @Column(length = 500)
    private String description;

    @JsonIgnore
    @ManyToMany(mappedBy = "courses", cascade = CascadeType.ALL) //, fetch = FetchType.LAZY)
    private List<Student> students = new ArrayList<>(); //todo convert this to a set

//    @JsonIgnore
    @ElementCollection
    @Enumerated(value = EnumType.STRING)
    private List<Faculty> faculties;
    
    @JsonIgnore
    @OneToOne(mappedBy = "course",cascade = CascadeType.ALL)
    private Score score = new Score();

    public Course() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(Student students) {
        this.students.add(students);
    }

    public List<Faculty> getFaculties() {
        return faculties;
    }

    public void setFaculties(List<Faculty> faculties) {
        this.faculties = faculties;
    }

    public Faculty facultyFormatter(String s) {
        Faculty faculty = null;
        if(s.equalsIgnoreCase("s"))
            faculty = Faculty.SCIENCE;
        if(s.equalsIgnoreCase("a"))
            faculty =  Faculty.ARTS;
        if(s.equalsIgnoreCase("c"))
            faculty = Faculty.COMMERCIAL;

        return  faculty;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

}
