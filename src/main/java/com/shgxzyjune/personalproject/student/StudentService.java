package com.shgxzyjune.personalproject.student;

import com.shgxzyjune.personalproject.Courses.Course;
import com.shgxzyjune.personalproject.Courses.CourseService;
import com.shgxzyjune.personalproject.Exceptions.ResourseNotFound;
import com.shgxzyjune.personalproject.ScoreSheet.ScoreService;
import com.shgxzyjune.personalproject.Utilities.Faculty;
import com.shgxzyjune.personalproject.classroom.ClassRoomService;
import com.shgxzyjune.personalproject.classroom.Classroom;
import com.shgxzyjune.personalproject.classroom.Register;
import com.shgxzyjune.personalproject.classroom.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutionException;

@Service
public class StudentService {


    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ClassRoomService classRoomService;

    @Autowired
    private CourseService courseService;

    @Autowired
    RegisterRepository registerRepository;
    
    @Autowired
    ScoreService scoreService;

    public List<Student> getStudents() {

        List<Student> students = new ArrayList<>();
        for (Student student : studentRepository.findAll()) {
            students.add(student);
        }
        return students;
    }

    public Student getStudent(int id){
        Student student;
        student = studentRepository.findById(id).orElseThrow(() -> new ResourseNotFound("Class","student",id));
        return  student;
    }

    public void addStudent(Student student, int classId) {

        student.setAge();
        Register register = new Register();
        registerRepository.save(register);

        Classroom classroom = classRoomService.getClassRoom(classId);
        student.setClassroom(classroom);
        student.setRegister(register);

// todo throw an exception if an already existing student name is entered
        studentRepository.save(student);
    }

    public void updateStudent(int id, Student student) {
        studentRepository.save(student);
    }

    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }

    public void addCourse(int id, int courseId){
        Course course = courseService.getCourse(courseId);
        Student st = studentRepository.findById(id).orElse(null);
        st.getCourses().add(course);
        studentRepository.save(st);
    }

    public Set<Course> getStudentCourses(int studentId){
        return getStudent(studentId).getCourses();
    }

    public Course getCourse(int studentId, int courseId){
        return  getStudent(studentId).getCourses().stream().filter(course -> course.getId()==(courseId)).findFirst().get();
    }

    public Course getCourse(Student student, int courseId){
       return getCourse(student.getId(),courseId);
    }

    public  void populateMandatoryCourses(int studentId){
        Student student = getStudent(studentId);
        int classId = student.getClassroom().getId();
        Faculty faculty = classRoomService.getClassRoom(classId).getFaculty();
        List<Course> courseList = courseService.getFacultyCourses(faculty);
        student.getCourses().addAll(courseList);
        studentRepository.save(student);
    }

    public List<Student> getSortedClassStudent(int classId) { //todo test this method
        return studentRepository.findByClassroomIdOrderByNameAsc(classId);
    }

    public void populateMandatoryCourses(int classID, Student student) {
        Faculty faculty = classRoomService.getClassRoom(classID).getFaculty();
        List<Course> courseList = courseService.getFacultyCourses(faculty);
        student.getCourses().addAll(courseList);
    }
}
