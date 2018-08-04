package com.shgxzyjune.personalproject.classroom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterRepository extends JpaRepository<Register,Integer> {

    Register findByClassroomIdAndStudentId(int classID, int studentID);

    Register findByStudentId(int studentId);
}
