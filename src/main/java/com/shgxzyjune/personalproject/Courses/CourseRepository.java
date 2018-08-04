package com.shgxzyjune.personalproject.Courses;

import com.shgxzyjune.personalproject.Utilities.Faculty;
import com.shgxzyjune.personalproject.student.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseRepository extends CrudRepository<Course, Integer> {

    List<Course> findByStudentsId(int studentId);
    boolean existsById(int courseId);
    List<Course> findByFaculties(Faculty faculty);

    Course findByIdAndStudentsId(int courseId, int studentId);
}
