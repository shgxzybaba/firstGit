package com.shgxzyjune.personalproject.classroom;

import com.shgxzyjune.personalproject.student.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRoomStudentRepository extends CrudRepository<Student,Integer> {
    List<Student> findByClassroomId(int id);
}
