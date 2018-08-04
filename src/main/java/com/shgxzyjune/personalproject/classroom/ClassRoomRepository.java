package com.shgxzyjune.personalproject.classroom;

import com.shgxzyjune.personalproject.Utilities.Faculty;
import com.shgxzyjune.personalproject.student.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRoomRepository extends CrudRepository<Classroom,Integer>{

    List<Student> findStudentById(int c);
    List<Classroom> findByFaculty(Faculty faculty);


}
