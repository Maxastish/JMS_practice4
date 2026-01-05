package com.example.controller.api;

import com.example.dto.StudentCourseDTO;
import com.example.model.StudentCourse;
import com.example.service.StudentCourseService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
    value = "/api/student-courses",
    produces = { "application/json", "application/xml" }
)
public class StudentCourseRestController {

    private final StudentCourseService studentCourseService;

    public StudentCourseRestController(StudentCourseService studentCourseService) {
        this.studentCourseService = studentCourseService;
    }

    @GetMapping("/student/{studentId}")
    public List<StudentCourseDTO> getByStudent(@PathVariable Integer studentId) {
        List<StudentCourse> studentCourses = studentCourseService.getAllByStudentId(studentId);
        return studentCourses.stream()
                .map(sc -> new StudentCourseDTO(
                        sc.getCourse().getId(),
                        sc.getCourse().getTitle(),
                        sc.getMark()
                ))
                .collect(Collectors.toList());
    }
}