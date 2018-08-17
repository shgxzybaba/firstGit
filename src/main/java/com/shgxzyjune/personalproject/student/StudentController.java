package com.shgxzyjune.personalproject.student;

import com.shgxzyjune.personalproject.Courses.Course;
import com.shgxzyjune.personalproject.ScoreSheet.ScoreService;
import com.shgxzyjune.personalproject.classroom.ClassRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@CrossOrigin
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private ClassRoomService classRoomService;
    
    @Autowired
    private ScoreService scoreService;

    @RequestMapping("classes/{classId}/students")
    public List<Student> getAllStudents(@PathVariable int classId){
        return classRoomService.getClassStudents(classId);
    }

    @RequestMapping("classes/{classId}/students/{id}")
    public Student getStudent(@PathVariable int id,@PathVariable int classId){
        return  classRoomService.getClassStudent(classId,id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/classes/{classId}/students")
    public void createStudent(@RequestBody Student student, @PathVariable int classId){

        studentService.populateMandatoryCourses(classId, student);
        studentService.addStudent(student, classId);
    }

    //todo edit this later
    @RequestMapping(method = RequestMethod.PUT, value = "/students/{id}")
    public void updateStudent(@RequestBody Student student, @PathVariable int id){
        studentService.updateStudent(id, student);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/students/{id}")
    public void deleteStudent(@PathVariable int id){
        studentService.deleteStudent(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/students/{id}/courses")
    public Set<Course> getCourses(@PathVariable int id){
        return studentService.getStudentCourses(id);
    }

    @GetMapping(value = "/students/{studentId}/courses/{courseId}/addcourse") //todo test
    public void addCourseToStudent(@PathVariable int studentId,@PathVariable int courseId) {
        studentService.addCourse(studentId,courseId);
    }

}
