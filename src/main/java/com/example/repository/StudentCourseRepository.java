package com.example.repository;

import com.example.model.StudentCourse;
import com.example.model.StudentCourseId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentCourseRepository
        extends JpaRepository<StudentCourse, StudentCourseId> {

    List<StudentCourse> findByStudentId(Integer studentId);

    List<StudentCourse> findByCourseId(Integer courseId);
}
