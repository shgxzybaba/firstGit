package com.shgxzyjune.personalproject.classroom;

import com.shgxzyjune.personalproject.student.Student;

import javax.persistence.*;
import java.util.List;

@Entity
public class Register {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id = 0;

//    @OneToMany(cascade = CascadeType.ALL)
//    private List<Classroom> classrooms;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Student student;

    @ElementCollection
    @Embedded
    private List<Status> statuses;

    @ManyToOne(cascade = CascadeType.ALL)
    private Classroom classroom;

    public Register() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Status> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<Status> statuses) {
        this.statuses = statuses;
    }
}
