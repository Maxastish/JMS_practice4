package com.example.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "student")
@XmlAccessorType(XmlAccessType.FIELD)
public class StudentDTO {

    @XmlElement
    public Integer id;

    @XmlElement
    public String name;

    @XmlElement
    public String birthDate;

    @XmlElement
    public String groupName;

    @XmlElementWrapper(name = "courses") // Обертка для списка курсов
    @XmlElement(name = "course")       // Имя элемента для каждого курса
    public List<StudentCourseDTO> courses;

    public StudentDTO() {}

    public StudentDTO(Integer id, String name, String birthDate, String groupName, List<StudentCourseDTO> courses) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.groupName = groupName;
        this.courses = courses;
    }
}