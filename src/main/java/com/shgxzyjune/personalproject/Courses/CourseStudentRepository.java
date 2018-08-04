package com.shgxzyjune.personalproject.Courses;

import com.shgxzyjune.personalproject.student.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseStudentRepository extends CrudRepository<Student,Integer> {
    List<Student> findByCoursesId(int id);

}
