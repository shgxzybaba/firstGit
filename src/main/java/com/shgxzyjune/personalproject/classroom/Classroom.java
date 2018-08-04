package com.shgxzyjune.personalproject.classroom;

import com.shgxzyjune.personalproject.Utilities.Faculty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    @Size(max = 7)
    private String className;

    @Enumerated(EnumType.STRING)
    private Faculty faculty;

//    @ManyToOne(cascade = CascadeType.ALL)
//    Register register;


    public Classroom() {
    }


    public Classroom(Integer c) { //to wrap instead of (int)
        this.id = c;
    }

    public int getId() {
        return id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setId(int id) {

        this.id = id;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public void setFaculty(String s) {
        if(s.equalsIgnoreCase("s"))
            setFaculty(Faculty.SCIENCE);
        if(s.equalsIgnoreCase("a"))
            setFaculty(Faculty.ARTS);
        if(s.equalsIgnoreCase("c"))
            setFaculty(Faculty.COMMERCIAL);
    }

//    public Register getRegister() {
//        return register;
//    }
//
//    public void setRegister(Register register) {
//        this.register = register;
//    }
}
