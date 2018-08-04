package com.shgxzyjune.personalproject.Courses;


import com.shgxzyjune.personalproject.ScoreSheet.Score;
import com.shgxzyjune.personalproject.classroom.ClassRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.shgxzyjune.personalproject.student.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private ClassRoomService classRoomService;

/////////////////////flagged
    @RequestMapping("/students/{id}/courses")
    public List<Course> getAllCourses(@PathVariable int id){
        return courseService.getCourses(id);
    }

    @RequestMapping("/courses/{id}") //gets a course whether the student offers it or not
    public Course getCourse(@PathVariable int id){
        return courseService.getCourse(id);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/courses") //this method is working
    public void addCourse(@RequestBody Course course){

        courseService.addCourse(course);

    }
    ///////////un-flagged
    @RequestMapping(method = RequestMethod.PUT, value = "classes/{classId}/students/{studentId}/courses/{id}") //todo needs editing
    public void updateCourse(@RequestBody Course course, @PathVariable int studentId, @PathVariable int id,@PathVariable int classId){
        course.setStudents(new Student(studentId,"","",classId)); //todo check if this works
        courseService.updateCourse(course);
    }

////flagged
    @RequestMapping(method = RequestMethod.DELETE, value = "/students/{studentId}/courses/{id}")
    public void deleteCourse(@PathVariable int id){
        courseService.deleteCourse(id);
    }

    ////flagged
    @RequestMapping(method = RequestMethod.GET, value = "/courses/{courseId}/students")
    public List<Student> getAllStudents(@PathVariable int courseId){
        return courseService.getAllStudents(courseId);
    }

    ////flagged
    @RequestMapping("/courses/{courseId}/students/{id}")
    public Student getStudent(@PathVariable int courseId, @PathVariable int id){
        return courseService.getStudent(courseId,id);
    }

    @GetMapping(value = "/courses/{courseId}/addFaculty/{faculty}")
    public void addFaculty(@PathVariable int courseId,@PathVariable String faculty) {
        courseService.addFaculty(courseId,faculty);
    }
    
    @GetMapping("/courses/{courseId}/students/{studentId}/get")
    public Course getStudentCourse(@PathVariable int courseId,@PathVariable int studentId){
        return courseService.getStudentCourse(studentId, courseId);
    }
    //todo test purpose
    @GetMapping("/{b}/{a}")
    public Score getScore(@PathVariable int b, @PathVariable int a){
        return courseService.getStudentCourseScore(a, b);
    }
    //todo test purpose
    @GetMapping("/courses")
    public List<Course> allCourses(){
        return courseService.findAllCourses();
    }

}
