package com.shgxzyjune.personalproject.ScoreSheet;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepository extends CrudRepository<Score,Double> {


    List<Score> findByStudentId(int studentId);
    List<Score> findByCourseId(int courseId);
    Score findByStudentIdAndCourseId(int studentId,int courseId);
}
