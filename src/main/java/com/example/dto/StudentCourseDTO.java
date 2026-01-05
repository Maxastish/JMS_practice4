package com.example.dto;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "course")
public class StudentCourseDTO {

    @XmlElement
    public Integer courseId;

    @XmlElement
    public String courseTitle;

    @XmlElement
    public Integer mark;

    public StudentCourseDTO() {}

    public StudentCourseDTO(Integer courseId, String courseTitle, Integer mark) {
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.mark = mark;
    }
}
