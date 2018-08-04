package com.shgxzyjune.personalproject.classroom;

import com.shgxzyjune.personalproject.Utilities.Faculty;
import com.shgxzyjune.personalproject.student.Student;
import com.shgxzyjune.personalproject.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClassRoomService {

    @Autowired
    ClassRoomRepository classRoomRepository;
    @Autowired
    ClassRoomStudentRepository classRoomStudentRepository;

    @Autowired
    RegisterRepository registerRepository;

    @Autowired
    StudentService studentService;


    public void addClassRoom(Classroom tcr, String s) {

        tcr.setFaculty(s);

        classRoomRepository.save(tcr);
        //todo test
    }

    public Classroom getClassRoom(int classroomName) {
        return classRoomRepository.findById(classroomName).orElse(null);
    }



    public Student getClassStudent(int c, int studentId) {
        List<Student> students = getClassStudents(c);
        return students.stream().filter(student1 -> student1.getId() == (studentId)).findFirst().get(); //returns a student object
    }

    public List<Student> getClassStudents(int c) {
//        Classroom classRoom = getClassRoom(c);
        List<Student> students = classRoomStudentRepository.findByClassroomId(c);
        return students;
    }

    public List<Classroom> getClasses() {
        List<Classroom> classrooms = new ArrayList<>();
        classRoomRepository.findAll().forEach(classrooms::add);
        return classrooms;
    }

    public List<Classroom> getClassesByFaculty(String f) {
        Faculty faculty = null;
        switch (f) {
            case "s":
                faculty = Faculty.SCIENCE;
                break;
            case "a":
                faculty = Faculty.ARTS;
                break;
            case "c":
                faculty = Faculty.COMMERCIAL;
                break;
        }
        return classRoomRepository.findByFaculty(faculty);
    }

    //a method that marks a student as either present or absent

    public void takeRollCall(int studentId) {
        Register register = registerRepository.findByStudentId(studentId);
//        List<Student> students = register.getStudent();
        register.getStatuses().add(new Status()); //the student is present
        registerRepository.save(register);

    }

    public void addRegister(int studentID,int classID) {
        Register register = new Register();
        register.setStudent(studentService.getStudent(studentID));
        register.setClassroom(getClassRoom(classID));
        registerRepository.save(register);
    }
}
