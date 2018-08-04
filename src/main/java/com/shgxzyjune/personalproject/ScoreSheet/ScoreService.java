package com.shgxzyjune.personalproject.ScoreSheet;

import com.shgxzyjune.personalproject.Courses.Course;
import com.shgxzyjune.personalproject.Courses.CourseService;
import com.shgxzyjune.personalproject.classroom.ClassRoomService;
import com.shgxzyjune.personalproject.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shgxzyjune.personalproject.student.Student;

import java.util.List;
import static java.util.stream.Collectors.toList;

@Service
public class ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;
    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private ClassRoomService classRoomService;

    public void setScoreValue(Score score, int courseId, int studentId) { //todo verify this method
        score.setGrade(score.getValue());
//       
////        courseService.getCourse(courseId).getScore().add(score);
//        
////        courseService.updateCourse(courseService.getCourse(courseId));//culprit code
//        scoreRepository.save(score);
        Course course = courseService.getStudentCourse(courseId, studentId);
        score.setCourse(courseService.getCourse(courseId));
        //course.setScore(score); 
        score.setStudent(studentService.getStudent(studentId));
        scoreRepository.save(score);

    }

    public List<Score> getCourseScores(int courseId) {
        return scoreRepository.findByCourseId(courseId); //.forEach(scores::add);
    }

    public List<Score> getStudentScores( int studentId) {

        return scoreRepository.findByStudentId(studentId);
        //return scores;

    }

    public Score getSingleScore(int studentId, int courseId) {
        return scoreRepository.findByStudentIdAndCourseId(studentId,courseId);
    }
    
    public void SetAllDefaultScores(int classId,int courseId){
     List<Score> scores = getClassCourseScores(classId, courseId);
     scores.forEach(score->score.setValue(0));
     scores.forEach(score-> setScoreValue(score, courseId, score.getStudent().getId()));
    }
     public List<Score> getClassCourseScores(int classId, int courseId){
         List<Student> students = classRoomService.getClassStudents(classId);
         List<Score> scores = students.stream().map(student -> getSingleScore(student.getId(), courseId)).collect(toList());
         return scores;
         
     }
}
