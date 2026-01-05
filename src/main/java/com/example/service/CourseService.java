package com.example.service;

import com.example.model.Course;
import com.example.repository.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final AuditService auditService;

    public CourseService(CourseRepository courseRepository, AuditService auditService) {
        this.courseRepository = courseRepository;
        this.auditService = auditService;
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public Optional<Course> findById(Integer id) {
        return courseRepository.findById(id);
    }

    @Transactional
    public Course save(Course course) {
        boolean isNew = course.getId() == null;

        Course saved = courseRepository.save(course);

        auditService.logChange(
                "Course",
                saved.getId(),
                isNew ? "INSERT" : "UPDATE",
                "Title=" + saved.getTitle()
                        + ", Professor=" + saved.getProfessor()
        );

        return saved;
    }

    @Transactional
    public void deleteById(Integer id) {
        courseRepository.findById(id).ifPresent(course -> {
            courseRepository.delete(course);

            auditService.logChange(
                    "Course",
                    id,
                    "DELETE",
                    "Deleted course: Title=" + course.getTitle()
            );
        });
    }
}