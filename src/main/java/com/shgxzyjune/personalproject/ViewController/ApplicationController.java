package com.shgxzyjune.personalproject.ViewController;

import com.shgxzyjune.personalproject.Courses.Course;
import com.shgxzyjune.personalproject.Courses.CourseService;
import com.shgxzyjune.personalproject.ScoreSheet.Score;
import com.shgxzyjune.personalproject.ScoreSheet.ScoreService;
import com.shgxzyjune.personalproject.classroom.ClassRoomService;
import com.shgxzyjune.personalproject.classroom.Classroom;
import com.shgxzyjune.personalproject.student.Student;
import com.shgxzyjune.personalproject.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Controller
public class ApplicationController {

    @Autowired
    ClassRoomService classRoomService;

    @Autowired
    StudentService studentService;
    
    @Autowired
    CourseService courseService;
    
    @Autowired
    ScoreService scoreService;

    Random random = new Random(); //for test purposes

    public ApplicationController() {

    }

    @RequestMapping("/")
    public String home(Model model) {

        List<Classroom> classrooms = classRoomService.getClasses();
        model.addAttribute("title","Hello, World!");

        model.addAttribute("classrooms",classrooms);
        return  "home";
    }
    @RequestMapping(method = RequestMethod.POST, value = "/StudentForm")
    public String createStudent(@RequestParam String Name, @RequestParam Integer classId, @RequestParam String Sex){
        //Classroom classRoom = classRoomService.getClassRoom(classId);
        Student student = new Student();
        //student.setId(random.nextInt(34)+15);
        student.setName(Name);
        student.setSex(Sex);
        studentService.populateMandatoryCourses(classId,student);


        studentService.addStudent(student, classId);

        return "StudentForm";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/StudentForm")
    public String enterStudent(Model model){
        List<Classroom> classrooms = classRoomService.getClasses();
        model.addAttribute("classrooms",classrooms);
        model.addAttribute("givenAction","/StudentForm");
        model.addAttribute("givenName","");
        return "StudentForm";
    }
    
    @GetMapping("/studentdetails/{studentId}")
    public String getStudentDetails(@PathVariable int studentId, Model model) {
        List<Score> scores = scoreService.getStudentScores(studentId);
        List<Course> courses = scores.stream().map(score -> score.getCourse()).collect(toList());
        Student student = studentService.getStudent(studentId);
        model.addAttribute("scores",scores);
        model.addAttribute("courses",courses);
        model.addAttribute("student",student);
        return "StudentScores";
    }

    @GetMapping("/courseSheet/{classID}/{courseId}")
    public String enterScores(Model model, @PathVariable int classID, @PathVariable int courseId){
        List<Score> scores = scoreService.getClassCourseScores(classID,courseId);
        //List<Score> scores = new ArrayList<>();
        //model.addAttribute("scores",scores);
        model.addAttribute("givenAction","/courseSheet");
        model.addAttribute("course",courseService.getCourse(courseId));
        model.addAttribute("scores",scores);


        return "CourseSheet";
    }

    @PostMapping("/courseSheet/{classID}/{courseId}")
    public String postScores(@PathVariable int courseId, int classID, @RequestParam List<Integer> scores) {
        List<Student> students = classRoomService.getClassStudents(classID);
        //students.stream().forEach(student -> {scoreService.setScoreValue((scores.forEach(score -> new Score(score)););});


        return "redirect:/";
    }
}
