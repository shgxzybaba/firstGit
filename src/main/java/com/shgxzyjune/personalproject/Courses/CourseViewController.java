package com.shgxzyjune.personalproject.Courses;

import com.shgxzyjune.personalproject.Utilities.Faculty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CourseViewController {

    @Autowired
    CourseService courseService;

    private final List<Faculty> faculties = new ArrayList<>();



    @GetMapping("/courseForm")
    public String getCourseForm(Model model){
        faculties.add(Faculty.COMMERCIAL);
        faculties.add(Faculty.ARTS);
        faculties.add(Faculty.SCIENCE);


        FacultyWrapper wrapper = new FacultyWrapper();
        wrapper.setFaculties(faculties);
//        model.addAttribute("givenMethod","get");
        model.addAttribute("givenName","");
        model.addAttribute("givenDescription","");
        model.addAttribute("wrapper",wrapper);
        model.addAttribute("givenAction","/courseForm/submit");
        model.addAttribute("faculty","faculty");

        return "CourseForm";
    }

    @PostMapping("/courseForm/submit")
    public String postCourse(@RequestParam(value = "givenName") String Name, @RequestParam(value = "givenDescription") String courseDescription, @ModelAttribute FacultyWrapper wrapper, Model model) {

        Course course = new Course();  //= new Course();
        course.setName(Name);
        course.setDescription(courseDescription);
        course.setFaculties(wrapper.getFaculties());
        courseService.addCourse(course);
        model.addAttribute("wrapper", wrapper);
        return "CourseForm";
    }


}
