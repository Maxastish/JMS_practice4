package com.example.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "students")
@XmlAccessorType(XmlAccessType.FIELD)
public class StudentDTOListWrapper {

    @XmlElement(name = "student")
    public List<StudentDTO> students;

    public StudentDTOListWrapper() {}

    public StudentDTOListWrapper(List<StudentDTO> students) {
        this.students = students;
    }
}