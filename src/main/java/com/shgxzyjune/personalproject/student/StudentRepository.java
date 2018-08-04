package com.shgxzyjune.personalproject.student;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student,Integer> {
    //Todo crud method statements
    //List<Student> findByCourseId(int id);
    boolean existsByName(String name);

    List<Student> findByClassroomIdOrderByNameAsc(int classId);
}
