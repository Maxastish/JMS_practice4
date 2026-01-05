package com.example.controller;

import com.example.model.Student;
import com.example.model.Course;
import com.example.service.StudentService;
import com.example.service.CourseService;
import com.example.service.StudentCourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UniversityController {

    private final StudentService studentService;
    private final CourseService courseService;
    private final StudentCourseService studentCourseService;

    public UniversityController(StudentService studentService,
                                CourseService courseService,
                                StudentCourseService studentCourseService) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.studentCourseService = studentCourseService;
    }

    @GetMapping("/university")
    public String university(Model model) {
        List<Student> students = studentService.findAll();
        List<Course> courses = courseService.findAll();

        for (Student s : students) {
            s.setStudentCourses(studentCourseService.getAllByStudentId(s.getId()));
        }

        model.addAttribute("students", students);
        model.addAttribute("courses", courses);

        return "university";
    }
}