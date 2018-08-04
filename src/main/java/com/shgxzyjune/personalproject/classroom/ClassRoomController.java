package com.shgxzyjune.personalproject.classroom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class ClassRoomController {

    @Autowired
    ClassRoomService classRoomService;



    @RequestMapping("/classes")
    public List<Classroom> getAllClassRooms() {
        //stuff
        return classRoomService.getClasses();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/classes/{c}")
    public Classroom classRoom(@PathVariable int c) {
        return classRoomService.getClassRoom(c);
    }


    //flagged
    @RequestMapping(method = RequestMethod.POST, value = "/class/{faculty}/save")
    public void addClassroom(@RequestBody Classroom c, @PathVariable String faculty) {
        classRoomService.addClassRoom(c, faculty);
        //stuff
    }

    @GetMapping(value = "/classes/faculty/{faculty}")
    public List<Classroom> getClassesByFaculty(@PathVariable String faculty) {
        return classRoomService.getClassesByFaculty(faculty);
    }

    @GetMapping("/classes/{classId}/students/{studentID}/addRegister")
    public void addToRegister(@PathVariable int classId, @PathVariable int studentID) {
        classRoomService.addRegister(studentID,classId);
    }

    @GetMapping("/rollcall/{studentID}")
    public void takeRollCall(@PathVariable int studentID) {
        classRoomService.takeRollCall(studentID);
    }
   
}
