package com.example.controller.api;

import com.example.dto.*;
import com.example.service.StudentCourseService;
import com.example.service.StudentService;
import com.example.model.Student;
import com.example.util.XmlUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/api/students")
public class StudentRestController {

    private final StudentService studentService;
    private final StudentCourseService studentCourseService;

    public StudentRestController(StudentService studentService, StudentCourseService studentCourseService) {
        this.studentService = studentService;
        this.studentCourseService = studentCourseService;
    }

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @GetMapping(produces = "application/json")
    public StudentDTOListWrapper getAllStudentsJson() {
        List<StudentDTO> students = studentService.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return new StudentDTOListWrapper(students);
    }
    
    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> getAllStudentsXml() {
        List<StudentDTO> students = studentService.findAll().stream()
                .map(this::convertToDTO)
                .toList();
        StudentDTOListWrapper wrapper = new StudentDTOListWrapper(students);
        String xml = XmlUtil.toXml(wrapper, "/xsl/students.xsl");
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_XML)
                .body(xml);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<StudentDTO> getStudentByIdJson(@PathVariable Integer id) {
        return studentService.findById(id)
                .map(student -> ResponseEntity.ok(convertToDTO(student)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> getStudentByIdXml(@PathVariable Integer id) {
        return studentService.findById(id)
                .map(student -> {
                    StudentDTO dto = convertToDTO(student);
                    String xml = XmlUtil.toXml(dto, "/xsl/students.xsl");
                    return ResponseEntity
                            .ok()
                            .contentType(MediaType.APPLICATION_XML)
                            .body(xml);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    private StudentDTO convertToDTO(Student student) {
        List<StudentCourseDTO> courses = studentCourseService.getAllByStudentId(student.getId()).stream()
                .map(sc -> new StudentCourseDTO(
                        sc.getCourse().getId(),
                        sc.getCourse().getTitle(),
                        sc.getMark()
                ))
                .collect(Collectors.toList());

        return new StudentDTO(
                student.getId(),
                student.getName(),
                student.getBirthDate().format(DATE_FORMATTER),
                student.getGroupName(),
                courses
        );
    }
}