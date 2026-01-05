package com.example.controller;

import com.example.model.Student;
import com.example.model.Course;
import com.example.model.StudentCourse;
import com.example.service.StudentService;
import com.example.service.CourseService;
import com.example.service.StudentCourseService;
import java.util.Collections;
import java.util.HashMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;
    private final CourseService courseService;
    private final StudentCourseService studentCourseService;

    public StudentController(StudentService studentService,
                             CourseService courseService,
                             StudentCourseService studentCourseService) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.studentCourseService = studentCourseService;
    }

    @GetMapping
    public String listStudents(Model model) {
        List<Student> students = Optional.ofNullable(studentService.findAll())
                                         .orElse(Collections.emptyList());
        List<Course> allCourses = Optional.ofNullable(courseService.findAll())
                                          .orElse(Collections.emptyList());

        Map<Integer, List<Course>> availableCoursesMap = new HashMap<>();

        for (Student s : students) {
            List<StudentCourse> scList = Optional.ofNullable(studentCourseService.getAllByStudentId(s.getId()))
                                                 .orElse(Collections.emptyList());

            s.setStudentCourses(scList);
            List<Course> studentCourses = scList.stream()
                                                .map(StudentCourse::getCourse)
                                                .toList();

            List<Course> available = allCourses.stream()
                                               .filter(c -> !studentCourses.contains(c))
                                               .toList();

            availableCoursesMap.put(s.getId(), available);
        }

        model.addAttribute("students", students);
        model.addAttribute("availableCoursesMap", availableCoursesMap);

        return "students";
    }

    @PostMapping("/add")
    public String addStudent(@RequestParam String name,
                             @RequestParam String groupName,
                             @RequestParam String birthDate) {
        Student s = new Student();
        s.setName(name);
        s.setGroupName(groupName);
        s.setBirthDate(java.time.LocalDate.parse(birthDate));
        studentService.save(s);
        return "redirect:/students";
    }

    @PostMapping("/delete")
    public String deleteStudent(@RequestParam Integer id) {
        studentService.deleteById(id);
        return "redirect:/students";
    }

    @PostMapping("/assignCourse")
    public String assignCourse(@RequestParam Integer studentId,
                               @RequestParam Integer courseId) {
        studentCourseService.assignStudentToCourse(studentId, courseId);
        return "redirect:/students";
    }

    @PostMapping("/removeCourse")
    public String removeCourse(@RequestParam Integer studentId,
                               @RequestParam Integer courseId) {
        studentCourseService.removeStudentFromCourse(studentId, courseId);
        return "redirect:/students";
    }
}
