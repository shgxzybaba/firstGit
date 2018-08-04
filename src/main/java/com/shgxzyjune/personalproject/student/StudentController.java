package com.shgxzyjune.personalproject.student;

import com.shgxzyjune.personalproject.Courses.Course;
import com.shgxzyjune.personalproject.ScoreSheet.ScoreService;
import com.shgxzyjune.personalproject.classroom.ClassRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private ClassRoomService classRoomService;
    
    @Autowired
    private ScoreService scoreService;



    //////flagged as working
    @RequestMapping("classes/{classId}/students")
    public List<Student> getAllStudents(@PathVariable int classId){
        return classRoomService.getClassStudents(classId);
    }
/////flagged as working
    @RequestMapping("classes/{classId}/students/{id}")
    public Student getStudent(@PathVariable int id,@PathVariable int classId){
        return  classRoomService.getClassStudent(classId,id);
    }
/////flagged as working
    @RequestMapping(method = RequestMethod.POST, value = "/classes/{classId}/students")
    public void createStudent(@RequestBody Student student, @PathVariable int classId){
        //Classroom classRoom = classRoomService.getClassRoom(classId);

        studentService.addStudent(student, classId);

    }

    //todo edit this later
    @RequestMapping(method = RequestMethod.PUT, value = "/students/{id}")
    public void updateStudent(@RequestBody Student student, @PathVariable int id){
        studentService.updateStudent(id, student);
    }
/////not yet flagged
    @RequestMapping(method = RequestMethod.DELETE, value = "classes/{classId}/students/{id}")
    public void deleteStudent(@PathVariable int id, @RequestBody int classId){
        Student student = classRoomService.getClassStudent(classId,id);
        studentService.deleteStudent(student.getId());
    }
    //flagged
    @RequestMapping(method = RequestMethod.GET, value = "/students/{id}/courses")
    public Set<Course> getCourses(@PathVariable int id){
        return studentService.getStudentCourses(id);
    }

    //flagged
    @GetMapping(value = "/students/{studentId}/courses/{courseId}/addcourse") //todo test
    public void addCourseToStudent(@PathVariable int studentId,@PathVariable int courseId) {
        studentService.addCourse(studentId,courseId);
    }

    @GetMapping(value = "/students/{id}/populate")
    public void populateCourses(@PathVariable int id) {
        studentService.populateMandatoryCourses(id);
    }
    
//    @GetMapping("/students/{id}/defaultScores")
//    public void addDefaultScores(@PathVariable int studentId){
//        scoreService.SetAllDefaultScores(studentId);
//    }
}
