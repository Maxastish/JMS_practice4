package com.example.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "group_name", nullable = false, length = 10)
    private String groupName;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentCourse> studentCourses = new ArrayList<>();

    public Student() {}

    public Student(String name, String groupName, LocalDate birthDate) {
        this.name = name;
        this.groupName = groupName;
        this.birthDate = birthDate;
    }

    // геттеры/сеттеры
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }

    public String getGroupName() { return groupName; }
    public void setGroupName(String groupName) { this.groupName = groupName; }

    public List<StudentCourse> getStudentCourses() { return studentCourses; }
    public void setStudentCourses(List<StudentCourse> studentCourses) { this.studentCourses = studentCourses; }

    public void addStudentCourse(StudentCourse sc) {
        studentCourses.add(sc);
        sc.setStudent(this);
    }

    public void removeStudentCourse(StudentCourse sc) {
        studentCourses.remove(sc);
        sc.setStudent(null);
    }
}