package com.shgxzyjune.personalproject.ScoreSheet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    /*
    **get a student score for a given subject
    * get all a student's scores
    * get all scores for a given subject(course)
    *
    * add a student's score for a given subject (Post)
    * do all url mappings
    *
     */

    @RequestMapping(method = RequestMethod.GET, value = "/students/{studentId}/courses/{courseId}/scores")
    public Score getStudentSubjectScore(@PathVariable int studentId,@PathVariable int courseId){
        return scoreService.getSingleScore(studentId,courseId); //todo debug this method
    }

    @RequestMapping(method = RequestMethod.GET,value = "/students/{studentId}/scores")
    public List<Score> getAllStudentScores(@PathVariable int studentId){
        return scoreService.getStudentScores(studentId);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/courses/{courseId}/scores")
    public List<Score> getAllCourseScores(@PathVariable int courseId) {

        return  scoreService.getCourseScores(courseId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/students/{studentId}/courses/{courseId}/addscore")
    public void addStudentSubjectScore(@PathVariable int studentId, @PathVariable int courseId, @RequestBody Score score) {
        scoreService.setScoreValue(score,courseId,studentId);
    }
    
    //use this for testing this method
    @RequestMapping("/score/{classId}/{courseId}")
    public List<Score> getClassSubjectScores(@PathVariable int classId, @PathVariable int courseId){
        return scoreService.getClassCourseScores(classId, courseId);
    }
    //another test method
    @GetMapping("/s/{classId}/{courseId}")
    public void setDefaultScores(@PathVariable int classId, @PathVariable int courseId){
        scoreService.SetAllDefaultScores(classId, courseId);
    }

}
