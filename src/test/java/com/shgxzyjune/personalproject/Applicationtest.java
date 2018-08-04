/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shgxzyjune.personalproject;

import com.shgxzyjune.personalproject.Courses.CourseService;
import com.shgxzyjune.personalproject.ScoreSheet.ScoreService;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author akinduro-pc
 */
@SpringBootTest
public class Applicationtest {
    
     @Autowired
     CourseService courseService;
     
     @Autowired
     ScoreService scoreService;
    
    public Applicationtest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void testCourse() {
         assertNotNull(courseService.getStudentCourse(1, 1));
     }
     
     @Test
     public void classStudentScoresTest(){
         assertNotEquals(0, scoreService.getClassCourseScores(1, 1));
     }
}
