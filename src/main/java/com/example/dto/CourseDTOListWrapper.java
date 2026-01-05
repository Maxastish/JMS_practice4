package com.example.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "courses")
@XmlAccessorType(XmlAccessType.FIELD)
public class CourseDTOListWrapper {

    @XmlElement(name = "course")
    private List<CourseDTO> courses;

    public CourseDTOListWrapper() {
    }

    public CourseDTOListWrapper(List<CourseDTO> courses) {
        this.courses = courses;
    }

    public List<CourseDTO> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseDTO> courses) {
        this.courses = courses;
    }
}
