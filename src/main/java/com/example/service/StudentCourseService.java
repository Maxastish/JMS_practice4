package com.example.service;

import com.example.model.*;
import com.example.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentCourseService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final StudentCourseRepository studentCourseRepository;
    private final AuditService auditService;

    public StudentCourseService(
            StudentRepository studentRepository,
            CourseRepository courseRepository,
            StudentCourseRepository studentCourseRepository,
            AuditService auditService) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.studentCourseRepository = studentCourseRepository;
        this.auditService = auditService;
    }

    @Transactional
    public void assignStudentToCourse(Integer studentId, Integer courseId) {
        Student student = studentRepository.findById(studentId).orElseThrow();
        Course course = courseRepository.findById(courseId).orElseThrow();

        StudentCourse sc = new StudentCourse();
        sc.setStudent(student);
        sc.setCourse(course);
        sc.setMark(0);

        studentCourseRepository.save(sc);

        Integer compositeId = studentId * 1000 + courseId;

        auditService.logChange(
                "StudentCourse",
                compositeId,
                "INSERT",
                "StudentID=" + studentId + ", CourseID=" + courseId
        );
    }


    @Transactional
    public void removeStudentFromCourse(Integer studentId, Integer courseId) {
        StudentCourseId id = new StudentCourseId(studentId, courseId);

        studentCourseRepository.findById(id).ifPresent(sc -> {
            studentCourseRepository.delete(sc);

            Integer compositeId = studentId * 1000 + courseId;

            auditService.logChange(
                    "StudentCourse",
                    compositeId,
                    "DELETE",
                    "StudentID=" + studentId + ", CourseID=" + courseId
            );
        });
    }


    @Transactional(readOnly = true)
    public List<StudentCourse> getAllByStudentId(Integer studentId) {
        return studentCourseRepository.findByStudentId(studentId);
    }

    @Transactional(readOnly = true)
    public List<StudentCourse> getAll() {
        return studentCourseRepository.findAll();
    }
}