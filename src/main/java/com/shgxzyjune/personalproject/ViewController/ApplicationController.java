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
        return  "home";
    }
}
