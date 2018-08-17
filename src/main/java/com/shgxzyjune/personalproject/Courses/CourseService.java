package com.shgxzyjune.personalproject.Courses;

import com.shgxzyjune.personalproject.ScoreSheet.Score;
import com.shgxzyjune.personalproject.Utilities.Faculty;
import com.shgxzyjune.personalproject.student.Student;
import com.shgxzyjune.personalproject.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    StudentService studentService;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseStudentRepository courseStudentRepository;


    public CourseService() {
    }

    public List<Course> getCourses(int studentId) {
        return courseRepository.findByStudentsId(studentId);
    }

    public Course getCourse(int id) {
        Course course = courseRepository.findById(id).orElse(null);
        return course;
    }

    public void addCourse(Course course) {
        courseRepository.save(course);
    }

    public void updateCourse(Course course) {
        courseRepository.save(course); //todo test this method
    }

    public void deleteCourse(int id) {
        courseRepository.deleteById(id);
    }

    public void addStudent(Student student, int courseId) {
        Course course = courseRepository.findById(courseId).orElse(null);
        course.getStudents().add(student);
        courseRepository.save(course);
    }

    public Student getStudent(int courseId, int studentId) {
        List<Student> students = getAllStudents(courseId);
        return students.stream().filter(student1 -> student1.getId() == (studentId)).findFirst().get();
    }

    public List<Student> getAllStudents(int id) {
        return courseStudentRepository.findByCoursesId(id);
    }

    public List<Course> getFacultyCourses(Faculty faculty) {
        return courseRepository.findByFaculties(faculty);
    }

    public void addFaculty(int courseId,String faculty) {
        Course course = getCourse(courseId);
        course.getFaculties().add(course.facultyFormatter(faculty));
        courseRepository.save(course);
    }
    
    public Course getStudentCourse(int studentId, int courseId){
        return courseRepository.findByIdAndStudentsId(courseId,studentId);
    }
    
    public Score getStudentCourseScore(int studentId, int courseId){
        Course course = getStudentCourse(studentId, courseId);
        return course.getScore();
    }

    public List<Course> findAllCourses(){
        return (List<Course>) courseRepository.findAll();
    }
        
}
