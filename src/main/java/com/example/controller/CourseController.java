package com.example.controller;

import com.example.model.Course;
import com.example.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public String listCourses(Model model) {
        model.addAttribute("courses", courseService.findAll());
        return "courses";
    }

    @PostMapping("/add")
    public String addCourse(@RequestParam String title,
                            @RequestParam String professor) {
        courseService.save(new Course(title, professor));
        return "redirect:/courses";
    }

    @PostMapping("/delete")
    public String deleteCourse(@RequestParam Integer id) {
        courseService.deleteById(id);
        return "redirect:/courses";
    }
}
