package com.example.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "course")
@XmlAccessorType(XmlAccessType.FIELD)
public class CourseDTO {

    private Integer id;
    private String title;
    private String professor;

    public CourseDTO() {}

    public CourseDTO(Integer id, String title, String professor) {
        this.id = id;
        this.title = title;
        this.professor = professor;
    }

    public Integer getId() { return id; }
    public String getTitle() { return title; }
    public String getProfessor() { return professor; }
}
