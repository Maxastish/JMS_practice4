package com.example.controller.api;

import com.example.dto.CourseDTO;
import com.example.dto.CourseDTOListWrapper;
import com.example.model.Course;
import com.example.service.CourseService;
import com.example.util.XmlUtil;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
    value = "/api/courses",
    produces = { "application/json", "application/xml" }
)
public class CourseRestController {

    private final CourseService courseService;

    public CourseRestController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CourseDTOListWrapper getAllJson() {
        List<CourseDTO> dtoList = courseService.findAll()
                .stream()
                .map(c -> new CourseDTO(c.getId(), c.getTitle(), c.getProfessor()))
                .toList();
        return new CourseDTOListWrapper(dtoList);
    }
    
    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> getAllXml() {
        List<CourseDTO> dtoList = courseService.findAll()
                .stream()
                .map(c -> new CourseDTO(c.getId(), c.getTitle(), c.getProfessor()))
                .toList();
        CourseDTOListWrapper wrapper = new CourseDTOListWrapper(dtoList);
        String xml = XmlUtil.toXml(wrapper, "/xsl/courses.xsl");
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_XML)
                .body(xml);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CourseDTO> getByIdJson(@PathVariable Integer id) {
        return courseService.findById(id)
                .map(c -> new CourseDTO(c.getId(), c.getTitle(), c.getProfessor()))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> getByIdXml(@PathVariable Integer id) {

        return courseService.findById(id)
                .map(c -> {
                    CourseDTO dto = new CourseDTO(
                            c.getId(),
                            c.getTitle(),
                            c.getProfessor()
                    );

                    String xml = XmlUtil.toXml(dto, "/xsl/course.xsl");

                    return ResponseEntity
                            .ok()
                            .contentType(MediaType.APPLICATION_XML)
                            .body(xml);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Course create(@RequestBody Course course) {
        return courseService.save(course);
    }

    @PutMapping("/{id}")
    public Course update(@PathVariable Integer id, @RequestBody Course course) {
        course.setId(id);
        return courseService.save(course);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        courseService.deleteById(id);
    }
}